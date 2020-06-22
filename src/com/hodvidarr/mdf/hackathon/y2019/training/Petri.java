package com.hodvidarr.mdf.hackathon.y2019.training;

// DO not copy upper than this

import java.io.File;
import java.util.*;

/**
 * 		https://www.isograd.com/FR/solutionconcours.php?contest_id=36
 * Done in 47min35sec...
 * By Hodvidar
 */
public final class Petri {
	
	// Name of class to put back : IsoContest
	
	private static final boolean TESTING = false; // ### To change for submit ###
	
	/** If 'false' only response and Failure are written **/
	private static final boolean VERBOSE = false;
	
	private static final boolean ONE_TEST = false;
	private static final int ONE_TEST_NUMBER = 1;
	private static final int NUMBER_OF_TESTS = 6; // TO CHANGE
	private static final String INPUT_DIRECTORY = "petri_input"; // TO CHANGE

	public static void main(String[] args) throws Exception 
	{
		Petri r = new Petri();
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
	
	private static void printIfVerbose(String s)
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
		int size = 0;
		String[][] tab = null;
		while(sc.hasNextLine()) 
		{
			i++;
			line = sc.nextLine();
			printIfVerbose("i="+i+" line:"+line);
			if(i == 1)
			{
				// do stuff
				size = Integer.parseInt(line);
				tab = new String[size][size];
				continue;
			}
			
			// do other stuff
			int j = 0;
			for(char c : line.toCharArray())
			{
				j++;
				tab[i-2][j-1] = ""+c;
			}
		}
		
		printIfVerbose("Searching...");
		
		while(true)
		{
			// break if no "." anymore
			boolean stillPoint = false;
			for(i = 0; i < size; i++)
			{
				if(stillPoint)
					break;
				for(int j = 0; j < size; j++)
				{
					if(stillPoint)
						break;
					if(tab[i][j].equals("."))
						stillPoint = true;
				}
			}
			if(!stillPoint)
				break;
			
			// 1) First propagation
			for(i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					String s = tab[i][j];
					if(ignore(s))
						continue;
					
					// propagate N
					if(i > 0)
					{
						if(tab[i-1][j].equals("."))
							tab[i-1][j] = "o"+s;
						else if(tab[i-1][j].startsWith("o"))
							if(!tab[i-1][j].substring(1, 2).equals(s))
								tab[i-1][j] = "=";
					}
					// propagate W
					if(j > 0)
					{
						if(tab[i][j-1].equals("."))
							tab[i][j-1] = "o"+s;
						else if(tab[i][j-1].startsWith("o"))
							if(!tab[i][j-1].substring(1, 2).equals(s))
								tab[i][j-1] = "=";
					}
					// propagate S
					if(i < size-1)
					{
						if(tab[i+1][j].equals("."))
							tab[i+1][j] = "o"+s;
						else if(tab[i+1][j].startsWith("o"))
							if(!tab[i+1][j].substring(1, 2).equals(s))
								tab[i+1][j] = "=";
					}
					// propagate N
					if(j < size-1)
					{
						if(tab[i][j+1].equals("."))
							tab[i][j+1] = "o"+s;
						else if(tab[i][j+1].startsWith("o"))
							if(!tab[i][j+1].substring(1, 2).equals(s))
								tab[i][j+1] = "=";
					}
				}
			}
			
			printIfVerbose("--------------");
			printTab(tab, size);
			
			// 2) Confirm propagation or "="
			for(i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					String s = tab[i][j];
					if(s.startsWith("o"))
						tab[i][j] = tab[i][j].substring(1, 2); // remove the "o";
				}
			}
			
			printIfVerbose("--------------");
			printTab(tab, size);
		}
		
		printIfVerbose("Tab:");
		printTab(tab, size);
		
		// count biggest colonie
		Map<Integer, Integer> colonies = new HashMap<>();
		for(i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				String s = tab[i][j];
				try {
					Integer n = Integer.parseInt(s);
					Integer val = colonies.get(n);
					if(val == null)
						val = 0;
					val++;
					colonies.put(n, val);
				} catch (Throwable e) {
					// nothing
				}
			}
		}
		int max = 0;
		for(Integer n : colonies.values())
		{
			if(n > max)
				max = n;
		}
		
		String result = ""+max;
		System.out.println(result);
		sc.close();
		return result;
	}
	
	private static boolean ignore(String s)
	{
		if(s.equals("#"))
			return true;
		if(s.equals("."))
			return true;
        return s.startsWith("o");
    }
	
	private static void printTab(String[][] tab, int size)
	{
		if(!VERBOSE)
			return;
		for(int i = 0; i < size; i++)
		{
			printIfVerbose(""+Arrays.deepToString(tab[i]));
		}
	}
	
}