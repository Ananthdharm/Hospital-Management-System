import java.util.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import java.io.FileWriter;	
import java.io.FileReader;	
import java.lang.*;
import java.io.FileInputStream;
import java.io.PrintWriter;	
import java.io.BufferedWriter;	
import java.lang.String;

public class main{
	public static void main(String[] args) throws FileNotFoundException,IOException{

		Scanner scan=new Scanner(System.in);
		outer:
		while(true)
		{
			System.out.println("**********************HOSPITAL MANAGEMENT SYSTEM**********************");
			System.out.println("1. About Doctors");
			System.out.println("2. About Patients");
			System.out.println("3. About Rooms");
			System.out.println("4. Exit");
			System.out.println("**********************************************************************");
			System.out.println("Please enter your choice:");
			int choice = scan.nextInt();
			
			switch(choice){
				case 1:
					while(true){
						System.out.println("\nWelcome to the Doctor's Page");
						System.out.println("1. View Doctor\n2.List all Docotrs\n3. Add a new doctor record\n4. Delete a doctor record\n5. Go to Home Page");
						System.out.println("Enter your choice: ");
						int ch1 = scan.nextInt();
						System.out.println("\n");
						switch(ch1){ 
							case 1:
								//Viewing doctors and details
                                try {
                                    callerclass.search("doctor_details.txt");
                                    }
                                catch(IOException e) {e.printStackTrace();}
								break;
                            case 2:
                                //Viewing the list of doctors
                                String line=null;
                                System.out.println("Here are the details about all the doctors:\n");
                                System.out.println("Note: Data is in the following manner: ID, Name, Age, Type of Doctor\n");
                                try{
                                    FileReader fr=new FileReader("doctor_details.txt");
                                    BufferedReader br= new BufferedReader(fr);
                                    while((line=br.readLine())!=null){
                                        String[] result = line.split("\\|");
                                        if (!result[0].startsWith("*")) {
                                        System.out.println((result[0]) +" " + (result[1]) + " " + (result[2])+ " " + result[3]);
                                        }
                                    }
                                    br.close();
                                }
                                catch(IOException ex){
                                    System.out.println("Error encountered!");
                                }
                                break; 
							case 3:
								//Adding doctor details
                                try{
								    callerclass.insert("doctor_details.txt");
                                   }
                                catch(IOException e) {e.printStackTrace();}
                                System.out.println("Doctor Details has been added Successfully");
								break;
							case 4:
								//Deleting doctor details
                                try{
                                    callerclass.remove("doctor_details.txt");
                                    }
                                catch(IOException e) {e.printStackTrace();}
								break;
							case 5:
								//Getting back to home page 
								continue outer;					
							default:
								System.out.println("Invalid Option");
						}
					}
				case 2: 
					while(true){
						System.out.println("\nWelcome to the Patient's Page");
						System.out.println("1. View Patient\n2. Add a new Patient\n3. Delete a record\n4. Go to Home Page");
						System.out.println("Enter your choice: ");
						int ch2 = scan.nextInt();
						switch(ch2){ 
							case 1:
								//Viewing patients and details
                                try{
								callerclass.search("patient_details.txt");
                                }
                                catch(IOException e) {e.printStackTrace();}
								break;
							case 2:
								//Adding patients and details
                                try{
                                    String Type = callerclass.insert("patient_details.txt");
                                    int[] avail = new int[3];
                                    BufferedReader br = new BufferedReader(new FileReader("availability_of_rooms.txt"));
                                    String s;
                                     while((s = br.readLine())!=null){
                                        String[] result = s.split("\\|");
                                        avail[0] = Integer.valueOf(result[0]);
                                        avail[1] = Integer.valueOf(result[1]);
                                        avail[2] = Integer.valueOf(result[2]);
                                     }
                                     br.close();
                                    if (Type.equalsIgnoreCase("general")){
                                            avail[0]-=1;
                                        if (avail[0]==0){
                                            System.out.println("General rooms filled!");
                                        }
                                    }
                                    else if (Type.equalsIgnoreCase("icu")){
                                            avail[1]-=1;
                                        if (avail[1]==0){
                                            System.out.println("ICU rooms filled!");
                                        }
                                    }
                                    else if (Type.equalsIgnoreCase("vip")){
                                            avail[2]-=1;
                                        if (avail[2]==0){
                                            System.out.println("VIP rooms filled!");
                                        }
                                    }
                                    PrintWriter pw = new PrintWriter(new FileOutputStream(new File("availability_of_rooms.txt"),true));
                                    String b = avail[0]+"|"+avail[1]+"|"+avail[2]+"|"+"$";
                                    pw.println(b);
                                    pw.close();
                                    System.out.println("Patient Details has been added Successfully");


                                }
                                catch(IOException e) {e.printStackTrace();}
								break;
							case 3:
								//Deleting patients and details
                                try{
								String Type = callerclass.remove("patient_details.txt");
                                int[] avail = new int[3];
                                BufferedReader br = new BufferedReader(new FileReader("availability_of_rooms.txt"));
                                String s;
                                while((s = br.readLine())!=null){
                                    String[] result = s.split("\\|");
                                    avail[0] = Integer.valueOf(result[0]);
                                    avail[1] = Integer.valueOf(result[1]);
                                    avail[2] = Integer.valueOf(result[2]);
                                    }
                                br.close();
                                if (Type.equalsIgnoreCase("general")){avail[0]+=1;}
                                else if (Type.equalsIgnoreCase("icu")){avail[1]+=1;}
                                else if (Type.equalsIgnoreCase("vip")){avail[2]+=1;}
                                PrintWriter pw = new PrintWriter(new FileOutputStream("availability_of_rooms.txt",true));
                                String b = avail[0]+"|"+avail[1]+"|"+avail[2]+"|"+"$";
                                pw.println(b);
                                pw.close();
                                }
                                catch(IOException e) {e.printStackTrace();}
								break;
							case 4:
								//Getting back to home page 
								continue outer;	
							default:
								System.out.println("Invalid Option");
						}
					}
				case 3:
						while(true){
							System.out.println("\nWelcome to the Room's Page");
							System.out.println("1. View types of rooms\n2. Check room availability\n3. Go to Home Page\n4.Exit");
							System.out.println("Enter your choice: ");
							int ch3 = scan.nextInt();
							switch(ch3){ 
								case 1:
									//Viewing rooms and costs 
									// roomObj.room_view();
                                    String name = "" ,rate = "",facilities="", s;
                                    try{
                                    BufferedReader br = new BufferedReader(new FileReader("details_of_rooms.txt"));
                                    String s1;
                                    System.out.println("Here are the details about all the rooms:\n");
                                    while((s1 = br.readLine())!=null)
                                    {
                                        String[] result = s1.split("\\|");
                                        name = result[0];
                                        rate = result[1];
                                        facilities = result[2];
                                        System.out.println( name + "\n" + rate + "\n" + facilities);
                                        System.out.println("\n\n\n");
                                    }
                                    br.close();
                                    }
                                    catch (FileNotFoundException ex)  
                                    {
                                        // insert code to run when exception occurs
                                    }
									break;
								case 2:
									//Check room availability
                                    try{
                                    BufferedReader br1 = new BufferedReader(new FileReader("availability_of_rooms.txt"));
                                    String s2;
                                    System.out.println("Here are the details about availability of rooms:\n");
                                    String[] result = new String[10];
                                    while((s2 = br1.readLine())!=null)
                                    {
                                        result = s2.split("\\|");
                                        // System.out.println( "General Wards" + " : " + Integer.valueOf(result[0]));
                                        // System.out.println( "ICU Wards" + " : " + Integer.valueOf(result[1]));
                                        // System.out.println( "VIP Wards" + " : " + Integer.valueOf(result[2]));
                                    }
                                    // String[] result1 = result.split("\\|");
                                    System.out.println( "General Wards" + " : " + result[0]);
                                    System.out.println( "ICU Wards" + " : " + result[1]);
                                    System.out.println( "VIP Wards" + " : " + result[2]);
                                    br1.close();
                                    }
                                    catch (FileNotFoundException ex)  
                                    {
                                    // insert code to run when exception occurs
                                    }
									break;
								
								case 3:
									//Getting back to home page 
									continue outer;	
								case 4:
									System.exit(0);
									break;
								default:
									System.out.println("Invalid Option");
							}
						}
				case 4: 
					System.exit(0); 
					break;
			}
		}	}
		
	
}
