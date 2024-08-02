package book.dal;
import book.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import book.model.AmazonKindle;

public class AmazonKindleDao {
	protected ConnectionManager connectionManager;
	private static AmazonKindleDao instance = null;
	protected AmazonKindleDao() {
		connectionManager = new ConnectionManager();
	}
	public static AmazonKindleDao getInstance() {
		if(instance == null) {
			instance = new AmazonKindleDao();
		}
		return instance;
	}
	
	public AmazonKindle create(AmazonKindle amazonKindle) throws SQLException {
		String insertAmazonKindle = "INSERT INTO AmazonKindle(Title,ImgURL,ProductURL,Price,Rating,isEditorsPick,Genre) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAmazonKindle);
			insertStmt.setString(1, amazonKindle.getTitle());
			insertStmt.setString(2, amazonKindle.getImgURL());
			insertStmt.setString(3, amazonKindle.getProductURL());
			insertStmt.setFloat(4, amazonKindle.getPrice());
			insertStmt.setInt(5, amazonKindle.getRating());
			insertStmt.setBoolean(6, amazonKindle.getIsEditorsPick());
			insertStmt.setString(7, amazonKindle.getGenre());
			insertStmt.executeUpdate();
			return amazonKindle;
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
	
	public AmazonKindle delete(AmazonKindle amazonKindle) throws SQLException {
		String deleteAmazonKindle = "DELETE FROM AmazonKindle WHERE KindleID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAmazonKindle);
			deleteStmt.setInt(1, amazonKindle.getKindleID());
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