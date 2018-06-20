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

public class Purple
{

	public static void main (String[] args) throws FileNotFoundException
 	{
 		if (args.length < 1) //if not enough(proper) inputs
 		{
 			System.out.println("Usage: java White <region-symbol>"); //error message
 			System.exit(-1);
 		}
		Scanner sc = new Scanner(new File("purple/" + args[0] + ".txt")); //call file from input

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

		//drawing each subregion
		for (int i = 0; i < num_subregions; i++)
		{
			sc.nextLine(); //skip new line
			sc.nextLine(); //skip empty line

			//get subregion and region from next line in file
			String subregion = sc.nextLine();
			String region = sc.nextLine();

			int num_points = sc.nextInt(); //get number of times to read file from next line
			double [] longs = new double [num_points]; //save longitude and latitude in double array
			double [] lats = new double [num_points];

			for (int j = 0; j < num_points; j++) //drawing subregions
			{
				longs[j] = sc.nextDouble();
				lats[j] = sc.nextDouble();
			}

			Scanner sck = new Scanner(new File("purple/" + region + args[1] + ".txt")); //use region and argument to call file

			sck.nextLine(); //skip line
			
			int red = 0; //set red, green, blue to 0
			int green = 0; //use these totals for final formula
			int blue = 0; 

			while (sck.hasNext())
			{
				double rojo = 0.0; //set rojo, azul and verde to 0
				double azul = 0.0; //set to 0.0 for double
				double verde = 0.0;
				int total = 0;

				String a = sck.nextLine();
				String [] comp = a.split(","); //split array by comma

				//use .contains for name that has portion of name
				//set to uppercase
				if (comp[0].toUpperCase().contains(subregion.toUpperCase()) || subregion.toUpperCase().contains(comp[0].toUpperCase()))
				{
					verde = Double.parseDouble(comp[3]); //set r,g,b to respective positions in array
					azul = Double.parseDouble(comp[2]);
					rojo = Double.parseDouble(comp[1]);

					total += verde + azul + rojo; //use += because it is a double and an int (conflicting data types)

					green = (int) ((verde / total) * 255);  //use formula 
					blue = (int) ((azul / total) * 255); //set red, green and blue to int 
					red = (int) ((rojo / total) * 255); 

					break; //break out of loop
				}

			}

			sck.close(); //close scanner

			Color pcolor = new Color(red, green, blue); //set pcolor in separate variable

			StdDraw.setPenColor(pcolor); //call pcolor in setPenColor
			
			//draw polygon
			StdDraw.filledPolygon(longs, lats);

		}

	}

}
