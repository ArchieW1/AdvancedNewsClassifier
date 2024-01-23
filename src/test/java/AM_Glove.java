import org.junit.jupiter.api.*;
import uob.oop.Glove;
import uob.oop.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AM_Glove {
    String strWord1 = "AutoMarking";
    Vector vecVector = new Vector(new double[]{123.00, -12.99, 0.00});
    Glove myGlove = new Glove(strWord1, vecVector);
    public static double doubMarks = 0;

    @Test
    @Order(1)
    void GloveConstructor() {
        assertEquals("uob.oop.Glove", myGlove.toString().split("@")[0]);
        doubMarks += 0.5;
    }

    @Test
    @Order(2)
    void getVocabulary() {
        assertEquals("AutoMarking", myGlove.getVocabulary());
        doubMarks += 0.5;
    }

    @Test
    @Order(3)
    void setVocabulary() { //0.5
        myGlove.setVocabulary("Birmingham");
        assertEquals("Birmingham", myGlove.getVocabulary());
        doubMarks += 0.5;
    }

    @Test
    @Order(4)
    void getVector() { //0.5
        assertEquals("123.00000,-12.99000,0.00000", myGlove.getVector().toString());
        doubMarks += 0.5;
    }

    @Test
    @Order(5)
    void setVector() { //0.5
        myGlove.setVector(new Vector(new double[]{0, -99, 22.341}));
        assertEquals("0.00000,-99.00000,22.34100", myGlove.getVector().toString());
        doubMarks += 0.5;
    }

    @Test
    @Order(6)
    void Marks() {
        System.out.println("===============================");
        System.out.println("Task 1 Marks: " + doubMarks + "/2.5");
        System.out.println("===============================");
    }
}
