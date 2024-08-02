package book.dal;

import book.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import book.model.GoodReads;

public class GoodReadsDao {
	protected ConnectionManager connectionManager;
	private static GoodReadsDao instance = null;
	protected GoodReadsDao() {
		connectionManager = new ConnectionManager();
	}
	public static GoodReadsDao getInstance() {
		if(instance == null) {
			instance = new GoodReadsDao();
		}
		return instance;
	}
	
	public GoodReads create(GoodReads goodReads) throws SQLException {
		String insertGoodReads = "INSERT INTO GoodReads(ISBN, Rating) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGoodReads);
			insertStmt.setString(1, goodReads.getISBN());
			insertStmt.setInt(2, goodReads.getRating());
			insertStmt.executeUpdate();
			return goodReads;
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
	
	public GoodReads delete(GoodReads goodReads) throws SQLException {
		String deleteGoodReads = "DELETE FROM GoodReads WHERE GoodReadsID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGoodReads);
			deleteStmt.setInt(1, goodReads.getGoodReadsID());
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