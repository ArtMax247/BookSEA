package book.model;

import java.sql.Date;

public class NYTBestSeller {
	protected int NYTID;
	protected String ISBN;
	protected Date NYTLastPublishedDate;
	protected float NYTAvgRange;
	
	public NYTBestSeller(int nYTID, String iSBN, Date nYTLastPublishedDate, float nYTAvgRange) {
		super();
		NYTID = nYTID;
		ISBN = iSBN;
		NYTLastPublishedDate = nYTLastPublishedDate;
		NYTAvgRange = nYTAvgRange;
	}

	public int getNYTID() {
		return NYTID;
	}

	public void setNYTID(int nYTID) {
		NYTID = nYTID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getNYTLastPublishedDate() {
		return NYTLastPublishedDate;
	}

	public void setNYTLastPublishedDate(Date nYTLastPublishedDate) {
		NYTLastPublishedDate = nYTLastPublishedDate;
	}

	public float getNYTAvgRange() {
		return NYTAvgRange;
	}

	public void setNYTAvgRange(float nYTAvgRange) {
		NYTAvgRange = nYTAvgRange;
	}
}
