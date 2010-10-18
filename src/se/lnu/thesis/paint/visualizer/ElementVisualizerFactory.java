package se.lnu.thesis.paint.visualizer;

import com.google.common.collect.ImmutableMap;
import se.lnu.thesis.paint.visualizer.edge.LineEdgeVisualizer;
import se.lnu.thesis.paint.visualizer.edge.PolarDendrogramEdgeVisualizer;
import se.lnu.thesis.paint.visualizer.vertex.CircleVertexVisualizer;
import se.lnu.thesis.paint.visualizer.vertex.PointVertexVisualizer;
import se.lnu.thesis.paint.visualizer.vertex.RectVertexVisualizer;
import se.lnu.thesis.paint.visualizer.vertex.TriangleVertexVisualizer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 * Date: 20.07.2010
 * Time: 23:51:08
 */
public class ElementVisualizerFactory {

    private static final double MAX_GROUP_VERTEX_SIZE = 0.02;

    private static final Color DEFAULT_ELEMENT_COLOR = new Color(100, 100, 100, 100);

    private static final int CIRCLE_VERTEX_VISUALIZER = -1;
    private static final int RECT_VERTEX_VISUALIZER = -2;
    private static final int POINT_VERTEX_VISUALIZER = -3;
    private static final int SMALL_CIRCLE_VISUALIZER = -6;
    private static final int TRIANGLE_VISUALIZER = -7;

    private static final int LINE_EDGE_VISUALIZER = -101;
    private static final int POLAR_DENDROGRAM_EDGE_VISUALIZER = -102;


    private static final ElementVisualizerFactory instance = new ElementVisualizerFactory();

    public static ElementVisualizerFactory getInstance() {
        return instance;
    }


    private ImmutableMap<Object, AbstractElementVisualizer> visualizers = new ImmutableMap.Builder<Object, AbstractElementVisualizer>()
            .put(SMALL_CIRCLE_VISUALIZER, new CircleVertexVisualizer(DEFAULT_ELEMENT_COLOR, 0.005))
            .put(CIRCLE_VERTEX_VISUALIZER, new CircleVertexVisualizer(DEFAULT_ELEMENT_COLOR))
            .put(TRIANGLE_VISUALIZER, new TriangleVertexVisualizer(DEFAULT_ELEMENT_COLOR))
            .put(RECT_VERTEX_VISUALIZER, new RectVertexVisualizer(DEFAULT_ELEMENT_COLOR))
            .put(POINT_VERTEX_VISUALIZER, new PointVertexVisualizer(DEFAULT_ELEMENT_COLOR))
            .put(LINE_EDGE_VISUALIZER, new LineEdgeVisualizer(DEFAULT_ELEMENT_COLOR))
            .put(POLAR_DENDROGRAM_EDGE_VISUALIZER, new PolarDendrogramEdgeVisualizer(DEFAULT_ELEMENT_COLOR))
            .build();

    private Map<Object, AbstractElementVisualizer> rectVisualizers = new HashMap<Object, AbstractElementVisualizer>();

    private ElementVisualizerFactory() {
    }


    public AbstractElementVisualizer getCircleVisualizer() {
        return visualizers.get(CIRCLE_VERTEX_VISUALIZER);
    }

    public AbstractElementVisualizer getPointVisualizer() {
        return visualizers.get(POINT_VERTEX_VISUALIZER);
    }

    public ElementVisualizer getLineEdgeVisializer() {
        return visualizers.get(LINE_EDGE_VISUALIZER);
    }

    public ElementVisualizer getPolarDendrogramEdgeVisializer() {
        return visualizers.get(POLAR_DENDROGRAM_EDGE_VISUALIZER);
    }

    public AbstractElementVisualizer getSmallCircleVisualizer() {
        return visualizers.get(SMALL_CIRCLE_VISUALIZER);
    }

    public AbstractElementVisualizer getTriangleVisualizer() {
        return visualizers.get(TRIANGLE_VISUALIZER);
    }

    public AbstractElementVisualizer getRectVisualizer() {
        return visualizers.get(RECT_VERTEX_VISUALIZER);
    }

    public ElementVisualizer getRectVisualizer(int maxGroupSize, int thisGroupSize) {

        if (rectVisualizers.containsKey(thisGroupSize)) {
            return rectVisualizers.get(thisGroupSize);
        } else {
            double size = (MAX_GROUP_VERTEX_SIZE / maxGroupSize) * thisGroupSize;
            rectVisualizers.put(thisGroupSize, new RectVertexVisualizer(DEFAULT_ELEMENT_COLOR, size));

            return rectVisualizers.get(thisGroupSize);
        }
    }
}