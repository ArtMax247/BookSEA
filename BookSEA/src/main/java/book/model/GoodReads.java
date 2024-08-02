package book.model;



public class GoodReads {
	protected int GoodReadsID;
	protected String ISBN;
	protected int Rating;

	
	public GoodReads(int GoodReadsID, String ISBN, int Rating) {
		this.GoodReadsID = GoodReadsID;
		this.ISBN = ISBN;
		this.Rating = Rating;
	}
	
	public GoodReads(int GoodReadsID) {
		this.GoodReadsID =GoodReadsID;
	}
	
	public GoodReads(String ISBN, int Rating) {
		this.ISBN = ISBN;
		this.Rating = Rating;
	}
	
	public int getGoodReadsID() {
		return GoodReadsID;
	}

	public void setGoodReadsID(int GoodReadsID) {
		this.GoodReadsID = GoodReadsID;
	}

	public String getISBN() {
		return ISBN;
	}
	
	public int getRating() {
		return Rating;
	}
}