import java.io.*;
import edu.princeton.cs.algs4.StdRandom;

public class PrefTestCase 
{
	static String[] branch = {"A1", "A3", "A4", "A7", "AA", "A8"};


	public static void main(String[] args) throws IOException 
	{
		FileWriter writer = new FileWriter("test.csv");
		writer.append("CGPA,1,2,3,4,5,6");
		writer.append('\n');
		
		for(int i=0;i<216;i++)
		{
			String tempArray[] = branch.clone();
			StdRandom.shuffle(tempArray);
		
			writer.append(String.valueOf((StdRandom.uniform(5.000, 10.000))));
			writer.append(',');
			writer.append(tempArray[0]);
			writer.append(',');	
			writer.append(tempArray[1]);
			writer.append(',');	
			writer.append(tempArray[2]);
			writer.append(',');	
			writer.append(tempArray[3]);
			writer.append(',');	
			writer.append(tempArray[4]);
			writer.append(',');	
			writer.append(tempArray[5]);
			writer.append('\n');	
			
		}
		
		writer.flush();
		writer.close();
		
		/*Printing out the file*/
		BufferedReader in = new BufferedReader(new FileReader("test.csv"));
		String line;
		while((line =in.readLine())!=null)
		{
			System.out.println(line);
		}
	 
		in.close();
		
	}

}
