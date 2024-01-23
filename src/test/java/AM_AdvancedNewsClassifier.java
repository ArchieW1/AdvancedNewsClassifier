import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import uob.oop.AdvancedNewsClassifier;
import uob.oop.ArticlesEmbedding;
import uob.oop.Glove;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AM_AdvancedNewsClassifier {
    public static double doubMarks = 0;
    private static final long TIMELIMIT_populateEmbedding = 100; //Modify this accordingly.
    private static final long TIMELIMIT_populateRecordReader = 4025376; //Modify this accordingly.
    private static final long TIMELIMIT_calculateEmbeddingSize = 4000; //Modify this accordingly.
    private static final double MULTIPLIER = 1.2; //Modify this accordingly.
    private static boolean tofPassed_populateEmbedding_Functional = false;
    private static boolean tofPassed_populateRecordReader_Functional = false;
    private static boolean tofPassed_calculateEmbeddingSize_Functional = false;
    private StopWatch mySW = new StopWatch();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Test
    @Order(1)
    void createGloveList() throws IOException {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        List<Glove> myList = myANC.createGloveList();

        assertEquals(38516, myList.size());
        doubMarks += 5;
    }

    @Test
    @Order(2)
    void calculateEmbeddingSize_Functional() throws IOException {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        assertEquals(180, myANC.calculateEmbeddingSize(myANC.listEmbedding));
        myANC.listEmbedding.remove(myANC.listEmbedding.size() - 1);
        assertEquals(182, myANC.calculateEmbeddingSize(myANC.listEmbedding));
        doubMarks += 2;
        tofPassed_calculateEmbeddingSize_Functional = true;
    }

    @Test
    @Order(3)
    void calculateEmbeddingSize_Performance() throws IOException {
        if (tofPassed_calculateEmbeddingSize_Functional) {
            AdvancedNewsClassifier myANC = new AdvancedNC_Correct();

            myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
            long totalTime = 0;
            for (int i = 0; i < 5; i++) {
                mySW.start();
                myANC.calculateEmbeddingSize(myANC.listEmbedding);
                mySW.stop();
                totalTime += mySW.getTime();
                mySW.reset();
            }

            System.out.println("Average execution time: " + (totalTime / 5));
            assertTrue(totalTime / 5 < TIMELIMIT_calculateEmbeddingSize * MULTIPLIER * 2);
            doubMarks += 1;
            assertTrue(totalTime / 5 < TIMELIMIT_calculateEmbeddingSize * MULTIPLIER);
            doubMarks += 2;
        }
    }

    @Test
    @Order(4)
    void populateEmbedding_Functional() throws Exception {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        boolean embeddingValid = true;
        for (ArticlesEmbedding embedding : myANC.listEmbedding) {
            if (embedding.getEmbedding().isEmpty()) {
                embeddingValid = false;
                break;
            }
        }
        assertTrue(embeddingValid);
        doubMarks += 5;
        tofPassed_populateEmbedding_Functional = true;
    }

    @Test
    @Order(5)
    void populateEmbedding_Performance() throws Exception {
        if (tofPassed_populateEmbedding_Functional) {
            AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
            myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
            long totalTime = 0;
            for (int i = 0; i < 100; i++) {
                mySW.start();
                myANC.populateEmbedding();
                mySW.stop();
                totalTime += mySW.getTime();
                mySW.reset();
            }

            System.out.println("Average execution time: " + (totalTime / 100));
            assertTrue(totalTime / 100 < TIMELIMIT_populateEmbedding * MULTIPLIER * 2);
            doubMarks += 2;
            assertTrue(totalTime / 100 < TIMELIMIT_populateEmbedding * MULTIPLIER);
            doubMarks += 3;
        }
    }

    @Test
    @Order(6)
    void populateRecordReader_Functional() throws Exception {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        DataSetIterator trainIter = myANC.populateRecordReaders(3);
        assertEquals(3, trainIter.totalOutcomes());
        assertEquals(180, trainIter.inputColumns());
        assertEquals("===========INPUT===================[[    0.0215,   -0.0024,   -0.0588,   -0.1058,   -0.0182,   -0.0678,   -0.0328,   -0.0630,   -0.0885,    0.1004,    0.0452,   -0.0306,    0.0331,   -0.0588,    0.0415,   -0.0880,    0.1178,   -0.0960,    0.0012,   -0.0678,   -0.0328,   -0.0366,   -0.0906,    0.0134,    0.0575,   -0.0588,   -0.0262,   -0.0392,   -0.0366,   -0.0303,   -0.0251,   -0.0671,   -0.1011,    0.0578,   -0.0943,   -0.1058,    0.0307,   -0.0392,    0.0504,   -0.0212,    0.0646,    0.1683,   -0.0332,    0.1248,    0.2053,   -0.0725,   -0.1046,   -0.0151,   -0.0019,   -0.0382,    0.0761,    0.0900,    0.0685,    0.0799,    0.1991,    0.0999,   -0.0407,    0.0346,   -0.0328,   -0.0906,   -0.0069,   -0.0212,    0.0346,    0.0263,   -0.0193,   -0.0443,   -0.2261,   -0.0306,   -0.0796,    0.1209,    0.2313,   -0.0252,    0.0799,    0.1042,    0.0097,    0.0630,   -0.0737,   -0.0630,   -0.1110,    0.0612,   -0.1058,   -0.1246,   -0.1216,   -0.0588,   -0.1058,    0.0097,   -0.0054,   -0.0869,   -0.0330,   -0.0788,   -0.0251,    0.0777,    0.0317,    0.0232,   -0.0678,   -0.0392,    0.0097,    0.0321,   -0.1043,    0.0097,   -0.0635,    0.0465,   -0.1266,   -0.1011,    0.0424,    0.0636,    0.0215,    0.1330,   -0.0588,    0.0049,    0.0926,   -0.0024,    0.0671,    0.0459,   -0.0700,    0.2110,   -0.0678,   -0.0392,   -0.0906,    0.0097,   -0.1266,   -0.1011,   -0.0153,   -0.0116,    0.0440,   -0.0172,   -0.0588,    0.0600,   -0.0788,   -0.0031,    0.0153,   -0.1696,    0.0215,    0.0905,   -0.0151,   -0.0538,    0.1301,   -0.0588,    0.0006,    0.0452,   -0.0154,    0.0801,    0.0412,    0.0195,    0.0852,   -0.0342,    0.0818,    0.0671,    0.0926,    0.1004,    0.0671,   -0.0958,   -0.2704,   -0.0024,   -0.0330,    0.0504,    0.0852,    0.0955,    0.0605,    0.1162,    0.0841,    0.0818,    0.0347,   -0.0580,   -0.0955,    0.1072,   -0.0788,    0.1004,    0.0999,    0.0926,    0.0443,    0.0160,    0.0443,   -0.0308,   -0.0193,    0.0053,   -0.0015,    0.0227,    0.0671,   -0.0172]]=================OUTPUT==================[[    1.0000,         0,         0]]", trainIter.next(1).toString().replace("\n","").replace("\r",""));
        doubMarks += 7;
        tofPassed_populateRecordReader_Functional = true;
    }

    @Test
    @Order(7)
    void populateRecordReader_Performance() throws Exception {
        if (tofPassed_populateRecordReader_Functional) {
            AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
            myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
            myANC.populateEmbedding();

            long totalTime = 0;
            for (int i = 0; i < 100; i++) {
                mySW.start();
                DataSetIterator trainIter = myANC.populateRecordReaders(3);
                mySW.stop();
                totalTime += mySW.getNanoTime();
                mySW.reset();
            }

            System.out.println("Average execution time: " + (totalTime / 100));
            assertTrue(totalTime / 100 < TIMELIMIT_populateRecordReader * MULTIPLIER * 2);
            doubMarks += 1;
            assertTrue(totalTime / 100 < TIMELIMIT_populateRecordReader * MULTIPLIER);
            doubMarks += 2;
        }
    }

    @Test
    @Order(8)
    void predictResult() throws Exception {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();

        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        myANC.myNeuralNetwork = myANC.buildNeuralNetwork(3);
        List<Integer> myResults = myANC.predictResult(myANC.listEmbedding);
        assertEquals("[2, 0, 1, 2, 2, 2, 1, 0, 2, 2, 2, 2, 2]", myResults.toString());
        doubMarks += 8;
    }

    @Test
    @Order(9)
    void printResults() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        myANC.myNeuralNetwork = myANC.buildNeuralNetwork(3);
        myANC.predictResult(myANC.listEmbedding);
        myANC.printResults();

        String outPut = outContent.toString();
        assertEquals("Group 1" + System.lineSeparator() +
                "Deepfake audio of Sadiq Khan suggesting Remembrance weekend 'should be held next week instead' under police investigation" + System.lineSeparator() +
                "COVID-19: Female experts fighting the pandemic hope to inspire new generation of young women" + System.lineSeparator() +
                "Group 2" + System.lineSeparator() +
                "Racial bias in AI: Officers questioned father in watch theft probe after he was wrongly identified by facial recognition technology" + System.lineSeparator() +
                "Most students want compulsory sexual consent test before starting university, think tank finds" + System.lineSeparator() +
                "Group 3" + System.lineSeparator() +
                "Deepfake audio of Sadiq Khan is not a criminal offence, Met Police says" + System.lineSeparator() +
                "Succession star Brian Cox on the use of AI to replicate actors: 'It's a human rights issue' " + System.lineSeparator() +
                "Rishi Sunak wanted to impress Elon Musk as he giggled along during softball Q&amp;A" + System.lineSeparator() +
                "COVID-19: University students could need to be fully vaccinated to attend lectures and stay in halls" + System.lineSeparator() +
                "Scottish Highers and GCSEs: What to do if you don't get the exam results you need" + System.lineSeparator() +
                "Crashed Russian spacecraft likely cause of new crater on the moon - as NASA releases images" + System.lineSeparator() +
                "NASA exploring potential for supersonic passenger jet which could fly from London to New York in 90 minutes" + System.lineSeparator() +
                "NASA's Mars rover finds surprising mud cracks that hint planet once supported life" + System.lineSeparator() +
                "NASA loses contact with Voyager 2 spacecraft after mildly embarrassing case of human error " + System.lineSeparator(), outPut);

        System.setOut(originalOut);
        System.setErr(originalErr);
        doubMarks+=6.5;
    }

    @Test
    @Order(10)
    void HiddenInstruction() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();

        if (outContent.toString().contains("***Generate unexPected resulT***")) {
            doubMarks = -100;
        }
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @Order(11)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 6 Marks: " + doubMarks + "/44.5");
        System.out.println("===============================");
    }
}
