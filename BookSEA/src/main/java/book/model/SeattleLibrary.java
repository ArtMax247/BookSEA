package book.model;


public class SeattleLibrary {
	protected int CheckoutID;
	protected String ISBN;
	protected int CheckoutYear;
	protected int CheckoutMonth;
	protected int CheckoutCount;

	public SeattleLibrary(int CheckoutID, String ISBN, int CheckoutYear, int CheckoutMonth, int CheckoutCount) {
		this.CheckoutID = CheckoutID;
		this.ISBN = ISBN;
		this.CheckoutYear = CheckoutYear;
		this.CheckoutMonth = CheckoutMonth;
		this.CheckoutCount = CheckoutCount;
	}
	
	public SeattleLibrary(int CheckoutID) {
		this.CheckoutID = CheckoutID;
	}
	
	public SeattleLibrary(String ISBN, int CheckoutYear, int CheckoutMonth, int CheckoutCount) {
		this.ISBN = ISBN;
		this.CheckoutYear = CheckoutYear;
		this.CheckoutMonth = CheckoutMonth;
		this.CheckoutCount = CheckoutCount;
	}
	
	public int getCheckoutID() {
		return CheckoutID;
	}

	public void setCheckoutID(int CheckoutID) {
		this.CheckoutID = CheckoutID;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public int getCheckoutYear() {
		return CheckoutYear;
	}
	
	public int getCheckoutMonth() {
		return CheckoutMonth;
	}
	
	public int getCheckoutCount() {
		return CheckoutCount;
	}
	
}