package uob.oop;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import java.util.Properties;


public class ArticlesEmbedding extends NewsArticles {
    private int intSize = -1;
    private String processedText = "";

    private INDArray newsEmbedding = Nd4j.create(0);

    public ArticlesEmbedding(String _title, String _content, NewsArticles.DataType _type, String _label) {
        //TODO Task 5.1 - 1 Mark
        super(_title, _content, _type, _label);
    }

    public void setEmbeddingSize(int _size) {
        //TODO Task 5.2 - 0.5 Marks
        this.intSize = _size;
    }

    public int getEmbeddingSize(){
        return intSize;
    }

    @Override
    public String getNewsContent() {
        //TODO Task 5.3 - 10 Marks
        if (!processedText.isEmpty()) {
            return processedText;
        }

        String cleaned = textCleaning(super.getNewsContent());
        String cleanedLemmanized = lemmanized(cleaned);
        processedText = removeStopWords(cleanedLemmanized, Toolkit.STOPWORDS);

        return processedText.trim();
    }

    private static String lemmanized(String cleaned) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument doc = pipeline.processToCoreDocument(cleaned);

        StringBuilder sb = new StringBuilder();
        for (CoreLabel token : doc.tokens()) {
            sb.append(token.lemma()).append(" ");
        }
        return sb.toString().toLowerCase();
    }

    public static String removeStopWords(String _content, String[] _stopWords) {
        StringBuilder sbContent = new StringBuilder();

        for (String word : _content.split(" ")) {
            boolean isStopWord = false;
            for (String stopWord : _stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord)
                sbContent.append(word).append(" ");
        }

        return sbContent.toString().trim();
    }

    public INDArray getEmbedding() throws Exception {
        //TODO Task 5.4 - 20 Marks

        if (this.intSize == -1) {
            throw new InvalidSizeException("Invalid size");
        }
        if (this.processedText.isEmpty()) {
            throw new InvalidTextException("Invalid text");
        }

        if (!newsEmbedding.isEmpty()) {
            return Nd4j.vstack(newsEmbedding.mean(1));
        }

        int vecSize = AdvancedNewsClassifier.listGlove.get(0).getVector().getVectorSize();
        newsEmbedding = Nd4j.zeros(intSize, vecSize);

        String[] words = this.processedText.split(" ");
        int index = 0;
        for (String word : words) {
            if (index > intSize - 1) {
                break;
            }
            for (Glove glove : AdvancedNewsClassifier.listGlove) {
                if (glove.getVocabulary().equals(word)) {
                    newsEmbedding.putRow(index, Nd4j.create(glove.getVector().getAllElements()));
                    index++;
                    break;
                }
            }
        }

        return Nd4j.vstack(newsEmbedding.mean(1));
    }

    /***
     * Clean the given (_content) text by removing all the characters that are not 'a'-'z', '0'-'9' and white space.
     * @param _content Text that need to be cleaned.
     * @return The cleaned text.
     */
    private static String textCleaning(String _content) {
        StringBuilder sbContent = new StringBuilder();

        for (char c : _content.toLowerCase().toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || Character.isWhitespace(c)) {
                sbContent.append(c);
            }
        }

        return sbContent.toString().trim();
    }
}
