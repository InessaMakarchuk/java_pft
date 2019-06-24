package ru.stqa.pft.sandbox;

public class DistanceBetweenPoints {
  public static void main(String[] args) {

    Point p1 = new Point(2,4);
    Point p2 = new Point(5,1);

    System.out.println("Расстояние между двумя точками с координатами (" + p1.x + ";" + p1.y + ") и (" + p2.x + ";" + p2.y + ") вычисляется как квадратный корень из ((" + p2.x + "-" + p1.x + ")^2 - (" +
            p2.y + "-" + p1.y + ")^2) и равен " + p1.distance(p2));
  }

}