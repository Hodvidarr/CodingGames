package com.hodvidar.mdf.hackathon.y2019.creditagricole;

import java.io.File;
import java.util.*;

/**
 * 		https://www.isograd.com/FR/solutionconcours.php
 * Done in 8min01sec...
 * By Hodvidar
 */
public class SalleDeSport 
{

		// Name of class to put back : IsoContest
		
		private static final boolean TESTING = false; // ### To change for submit ###
		
		/** If 'false' only response and Failure are written **/
		private static final boolean VERBOSE = false;
		
		private static final boolean ONE_TEST = false;
		private static final int ONE_TEST_NUMBER = 1;
		private static final int NUMBER_OF_TESTS = 4; // TO CHANGE
		private static final String INPUT_DIRECTORY = "salle-de-sport_input"; // TO CHANGE

		public static void main(String[] args) throws Exception 
		{
			SalleDeSport r = new SalleDeSport();
			if(!TESTING)
			{	
				r.test(null);
				return;
			}
			int i;
			int max;
			if(ONE_TEST)
			{
				i = ONE_TEST_NUMBER;
				max = ONE_TEST_NUMBER;
			}
			else
			{
				i = 1;
				max = NUMBER_OF_TESTS;
			}
			for(; i <= max; i++)
			{
				System.err.println("\n--- TEST n°"+i+" --");
				String result = r.test("resources\\"+INPUT_DIRECTORY+"\\input"+i+".txt");
				// --- CHECKING ---
				File file2 = new File("resources\\"+INPUT_DIRECTORY+"\\output"+i+".txt");
				// Scanner sc = new Scanner(System.in);
				Scanner sc2 = new Scanner(file2);
				String line2 = sc2.nextLine();
				System.err.println("Solution is: \n"+line2);
				if(result.equals(line2))
					System.err.println("SUCCESS!");
				else
					System.err.println("FAILURE! found: "+result);
				sc2.close();
			}
		}
		
		public static void printIfVerbose(String s)
		{
			if (VERBOSE)
				System.err.println(s);
		}
		
		

		private String test(String inputFile) throws Exception 
		{
			String  line = "";
			Scanner sc;
			if(TESTING)
			{
				File file = new File(inputFile);
				sc = new Scanner(file);
			}
			else
			{
				sc = new Scanner(System.in);
			}
			// --- INPUT ---
			printIfVerbose("DEBUGGING");
			int i = 0;
			int min = 0;
			int max = 0;
			int div = 0;
			String[][] tab = null;
			while(sc.hasNextLine()) 
			{
				i++;
				line = sc.nextLine();
				printIfVerbose("i="+i+" line:"+line);
				if(i == 1)
				{
					// do stuff
					min = Integer.parseInt(line);
					continue;
				}
				if(i == 2)
				{
					// do stuff
					max = Integer.parseInt(line);
					continue;
				}
				if(i == 3)
				{
					// do stuff
					div = Integer.parseInt(line);
					continue;
				}
				
				// do other stuff
				
			}
			
			printIfVerbose("Searching...");
			for(i = min; i <= max; i++)
			{
				if(i%div == 0)
				{
					System.out.println(""+i);
					sc.close();
					return ""+i;
				}
			}
			
			printIfVerbose("Searching...");
			
			
			String result = "";
			System.out.println(result);
			sc.close();
			return "-1";
		}
		
	}