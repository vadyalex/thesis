package se.lnu.thesis.paint.element;

import se.lnu.thesis.paint.visualizer.AbstractElementVisualizer;
import se.lnu.thesis.utils.IdUtils;

import java.awt.geom.Point2D;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 * Date: 02.07.2010
 * Time: 17:54:07
 */
public class VertexElement extends VisualizableElement {

    public static final int VERTEX_DRAWING_ORDER = 1;

    public static VertexElement init(Object o) {
        return init(o, null, null);
    }

    public static VertexElement init(Object o, double x, double y) {
        return init(o, x, y, null);
    }

    public static VertexElement init(Object o, Point2D position) {
        return init(o, position, null);
    }

    public static VertexElement init(Object o, Point2D position, AbstractElementVisualizer visualizer) {
        if (position == null) {
            throw new IllegalArgumentException("Argument 'position' cannt be null!");
        }

        return init(o, position.getX(), position.getY(), visualizer);
    }

    public static VertexElement init(Object o, double x, double y, AbstractElementVisualizer visualizer) {
        VertexElement result = new VertexElement();

        result.setId(IdUtils.next());

        result.setObject(o);
        result.setPosition(new Point2D.Double(x, y));
        result.setVisualizer(visualizer);

        return result;
    }

    public VertexElement() {

    }

    public ElementType getType() {
        return ElementType.NODE;
    }

    public int getDrawingOrder() {
        return VERTEX_DRAWING_ORDER;
    }

}
