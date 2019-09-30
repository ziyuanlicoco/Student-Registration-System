import java.io.*;
import java.util.*;

public class Registration {
	static ArrayList<Course> allcourses = new ArrayList<>(); //declare arraylist that stores all courses
	static ArrayList<Student> students = new ArrayList<>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		String username;
		String password;
		String menu;
		int choice;
		File f = new File ("Courses.ser");//check if this is the first time using the system
		if (f.exists() == false) {//first time, read course from csv fild and student is empty
			allcourses = readCSVFile();	
			User.allcourses = allcourses;
			students = new ArrayList<Student>();
		} else { //not first time, deserialize
			allcourses = deserial_course();
			User.allcourses = allcourses;
			students = deserial_student();
		}
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		User.allcourses = allcourses;
		
		System.out.println("Are you an admin or student?");//check if admin or student
		String ans = input.readLine();
		if (ans.equalsIgnoreCase("admin")) {
			Admin a = new Admin();
			do {
				System.out.println("Please enter your name and password: ");//check username and password
				username = input.readLine();
				password = input.readLine();
			} while (!username.equals("Admin") || !password.equals("Admin001")) ;//reenter if not correct
		
			do{
				System.out.println("Press CM for Course Management menu");	//let user choose menu option
				System.out.println("Press R for Reports menu");	
				System.out.println("Press Q for Exit");	
				menu = input.readLine();
				if (menu.equalsIgnoreCase("CM")) {
					a.menuCM();
					do{
						choice = Integer.parseInt(input.readLine());
						
						if (choice == 1) {//execute corresponding method based on user's choice
							a.createCourse();
							a.menuCM();
						} else if (choice == 2) {
							a.deleteCourse();
							a.menuCM();
						} else if (choice == 3) {
							a.editCourse();
							a.menuCM();
						} else if (choice == 4) {
							a.viewCourse();
							a.menuCM();
						} else if (choice == 5) {
							a.regiserStu();
							a.menuCM();
						} else if (choice == 6) {
						
						} else 
							System.out.println("Invalid input");
					} while (choice != 6);//exit cm menu when user chooses 6
			
				} else if (menu.equalsIgnoreCase("R")) {
					a.menuRe();
					do {
						choice = Integer.parseInt(input.readLine());
						
						if (choice == 1) {
							a.viewAllCourse();
							a.menuRe();
						} else if  (choice == 2) {
							a.viewFullCourse();
							a.menuRe();
						} else if (choice == 3) {
							a.listFullCourse();
							a.menuRe();
						} else if (choice == 4) {
							a.viewStudentInCourse();
							a.menuRe();
						} else if (choice == 5) {
							a.viewAStudent();
							a.menuRe();
						} else if (choice == 6) {
							a.sortCourses();
							a.menuRe();
						} else {
							System.out.println("Invalid input");
						}
					} while (choice != 7);//exit r menu
				}		
			} while (!menu.equalsIgnoreCase("Q"));//exit system and wait for serialize
			
		} else if (ans.equalsIgnoreCase("student")) {
			Student s = new Student();
			System.out.println("Please enter your username and password: ");
			String stuUserName = input.readLine();
			String stuPassWord = input.readLine();
			checkPassword(stuUserName,stuPassWord);
			int m = 0;
			for (int i = 0; i<students.size();i++) {
				if (stuUserName.equals(students.get(i).getUsername())&&stuPassWord.equals(students.get(i).getPassword())){
					m = i;
				}
			}
			String stuFirstName = students.get(m).getFirstName();
			String stuLastName = students.get(m).getLastName();//use username password to get student first name and last name
			do{
				System.out.println("Press CM for Course Management menu");		
				System.out.println("Press Q for Exit");	
				menu = input.readLine();
				if (menu.equalsIgnoreCase("CM")) {
					s.menuCM();
					do{
						choice = Integer.parseInt(input.readLine());
						if (choice == 1) {
							s.viewAllCourse();
							s.menuCM();
						} else if (choice == 2) {
							s.viewNotFullCourse();
							s.menuCM();
						} else if (choice == 3) {//take input and put the inputs as methods parameters
							System.out.println("Please enter name of the course: ");
							String course = input.readLine();
							System.out.println("Please enter section of the course: ");
							String section = input.readLine();
							System.out.println("Please enter your first name: ");
							String firstName = input.readLine();
							System.out.println("Please enter your last name: ");
							String lastName = input.readLine();
							while (firstName.equalsIgnoreCase(stuFirstName)== false || lastName.equalsIgnoreCase(stuLastName) == false) {//have to enter correct name 
								System.out.println("Name does not match. Please reenter.");
								firstName = input.readLine();
								lastName = input.readLine();
							}
							for(int i = 0; i<allcourses.size();i++) {
								int x = allcourses.get(i).getNameList().size();
								if (allcourses.get(i).getCourseName().equalsIgnoreCase(course) ) {
									for(int n =0; n<allcourses.get(i).getNameList().size();n++) {
										if (firstName.equalsIgnoreCase(allcourses.get(i).getNameList().get(n).getFirstName()) && lastName.equalsIgnoreCase(allcourses.get(i).getNameList().get(n).getLastName())) {
											System.out.println("You are already in this course!");//cannot register a class you are already in
											x = n;
											break;
										} 
									}
									if (x ==allcourses.get(i).getNameList().size())
									s.registerCourse(course, section, firstName, lastName);
								
									
								}
							}
							
							s.menuCM();
						} else if (choice == 4) {//take input and delete a registered course
							System.out.println("Please enter name of the course: ");
							String course = input.readLine();
							System.out.println("Please enter your first name: ");
							String firstName = input.readLine();
							System.out.println("Please enter your last name: ");
							String lastName = input.readLine();
							while (firstName.equalsIgnoreCase(stuFirstName)== false || lastName.equalsIgnoreCase(stuLastName) == false) {
								System.out.println("Name does not match. Please reenter.");
								firstName = input.readLine();
								lastName = input.readLine();
							}
							s.withdrawCourse(course,firstName,lastName);
							s.menuCM(); //doesnot have time to do this but
										//could have checked like choice== 3 whether the student is in this course or not
										//because we cannot delete from a course not registered in
						} else if (choice == 5) {
							System.out.println("Please enter your username: ");
							String userName = input.readLine();
							while (userName.equals(stuUserName)== false) {
								System.out.println("Username does not match. Please reenter.");//have to input the correct username again then check
								userName = input.readLine();
							}
							s.viewRegisteredCourse(userName);
							s.menuCM();
						} else if (choice == 6){
						} else {
							System.out.println("Invalid input");
						}	
					} while (choice != 6);//exit cm menu
				}	
			} while (!menu.equalsIgnoreCase("Q"));//exit system and wait for serialize
			
		} else {
			
		}
		
	
		serial_course(allcourses);//serialize courses
		serial_student(students);//serialize students
		input.close();
	}
	
	public static ArrayList<Course> readCSVFile() throws IOException{
		ArrayList<Course> crs = new ArrayList<>();
		
		FileReader fileReader = new FileReader("src/MyUniversityCourses.csv"); //read data from csv file
		BufferedReader bufferedReader = new BufferedReader(fileReader); //wrap filereader in bufferedreader
		String line = bufferedReader.readLine(); //skip first line of file

		while((line = bufferedReader.readLine()) != null) { //read start from second line
			String[] n = line.split(","); //create an array for each line of file (a course),split strings by ',' and store them in an array
			ArrayList<Student> newStuList = new ArrayList<>();
			Course m = new Course(n[0], n[1],n[2],n[3],newStuList,n[5],n[6],n[7]); //each part of the array is a datafield of a course
			crs.add(m); //add a course to the arraylist
		}
		bufferedReader.close();
		return crs;
		
	}
	
	public static void serial_course(ArrayList<Course> c) throws IOException, ClassNotFoundException{//with help from tutor and files on course website
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Courses.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(c);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void serial_student(ArrayList<Student> s) throws IOException, ClassNotFoundException{//with help from tutor and files on course website
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Students.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(s);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static ArrayList<Course> deserial_course() throws IOException, ClassNotFoundException{//with help from tutor and files on course website
		ArrayList<Course> crs = new ArrayList<>();
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream("Courses.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as ArrayList<Course>.readObject will take the object from ObjectInputStream
		      crs = (ArrayList<Course>)ois.readObject();
		     
		      ois.close();
		      fis.close();
		      return crs;
		}
		catch(IOException ioe) {
		      ioe.printStackTrace();
		      return null;
		}
		catch(ClassNotFoundException cnfe) {
		      cnfe.printStackTrace();
		      return null;
		}
	}
	
	public static ArrayList<Student> deserial_student() throws IOException, ClassNotFoundException{//with help from tutor and files on course website
		ArrayList<Student> stud = new ArrayList<>();
		
		try{
			//FileInputSystem recieves bytes from a file
		    FileInputStream fis = new FileInputStream("Students.ser");
		      
		    //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		    ObjectInputStream ois = new ObjectInputStream(fis);
		      
		    stud = (ArrayList)ois.readObject();
		      
		    ois.close();
		    fis.close();
		    return stud;
		}
		catch(IOException ioe) {

		      return null;
		 }
		catch(ClassNotFoundException cnfe) {
			  cnfe.printStackTrace();
			  return null;
		}
	}
	
	public static void checkPassword(String username,String password) throws IOException{
		Scanner input= new Scanner(System.in);
		for (int i = 0; i<students.size();i++) {
			if (username.equals(students.get(i).getUsername())&&password.equals(students.get(i).getPassword())){
				System.out.println("Logged in");
				return;
			}
				
		}
		System.out.println("Invalid input.");
		System.out.println("Please reenter username and password: ");
		
		username = input.nextLine();
		password = input.nextLine();
		checkPassword(username,password);
		
		input.close();
	}//loop through student arraylist and check if username and password are correct
	
	
}
