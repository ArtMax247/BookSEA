package book.model;

public class Books {
	protected String ISBN;
	protected String Title;
	protected String Author;
	protected String PublishedYear;
	protected String Genre;
	protected String ImgURL;
	protected Float ARating;
	protected Float GRating;
	protected Float NRating;
	protected int Checkout;
	
	
	public Books(String isbn, String title, String author, String publishedYear, String genre) {
		ISBN = isbn;
		Title = title;
		Author = author;
		PublishedYear = publishedYear;
		Genre = genre;
	}
	
	public Books(String isbn, String title, String author, String publishedYear, String genre, String imgURL, Float aRating, Float gRating, Float nRating, int checkout) {
		ISBN = isbn;
		Title = title;
		Author = author;
		PublishedYear = publishedYear;
		Genre = genre;
		ImgURL = imgURL;
		ARating = aRating;
		GRating = gRating;
		NRating = nRating;
		Checkout = checkout;
	}

	

	public String getImgURL() {
		return ImgURL;
	}

	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}

	public Float getARating() {
		return ARating;
	}

	public void setARating(Float aRating) {
		ARating = aRating;
	}

	public Float getGRating() {
		return GRating;
	}

	public void setGRating(Float gRating) {
		GRating = gRating;
	}

	public Float getNRating() {
		return NRating;
	}

	public void setNRating(Float nRating) {
		NRating = nRating;
	}

	public int getCheckout() {
		return Checkout;
	}

	public void setCheckout(int checkout) {
		Checkout = checkout;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPublishedYear() {
		return PublishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		PublishedYear = publishedYear;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}
}
