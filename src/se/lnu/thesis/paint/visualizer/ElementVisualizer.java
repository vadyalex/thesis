package se.lnu.thesis.paint.visualizer;

import se.lnu.thesis.paint.element.Element;

import javax.media.opengl.GLAutoDrawable;

/**
 * Created by IntelliJ IDEA.
 * User: vady
 * Date: 20.03.2010
 * Time: 0:59:03
 */
public interface ElementVisualizer {

    public void draw(GLAutoDrawable drawable, Element element);

}
