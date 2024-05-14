package application;

public class UserInfoBeneficiary {
	private Integer User_ID;
	private String First_Name;
	private String Last_Name;
	private String E_mail;
	private Integer GPA;
	private String Scholarship_Applied;
	private String School_Project_Applied;
	
	public UserInfoBeneficiary(Integer User_ID, String First_Name, String Last_Name, String E_mail, Integer GPA, String Scholarship_Applied, String School_Project_Applied) {
		this.User_ID = User_ID;
		this.First_Name = First_Name;
		this.Last_Name = Last_Name;
		this.E_mail = E_mail;
		this.GPA = GPA;
		this.Scholarship_Applied = Scholarship_Applied;
		this.School_Project_Applied = School_Project_Applied;
	}
	
	public Integer getUser_ID() {
		return User_ID;
	}
	
	public String getFirst_Name() {
		return First_Name;
	}
	
	public String getLast_Name() {
		return Last_Name;
	}
	
	public String getE_mail() {
		return E_mail;
	}
	
	public Integer getGPA() {
		return GPA;
	}
	
	public String getScholarship_Applied() {
		return Scholarship_Applied;
	}
	
	public String getSchool_Project_Applied() {
		return School_Project_Applied;
	}
	
}
