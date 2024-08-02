package book.model;

public class Users {
	protected String UserName;
	protected String Password;
	protected String FirstName;
	protected String LastName;
	protected String Email;
	protected String Phone;
	
	public Users(String userName, String password, String firstName, String lastName, String email, String phone) {
		super();
		UserName = userName;
		Password = password;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Phone = phone;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
}
