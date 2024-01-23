import uob.oop.AdvancedNewsClassifier;

import java.io.IOException;

public class AdvancedNC_Correct extends AdvancedNewsClassifier {

    public AdvancedNC_Correct() throws IOException {
        myTK = new Toolkit_Correct();
        myTK.loadGlove();
        listNews = myTK.loadNews();
        listGlove = createGloveList();
        listEmbedding = loadData();
    }
}
