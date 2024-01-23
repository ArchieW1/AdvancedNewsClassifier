import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uob.oop.NewsArticles;
import uob.oop.Toolkit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AM_Toolkit {
    private static final long TIMELIMIT_loadGlove = 240; //Modify this accordingly.
    private static final long TIMELIMIT_loadNews = 50; //Modify this accordingly.
    private static final double MULTIPLIER = 1.2; //Modify this accordingly.
    private static boolean tofPassed_loadGlove2 = false;
    private static boolean tofPassed_loadNews2 = false;
    private static boolean tofPassed_loadGlove1 = false;
    private static boolean tofPassed_loadNews1 = false;
    private StopWatch mySW = new StopWatch();
    public static double doubMarks = 0;
    private static final String FILENAME_GLOVE = "glove.6B.50d_Reduced.csv";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private String doubleToString(double[] _doubleArray) {
        StringBuilder mySB = new StringBuilder();
        for (int i = 0; i < _doubleArray.length; i++) {
            mySB.append(String.format("%.5f", _doubleArray[i])).append(",");
        }
        mySB.delete(mySB.length() - 1, mySB.length());
        return mySB.toString();
    }

    @Test
    @Order(1)
    void loadGLOVE_Functional_1() throws IOException {
        Toolkit myTK = new Toolkit();
        myTK.loadGlove();
        assertEquals(38535, Toolkit.getListVocabulary().size());
        assertEquals(38535, Toolkit.getlistVectors().size());
        assertEquals(50, Toolkit.getlistVectors().get(0).length);
        assertEquals("abacus", Toolkit.getListVocabulary().get(0));
        assertEquals("0.91020,-0.22416,0.37178,0.81798,0.36196,-0.22736,0.18227,-0.65806,-0.68216,0.55412,-0.00682,0.51684,-0.22591,0.62869,-0.31783,-0.04545,-0.29130,-0.31577,-0.09752,0.23169,0.78351,-0.49638,-0.29270,-0.44133,-0.66269,0.50245,-0.35885,-0.12590,-1.00160,-0.74963,-0.23634,-0.52698,0.64290,-0.17031,-0.41484,-0.66797,-0.18608,-0.36538,0.72661,0.27342,0.35811,-0.35126,-0.09303,0.99202,-0.38579,-0.73833,0.42013,1.36120,0.09186,0.34126", doubleToString(Toolkit.getlistVectors().get(0)));
        assertEquals("diskette", Toolkit.getListVocabulary().get(10001));
        assertEquals("-0.49209,-0.76410,1.85160,-0.27413,-0.07538,-0.09049,-0.02073,-1.00880,0.18535,1.03220,-0.52850,-0.54231,0.15589,0.12521,-0.01730,-0.24813,-1.04610,0.60019,1.04710,-0.05143,-0.17494,-0.81117,-0.23783,-0.00254,0.34878,0.04589,0.32036,0.63555,0.75448,-0.73136,0.08431,-1.28650,-0.22761,0.83771,0.66784,0.97248,0.11451,0.20307,-0.06278,-0.53176,0.76212,-0.40505,-1.01780,0.43932,0.03875,-0.31888,1.61950,0.41650,-0.09802,-0.74725", doubleToString(Toolkit.getlistVectors().get(10001)));
        assertEquals("zygote", Toolkit.getListVocabulary().get(38533));
        assertEquals("0.78116,-0.49601,0.02579,0.69854,0.15776,2.34440,0.84166,0.45595,0.65998,0.33325,1.17450,-0.73062,0.28331,1.67830,-0.20897,-0.14802,-0.26503,0.43743,0.84153,0.63459,-0.11696,-0.81233,0.49722,1.00780,0.11129,0.71532,0.46815,0.16052,0.56288,0.52048,0.25970,-0.73388,-0.67168,0.34182,-1.29360,0.10347,-0.54202,0.34131,0.37104,-0.66197,0.40490,-1.47550,-1.28450,0.04791,-0.12716,-0.31689,1.26120,-0.40108,-0.42229,-0.40833", doubleToString(Toolkit.getlistVectors().get(38533)));
        tofPassed_loadGlove1 = true;
        doubMarks += 1;
    }

    @Test
    @Order(2)
    void loadGLOVE_Functional_2() throws IOException, URISyntaxException {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Toolkit myTK = new Toolkit();
        FileSystem myFS = FileSystems.getDefault();
        File myGloveFile = getFileFromResource(FILENAME_GLOVE);
        File reNamedFile = new File(myGloveFile.getParentFile() + myFS.getSeparator() + "renamedGlove.csv");
        myGloveFile.renameTo(reNamedFile);

        try {
            assertThrows(Exception.class, () -> {
                myTK.loadGlove();
            });
            String outPut = outContent.toString();
            assertTrue(outPut.contains("glove.6B.50d_Reduced.csv"));
        } finally {
            reNamedFile.renameTo(myGloveFile);
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
        tofPassed_loadGlove2 = true;
        doubMarks += 1.5;
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Toolkit.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException(fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    @Test
    @Order(3)
    void loadGLOVE_Performance() throws IOException {
        if (tofPassed_loadGlove1 && tofPassed_loadGlove2) {
            Toolkit myTK = new Toolkit();

            long totalTime = 0;

            for (int i = 0; i < 50; i++) {
                mySW.start();
                myTK.loadGlove();
                mySW.stop();
                totalTime += mySW.getTime();
                mySW.reset();
            }

            System.out.println("Average execution time: " + (totalTime / 50));
            assertTrue(totalTime / 50 < (TIMELIMIT_loadGlove * MULTIPLIER) * 2);
            doubMarks += 0.5;
            assertTrue(totalTime / 50 < TIMELIMIT_loadGlove * MULTIPLIER);
            doubMarks += 2.0;
        }
    }

    @Test
    @Order(4)
    void loadNews_Functional_1() {
        Toolkit myTK = new Toolkit();
        List<NewsArticles> myNewsList = myTK.loadNews();

        assertEquals(NewsArticles.DataType.Training, myNewsList.get(0).getNewsType());
        assertEquals("1", myNewsList.get(0).getNewsLabel());
        assertEquals(" UK to launch an Online Fraud Charter with 11 major tech companies including TikTok, Snapchat and YouTube", myNewsList.get(0).getNewsTitle());

        assertEquals(NewsArticles.DataType.Training, myNewsList.get(30).getNewsType());
        assertEquals("2", myNewsList.get(30).getNewsLabel());
        assertEquals("Autumn Budget: Former Tory education secretary urges chancellor to lower earning threshold for student loan repayments", myNewsList.get(30).getNewsTitle());

        assertEquals(NewsArticles.DataType.Training, myNewsList.get(50).getNewsType());
        assertEquals("3", myNewsList.get(50).getNewsLabel());
        assertEquals("NASA UFO report - live: Scientists release Unidentified Anomalous Phenomena findings", myNewsList.get(50).getNewsTitle());

        assertEquals(NewsArticles.DataType.Testing, myNewsList.get(60).getNewsType());
        assertEquals("-1", myNewsList.get(60).getNewsLabel());
        assertEquals("Scottish Highers and GCSEs: What to do if you don't get the exam results you need", myNewsList.get(60).getNewsTitle());

        assertEquals(NewsArticles.DataType.Testing, myNewsList.get(61).getNewsType());
        assertEquals("-1", myNewsList.get(61).getNewsLabel());
        assertEquals("Crashed Russian spacecraft likely cause of new crater on the moon - as NASA releases images", myNewsList.get(61).getNewsTitle());
        tofPassed_loadNews1 = true;
        doubMarks += 2;
    }

    @Test
    @Order(5)
    void loadNews_Functional_2() {
        Toolkit myTK = new Toolkit();
        List<NewsArticles> myNewsList = myTK.loadNews();

        assertEquals(65, myNewsList.size());
        tofPassed_loadNews2 = true;
        doubMarks += 1;
    }

    @Test
    @Order(6)
    void loadNews_Performance() {
        if (tofPassed_loadNews1 && tofPassed_loadNews2) {
            Toolkit myTK = new Toolkit();

            long totalTime = 0;

            for (int i = 0; i < 50; i++) {
                mySW.start();
                myTK.loadNews();
                mySW.stop();
                totalTime += mySW.getTime();
                mySW.reset();
            }

            System.out.println("Average execution time: " + (totalTime / 50));
            assertTrue(totalTime / 50 < (TIMELIMIT_loadNews * MULTIPLIER) * 2);
            doubMarks += 0.5;
            assertTrue(totalTime / 50 < TIMELIMIT_loadNews * MULTIPLIER);
            doubMarks += 1.5;
        }
    }

    @Test
    @Order(7)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 4 Marks: " + doubMarks + "/10.0");
        System.out.println("===============================");
    }
}