package  Week03.interfaceComparable;
import java.util.Arrays;
public class TestPoint {
	public static void main(String[] args) {
		//Comparable<Point> [] points = new Comparable[5];
		//Comparable<Point> [] points = new Point[5];
		Point [] points = new Point[5];
		for (int i=0; i<points.length; i++){
			points[i] = new Point(points.length-i, points.length-i);
		}
		System.out.println("Values before sorting:");
		for (int i = 0 ; i < points.length ; i++)
			System.out.print(points[i]+", ");
		System.out.println();

		// [(3,3),(4,4),(2,2),(1,1),(0,0)]

//		for (int i = 0; i < points.length; i++) {
//			for (int j = 0; j < points.length; j++) {
//				if(points[i].compareTo(points[j])==-1){
//					Point temp = points[i];
//					points[i]=points[j];
//					points[j]=temp;
//				}
//
//			}
//		}
		Arrays.sort(points);

		System.out.println("Values after sorting:");
		for (int i = 0 ; i < points.length ; i++)
			System.out.print(points[i]+", ");
		System.out.println();
	}
}
