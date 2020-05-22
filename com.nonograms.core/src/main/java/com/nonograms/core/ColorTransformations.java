package com.nonograms.core;

import java.awt.*;

public class ColorTransformations {

    public static Color pixelToColor(int p){
        return new Color((p>>16) & 0xff, (p>>8) & 0xff, p & 0xff, (p>>24) & 0xff);
    }
    public static int colorToPixel(Color c){
        int p = 0;
        p = p | (c.getAlpha()<<24);
        p = p | (c.getRed()<<16);
        p = p | (c.getGreen()<<8);
        p = p | c.getBlue();
        return p;
    }

    public static Color toGreyScale(Color c){
        int newShade = (c.getRed() + c.getBlue() + c.getGreen())/3;
        return new Color(newShade, newShade, newShade, c.getAlpha());
    }

    public static Color toBlackAndWhite(Color c, int blackLimit){
        int intensity = (c.getRed() + c.getBlue() + c.getGreen())/3;
        int newShade;
        if(intensity < blackLimit) newShade = 0;
                else newShade = 255;
         return new Color(newShade, newShade, newShade, c.getAlpha());
    }
}
