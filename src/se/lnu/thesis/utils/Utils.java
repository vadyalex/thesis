package se.lnu.thesis.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import se.lnu.thesis.io.gml.GmlReader;

import javax.media.opengl.GL;
import java.awt.*;
import java.awt.geom.Point2D;


public class Utils {

    public static final Logger LOGGER = Logger.getLogger(Utils.class);

    public static final String SPEC_SYMBOLS = " \t\n";

    public static final double PIdev180 = 0.017444;

    public static final double COLOR_MAX = 256.0;

    public static double inRadians(double angle) {
        return angle * PIdev180;
    }

    public static void computeOnCirclePosition(Point2D position, double angle, double radius, double xCenter, double yCenter) {
        if (position == null) {
            throw new IllegalArgumentException("Argument 'position' cannt be null! Initialize it before method call");
        }

        double x = Math.cos(inRadians(angle)) * radius + xCenter;
        double y = Math.sin(inRadians(angle)) * radius + yCenter;

        position.setLocation(x, y);

        LOGGER.debug(angle + "; " + x + ", " + y);
    }

    public static double min(double value1, double value2) {
        return value1 < value2 ? value1 : value2;
    }

    public static double max(double value1, double value2) {
        return value1 > value2 ? value1 : value2;
    }

    public static double colorAsDouble(int color) {
        return color / COLOR_MAX;
    }

    public static void color(GL gl, Color color) {
        gl.glColor3d(colorAsDouble(color.getRed()),
                colorAsDouble(color.getGreen()),
                colorAsDouble(color.getBlue()));
    }

    /**
     * Extract Integer value of the property.
     * <p/>
     * For example:
     * TAG 123      ==> 123
     * id 12314     ==> 12314
     * date 220609  ==> 220609
     *
     * @param s   Source string
     * @param tag Property name
     * @return Resulting string of the property value
     */
    public static Integer extractIntegerValue(String s, String tag) {
        s = StringUtils.strip(s, SPEC_SYMBOLS);
        String value = s.substring(s.indexOf(tag) + tag.length());

        Integer result = null;
        try {
            result = Integer.valueOf(StringUtils.strip(value, SPEC_SYMBOLS));
        } catch (NumberFormatException e) {
            GmlReader.LOGGER.error("Cannt convert value '" + value + "' to Integer");
        }
        return result;
    }

    /**
     * Extract String value of the property.
     * <p/>
     * For example:
     * TAG "value"      ==> value
     * time "11:12"     ==> 11:12
     * name "Mr. Smith"  ==> Mr. Smith
     *
     * @param s   Source string
     * @param tag Property name
     * @return Resulting string of the property value
     */
    public static String extractStringValue(String s, String tag) {
        return s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
    }
}
