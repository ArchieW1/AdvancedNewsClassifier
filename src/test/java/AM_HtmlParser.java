import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uob.oop.HtmlParser;
import uob.oop.NewsArticles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AM_HtmlParser {
    private String strHTML = "<datatype>Training</datatype><label>2</label>";
    private String strHTML2 = "<datatype>Testing</datatype><label>1</label>";
    private String strHTML3 = "This datatype a label";
    public static double doubMarks = 0;

    @Test
    @Order(1)
    void getDataType_1() {
        assertEquals(NewsArticles.DataType.Training, HtmlParser.getDataType(strHTML));
        doubMarks += 0.5;
    }

    @Test
    @Order(2)
    void getDataType_2() {
        assertEquals(NewsArticles.DataType.Testing, HtmlParser.getDataType(strHTML2));
        doubMarks += 0.5;
    }

    @Test
    @Order(3)
    void getDataType_3() {
        assertEquals(NewsArticles.DataType.Testing, HtmlParser.getDataType(strHTML3));
        doubMarks += 0.5;
    }

    @Test
    @Order(4)
    void getLabel_1() {
        assertEquals("2", HtmlParser.getLabel(strHTML));
        doubMarks += 0.5;
    }

    @Test
    @Order(5)
    void getLabel_2() {
        assertEquals("1", HtmlParser.getLabel(strHTML2));
        doubMarks += 0.5;
    }

    @Test
    @Order(6)
    void getLabel_3() {
        assertEquals("-1", HtmlParser.getLabel(strHTML3));
        doubMarks += 0.5;
    }

    @Test
    @Order(7)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 3 Marks: " + doubMarks + "/3.0");
        System.out.println("===============================");
    }

}
