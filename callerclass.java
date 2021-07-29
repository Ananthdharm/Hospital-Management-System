import java.io.*;
import java.util.Scanner;

public class callerclass
{
	public static int count;
	public static final int[] Address_list = new int[100];
	public static final String[] usn_list = new String[100];
	public static Scanner s = new Scanner(System.in);

	public static void create_index(String filepath)throws IOException,ArrayIndexOutOfBoundsException
	{
		count = -1;
		long pos;
		RandomAccessFile file = new RandomAccessFile(filepath, "r");	
		pos = file.getFilePointer();
		String s ;
		while((s = file.readLine())!=null)
		{
			String[] result = s.split("\\|");
			count++;
			usn_list[count] = result[0];
			Address_list[count] = (int)pos;
			pos=file.getFilePointer();	
		}
		file.close();
		sort_index();
	}

	public static void sort_index()throws IOException
	{
		for(int i=0;i<=count;i++)
		{
			for(int j=i+1;j<=count;j++)
			{
				if(usn_list[i].compareTo(usn_list[j])>0)
				{
					String temp = usn_list[i];
					usn_list[i] = usn_list[j];
					usn_list[j] = temp;
					int temp1 = Address_list[i];
					Address_list[i]=Address_list[j];
					Address_list[j]=temp1;
				}
			}
		}
	}

	public static String insert(String filepath)throws IOException,FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filepath),true));
		System.out.println("Enter ID,Name,Age and Type ");
		String ID = s.nextLine();
		String name = s.nextLine();
		String Age = s.nextLine(); 
		String Type = s.nextLine();
		String b = ID+"|"+name+"|"+Age+"|"+Type+"|"+"$";
		pw.println(b);
		pw.close();
		create_index(filepath);
		return Type;
	}

	public static void search(String filepath)throws IOException
	{
		int pos;
		System.out.println("Enter the ID to be searched");
		String key = s.nextLine();
		pos = search_index(key);
		
		if(pos!=-1)
			display_record(pos, filepath);
		else
			System.out.println("Record not found");
	}
	
	public static int search_index(String key)
	{	
		int low = 0, high = count, mid = 0;
		while(low <= high)
		{
			mid = (low + high)/2;
			if(usn_list[mid].equals(key))
				return mid;
			
			if(usn_list[mid].compareTo(key)>0)
				high = mid - 1;
			
			if(usn_list[mid].compareTo(key)<0)
				low = mid + 1;
		}
		return -1;
	}

	public static void display_record(int pos, String filepath)throws IOException
	{
		RandomAccessFile file = new RandomAccessFile(filepath, "r");
		
		int address = Address_list[pos];
		String usn="",sem="",branch="",name="";
		
		file.seek(address);			
		String s = file.readLine();
		
		while(s!=null)
		{
			String[] result = s.split("\\|");
			usn = result[0];
			name = result[1];
			sem = result[2];
			branch = result[3];
			System.out.println("\nRecord Details");
			System.out.println("ID: " + usn);
			System.out.println("Name: " + name);
			System.out.println("Age: " + sem);
			System.out.println("Type: " + branch);
			break;
		}
		file.close();
	}

	public static String remove(String filepath)throws IOException
	{
		System.out.println("Enter the ID to be deleted");
		String key = s.nextLine();
		
		int pos = search_index(key);
		if(pos != -1)
		{
			delete_from_file(pos, filepath);
			create_index(filepath);
		}
		else
			System.out.println("Record not found");

		return "general";
	}
 
	public static void delete_from_file(int pos, String filepath)throws IOException
	{
		display_record(pos, filepath);
		
		RandomAccessFile file = new RandomAccessFile(filepath, "rw");
		System.out.println("Are you sure you want to delete? (Y/N)");
		String ch = s.nextLine();
		
		if(ch.equalsIgnoreCase("y"))
		{
			int address= Address_list[pos];
			String del_ch="*";
			file.seek(address);
			file.writeBytes(del_ch);
			System.out.println("Record is deleted");
		}
		file.close();
	}
}