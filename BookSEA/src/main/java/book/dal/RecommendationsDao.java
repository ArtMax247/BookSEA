package book.dal;


import book.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommendationsDao {
	protected ConnectionManager connectionManager;
	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
	public Recommendations create(String username, String isbn) throws SQLException {
		String insertRecommendation = "INSERT INTO Recommendations(UserName,ISBN) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation);
			insertStmt.setString(1, username);
			insertStmt.setString(2, isbn);
			insertStmt.executeUpdate();
			return null;
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
	
	public List<Books> getRecommendationFromUserName(String UserName) throws SQLException {
		String selectReadingList = "SELECT ISBN FROM Recommendations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Books> readingList = new ArrayList<Books>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReadingList);
			selectStmt.setString(1, UserName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			BooksDao booksDao = BooksDao.getInstance();
			while(results.next()) {
				
				String isbn = results.getString("ISBN");
				
				Books book = booksDao.getBookByISBN(isbn);
			
				readingList.add(book);
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
		return readingList;
	}
	
	public Recommendations delete(String username, String isbn) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE UserName=? AND ISBN=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setString(1, username);
			deleteStmt.setString(2, isbn);
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