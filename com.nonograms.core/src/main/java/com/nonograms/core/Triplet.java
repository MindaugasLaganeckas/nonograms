package com.nonograms.core;

public class Triplet {
  private int r;
  private int g;
  private int b;

  public Triplet() {
    this.r = 0;
    this.g = 0;
    this.b = 0;
  }

  public Triplet(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }

  public Triplet add(Triplet t) {
    return new Triplet(t.getR() + this.r, t.getG() + this.g, t.getB() + this.b);
  }

  public Triplet divide(int i) {
    return new Triplet(this.r / i, this.g / i, this.b / i);
  }
}
