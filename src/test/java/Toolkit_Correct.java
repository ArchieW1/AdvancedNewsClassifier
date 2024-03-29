import uob.oop.HtmlParser;
import uob.oop.NewsArticles;
import uob.oop.Toolkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Toolkit_Correct extends Toolkit {
    private static final String FILENAME_GLOVE = "glove.6B.50d_Reduced.csv";
    @Override
    public void loadGlove() throws IOException {
        BufferedReader myReader = null;
        //TODO Task 4.1 - 5 marks
        try {
            listVocabulary = new ArrayList<>();
            listVectors = new ArrayList<>();
            myReader = new BufferedReader(new FileReader(getFileFromResource(FILENAME_GLOVE)));
            String resultLine = myReader.readLine();

            while (resultLine != null) {
                String[] splitResult = resultLine.split(",");
                listVocabulary.add(splitResult[0]);
                double[] doubVectors = new double[splitResult.length - 1];
                for (int i = 1; i < splitResult.length; i++) {
                    doubVectors[i - 1] = Double.parseDouble(splitResult[i]);
                }
                listVectors.add(doubVectors);
                resultLine = myReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            myReader.close();
        }
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

    @Override
    public List<NewsArticles> loadNews() {
        List<NewsArticles> listNews = new ArrayList<>();
        //TODO Task 4.2 - 5 Marks
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/News"))) {
            paths.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".htm")).sorted(Comparator.comparing(Path::getFileName)).forEach(p -> {
                StringBuilder content = new StringBuilder();
                try (BufferedReader br = Files.newBufferedReader(p)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    NewsArticles myNews = new NewsArticles(HtmlParser.getNewsTitle(content.toString()), HtmlParser.getNewsContent(content.toString()), HtmlParser.getDataType(content.toString()), HtmlParser.getLabel(content.toString()));
                    listNews.add(myNews);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listNews;
    }
}
