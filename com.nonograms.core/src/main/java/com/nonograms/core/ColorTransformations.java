package com.nonograms.core;

import java.awt.*;

public class ColorTransformations {
    public ColorTransformations(){
    }

    public Color pixelToColor(int p){
        return new Color((p>>16) & 0xff, (p>>8) & 0xff, p & 0xff, (p>>24) & 0xff);
    }
    public int colorToPixel(Color c){
        int p = 0;
        p = p | (c.getAlpha()<<24);
        p = p | (c.getRed()<<16);
        p = p | (c.getGreen()<<8);
        p = p | c.getBlue();
        return p;
    }

    public Color toGreyScale(Color c){
        int newShade = (c.getRed() + c.getBlue() + c.getGreen())/3;
        return new Color(newShade, newShade, newShade, c.getAlpha());
    }

    public Color toBlackAndWhite(Color c){
        int intensity = (c.getRed() + c.getBlue() + c.getGreen())/3;
        int newShade;
        if(intensity < 128) newShade = 0;
                else newShade = 255;
         return new Color(newShade, newShade, newShade, c.getAlpha());
    }
}
