package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(5, 1);
    double expected = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    Assert.assertEquals(p1.distance(p2), expected);
  }

  @Test
  public void testDistanceGreaterThanZero() {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(5, 1);
    Assert.assertTrue(p1.distance(p2) >= 0);
  }

  @Test
  public void testDistanceSame() {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(5, 1);
    double anothercalc = Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    Assert.assertEquals(p1.distance(p2), anothercalc);
  }

}
