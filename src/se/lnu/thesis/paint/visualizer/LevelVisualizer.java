package se.lnu.thesis.paint.visualizer;

import se.lnu.thesis.paint.element.AbstractGraphElement;
import se.lnu.thesis.paint.element.LevelElement;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 * Date: 28.08.2010
 * Time: 3:28:30
 */
public class LevelVisualizer extends AbstractGraphElementVisualizer {

    protected Color borderColor;

    public LevelVisualizer() {
        setMainColor(Color.BLACK);
        setBorderColor(Color.WHITE);
    }

    @Override
    public void draw(GLAutoDrawable drawable, AbstractGraphElement element) {
        setDrawable(drawable); // update OpenGL drawing contex

        if (element.getId() != null) {
            gl().glPushName(element.getId()); // set element id
        }

        drawShape(element);

        if (element.getId() != null) {
            gl().glPopName();
        }

    }

    protected void drawShape(AbstractGraphElement element) {
        LevelElement levelElement = (LevelElement) element;

        gl().glBegin(GL.GL_QUADS);
        color(getMainColor());
        gl().glVertex2d(levelElement.getPosition().getX(),
                levelElement.getPosition().getY());
        gl().glVertex2d(levelElement.getPosition().getX() + levelElement.getDimension().getX(),
                levelElement.getPosition().getY());
        gl().glVertex2d(levelElement.getPosition().getX() + levelElement.getDimension().getX(),
                levelElement.getPosition().getY() - levelElement.getDimension().getY());
        gl().glVertex2d(levelElement.getPosition().getX(),
                levelElement.getPosition().getY() - levelElement.getDimension().getY());
        gl().glEnd();


/*
        color(getBorderColor());
        gl().glLineWidth(0.5f);
        gl().glBegin(GL.GL_LINE_LOOP);
        gl().glVertex2d(levelElement.getPosition().getX(),
                levelElement.getPosition().getY());
        gl().glVertex2d(levelElement.getPosition().getX() + levelElement.getDimension().getX(),
                levelElement.getPosition().getY());
        gl().glVertex2d(levelElement.getPosition().getX() + levelElement.getDimension().getX(),
                levelElement.getPosition().getY() - levelElement.getDimension().getY());
        gl().glVertex2d(levelElement.getPosition().getX(),
                levelElement.getPosition().getY() - levelElement.getDimension().getY());
        gl().glEnd();
*/


        levelElement.getPreview().drawElements(getDrawable());
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
