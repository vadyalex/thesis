package se.lnu.thesis.io.graphml;

import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lnu.thesis.utils.GraphUtils;

import java.util.Stack;

/**
 * User: vady
 * Date: 28.01.2010
 * Time: 0:01:49
 */
public class JungTreeYedHandler extends JungYedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JungTreeYedHandler.class);

    protected void endTagGraph() {
        // add it to the list
        graphs.add(graph2tree());
        graph = null;
    }

    protected Tree graph2tree() {
        DelegateTree tree = new DelegateTree();

        Object root = GraphUtils.getRoot((Graph) graph);

        tree.addVertex(root);


        Stack stack = new Stack();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object parent = stack.pop();

            for (Object child : ((Graph) graph).getSuccessors(parent)) {
                if (!tree.containsVertex(child)) {
                    tree.addChild(parent + "->" + child, parent, child);

                    stack.push(child);
                }
            }

        }


        return tree;
    }

}