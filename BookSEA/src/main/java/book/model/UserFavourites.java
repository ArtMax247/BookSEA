package book.model;

public class UserFavourites {
	protected int FavID;
	protected String UserName;
	protected int GenreID;

	public UserFavourites(int favID, String userName, int genreID) {
		this.FavID = favID;
		this.UserName = userName;
		this.GenreID = genreID;
	}

	// This constructor can be used for reading records from MySQL, where we only
	// have the FavID, such as a foreign key reference to FavId.
	// Given FavId, we can fetch the full UserFavourites record.
	public UserFavourites(int favID) {
			this.FavID = favID;
		}

	// This constructor can be used for creating new records, where the postId may
	// not be
	// assigned yet since it is auto-generated by MySQL.
	public UserFavourites(String userName, int genreID) {
		this.UserName = userName;
		this.GenreID = genreID;
	}

	public int getFavID() {
		return FavID;
	}

	public void setFavID(int favID) {
		FavID = favID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getGenreID() {
		return GenreID;
	}

	public void setGenreID(int genreID) {
		GenreID = genreID;
	}
}
