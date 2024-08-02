package book.dal;

import book.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book.model.TrendingTikTok;

public class TrendingTikTokDao {
	protected ConnectionManager connectionManager;
	private static TrendingTikTokDao instance = null;
	protected TrendingTikTokDao() {
		connectionManager = new ConnectionManager();
	}
	public static TrendingTikTokDao getInstance() {
		if(instance == null) {
			instance = new TrendingTikTokDao();
		}
		return instance;
	}
	
	public TrendingTikTok create(TrendingTikTok trendingTikTok) throws SQLException {
		String insertTrendingTikTok = "INSERT INTO TrendingTikTok(GenreID,GenreName,VideoURL,CooccuringHash) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTrendingTikTok);
			insertStmt.setInt(1, trendingTikTok.getGenreID());
			insertStmt.setString(2, trendingTikTok.getGenreName());
			insertStmt.setString(3, trendingTikTok.getVideoURL());
			insertStmt.setString(4, trendingTikTok.getCooccuringHash());
			insertStmt.executeUpdate();
			return trendingTikTok;
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
	
	public TrendingTikTok delete(TrendingTikTok trendingTikTok) throws SQLException {
		String deleteTrendingTikTok = "DELETE FROM TrendingTikTok WHERE GenreID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrendingTikTok);
			deleteStmt.setInt(1, trendingTikTok.getGenreID());
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
	
	public List<String> getGenre( ) throws SQLException {
		String UserFavourites = "SELECT DISTINCT GenreName FROM TrendingTikTok";
		Connection connection = null;
		PreparedStatement Stmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			Stmt = connection.prepareStatement(UserFavourites);
			
			results = Stmt.executeQuery();
			
			List<String> genres = new ArrayList<String>();
			while(results.next()) {
				String genre = results.getString("GenreName");
				
				genres.add(genre);
			}
			
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(Stmt != null) {
				Stmt.close();
			}
		}
	}
	
	public int getGenreByName(String genre) throws SQLException {
		String UserFavourites = "SELECT GenreID FROM TrendingTikTok WHERE GenreName=?";
		Connection connection = null;
		PreparedStatement Stmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			Stmt = connection.prepareStatement(UserFavourites);
			Stmt.setString(1,genre);
			results = Stmt.executeQuery();
			
			int genreID = 0;
			if(results.next()) {
				genreID = results.getInt("GenreID");
			}
			
			return genreID;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(Stmt != null) {
				Stmt.close();
			}
		}
	}
	
	
	
}