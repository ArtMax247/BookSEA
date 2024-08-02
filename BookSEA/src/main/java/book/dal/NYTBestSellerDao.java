package book.dal;

import book.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import book.model.Books;
import book.model.NYTBestSeller;

public class NYTBestSellerDao extends BooksDao {
	private static NYTBestSellerDao instance = null;
	protected NYTBestSellerDao() {
		super();
	}
	public static NYTBestSellerDao getInstance() {
		if(instance == null) {
			instance = new NYTBestSellerDao();
		}
		return instance;
	}
	
	public NYTBestSeller create(NYTBestSeller nYTBestSeller) throws SQLException {
		
		String insertAmazonKindle = "INSERT INTO NYTBestSeller(ISBN,NYTLastPublishedDate,NYTAvgRange) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAmazonKindle);
			insertStmt.setDate(1, nYTBestSeller.getNYTLastPublishedDate());
			insertStmt.setFloat(2, nYTBestSeller.getNYTAvgRange());
			insertStmt.executeUpdate();
			return nYTBestSeller;
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
	
	public NYTBestSeller delete(NYTBestSeller nYTBestSeller) throws SQLException {
		String deleteNYTBestSeller = "DELETE FROM NYTBestSeller WHERE ISBN=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNYTBestSeller);
			deleteStmt.setString(1, nYTBestSeller.getISBN());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ISBN=" + nYTBestSeller.getISBN());
			}

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