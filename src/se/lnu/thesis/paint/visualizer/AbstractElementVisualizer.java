package se.lnu.thesis.paint.visualizer;

import se.lnu.thesis.paint.element.Element;
import se.lnu.thesis.utils.Utils;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 */
public abstract class AbstractElementVisualizer implements ElementVisualizer {

    public static final Color DEFAULT_MAIN_COLOR = Color.WHITE;      // default levelBackgroud for graph elements visualization
    public static final Color DEFAULT_SUBGRAPH_COLOR = Color.YELLOW; // default subgraph levelBackgroud
    public static final Color DEFAULT_SELECTION_COLOR = Color.GREEN;  // default selection levelBackgroud
    public static final Color DEFAULT_FOCUSED_COLOR = Color.BLUE;  // default selection levelBackgroud


    private GLAutoDrawable drawable; // OpenGL drawing context

    private GLU glu;

    private Color mainColor = DEFAULT_MAIN_COLOR;
    private Color selectionColor = DEFAULT_SELECTION_COLOR;
    private Color subgraphColor = DEFAULT_SUBGRAPH_COLOR;
    private Color focusedColor = DEFAULT_FOCUSED_COLOR;

    public AbstractElementVisualizer() {

    }

    public AbstractElementVisualizer(Color mainColor) {
        setMainColor(mainColor);
    }

    public void draw(GLAutoDrawable drawable, Element element) {
        setDrawable(drawable); // update OpenGL drawing contex

        if (element.getId() != null) {
            gl().glPushName(element.getId()); // set element id
        }

        drawingColor(element); // set element levelBackgroud
        drawShape(element); // draw object

        if (element.getId() != null) {
            gl().glPopName();
        }
    }

    protected abstract void drawShape(Element element);


    protected void drawingColor(Element element) {
        if (element.isFocused()) {
            color(focusedColor);
        } else {
            if (element.isSelected()) {
                color(getSelectionColor());
            } else {
                if (element.isHighlighted()) {
                    color(getSubgraphColor());
                } else {
                    color(getMainColor());
                }
            }
        }
    }

    protected void color(Color color) {
        gl().glColor3d(Utils.colorAsDouble(color.getRed()),
                Utils.colorAsDouble(color.getGreen()),
                Utils.colorAsDouble(color.getBlue()));
    }

    public Color getSubgraphColor() {
        return subgraphColor;
    }

    public void setSubgraphColor(Color subgraphColor) {
        this.subgraphColor = subgraphColor;
    }

    public Color getSelectionColor() {
        return selectionColor;
    }

    public void setSelectionColor(Color selectionColor) {
        this.selectionColor = selectionColor;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    protected GLAutoDrawable getDrawable() {
        return drawable;
    }

    protected void setDrawable(GLAutoDrawable drawable) {
        this.drawable = drawable;
    }

    protected GL gl() {
        return getDrawable().getGL();
    }

    protected GLU glu() {
        if (glu == null) {
            glu = new GLU();
        }

        return glu;
    }


}