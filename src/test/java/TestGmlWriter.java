

import edu.uci.ics.jung.graph.Graph;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.lnu.thesis.io.gml.GmlWriter;
import se.lnu.thesis.utils.GraphMaker;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 * Date: 11.08.2010
 * Time: 16:09:13
 * To change this template use File | Settings | File Templates.
 */
public class TestGmlWriter {

    @Test
    public void viewBinaryTree() throws IOException {
        Graph graph = GraphMaker.createTestBinaryTree();
        assertEquals(11, graph.getVertexCount());

        GmlWriter writer = new GmlWriter();

        writer.write(graph, System.out);

        FileOutputStream out = new FileOutputStream("test.gml");
        writer.write(graph, out);
        out.close();

    }

}
