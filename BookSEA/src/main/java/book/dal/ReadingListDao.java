//ReadingList - getReadingListByUserName
//SeattleLibrary - getTopBooksByUserFavorites (Top 5 trending books based on user favorite genre, 'for you' feature)
//SeattleLibrary - getTopBooks (Trending books feature)

package book.dal;

import book.model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadingListDao {
	protected ConnectionManager connectionManager;
	private static ReadingListDao instance = null;
	protected ReadingListDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReadingListDao getInstance() {
		if(instance == null) {
			instance = new ReadingListDao();
		}
		return instance;
	}
	
	public ReadingList create(String username, String isbn) throws SQLException {
		String insertReadingList = "INSERT INTO ReadingList(UserName,ISBN) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReadingList);
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
	
	public List<Books> getReadingListFromUserName(String UserName) throws SQLException {
		String selectReadingList = "SELECT ISBN FROM ReadingList WHERE UserName=?;";
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
	
	public ReadingList delete(String username, String isbn) throws SQLException {
		String deleteReadingList = "DELETE FROM ReadingList WHERE UserName=? AND ISBN=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReadingList);
			deleteStmt.setString(1, username);
			deleteStmt.setString(2, isbn);
			deleteStmt.executeUpdate();
			
			//System.out.printf("Deleted %s", username);
			
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