package survey;

import java.io.*;
import java.util.Scanner;

public class Survey 
{
	private int age;
	private String filePath = "C:\\Users\\timot\\eclipse-workspace\\Lab7_TimBogun\\src\\survey\\";
	private String fileName;
	private String stateAbbreviation;
	private String[][] states;
	private String state;
	private int zip;
	
	private Scanner sc = new Scanner(System.in);
	private DataInputStream dataInputStream;

	
	public static void main(String[] args) {
        Survey survey = new Survey();
        try {
            survey.getAge();
            survey.getInputFileName();
            survey.readBinaryFile();
            survey.getState();
            survey.getZip();
            System.out.println("Your age: " + survey.age);
            System.out.println("Your state and zip: " + survey.state + " " + survey.zip);
            System.out.println("Your participation has been valuable. ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.print("Thank you for your time.");
        }
    }
	
	public int getAge() 
	{
		String input = "";
		try {
			System.out.print("Please enter your age or 'q' to quit: ");
			input = sc.nextLine();
			if (input.equals("q"))
			{
				System.out.println("You've chosen not to participate. Thank you for your time.");
				System.exit(0);
			}
			else
				age = Integer.parseInt(input);
		} catch (NumberFormatException e) {
	        System.out.println("You've chosen not to participate. Thank you for your time.");
	        System.exit(0);
		}
		return age;
	}
	public boolean getInputFileName() throws IOException
	{
		boolean bool = false;
		while (!bool)
		{
			System.out.print("Please enter the name of your file or enter quit: ");
			fileName = sc.nextLine();
			if (fileName.equals("quit"))
			{
				break;
			}
			try {
				dataInputStream = new DataInputStream(new FileInputStream(filePath+fileName));
				bool = true;
			} catch(FileNotFoundException e) {
				System.out.println("Unable to open file, reenter the file name.");
			} 
		}
		return bool;
	}
	public void readBinaryFile() throws IOException
	{
		dataInputStream = new DataInputStream(new FileInputStream(filePath+fileName));
		states = new String[50][2];
		
		for (int i = 0; i < 50; i++) 
		{
	        states[i][0] = dataInputStream.readUTF();
	        states[i][1] = dataInputStream.readUTF();
		}
	}
	public String getState() 
	{
		boolean validState = false;
        while (!validState) 
        {
			try {
		        
		            System.out.print("Please enter the 2 letter state abbreviation: ");
		            stateAbbreviation = sc.nextLine().trim().toUpperCase();
		            for (int i = 0; i < 50; i++) 
		            {
		                if (stateAbbreviation.equals(states[i][0])) 
		                {
		                    validState = true;
		                    state = states[i][1];
		                    break;
		                }
		            }
		            if (!validState) {
		                throw new InvalidStateNameException();
		            }
		        } catch (InvalidStateNameException e) {
	            System.out.println(e.getMessage());
	        }
        }
		return stateAbbreviation;
	}
	public int getZip()
	{
		boolean validZip = false;
		while (!validZip)
		{
			try {
				System.out.print("Please enter your zip code: ");
				String input = sc.nextLine();
				if (input.length() == 5)
				{
					zip = Integer.parseInt(input);
					validZip = true;
				} else
					System.out.println("Invalid zip code; please reenter.");
			} catch (NumberFormatException e) {
				System.out.println("Invalid zip code; please reenter.");
			}
		}
		return zip;
	}
}
