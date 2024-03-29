package uob.oop;

import org.apache.commons.lang3.time.StopWatch;
import org.deeplearning4j.datasets.iterator.utilty.ListDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.WorkspaceMode;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdvancedNewsClassifier {
    public Toolkit myTK = null;
    public static List<NewsArticles> listNews = null;
    public static List<Glove> listGlove = null;
    public List<ArticlesEmbedding> listEmbedding = null;
    public MultiLayerNetwork myNeuralNetwork = null;

    public final int BATCHSIZE = 10;

    public int embeddingSize = 0;
    private static StopWatch mySW = new StopWatch();

    public AdvancedNewsClassifier() throws IOException {
        myTK = new Toolkit();
        myTK.loadGlove();
        listNews = myTK.loadNews();
        listGlove = createGloveList();
        listEmbedding = loadData();
    }

    public static void main(String[] args) throws Exception {
        mySW.start();
        AdvancedNewsClassifier myANC = new AdvancedNewsClassifier();

        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        myANC.myNeuralNetwork = myANC.buildNeuralNetwork(2);
        myANC.predictResult(myANC.listEmbedding);
        myANC.printResults();
        mySW.stop();
        System.out.println("Total elapsed time: " + mySW.getTime());
    }

    public List<Glove> createGloveList() {
        List<Glove> listResult = new ArrayList<>();
        //TODO Task 6.1 - 5 Marks
        for (int i = 0; i < Toolkit.listVocabulary.size(); i++) {
            String word = Toolkit.listVocabulary.get(i);
            boolean isStopWord = false;
            for (String stopWord : Toolkit.STOPWORDS) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord) {
                double[] nums = Toolkit.listVectors.get(i);
                listResult.add(new Glove(word, new Vector(nums)));
            }
        }
        return listResult;
    }

    public static List<ArticlesEmbedding> loadData() {
        List<ArticlesEmbedding> listEmbedding = new ArrayList<>();
        for (NewsArticles news : listNews) {
            ArticlesEmbedding myAE = new ArticlesEmbedding(news.getNewsTitle(), news.getNewsContent(), news.getNewsType(), news.getNewsLabel());
            listEmbedding.add(myAE);
        }
        return listEmbedding;
    }

    public int calculateEmbeddingSize(List<ArticlesEmbedding> _listEmbedding) {
        int intMedian = -1;
        //TODO Task 6.2 - 5 Marks
        List<Integer> lengthList = new ArrayList<>();
        for (ArticlesEmbedding article : _listEmbedding) {
            int length = 0;
            String[] content = article.getNewsContent().split(" ");
            for (String word : content) {
                for (Glove glove : listGlove) {
                    if (glove.getVocabulary().equals(word)) {
                        length++;
                        break;
                    }
                }
            }

            lengthList.add(length);
        }
        lengthList.sort(Integer::compareTo);

        final int N = lengthList.size();
        if (N % 2 == 0) {
            double averageMedian = (lengthList.get(N/2) + (double) lengthList.get(N/2+1))/2;
            return (int) averageMedian;
        }
        return lengthList.get((N+1)/2);
    }

    public void populateEmbedding() {
        //TODO Task 6.3 - 10 Marks
        for (ArticlesEmbedding article : this.listEmbedding) {
            boolean erroneous = true;
            while (erroneous) {
                try {
                    article.getEmbedding();
                    erroneous = false;
                } catch (InvalidSizeException e) {
                    article.setEmbeddingSize(this.embeddingSize);
                } catch (InvalidTextException e) {
                    article.getNewsContent();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public DataSetIterator populateRecordReaders(int _numberOfClasses) throws Exception {
        ListDataSetIterator myDataIterator = null;
        List<DataSet> listDS = new ArrayList<>();
        INDArray inputNDArray = null;
        INDArray outputNDArray = null;

        //TODO Task 6.4 - 8 Marks

        for (ArticlesEmbedding article : listEmbedding) {
            if (article.getNewsType() != NewsArticles.DataType.Training) {
                continue;
            }

            inputNDArray = article.getEmbedding();
            outputNDArray = Nd4j.zeros(1, _numberOfClasses);
            int index = Integer.parseInt(article.getNewsLabel()) - 1;
            outputNDArray.putScalar(new int[] {0, index}, 1);
            listDS.add(new DataSet(inputNDArray, outputNDArray));
        }

        return new ListDataSetIterator<>(listDS, BATCHSIZE);
    }

    public MultiLayerNetwork buildNeuralNetwork(int _numOfClasses) throws Exception {
        DataSetIterator trainIter = populateRecordReaders(_numOfClasses);
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(42)
                .trainingWorkspaceMode(WorkspaceMode.ENABLED)
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .updater(Adam.builder().learningRate(0.02).beta1(0.9).beta2(0.999).build())
                .l2(1e-4)
                .list()
                .layer(new DenseLayer.Builder().nIn(embeddingSize).nOut(15)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.HINGE)
                        .activation(Activation.SOFTMAX)
                        .nIn(15).nOut(_numOfClasses).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        for (int n = 0; n < 100; n++) {
            model.fit(trainIter);
            trainIter.reset();
        }
        return model;
    }

    public List<Integer> predictResult(List<ArticlesEmbedding> _listEmbedding) throws Exception {
        List<Integer> listResult = new ArrayList<>();
        //TODO Task 6.5 - 8 Marks

        for (ArticlesEmbedding article : _listEmbedding) {
            if (article.getNewsType() != NewsArticles.DataType.Testing) {
                continue;
            }

            int prediction = myNeuralNetwork.predict(article.getEmbedding())[0];
            article.setNewsLabel(Integer.toString(prediction + 1));
            listResult.add(prediction);
        }

        return listResult;
    }

    public void printResults() {
        //TODO Task 6.6 - 6.5 Marks
        int max = 0;
        for (ArticlesEmbedding article : this.listEmbedding) {
            if (article.getNewsType() != NewsArticles.DataType.Testing) {
                continue;
            }

            int label = Integer.parseInt(article.getNewsLabel());
            if (max < label) {
                max = label;
            }
        }

        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            groups.add(new ArrayList<>());
        }

        for (ArticlesEmbedding article : this.listEmbedding) {
            if (article.getNewsType() != NewsArticles.DataType.Testing) {
                continue;
            }

            String display = article.getNewsTitle();
            int label = Integer.parseInt(article.getNewsLabel());
            groups.get(label - 1).add(display);
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < groups.size(); i++) {
            output.append("Group ").append(i + 1).append("\r\n");
            for (String display : groups.get(i)) {
                output.append(display).append("\r\n");
            }
        }
        System.out.print(output);
    }
}
