package book.model;


public class AmazonKindle {
	protected int KindleID;
	protected String Title;
	protected String ImgURL;
	protected String ProductURL;
	protected float Price;
	protected int Rating;
	protected boolean isEditorsPick;
	protected String Genre;
	
	public AmazonKindle(int KindleID, String Title, String ImgURL, String ProductURL, float Price, int Rating,
			boolean isEditorsPick, String Genre) {
		this.KindleID = KindleID;
		this.Title = Title;
		this.ImgURL = ImgURL;
		this.ProductURL = ProductURL;
		this.Price = Price;
		this.Rating = Rating;
		this.isEditorsPick = isEditorsPick;
		this.Genre = Genre;
	}
	
	public AmazonKindle(int KindleID) {
		this.KindleID = KindleID;
	}
	
	public AmazonKindle(String Title, String ImgURL, String ProductURL, float Price, int Rating,
			boolean isEditorsPick, String Genre) {
		this.Title = Title;
		this.ImgURL = ImgURL;
		this.ProductURL = ProductURL;
		this.Price = Price;
		this.Rating = Rating;
		this.isEditorsPick = isEditorsPick;
		this.Genre = Genre;
	}
	
	public int getKindleID() {
		return KindleID;
	}

	public void setKindleID(int KindleID) {
		this.KindleID = KindleID;
	}

	public String getTitle() {
		return Title;
	}

	public String getImgURL() {
		return ImgURL;
	}

	public String getProductURL() {
		return ProductURL;
	}

	public float getPrice() {
		return Price;
	}

	public int getRating() {
		return Rating;
	}

	public boolean getIsEditorsPick() {
		return isEditorsPick;
	}

	public String getGenre() {
		return Genre;
	}


}