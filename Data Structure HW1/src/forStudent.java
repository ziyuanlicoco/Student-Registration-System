
public interface forStudent {// Interface for Student 
	public void viewAllCourse();
	public void viewNotFullCourse();
	public void registerCourse(String course, String section, String firstName, String lastName);
	public void withdrawCourse(String course,String firstName, String lastName );
	public void viewRegisteredCourse(String username);
	
	public void menuCM();//some methods that will be implemented in Student class

}
