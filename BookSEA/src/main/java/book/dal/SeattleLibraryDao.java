package book.dal;

import book.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import book.model.SeattleLibrary;

public class SeattleLibraryDao {
	protected ConnectionManager connectionManager;
	private static SeattleLibraryDao instance = null;
	protected SeattleLibraryDao() {
		connectionManager = new ConnectionManager();
	}
	public static SeattleLibraryDao getInstance() {
		if(instance == null) {
			instance = new SeattleLibraryDao();
		}
		return instance;
	}

	
	public SeattleLibrary create(SeattleLibrary seattleLibrary) throws SQLException {
		String insertSeattleLibrary = "INSERT INTO SeattleLibrary(ISBN,CheckoutYear,CheckoutMonth,CheckoutCount) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSeattleLibrary);
			insertStmt.setString(1, seattleLibrary.getISBN());
			insertStmt.setInt(2, seattleLibrary.getCheckoutYear());
			insertStmt.setInt(3, seattleLibrary.getCheckoutMonth());
			insertStmt.setInt(4, seattleLibrary.getCheckoutCount());
			insertStmt.executeUpdate();
			return seattleLibrary;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public List<SeattleLibrary> getTopBooksByUserFavorites(UserFavourites GenreID) throws SQLException {
		List<SeattleLibrary> topBooks = new ArrayList<SeattleLibrary>();
		String selectTopBooks =
			"WITH UserFavoriteGenre AS (\n"
			+ "    SELECT \n"
			+ "        UserFavourites.UserName,\n"
			+ "        UserFavourites.GenreID\n"
			+ "    FROM \n"
			+ "        UserFavourites\n"
			+ "    JOIN (\n"
			+ "        SELECT \n"
			+ "            UserName,\n"
			+ "            GenreID,\n"
			+ "            COUNT(*) AS GenreCount\n"
			+ "        FROM \n"
			+ "            UserFavourites\n"
			+ "        WHERE \n"
			+ "            UserName =? \n"
			+ "        GROUP BY \n"
			+ "            UserName, GenreID\n"
			+ "    ) AS favGenres ON UserFavourites.UserName = favGenres.UserName AND UserFavourites.GenreID = favGenres.GenreID\n"
			+ "    WHERE \n"
			+ "        (UserFavourites.UserName, favGenres.GenreCount) IN (\n"
			+ "            SELECT \n"
			+ "                fg.UserName,\n"
			+ "                MAX(fg.GenreCount)\n"
			+ "            FROM \n"
			+ "                (\n"
			+ "                    SELECT \n"
			+ "                        UserName,\n"
			+ "                        GenreID,\n"
			+ "                        COUNT(*) AS GenreCount\n"
			+ "                    FROM \n"
			+ "                        UserFavourites\n"
			+ "                    WHERE \n"
			+ "                        UserName =? \n"
			+ "                    GROUP BY \n"
			+ "                        UserName, GenreID\n"
			+ "                ) AS fg\n"
			+ "            GROUP BY \n"
			+ "                fg.UserName\n"
			+ "        )\n"
			+ "),\n"
			+ "\n"
			+ "-- Step 2: Get the top 5 books for the favorite genre of the specific user\n"
			+ "TopBooks AS (\n"
			+ "    SELECT \n"
			+ "        SeattleLibrary.CheckoutID,\n"
			+ "        SeattleLibrary.ISBN,\n"
			+ "        SeattleLibrary.CheckoutYear,\n"
			+ "        SeattleLibrary.CheckoutMonth,\n"
			+ "        SeattleLibrary.CheckoutCount,\n"
			+ "        UserFavourites.UserName\n"
			+ "    FROM \n"
			+ "        SeattleLibrary\n"
			+ "    JOIN \n"
			+ "        Books ON SeattleLibrary.ISBN = Books.ISBN\n"
			+ "    JOIN \n"
			+ "        UserFavourites ON Books.Genre = UserFavourites.GenreID  -- Join on GenreID instead of Genre\n"
			+ "    JOIN \n"
			+ "        UserFavoriteGenre ON UserFavourites.UserName = UserFavoriteGenre.UserName AND UserFavourites.GenreID = UserFavoriteGenre.GenreID\n"
			+ "    WHERE \n"
			+ "        UserFavourites.UserName = 'specific_username'  -- Filter on specific user\n"
			+ "    ORDER BY \n"
			+ "        SeattleLibrary.CheckoutCount DESC\n"
			+ "    LIMIT 5\n"
			+ ")\n"
			+ "\n"
			+ "\n"
			+ "-- Final Select: Select the results from Step 2\n"
			+ "SELECT \n"
			+ "    TopBooks.CheckoutID,\n"
			+ "    TopBooks.ISBN,\n"
			+ "    TopBooks.CheckoutYear,\n"
			+ "    TopBooks.CheckoutMonth,\n"
			+ "    TopBooks.CheckoutCount,\n"
			+ "    TopBooks.UserName\n"
			+ "FROM \n"
			+ "    TopBooks;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopBooks);
			//selectStmt.setInt(1, UserFavourites.getGenreID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int CheckoutID = results.getInt("CheckoutID");
				String ISBN = results.getString("ISBN");
				int CheckoutYear = results.getInt("CheckoutYear");
				int CheckoutMonth = results.getInt("CheckoutMonth");
				int CheckoutCount = results.getInt("CheckoutCount");
				SeattleLibrary seattleLibrary = new SeattleLibrary(CheckoutID, ISBN, CheckoutYear, CheckoutMonth, CheckoutCount);
				topBooks.add(seattleLibrary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return topBooks;
	}

	public List<Books> getTopBooks() throws SQLException {
		List<Books> topBooksByMonth = new ArrayList<Books>();
		String selectTopBooksByMonth =
			"SELECT Books.ISBN, "
			+ "    Books.Title, Books.Author, Books.PublishedYear, Books.Genre "
			+ "FROM\n"
			+ "    SeattleLibrary\n"
			+ "JOIN\n"
			+ "    Books ON SeattleLibrary.ISBN = Books.ISBN\n"
			+ "WHERE\n"
			+ "    SeattleLibrary.ISBN <> \"000000NULL\" AND SeattleLibrary.CheckoutMonth = ?\n"
			+ "GROUP BY SeattleLibrary.ISBN, SeattleLibrary.CheckoutMonth\n"
			+ "ORDER BY \n"
			+ "    SUM(SeattleLibrary.CheckoutCount) DESC\n"
			+ "LIMIT 5;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopBooksByMonth);
			
			// Get the current date
	        LocalDate currentDate = LocalDate.now();

	        // Get the month number from the current date
	        int monthNumber = currentDate.getMonthValue();
			
			selectStmt.setInt(1, monthNumber);
			results = selectStmt.executeQuery();

			while(results.next()) {
				String title = results.getString("Title");
				String isbn = results.getString("ISBN");
				String author = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				Books topBookByMonth = new Books(isbn, title, author, publishedYear, genre);
				topBooksByMonth.add(topBookByMonth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return topBooksByMonth;
	}
	
	public SeattleLibrary delete(SeattleLibrary seattleLibrary) throws SQLException {
		String deleteSeattleLibrary = "DELETE FROM SeattleLibrary WHERE CheckoutID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSeattleLibrary);
			deleteStmt.setInt(1, seattleLibrary.getCheckoutID());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
}
////getTopBooksByUserFavorites- Top 5 trending books based on user favorite genre, 'for you' feature)
////getTopBooks - Trending books feature

