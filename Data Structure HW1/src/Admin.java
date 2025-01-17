import java.util.*;
import java.io.*;

public class Admin extends User implements forAdmin, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//generated by eclipse


	public Admin() {
		super();
	}
	
	public Admin(String username, String password, String firstName, String lastName ) {
		super(username,password,firstName,lastName);
	}

	Scanner input = new Scanner(System.in);


	@Override
	public void createCourse() {//take input and create a course
		System.out.println("Please enter course name: ");
		String courseName = input.nextLine();
		System.out.println("Please enter course id: ");
		String courseID = input.nextLine();
		System.out.println("Please enter course maximum student number: ");
		String maxStu = input.nextLine();
		String currentStu = "0"; //a new course should have no current student
		ArrayList<Student> namelist = new ArrayList<>();//a new course should have no current student
		System.out.println("Please enter course instructor: ");
		String instructor = input.nextLine();
		System.out.println("Please enter course section: ");
		String section = input.nextLine();
		System.out.println("Please enter course location: ");
		String location = input.nextLine();
		Course newCourse = new Course(courseName, courseID, maxStu, currentStu, namelist,instructor,section,location);//generate a new couse
		allcourses.add(newCourse);//add tthe new course to system
		System.out.println("Course added");
	}

	@Override
	public void deleteCourse() { //delete a course
		System.out.println("Please enter the course ID of the course you want to delete: ");
		String  courseToDelete = input.nextLine();
		int m = allcourses.size()+1 ;
		for (int i = 0; i< allcourses.size(); i++) {
			if (courseToDelete.equals(allcourses.get(i).getCourseID())) {
				allcourses.remove(i);
				m =i;
				System.out.println("Course deleted");
				break;
			}
		}
		if (m > allcourses.size()) {//cannot delete a course not exist
			System.out.println("Course does not exist");
			deleteCourse();
		}
	}

	@Override
	public void editCourse() {//change a course instructor by searching name
		int m = allcourses.size() +1;
		System.out.println("Please enter the name of the course you want to edit: ");
		String courseToEdit = input.nextLine();
		System.out.println("Please enter the name of the new instructor: ");
		String newInstructor = input.nextLine();
		for (int i = 0; i< allcourses.size(); i++) {
			if (courseToEdit.equalsIgnoreCase(allcourses.get(i).getCourseName())) {
				allcourses.get(i).setInstructor(newInstructor);
				System.out.println("Course edited");
				m = i;
				break;
			}
		}
		if (m > allcourses.size()) {//cannot edit not existing course
			System.out.println("Course does not exist");
			editCourse();
		}
	}

	@Override
	public void viewCourse() {//check a course information
		int m = allcourses.size() +1;
		System.out.println("Please enter the course ID of the course you want to view: ");
		String courseToView = input.nextLine();
		for (int i = 0; i< allcourses.size(); i++) {
			if (courseToView.equals(allcourses.get(i).getCourseID())) {
				System.out.println(allcourses.get(i));//print course
				System.out.println("Registered students' username: ");
				for (int a = 0; a< Registration.allcourses.get(i).getNameList().size();a++) {
					System.out.print(Registration.allcourses.get(i).getNameList().get(a).getUsername()+" ");//print username of students in this course
				}
				m=i;
				break;
			}
		}
		if (m > allcourses.size()) {
			System.out.println("Course does not exist");
			viewCourse();
		}
	}

	@Override
	public void regiserStu() {//add a student in system
		System.out.println("Please enter student's first name: ");
		String a = input.nextLine();
		System.out.println("Please enter student's last name: ");
		String b = input.nextLine();
		System.out.println("Please enter student's username: ");
		String c = input.nextLine();
		if (Registration.students.size()>0) {
		}
			for (int i = 0; i< Registration.students.size(); i++) {
				if (c.equals(Registration.students.get(i).getUsername())) {
					System.out.println("Username taken. Please enter another one: ");//username must be unique
					c = input.nextLine();
					i = 0;
			}
		}
		
		System.out.println("Please enter student's password: ");
		String d = input.nextLine();
		System.out.println("Please enter student's ID: ");
		String e = input.nextLine();
		Student newStu =  new Student(c,d,a,b,e);
		Registration.students.add(newStu);
		System.out.println("Student registered");
	
	}

	@Override
	public void viewAllCourse() {//view all courses
		for (int i = 0; i< allcourses.size();i++) {
			System.out.println("Course name: "+ Registration.allcourses.get(i).getCourseName());
			System.out.println("Course ID: "+  Registration.allcourses.get(i).getCourseID());
			
			System.out.println("Registered students' names: ");
			for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
				System.out.println(Registration.allcourses.get(i).getNameList().get(m).getFirstName() + Registration.allcourses.get(i).getNameList().get(m).getLastName());
			}
			System.out.println("Registered students' ID: ");
			for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
				System.out.println(Registration.allcourses.get(i).getNameList().get(m).getStudentID());
			}
			System.out.println("Current student registered: " + allcourses.get(i).getCurrentStu());
			System.out.println("Maximum student allowed to register: " + allcourses.get(i).getMaxStu());
		}   //print course
			//then print student info in the course
		
	}

	@Override
	public void viewFullCourse() {
		for (int i = 0; i< Registration.allcourses.size(); i++) {
			if (Registration.allcourses.get(i).getMaxStu()==Registration.allcourses.get(i).getCurrentStu()) {
				System.out.println("Course information: "+ allcourses.get(i));
				System.out.println("Registered students' names: ");
				for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
					System.out.println(Registration.allcourses.get(i).getNameList().get(m).getFirstName() +" "+ Registration.allcourses.get(i).getNameList().get(m).getLastName());
				}
				
				System.out.println("Registered students' username: ");
				for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
					System.out.print(Registration.allcourses.get(i).getNameList().get(m).getUsername()+" ");
				}
				
				System.out.println("\nRegistered students' ID: ");
				for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
					System.out.print(Registration.allcourses.get(i).getNameList().get(m).getStudentID()+ " ");
				}
				System.out.println("");
			}
		}//only print course whose max student number equals current student number
	}

	@Override
	public void listFullCourse() {
		try{
			for (int i = 0; i< allcourses.size(); i++) {
				if (allcourses.get(i).getMaxStu()==allcourses.get(i).getCurrentStu()) {
					FileWriter fileWriter = new FileWriter("src/FullCourses.txt");
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					String text = allcourses.get(i) + "\n";
					bufferedWriter.write(text);
					String n = "Student username: \n";
					bufferedWriter.write(n);
					for(int m = 0; m<allcourses.get(i).getNameList().size();m++) {
						String textt =Registration.allcourses.get(i).getNameList().get(m).getUsername()+" ";
						bufferedWriter.write(textt);
					}
					bufferedWriter.close();
				}
			}
		}
		catch(IOException exk) {
			System.out.println( "Error writing file");
			exk.printStackTrace();		
		}
	} //write result to a txt file

	@Override
	public void viewStudentInCourse() {
		System.out.println("Please enter the course ID of the course you want to view: ");
		String stuToView = input.nextLine();
		for (int i = 0; i< allcourses.size(); i++) {
			if (stuToView.equals(allcourses.get(i).getCourseID())) {
				for (int m = 0; m< Registration.allcourses.get(i).getNameList().size();m++) {
					System.out.println(Registration.allcourses.get(i).getNameList().get(m).getFirstName() +" "
							+ Registration.allcourses.get(i).getNameList().get(m).getLastName()+" "
							+ Registration.allcourses.get(i).getNameList().get(m).getUsername()+" "
							+ Registration.allcourses.get(i).getNameList().get(m).getStudentID());
				}
			}
		}// view student information in a specific course
	}

	@Override
	public void viewAStudent() {
		System.out.println("Please enter the student's first name: ");
		String firstName = input.nextLine();
		System.out.println("Please enter the student's last name: ");
		String lastName = input.nextLine();
		for (int i = 0; i< allcourses.size(); i++) {
			for (int m = 0; m< allcourses.get(i).getNameList().size();m++) {
				if (firstName.equals(allcourses.get(i).getNameList().get(m).getFirstName()) && (lastName.equals(allcourses.get(i).getNameList().get(m).getLastName()))) {
					System.out.println("Registered in course: "+allcourses.get(i).getCourseName());
					break;
				}
			}
		}//view all courses a specific student is in
	}
	@Override
	public void sortCourses() {
		ArrayList<Course> sortedCourses = allcourses;
		for (int i = 0; i< sortedCourses.size(); i++) {
			for (int j = sortedCourses.size()-1; j > i; j--) {
				Course temp = new Course();
				int result = sortedCourses.get(i).compareTo(sortedCourses.get(j));
				if (result == -1) {
					temp = sortedCourses.get(i);
					sortedCourses.set(i,sortedCourses.get(j));
					sortedCourses.set(j,temp);
				}
			}	
		}
		for (int i = 0; i< sortedCourses.size(); i++) {
			System.out.println(sortedCourses.get(i));
		}
		//sort courses by current student number
		//top one have more current student number
	}
	
	
	@Override
	public void menuCM() {//display menu
		System.out.println("1. Create a new course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display course information");
		System.out.println("5. Register a student");
		System.out.println("6. Exit");
	}
	
	@Override
	public void menuRe() {
		System.out.println("1. View all courses");
		System.out.println("2. View all full courses");
		System.out.println("3. Export all full courses");
		System.out.println("4. View registered students for a course");
		System.out.println("5. View all registered courses of a student");
		System.out.println("6. Sort courses");
		System.out.println("7. Exit");
	}

}
