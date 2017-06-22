/*A class model representing a student with its important details*/

import java.util.Comparator;

public class StudentModel 
{
	public double cgpa;
	public String studentMailID;
	public String branchPref[] = new String[6];
	public int topPref;
	public int allocationFlag;
	
	public StudentModel(String id, double parseDouble, String string1, String string2, String string3, String string4,
			String string5, String string6) 
	{
		this.studentMailID = id;
		this.cgpa = parseDouble;
		branchPref[0] = new String(string1);
		branchPref[1] = new String(string2);
		branchPref[2] = new String(string3);
		branchPref[3] = new String(string4);
		branchPref[4] = new String(string5);
		branchPref[5] = new String(string6);
		topPref = 0;
		allocationFlag=0;
	}

	
	public boolean canPopTopPref()
	{
		if(topPref>=5)
			return false;
		else
			return true;
	}
	
	
	public void popTopPref()
	{
			++topPref;
	}
	
	public String peekTopPref()
	{
		return branchPref[topPref];
	}
	
	public void allocate()
	{
		allocationFlag=1;
	}

	public boolean isAllocated()
	{
		if(allocationFlag==0)
			return false;
		else
			return true;
	}


	/*Returns a comparator*/
	public static StudentCompare sortHelper()
	{
		return new StudentCompare();
	}

	/*Comparator class for sorting with respect to student's CGPA*/
	public static class StudentCompare implements Comparator<StudentModel>
	{
		public int compare(StudentModel o1, StudentModel o2) 
		{
			return (-1)*(Double.compare(o1.cgpa, o2.cgpa));
		}
	}
	
}
