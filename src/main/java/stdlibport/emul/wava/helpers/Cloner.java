/**
 * Copyright (C) 2020 Fungus Software <https://fungus-soft.com>
 * This file is a part of Wava(TM)
 */
package wava.helpers;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.Point;
import java.awt.Dimension;

/**
 * Because clone() is protected in GWT
 */
public class Cloner {

    public static float[] clone(float[] obj) {
        float[] ret = new float[obj.length];
        for (int i = 0; i < obj.length; i++)
            ret[i] = obj[i];
        return ret;
    }

    public static Insets clone(Insets obj) {
        return new Insets(obj.top, obj.left, obj.bottom, obj.right);
    }

    public static AffineTransform clone(AffineTransform obj) {
        return obj; // TODO clone
    }

    public static Rectangle clone(Rectangle obj) {
        return new Rectangle(obj.x, obj.y, obj.width, obj.height);
    }

    public static int[] clone(int[] obj) {
        int[] arr = new int[obj.length];
        for (int i = 0; i < obj.length; i++)
            arr[i] = obj[i];
        return arr;
    }

    public static Point clone(Point p) {
        return new Point(p.x, p.y);
    }

    public static Dimension clone(Dimension obj) {
        return new Dimension(obj.width, obj.height);
    }

    public static GeneralPath clone(GeneralPath gp) {
        // TODO Auto-generated method stub
        return gp;
    }

}