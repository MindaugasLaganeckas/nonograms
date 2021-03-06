package com.nonograms.core;

import com.google.common.io.Files;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {

    public static void main(String[] args) throws IOException {

        final String input = args[0];
        final String nonogramOutput = args[1];
        final String solutionOutput = args[2];
        final String extension = args[3];
        int numBlocksX = 35;
        int numBlocksY = 35;

        createNonogramImage(input, nonogramOutput, solutionOutput, extension, numBlocksX, numBlocksY);
    }

    private static void createNonogramImage(String input, String nonogramOutput, String solutionOutput, String extension, int numBlocksX, int numBlocksY) throws IOException {
        BufferedImage img = ImageIO.read(new File(input));
        int[][] solution = changeImage(img, numBlocksX, numBlocksY);

        saveImage(solutionOutput, extension, img);

        ArrayList<Integer>[] t = (ArrayList<Integer>[]) new ArrayList[numBlocksX];
        ArrayList<Integer>[] l = (ArrayList<Integer>[]) new ArrayList[numBlocksY];
        Nonograms.createNonogram(solution, numBlocksX, numBlocksY, t, l);
        img = Nonograms.drawNonogram(t, l);

        saveImage(nonogramOutput, extension, img);
    }

    private static void saveImage(String output, String extension, BufferedImage img) throws IOException {
        File outfile = new File(output);
        ImageIO.write(img, extension, outfile);
    }

    public static int[][] changeImage(BufferedImage img, final int numBlocksX, final int numBlocksY) {
        // get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        double blockSizeX = (double) width / numBlocksX;
        double blockSizeY = (double) height / numBlocksY;

        Triplet[][] blocks = new Triplet[numBlocksX][numBlocksY];
        int[][] sizes = new int[numBlocksX][numBlocksY];
        for (int i = 0; i < numBlocksX; i++) {
            for (int j = 0; j < numBlocksY; j++) {
                blocks[i][j] = new Triplet();
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //get pixel value
                int p = img.getRGB(i, j);
                Color c = ColorTransformations.pixelToColor(p);
                int posX = (int) (i / blockSizeX);
                int posY = (int) (j / blockSizeY);
                blocks[posX][posY] = blocks[posX][posY].add(new Triplet(c.getRed(), c.getGreen(), c.getBlue()));
                sizes[posX][posY] = sizes[posX][posY] + 1;
            }
        }

        int[][] solution = new int[numBlocksX][numBlocksY];
        for (int i = 0; i < numBlocksX; i++) {
            for (int j = 0; j < numBlocksY; j++) {
                try {
                    blocks[i][j] = blocks[i][j].divide(sizes[i][j]);
                    solution[i][j] = blocks[i][j].toBlackAndWhite(128);
                } catch (Exception e) {
                    System.out.println(i + " " + j);
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //get pixel value
                int posX = (int) (i / blockSizeX);
                int posY = (int) (j / blockSizeY);
                Color c = new Color(blocks[posX][posY].getR(), blocks[posX][posY].getG(), blocks[posX][posY].getB());
                Color newColor = ColorTransformations.toBlackAndWhite(c, 128);
                int newPixel = ColorTransformations.colorToPixel(newColor);
                img.setRGB(i, j, newPixel);
            }
        }
        return solution;
    }



}
