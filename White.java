/*
Program that draws a map from the list of long/lat pairs
Author: Savannah Obregon
Class: CS 1233 01
*/
//import scanner, io.file, color and error
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException; //import for the throw if not found stop
import java.awt.Color; 

public class White
{
	public static void main (String[] args) throws FileNotFoundException
 	{
 		if (args.length < 1) //error message for invalid inputs
 		{
 			System.out.println("Usage: java White <region-symbol>"); //error message
 			System.exit(-1);
 		}
		Scanner sc = new Scanner(new File("purple/" + args[0] + ".txt")); //filename from input

		//reading min long/lat
		double xmin = sc.nextDouble();
		double ymin = sc.nextDouble(); //y is lat and x is long

		//reading max long/lat
		double xmax = sc.nextDouble();
		double ymax = sc.nextDouble();

		//changing window sizing
		StdDraw.setCanvasSize(712, 512); 

		//scale drawing window
		StdDraw.setXscale(xmin, xmax); 
		StdDraw.setYscale(ymin, ymax);

		//number of subregions
		int num_subregions = sc.nextInt();

		//System.out.println("subregions: " + num_subregions);
		//System.out.println("xmin: " + xmin + " ymin: " + ymin);
		//System.out.println("xmax: " + xmax + " ymax: " + ymax);

		//drawing each subregion
		for (int i = 0; i < num_subregions; i++)
		{
			sc.nextLine(); //skip new line
			sc.nextLine(); //skip empty line

			String subregion = sc.nextLine(); //get subregion after skipped lines from file
			String region = sc.nextLine(); //get region after subregion from file

			int num_points = sc.nextInt(); //set number of coord to num_points
			double [] longs = new double [num_points]; //new array for longitude and latitude..
			double [] lats = new double [num_points]; //set as double array for long and lat

			for (int j = 0; j < num_points; j++) //for loop going through designated number of points from file
			{
				longs[j] = sc.nextDouble();
				lats[j] = sc.nextDouble();
			}

			//Color red = new Color (255, 0, 0);
			//StdDraw.setPenColor(red);

			//draw the polygon
			//StdDraw.filledPolygon(longs, lats);
			StdDraw.polygon(longs, lats); //draw polygons

		}

	}

}
















