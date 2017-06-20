import java.io.*;
import java.util.*;

public class Predictor 
{
	static int capacity[] = {10, 10, 10, 10, 10, 10};
	static List<StudentModel> listOfStudents = new ArrayList<StudentModel>();
	static Branches programsOffered = new Branches(capacity);
	
	public static void createList() throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("testcsv"));
		in.readLine();
		String line;
		while((line = in.readLine())!=null)
		{
			String[] temp = line.split(",");
			listOfStudents.add(new StudentModel(temp[0],Double.parseDouble(temp[1]), temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]));
		}

		System.out.println("***Running pre diagnostics***");
		System.out.println("Removed entries : "+ preDiagnostics());
		System.out.println("***End of pre diagnostics***");
		
		/*Sorting w.r.t to CGPA of students*/
		Collections.sort(listOfStudents, StudentModel.sortHelper());
		
		
		/*Printing the list*/
		for(StudentModel t : listOfStudents)
		{
			System.out.printf("%s,%f,%s,%s,%s,%s,%s,%s\n",t.studentMailID ,t.cgpa, t.branchPref[0], t.branchPref[1], t.branchPref[2], t.branchPref[3], t.branchPref[4], t.branchPref[5]);
		}
		in.close();
	}
	
	public static int preDiagnostics()
	{
		int count = listOfStudents.size();
		List<StudentModel> tempListOfStudents = new ArrayList<StudentModel>();
		 String[] branch = {"A1", "A3", "A4", "A7", "AA", "A8"};
		 Arrays.sort(branch);
		 
		/*All branches in the preference be unique*/
		for(StudentModel t : listOfStudents)
		{
			String tempBranch[] = new String[6];
			for(int i=0;i<6;i++)
				tempBranch[i] = t.branchPref[i];
			Arrays.sort(tempBranch);
			if(Arrays.equals(branch, tempBranch))
			{
				tempListOfStudents.add(t);
				count--;
			}
			else
			{
				System.out.print("Removed : ");
				System.out.printf("%s,%f,%s,%s,%s,%s,%s,%s\n",t.studentMailID ,t.cgpa, t.branchPref[0], t.branchPref[1], t.branchPref[2], t.branchPref[3], t.branchPref[4], t.branchPref[5]);
			}
		}
		
		listOfStudents = tempListOfStudents;
		
		
		
		return count;
	}
	
 	public static void allocate()
	{
		for(StudentModel student : listOfStudents)
		{
			while(student.topPref<=6 && student.isAllocated() == false)
			{
				if(Branches.allBranchesFull() == true)
				{
					student.allocationFlag = -1;
					break;
				}
				
				/*allocate the branch is capacity is not full*/
				if(  Branches.getSizeOfBranch(student.peekTopPref()) < Branches.getBranch(student.peekTopPref()).length  )
				{	
					Branches.add(student, student.peekTopPref());
				}
				
				/*if capacity is full, move to next preference*/
				else
				{ 
					if(student.canPopTopPref())
						student.popTopPref();
				}				
			}
		}
		Branches.printAllocatedStudents();
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		createList();
		allocate();
		Branches.showCutOff();
	}

	
}
