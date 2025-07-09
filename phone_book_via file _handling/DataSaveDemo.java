
import java.io.*;
import java.util.*;

public class DataSaveDemo {

	public static void main(String[] args) {
		
		String name;
		String numberstr;
		Scanner sc = new Scanner(System.in);
		
		File nameFile = new File("nameFile.txt");
		File numberFile = new File("numberFile.txt");
		
		// creating new files
		try {
			nameFile.createNewFile();
			numberFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println();
			System.out.println("------- Menu -------");
			System.out.println("a -> add number\ns -> show names\nf -> find number\nq -> quit");
			System.out.print("Enter choice : ");
			char choice = '@';
			try {
				choice = sc.nextLine().toLowerCase().charAt(0);
			}catch(StringIndexOutOfBoundsException e){
		
			}
			
			System.out.println();
			
			switch(choice) {
				case 'a':
					System.out.print("Enter Name : ");
					name = sc.nextLine().trim();
					
					System.out.print("Enter phone number : ");
					numberstr = sc.nextLine();
					
					
					// formatting the number
					String new_number_str = "";
					for(int i = 0; i<numberstr.length();i++) {
						if(numberstr.charAt(0)=='+') {
							numberstr = numberstr.substring(3);
						}
						if(numberstr.charAt(i) == '-' || numberstr.charAt(i) == ' ' ) {
							
						}else {
							new_number_str += numberstr.charAt(i);
						}
					}
					
					// parse string to long
					long number = 0;
					try {
						number = Long.parseLong(new_number_str);						
					}catch(Exception e) {
						System.out.println("Enter valid number");
					}
					
					// writing data to the file
					try {
						FileWriter nameWriter = new FileWriter("nameFile.txt",true);
						nameWriter.append("NAME\tNUMBER\n");						
						nameWriter.append(name+"\t");
						nameWriter.append(number+"\n");
						
						nameWriter.close();
					
						FileWriter numberWriter = new FileWriter("numberFile.txt", true);
						numberWriter.append(number+"\n");
						numberWriter.close();
					} catch (IOException e) {
						System.out.print("we are facing some error : ");
						e.printStackTrace();
					}
					
					break;
				case 's':
					
					Set<String> set = new HashSet<>();
					try {
						Scanner sc_names = new Scanner(nameFile);
						
						while(sc_names.hasNextLine()) {
							String str = sc_names.nextLine();
							set.add(str);
						}
					 }catch (FileNotFoundException e) {
							e.printStackTrace();
					 }
					
					
					System.out.println(set);
					break;
					
				case 'f':
					System.out.print("Enter Name : ");
					String person = sc.nextLine();
					
					int countLine = 0;
					int flag = 0;
					boolean matchfound = false;
					
					try {
						Scanner sc_name = new Scanner(nameFile);
						Scanner sc_number = new Scanner(numberFile);
						
						// finding name in file
						while(sc_name.hasNextLine()) {
							countLine ++;
							String fetchedName = sc_name.nextLine();
							if(fetchedName.equals(person)) {
								System.out.println("-----------------------------");
								System.out.println("Person : " + fetchedName);
								person = "";
								matchfound = true;
								break;
							}	
						}
						if(!matchfound) {
							System.out.println("No match found !");
							break;
						}
						
						// finding number in the same line in which the name is located
						while(sc_number.hasNextLine()) {
			
							flag ++;
							
							String fetchedNumber = sc_number.nextLine();
							if(flag == countLine) {
								System.out.println("Phone no : " + fetchedNumber);
								System.out.println("-----------------------------");
								break;
							}
						}
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					break;
					
				case 'q': System.out.println("exited ! ");
					return;
					
				default: System.out.println("please enter valid choice !");
			}
		}
		
	}

}
