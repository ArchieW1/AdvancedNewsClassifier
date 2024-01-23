import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uob.oop.NewsArticles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AM_NewsArticles {
    private String strTitle = "This is the title ";
    private String strContent = "Test news for the Auto-Marking system.";
    private NewsArticles.DataType myDT = NewsArticles.DataType.Testing;
    private String strLabel = "1";
    private NewsArticles myNA = new NewsArticles(strTitle, strContent, myDT, strLabel);
    public static double doubMarks = 0;

    @Test
    @Order(1)
    void NewsArticlesConstructor() {
        assertEquals("uob.oop.NewsArticles", myNA.toString().split("@")[0]);
        doubMarks += 0.5;
    }

    @Test
    @Order(2)
    void getNewsLabel() {
        assertEquals("1", myNA.getNewsLabel());
        doubMarks += 0.5;
    }

    @Test
    @Order(3)
    void getNewsType() {
        assertEquals(myDT, myNA.getNewsType());
        doubMarks += 0.5;
    }

    @Test
    @Order(4)
    void getNewsTitle() {
        assertEquals(strTitle, myNA.getNewsTitle());
        doubMarks += 0.5;
    }

    @Test
    @Order(5)
    void getNewsContent() {
        assertEquals(strContent, myNA.getNewsContent());
        doubMarks += 0.5;
    }

    @Test
    @Order(6)
    void setNewsLabel() {
        myNA.setNewsLabel("99");
        assertEquals("99", myNA.getNewsLabel());
        doubMarks += 0.5;
    }

    @Test
    @Order(7)
    void setNewsType() {
        myNA.setNewsType(NewsArticles.DataType.Training);
        assertEquals(NewsArticles.DataType.Training, myNA.getNewsType());
        doubMarks += 0.5;
    }

    @Test
    @Order(8)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 2 Marks: " + doubMarks + "/3.5");
        System.out.println("===============================");
    }
}
