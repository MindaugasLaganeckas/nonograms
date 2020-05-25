package com.nonograms.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Nonograms {
    public static void createNonogram(int[][] solution, int numBlocksX, int numBlocksY, ArrayList<Integer>[] topNumbers, ArrayList<Integer>[] leftNumbers){
        for (int i = 0; i < numBlocksX; i++) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            int black = 0;
            for (int j = 0; j < numBlocksY; j++) {
                if(solution[i][j] == 1){
                    black++;
                    if(j == (numBlocksY - 1)){
                        a.add(black);
                    }
                }
                else if (black > 0){
                    a.add(black);
                    black = 0;
                }
            }
            topNumbers[i] = a;
        }
        for (int i = 0; i < numBlocksY; i++) {
            ArrayList<Integer> a = new ArrayList();
            int black = 0;
            for (int j = 0; j < numBlocksX; j++) {
                if(solution[j][i] == 1){
                    black++;
                    if(j == (numBlocksX - 1)){
                        a.add(black);
                    }
                }
                else if (black > 0){
                    a.add(black);
                    black = 0;
                }
            }
            leftNumbers[i] = a;
        }
    }

    public static List<Integer> caclulateNonogramDimensions(List<Integer>[] topNumbers, List<Integer>[] leftNumbers){
        List<Integer> topLeft = new ArrayList();
        int max_top = 0;
        for (int i = 0; i < topNumbers.length; i++) {

        }

        return topLeft;
    }

    public static BufferedImage drawNonogram(List<Integer>[] topNumbers, List<Integer>[] leftNumbers){
        BufferedImage image = new BufferedImage(101, 101, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                Color c;
                if(((i % 5) == 0) || ((j % 5) == 0) ){
                    c = new Color (0,0,0);
                } else {
                    c = new Color (255,255,255);
                }
                int p = ColorTransformations.colorToPixel(c);
                image.setRGB(i ,j, p);
            }
        }
        return image;
    }
}
