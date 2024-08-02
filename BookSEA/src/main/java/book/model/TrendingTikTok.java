package book.model;

public class TrendingTikTok {
	protected int GenreID;
	protected String GenreName;
	protected String VideoURL;
	protected String CooccuringHash;
	
	public TrendingTikTok(int genreID, String genreName, String videoURL, String cooccuringHash) {
		this.GenreID = genreID;
		this.GenreName = genreName;
		this.VideoURL = videoURL;
		this.CooccuringHash = cooccuringHash;
	}
	
	public TrendingTikTok(String genreName, String videoURL, String cooccuringHash) {
		this.GenreName = genreName;
		this.VideoURL = videoURL;
		this.CooccuringHash = cooccuringHash;
	}
	
	public TrendingTikTok(int genreID) {
		this.GenreID = genreID;
	}

	public int getGenreID() {
		return GenreID;
	}

	public void setGenreID(int genreID) {
		GenreID = genreID;
	}

	public String getGenreName() {
		return GenreName;
	}

	public void setGenreName(String genreName) {
		GenreName = genreName;
	}

	public String getVideoURL() {
		return VideoURL;
	}

	public void setVideoURL(String videoURL) {
		VideoURL = videoURL;
	}

	public String getCooccuringHash() {
		return CooccuringHash;
	}

	public void setCooccuringHash(String cooccuringHash) {
		CooccuringHash = cooccuringHash;
	}
	
	
}
