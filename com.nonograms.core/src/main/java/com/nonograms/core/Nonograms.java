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
            if(topNumbers[i].size() > max_top){
                max_top = topNumbers[i].size();
            }
        }
        topLeft.add(max_top);
        int max_left = 0;
        for (int i = 0; i < leftNumbers.length; i++) {
            if(leftNumbers[i].size() > max_left){
                max_left = leftNumbers[i].size();
            }
        }
        topLeft.add(max_left);

        return topLeft;
    }

    public static BufferedImage drawNonogram(List<Integer>[] topNumbers, List<Integer>[] leftNumbers){
        List topLeft = caclulateNonogramDimensions(topNumbers, leftNumbers);
        int numCellsTop = (int) topLeft.get(0);
        int numCellsLeft = (int) topLeft.get(1);
        int numCellsHeight = leftNumbers.length;
        int numCellsWidth = topNumbers.length;
        int cellSize = 25;
        int outerBorder = 5;
        int innerBorder = 1;
        int fiveBorder = 3;
        int imageHeight = outerBorder + (numCellsTop - 1) * innerBorder + numCellsTop * cellSize + outerBorder +
                (int)(numCellsHeight - 1) / 5 * (fiveBorder - innerBorder) + (numCellsHeight - 1) * innerBorder +
                numCellsHeight * cellSize + outerBorder;
        int imageWidth = outerBorder + (numCellsLeft - 1) * innerBorder + numCellsLeft * cellSize + outerBorder +
                (int)(numCellsWidth - 1) / 5 * (fiveBorder - innerBorder) + (numCellsWidth - 1) * innerBorder +
                numCellsWidth * cellSize + outerBorder;
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        //Fill picture white
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                Color c = new Color (255,255,255);
                int p = ColorTransformations.colorToPixel(c);
                image.setRGB(i ,j, p);
            }
        }

        int startLeft = outerBorder + numCellsLeft * cellSize + (numCellsLeft - 1) * innerBorder;
        int startTop = outerBorder + numCellsTop * cellSize + (numCellsTop - 1) * innerBorder;

        //Numbers area in grey
        Color c = new Color (220,220,220);
        int p = ColorTransformations.colorToPixel(c);

        for (int i = startLeft; i < imageWidth; i++) {
            for (int j = 0; j < startTop; j++) {
                image.setRGB(i,j,p);
            }
        }
        for (int i = startTop; i < imageHeight; i++) {
            for (int j = 0; j < startLeft; j++) {
                image.setRGB(j,i,p);
            }
        }

        //Borders
        c = new Color (0,0,0);
        p = ColorTransformations.colorToPixel(c);

        //Draw outer border
        for (int i = startLeft; i < imageWidth; i++) {
            for (int j = 0; j < outerBorder; j++) {
                image.setRGB(i,j,p);
            }
        }

        //Draw top
        for (int i = startLeft; i < imageWidth; i++) {
            for (int j = 1; j < numCellsTop; j++) {
                for (int k = 0; k < innerBorder; k++) {
                    int i2 = outerBorder + j * cellSize + (j-1)*innerBorder + k;
                    image.setRGB(i,i2,p);
                }
            }
        }

        //Draw middle outer border
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < outerBorder; j++) {
                image.setRGB(i,j+startTop,p);
            }
        }

        //Draw middle
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 1; j < numCellsHeight; j++) {
                int border;
                if(j % 5 == 0){
                    border = fiveBorder;
                }else{
                    border = innerBorder;
                }
                for (int k = 0; k < border; k++) {
                    int i2 = startTop + outerBorder + j * cellSize + (j-1)*innerBorder + (int)(j - 1) / 5 * (fiveBorder - innerBorder) + k;
                    image.setRGB(i,i2,p);
                }
            }
        }

        //Draw bottom border
        for (int i = 0; i < imageWidth; i++) {
            for (int j = imageHeight - outerBorder; j < imageHeight; j++) {
                image.setRGB(i,j,p);
            }
        }

        //Draw outer border
        for (int i = startLeft; i < imageHeight; i++) {
            for (int j = 0; j < outerBorder; j++) {
                image.setRGB(j,i,p);
            }
        }

        //Draw left
        for (int i = startTop; i < imageHeight; i++) {
            for (int j = 1; j < numCellsLeft; j++) {
                for (int k = 0; k < innerBorder; k++) {
                    int i2 = outerBorder + j * cellSize + (j-1)*innerBorder + k;
                    image.setRGB(i2,i,p);
                }
            }
        }

        //Draw middle outer border
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < outerBorder; j++) {
                image.setRGB(j+startLeft, i, p);
            }
        }

        //Draw middle
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 1; j < numCellsWidth; j++) {
                int border;
                if(j % 5 == 0){
                    border = fiveBorder;
                }else{
                    border = innerBorder;
                }
                for (int k = 0; k < border; k++) {
                    int i2 = startLeft + outerBorder + j * cellSize + (j-1)*innerBorder + (int)(j - 1) / 5 * (fiveBorder - innerBorder) + k;
                    image.setRGB(i2,i,p);
                }
            }
        }

        //Draw right border
        for (int i = 0; i < imageHeight; i++) {
            for (int j = imageWidth - outerBorder; j < imageWidth; j++) {
                image.setRGB(j,i,p);
            }
        }

        return image;
    }
}
