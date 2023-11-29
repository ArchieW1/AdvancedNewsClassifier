package uob.oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Toolkit {
    public static List<String> listVocabulary = null;
    public static List<double[]> listVectors = null;
    private static final String FILENAME_GLOVE = "glove.6B.50d_Reduced.csv";

    public static final String[] STOPWORDS = {"a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your"};

    public void loadGlove() throws IOException {
        BufferedReader myReader = null;
        //TODO Task 4.1 - 5 marks
        try {
            myReader = new BufferedReader(new FileReader(getFileFromResource(FILENAME_GLOVE)));
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        listVocabulary = new ArrayList<>();
        listVectors = new ArrayList<>();
        myReader.lines().forEach(Toolkit::parseRecordStr);
    }

    private static void parseRecordStr(String rec) {
        String[] recArr = rec.split(",");
        listVocabulary.add(recArr[0]);
        double[] nums = new double[recArr.length - 1];
        for (int i = 1; i < recArr.length; i++) {
            nums[i - 1] = Double.parseDouble(recArr[i]);
        }
        listVectors.add(nums);
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

    public List<NewsArticles> loadNews() {
        List<NewsArticles> listNews = new ArrayList<>();
        //TODO Task 4.2 - 5 Marks
        File directory;
        try {
            directory = getFileFromResource("News");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Apparently mac orders incorrectly by default so added a sort to ensure consistency.
        List<File> listFiles = listSortedFiles(directory);

        for (File file : listFiles) {
            listNews.add(parseArticle(file));
        }

        return listNews;
    }

    private static NewsArticles parseArticle(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(l -> sb.append(l).append("\n"));
        String htmlContents = sb.toString();

        String title = HtmlParser.getNewsTitle(htmlContents);
        String content = HtmlParser.getNewsContent(htmlContents);
        NewsArticles.DataType type = HtmlParser.getDataType(htmlContents);
        String label = HtmlParser.getLabel(htmlContents);

        return new NewsArticles(title, content, type, label);
    }

    private static List<File> listSortedFiles(File directory) {
        List<File> listFiles = new ArrayList<>();
        for (File file : directory.listFiles()) {
            listFiles.add(file);
        }
        listFiles.sort((x, y) -> x.getName().compareTo(y.getName()));
        return listFiles;
    }

    public static List<String> getListVocabulary() {
        return listVocabulary;
    }

    public static List<double[]> getlistVectors() {
        return listVectors;
    }
}
