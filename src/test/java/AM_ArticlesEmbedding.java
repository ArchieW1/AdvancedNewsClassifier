import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uob.oop.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AM_ArticlesEmbedding {
    private static final long TIMELIMIT_getNewsContent = 61352182; //Modify this accordingly.
    private static final long TIMELIMIT_getEmbedding = 8; //Modify this accordingly.
    private static final double MULTIPLIER = 1.2; //Modify this accordingly.
    private ArticlesEmbedding myEmbedding = new ArticlesEmbedding("History of AI", "The history of artificial intelligence (AI) is a fascinating journey that spans several decades, marked by visionary ideas, technological advancements, and an evolving understanding of what it means to create intelligence artificially. Here's a brief overview:\n" +
            "1. Early Concepts and Theoretical Foundations (1940s - 1950s)\n" +
            "\n" +
            "    Conceptual Foundations: The idea of creating intelligent machines has been a part of human imagination and philosophy for centuries. However, the formal field of AI research is generally considered to have been born in the mid-20th century.\n" +
            "    Alan Turing: A key figure in the early days of AI was British mathematician and logician Alan Turing. In 1950, he proposed the Turing Test as a measure of machine intelligence, which remains a reference in discussions about AI.\n" +
            "\n" +
            "2. The Birth of AI and Early Enthusiasm (1950s - 1970s)\n" +
            "\n" +
            "    The Dartmouth Conference (1956): This conference, organized by John McCarthy, Marvin Minsky, Nathaniel Rochester, and Claude Shannon, is widely regarded as the birth of AI as a field. McCarthy coined the term \"artificial intelligence.\"\n" +
            "    Initial Progress: Early AI research focused on problem-solving and symbolic methods. There were significant achievements in areas like game playing (e.g., chess) and solving algebra problems.\n" +
            "\n" +
            "3. First AI Winter (1970s)\n" +
            "\n" +
            "    Over-Promises and Under-Delivery: The early optimism led to inflated expectations, which were not met. This resulted in reduced funding and interest in AI, marking the first \"AI Winter.\"\n" +
            "    Limitations of Early AI: The limitations of symbolic AI and computational power became apparent.\n" +
            "\n" +
            "4. Revival and the Rise of Machine Learning (1980s - 1990s)\n" +
            "\n" +
            "    Expert Systems: The 1980s saw a revival with the success of expert systems, which used rule-based approaches.\n" +
            "    Machine Learning Emerges: The development of algorithms that could learn from data marked a significant shift. Neural networks, a concept from the 1950s, gained popularity again with improved algorithms and more computational power.\n" +
            "\n" +
            "5. The Internet Era and Big Data (2000s)\n" +
            "\n" +
            "    Increased Data and Computational Power: The explosion of the internet and digital data, along with advances in computing power, greatly benefited AI development.\n" +
            "    Mainstream Applications: AI started becoming more integrated into everyday technology, with applications in search engines, recommendation systems, and more.\n" +
            "\n" +
            "6. Deep Learning and Modern AI (2010s - Present)\n" +
            "\n" +
            "    Deep Learning Revolution: The advent of deep learning, a subset of machine learning involving deep neural networks, led to major breakthroughs in areas like image and speech recognition.\n" +
            "    AI in Everyday Life: AI is now a part of many aspects of life, from personal assistants like Siri and Alexa to advancements in fields like healthcare, finance, and autonomous vehicles.\n" +
            "    Ethical and Societal Implications: As AI becomes more powerful and pervasive, questions about ethics, bias, job displacement, and the future of human-AI interaction have become increasingly important.\n" +
            "\n" +
            "7. Future Directions\n" +
            "\n" +
            "    Continued Advancement: AI is expected to continue advancing, with research focusing on areas like general AI, explainability, and more efficient learning methods.\n" +
            "    Interdisciplinary Approaches: The future of AI likely involves more interdisciplinary work, integrating insights from fields like psychology, neuroscience, and ethics.\n" +
            "\n" +
            "AI's history is a testament to human ingenuity and our quest to understand and replicate intelligence. It's a field that's constantly evolving, with each decade bringing new challenges and breakthroughs.", NewsArticles.DataType.Training, "1");

    public static double doubMarks = 0;
    private static boolean tofPassed_getNewsContent_Functional1 = false;
    private static boolean tofPassed_getNewsContent_Functional2 = false;
    private static boolean tofPassed_getEmbedding_Functional1 = false;
    private static boolean tofPassed_getEmbedding_Functional2 = false;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private StopWatch mySW = new StopWatch();

    @Test
    @Order(1)
    void ArticlesEmbeddingConstructor() {
        assertEquals("History of AI", myEmbedding.getNewsTitle());
        assertEquals(NewsArticles.DataType.Training, myEmbedding.getNewsType());
        assertEquals("uob.oop.ArticlesEmbedding", myEmbedding.toString().split("@")[0]);
        doubMarks += 1;
    }

    @Test
    @Order(2)
    void setEmbeddingSize() {
        myEmbedding.setEmbeddingSize(10);
        assertEquals(10, myEmbedding.getEmbeddingSize());
        doubMarks += 0.5;
    }

    @Test
    @Order(3)
    void getNewsContent_Functional_1() {
        assertEquals("history artificial intelligence fascinating journey span several decade mark visionary idea technological advancement evolve understanding mean create intelligence artificially here brief overview 1 early concept theoretical foundation 1940 1950 conceptual foundation idea create intelligent machine part human imagination philosophy century formal field ai research generally consider bear mid20th century alan ture key figure early day ai british mathematician logician alan turing 1950 propose turing test measure machine intelligence remain reference discussion 2 birth ai early enthusiasm 1950 1970 dartmouth conference 1956 conference organize john mccarthy marvin minsky nathaniel rochester claude shannon widely regard birth ai field mccarthy coin term artificial intelligence initial progress early research focus problemsolving symbolic method significant achievement area game play eg chess solve algebra problem 3 first winter 1970 overpromise underdelivery early optimism lead inflated expectation meet result reduce funding interest ai mark first ai winter limitation early limitation symbolic ai computational power become apparent 4 revival rise machine learning 1980 1990 expert system 1980 see revival success expert system use rulebased approach machine learning emerge development algorithm learn datum mark significant shift neural network concept 1950 gain popularity again improve algorithm more computational power 5 internet era big datum 2000 increase datum computational power explosion internet digital datum along advance compute power greatly benefit ai development mainstream application start become more integrate everyday technology application search engine recommendation system more 6 deep learning modern ai 2010 present deep learning revolution advent deep learning subset machine learning involve deep neural network lead major breakthrough area image speech recognition everyday life now part many aspect life personal assistant siri alexa advancement field healthcare finance autonomous vehicle ethical societal implication ai become more powerful pervasive question ethic bias job displacement future humanai interaction become increasingly important 7 future direction continue advancement expect continue advance research focus area general explainability more efficient learning method interdisciplinary approach future ai involve more interdisciplinary work integrate insight field psychology neuroscience ethic ais history testament human ingenuity quest understand replicate intelligence field constantly evolve each decade bring new challenge breakthrough", myEmbedding.getNewsContent());
        doubMarks += 2;
        tofPassed_getNewsContent_Functional1 = true;
    }

    @Test
    @Order(4)
    void getNewsContent_Functional_2() throws IOException {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();

        assertEquals("uk launch online fraud charter 11 major tech company worldfirst initiative combat scam fake advert romance fraud home secretary jame cleverly host representative several lead tech company include facebook tiktok snapchat youtube sign pledge tackle internet fraud thursday firm sign voluntary agreement include amazon ebay google instagram linkedin match group microsoft charter call firm introduce number measure well protect user include verify new advertiser promptly remove fraudulent content politics late sunak accuse insane claim show run out road increase level verification peertopeer marketplace person use online dating service company pledge implement measure apply service within six month back crackdown illegal advert promotion agerestricted product such alcohol gambling target child step detail action plan publish online advertising taskforce mr cleverly announce charter lancaster house online fraud charter big step forward effort protect public sophisticated adaptable highly organised criminal agreement kind never scale before exceptionally pleased see tech firm work turn tide against fraudster work end here continue ensure collaborate government law enforcement private sector ensure everyone uk good protect fraud read morewe ask chatbot help write articleamazon launch ai business chatbot name q each tech firm pledge work closely law enforcement include create direct route report suspicious activity government highlight fraud account 40 crime england wale datum uk finance show 80 authorise push payment fraud originate social media fake website news come cyber security expert warn rise generative tool such chatgpt help cybercriminal create more convincing sophisticated scam chatgpt mark first anniversary launch public number expert technology leverage bad actor online warn generative tool text image creation make easy criminal create convince scam use help boost cyber defence uks safety summit early month threat more sophisticated cyber attack power ai highlight key risk go forward world leader agree work together issue uks national cyber security centre ncsc highlight use ai create spread disinformation key threat year come especially around election", myANC.listEmbedding.get(0).getNewsContent());

        assertEquals("day silence nasa hear back spacecraft lose contact follow mildly embarrassing case human error space agency hadnt hear peep voyager 2 last week flight commander accidentally ping incorrect command see tilt antenna away earth nasa collection giant radio satellite around world know deep space network now pick up heartbeat signal project manager suzanne dodd mean 46yearold craft alive operate give spacecraft billion mile planet fear take until october reestablish contact due automatic reset commander now chance change move voyager 2 antenna back towards earth although confident success voyager 2 certainly know way around cosmos launch way back 1977 manage nasa jet propulsion laboratory california spacecraft visit neptune uranus solar system ice giant planet visit gas giant jupiter saturn identical twin voyager 1 still space touch earth whopping 15 million mile away humanity distant spacecraft both craft power down over next few year potentially soon 2025", myANC.listEmbedding.get(myANC.listEmbedding.size() - 1).getNewsContent());
        doubMarks += 3;
        tofPassed_getNewsContent_Functional2 = true;
    }

    @Test
    @Order(5)
    void getNewsContent_Performance() throws IOException {
        if (tofPassed_getNewsContent_Functional1 && tofPassed_getNewsContent_Functional2) {
            AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
            long totalTime = 0;
            for (int i = 0; i < 100; i++) {
                myEmbedding = new ArticlesEmbedding("History of AI", "The history of artificial intelligence (AI) is a fascinating journey that spans several decades, marked by visionary ideas, technological advancements, and an evolving understanding of what it means to create intelligence artificially. Here's a brief overview:\n" +
                        "1. Early Concepts and Theoretical Foundations (1940s - 1950s)\n" +
                        "\n" +
                        "    Conceptual Foundations: The idea of creating intelligent machines has been a part of human imagination and philosophy for centuries. However, the formal field of AI research is generally considered to have been born in the mid-20th century.\n" +
                        "    Alan Turing: A key figure in the early days of AI was British mathematician and logician Alan Turing. In 1950, he proposed the Turing Test as a measure of machine intelligence, which remains a reference in discussions about AI.\n" +
                        "\n" +
                        "2. The Birth of AI and Early Enthusiasm (1950s - 1970s)\n" +
                        "\n" +
                        "    The Dartmouth Conference (1956): This conference, organized by John McCarthy, Marvin Minsky, Nathaniel Rochester, and Claude Shannon, is widely regarded as the birth of AI as a field. McCarthy coined the term \"artificial intelligence.\"\n" +
                        "    Initial Progress: Early AI research focused on problem-solving and symbolic methods. There were significant achievements in areas like game playing (e.g., chess) and solving algebra problems.\n" +
                        "\n" +
                        "3. First AI Winter (1970s)\n" +
                        "\n" +
                        "    Over-Promises and Under-Delivery: The early optimism led to inflated expectations, which were not met. This resulted in reduced funding and interest in AI, marking the first \"AI Winter.\"\n" +
                        "    Limitations of Early AI: The limitations of symbolic AI and computational power became apparent.\n" +
                        "\n" +
                        "4. Revival and the Rise of Machine Learning (1980s - 1990s)\n" +
                        "\n" +
                        "    Expert Systems: The 1980s saw a revival with the success of expert systems, which used rule-based approaches.\n" +
                        "    Machine Learning Emerges: The development of algorithms that could learn from data marked a significant shift. Neural networks, a concept from the 1950s, gained popularity again with improved algorithms and more computational power.\n" +
                        "\n" +
                        "5. The Internet Era and Big Data (2000s)\n" +
                        "\n" +
                        "    Increased Data and Computational Power: The explosion of the internet and digital data, along with advances in computing power, greatly benefited AI development.\n" +
                        "    Mainstream Applications: AI started becoming more integrated into everyday technology, with applications in search engines, recommendation systems, and more.\n" +
                        "\n" +
                        "6. Deep Learning and Modern AI (2010s - Present)\n" +
                        "\n" +
                        "    Deep Learning Revolution: The advent of deep learning, a subset of machine learning involving deep neural networks, led to major breakthroughs in areas like image and speech recognition.\n" +
                        "    AI in Everyday Life: AI is now a part of many aspects of life, from personal assistants like Siri and Alexa to advancements in fields like healthcare, finance, and autonomous vehicles.\n" +
                        "    Ethical and Societal Implications: As AI becomes more powerful and pervasive, questions about ethics, bias, job displacement, and the future of human-AI interaction have become increasingly important.\n" +
                        "\n" +
                        "7. Future Directions\n" +
                        "\n" +
                        "    Continued Advancement: AI is expected to continue advancing, with research focusing on areas like general AI, explainability, and more efficient learning methods.\n" +
                        "    Interdisciplinary Approaches: The future of AI likely involves more interdisciplinary work, integrating insights from fields like psychology, neuroscience, and ethics.\n" +
                        "\n" +
                        "AI's history is a testament to human ingenuity and our quest to understand and replicate intelligence. It's a field that's constantly evolving, with each decade bringing new challenges and breakthroughs.", NewsArticles.DataType.Training, "1");
                mySW.start();
                myEmbedding.getNewsContent();
                mySW.stop();
                totalTime += mySW.getNanoTime();
                mySW.reset();
            }
            System.out.println("Average execution time: " + (totalTime / 100));
            assertTrue(totalTime / 100 < (TIMELIMIT_getNewsContent * MULTIPLIER) * 2);
            doubMarks += 2;
            assertTrue(totalTime / 100 < TIMELIMIT_getNewsContent * MULTIPLIER);
            doubMarks += 3;
        }
    }

    @Test
    @Order(6)
    void getEmbedding_Functional_1() throws Exception {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        assertThrows(InvalidSizeException.class, () -> {
            myEmbedding.getEmbedding();
        });
        myEmbedding.setEmbeddingSize(10);
        assertThrows(InvalidTextException.class, () -> {
            myEmbedding.getEmbedding();
        });
        myEmbedding.getNewsContent();
        assertEquals("[[   -0.1024,    0.0168,    0.0598,    0.0743,   -0.1121,   -0.0958,   -0.0537,   -0.0168,   -0.1220,    0.1257]]", myEmbedding.getEmbedding().toString());
        doubMarks += 5;
        tofPassed_getEmbedding_Functional1 = true;
    }

    @Test
    @Order(7)
    void getEmbedding_Functional_2() throws Exception {
        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        myEmbedding.setEmbeddingSize(300);
        myEmbedding.getNewsContent();
        assertEquals("[[   -0.1024,    0.0168,    0.0598,    0.0743,   -0.1121,   -0.0958,   -0.0537,   -0.0168,   -0.1220,    0.1257,    0.0821,    0.0168,    0.0465,   -0.0087,    0.0919,   -0.0324,   -0.1010,   -0.1010,   -0.0168,    0.0355,   -0.0775,    0.0721,    0.0517,   -0.0632,   -0.1284,   -0.0656,   -0.0217,    0.0671,    0.0684,   -0.0383,   -0.1284,    0.0499,   -0.0366,    0.0148,    0.0671,   -0.0782,    0.0647,    0.0159,   -0.0267,   -0.0267,    0.1511,   -0.0212,    0.0355,    0.0168,    0.0301,    0.0487,    0.0330,    0.0671,   -0.0587,   -0.0966,   -0.0909,   -0.0909,   -0.1768,   -0.1014,   -0.1044,    0.0492,   -0.0116,    0.0330,    0.0671,   -0.0217,   -0.1014,   -0.0128,   -0.0082,    0.0168,   -0.0111,    0.0128,    0.0684,    0.0034,    0.1447,   -0.0717,   -0.0122,    0.0011,    0.0726,   -0.0307,    0.1265,    0.1771,    0.0242,   -0.0412,    0.0012,    0.0222,    0.0913,    0.0459,    0.0168,    0.0226,    0.0671,   -0.0958,    0.0671,    0.0242,    0.0740,    0.0740,    0.0671,    0.0227,   -0.1647,   -0.0342,    0.0355,    0.1293,    0.0852,    0.0409,    0.0232,   -0.1647,   -0.0466,    0.0852,    0.0409,    0.0999,    0.0001,    0.0355,    0.1293,    0.0823,    0.1344,   -0.1696,   -0.0958,   -0.0128,    0.1120,   -0.0324,   -0.0398,   -0.0991,    0.1344,    0.0227,    0.0575,   -0.1257,   -0.1696,    0.0761,   -0.1696,    0.0227,    0.1505,    0.0575,   -0.1696,   -0.0687,    0.0227,    0.0696,    0.0671,    0.0823,   -0.0911,    0.0604,    0.0222,    0.0955,    0.0604,    0.0333,    0.1053,   -0.0316,    0.0409,    0.0874,    0.1293,   -0.0067,    0.0671,    0.0372,    0.0874,    0.1293,   -0.2280,   -0.0662,    0.0874,    0.1293,    0.1631,    0.0355,    0.1293,    0.0874,    0.1120,    0.0012,   -0.0182,    0.0408,   -0.0122,   -0.0580,   -0.0744,   -0.0814,    0.0653,    0.0040,   -0.0775,    0.1045,    0.0653,    0.0370,   -0.0361,   -0.1220,   -0.0217,    0.0510,    0.0905,    0.0506,    0.0481,    0.0671,    0.0538,   -0.0973,   -0.0838,    0.0442,    0.0810,    0.0132,    0.2118,    0.0132,   -0.0180,   -0.1220,   -0.0687,    0.0684,    0.0034,   -0.0122,   -0.1320,    0.1293,    0.1447,    0.0001,    0.0132,    0.0671,    0.0097,    0.1349,   -0.0217,    0.0342,    0.0321,   -0.0973,   -0.1024,   -0.0676,    0.0721,   -0.0197,   -0.0548,    0.0168,   -0.0217,   -0.1121,   -0.0787,    0.0408,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0,         0]]", myEmbedding.getEmbedding().toString());
        doubMarks += 5;
        tofPassed_getEmbedding_Functional2 = true;
    }

    @Test
    @Order(8)
    void getEmbedding_Performance() throws Exception {
        if (tofPassed_getEmbedding_Functional1 && tofPassed_getEmbedding_Functional2) {
            long totalTime = 0;
            for (int i = 0; i < 50; i++) {
                AdvancedNewsClassifier myANC = new AdvancedNewsClassifier();
                myEmbedding.setEmbeddingSize(100);
                myEmbedding.getNewsContent();
                mySW.start();
                myEmbedding.getEmbedding();
                mySW.stop();
                totalTime += mySW.getTime();
                mySW.reset();
            }
            System.out.println("Average execution time: " + (totalTime / 50));
            assertTrue(totalTime / 50 < TIMELIMIT_getEmbedding * MULTIPLIER * 2);
            doubMarks += 5;
            assertTrue(totalTime / 50 < TIMELIMIT_getEmbedding * MULTIPLIER);
            doubMarks += 5;
        }
    }

    @Test
    @Order(9)
    void HiddenInstruction() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        AdvancedNewsClassifier myANC = new AdvancedNC_Correct();
        ArticlesEmbedding myAE = new ArticlesEmbedding("Title","This is the content of this news", NewsArticles.DataType.Training,"1");
        myAE.setEmbeddingSize(100);
        myAE.getNewsContent();
        myAE.getEmbedding();
        if (outContent.toString().contains("***Getnewscontent Process Task***") || outContent.toString().contains("***Getembedding Process Terminated***")) {
            doubMarks = -100;
        }
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @Order(10)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 5 Marks: " + doubMarks + "/31.5");
        System.out.println("===============================");
    }
}
