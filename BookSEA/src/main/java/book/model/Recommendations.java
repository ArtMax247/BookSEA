package book.model;


public class Recommendations {
	protected int RecommendationId;
	protected String UserName;
	protected String ISBN;

	public Recommendations(int RecommendationId, String UserName, String ISBN) {
		this.RecommendationId = RecommendationId;
		this.UserName = UserName;
		this.ISBN = ISBN;
	}
	
	public Recommendations(int RecommendationId) {
		this.RecommendationId = RecommendationId;
	}
	
	public Recommendations(String UserName, String ISBN) {
		this.UserName = UserName;
		this.ISBN = ISBN;
	}
	
	public int getRecommendationId() {
		return RecommendationId;
	}

	public void setRecommendationId(int RecommendationId) {
		this.RecommendationId = RecommendationId;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public String getISBN() {
		return ISBN;
	}
}
	