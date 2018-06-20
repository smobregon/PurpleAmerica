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

public class RedBlue
{
	public static void main (String[] args) throws FileNotFoundException
 	{
 		if (args.length < 1) //if incorrect input
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

		//drawing each subregion
		for (int i = 0; i < num_subregions; i++)
		{
			sc.nextLine(); //skip new line
			sc.nextLine(); //skip empty line

			String subregion = sc.nextLine();//get subregion

			String region = sc.nextLine(); //get region

			int num_points = sc.nextInt(); //set num_points 
			double [] longs = new double [num_points];
			double [] lats = new double [num_points];

			for (int j = 0; j < num_points; j++)
			{
				longs[j] = sc.nextDouble();
				lats[j] = sc.nextDouble();
			}

			Scanner sck = new Scanner(new File("purple/" + region + args[1] + ".txt")); //call file name from 'region' and input

			sck.nextLine(); //skip line
			int rojo = 0; //set r, g, b colors to 0
			int azul = 0;
			int verde = 0;
			while (sck.hasNext()) //loop going through file as long as there is still something to read from
			{
				String a = sck.nextLine(); 
				String [] comp = a.split(","); //split array by commas 

				//use contains for file/state that has some portion of the name in it
				//change to uppercase

				if (comp[0].toUpperCase().contains(subregion.toUpperCase()) || subregion.toUpperCase().contains(comp[0].toUpperCase()))
				{
					verde = Integer.parseInt(comp[3]); //set r, g, b equal to positions in array respecitvely
					azul = Integer.parseInt(comp[2]);
					rojo = Integer.parseInt(comp[1]);
					break; //break out of loop
				}

			}

			sck.close(); //close scanner

			//if statement for if red is over blue and green

			if (rojo> azul && rojo > verde)
			{
				StdDraw.setPenColor(Color.RED); //set pen color to respective greater color
			}
			//else if for blue being over green and red
			else if(azul > rojo && azul > verde)
            {
                StdDraw.setPenColor(Color.BLUE);
            }
            //else if for green being over red and blue
            else if(verde > rojo && verde > azul)
            {
                StdDraw.setPenColor(Color.GREEN);
            }

            else
            {
                StdDraw.setPenColor(Color.BLACK); // any other case
            }
			
			//draw the polygon
			StdDraw.filledPolygon(longs, lats);

		}

	}

}
















