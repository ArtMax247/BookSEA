package book.model;

import java.util.Date;

public class ReadingList {
	protected int ListID;
	protected Date Created;
	protected String UserName;
	protected String ISBN;

	public ReadingList(int ListID,  Date Created, String UserName, String ISBN) {
		this.ListID = ListID;
		this.Created = Created;
		this.UserName = UserName;
		this.ISBN = ISBN;
	}
	
	public ReadingList(int ListID) {
		this.ListID = ListID;
	}
	
	public ReadingList(Date Created, String UserName, String ISBN) {
		this.Created = Created;
		this.UserName = UserName;
		this.ISBN = ISBN;
	}
	
	public int getListID() {
		return ListID;
	}

	public void setListID(int ListID) {
		this.ListID = ListID;
	}

	public Date getCreated() {
		return Created;
	}

	public String getUserName() {
		return UserName;
	}

	public String getISBN() {
		return ISBN;
	}
	
}