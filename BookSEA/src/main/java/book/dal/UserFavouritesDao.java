package book.dal;

import book.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFavouritesDao {
	protected ConnectionManager connectionManager;
	private static UserFavouritesDao instance = null;
	protected UserFavouritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserFavouritesDao getInstance() {
		if(instance == null) {
			instance = new UserFavouritesDao();
		}
		return instance;
	}
	
	public UserFavourites create(UserFavourites userFavourites) throws SQLException {
		String insertUserFavourites = "INSERT INTO UserFavourites(FavID,UserName,GenreID) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserFavourites);
			insertStmt.setInt(1, userFavourites.getFavID());
			insertStmt.setString(2, userFavourites.getUserName());
			insertStmt.setInt(3, userFavourites.getGenreID());
			insertStmt.executeUpdate();
			return userFavourites;
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
	
	public UserFavourites delete(UserFavourites userFavourites) throws SQLException {
		String deleteUserFavourites = "DELETE FROM UserFavourites WHERE FavID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserFavourites);
			deleteStmt.setInt(1, userFavourites.getFavID());
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
	
	public List<String> getUserFavouritesByUserName(String UserName) throws SQLException {
		String UserFavourites = "SELECT tt.GenreName FROM UserFavourites as uf\n"
				+ "INNER JOIN TrendingTikTok as tt\n"
				+ "ON uf.GenreID = tt.GenreID\n"
				+ "WHERE UserName =?";
		Connection connection = null;
		PreparedStatement Stmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			Stmt = connection.prepareStatement(UserFavourites);
			Stmt.setString(1, UserName);
			results = Stmt.executeQuery();
			
			List<String> UserFav = new ArrayList<String>();
			while(results.next()) {
				String genre = results.getString("GenreName");
				
				UserFav.add(genre);
			}
			
			return UserFav;
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
	
	public void saveFavoriteGenres(String username, List<String> genresList) {
        String deleteSQL = "DELETE FROM UserFavourites WHERE UserName = ?";
        String insertSQL = "INSERT INTO UserFavourites (UserName, GenreID) VALUES (?, ?)";
        
        TrendingTikTokDao TTDao = TrendingTikTokDao.getInstance();
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
             
            conn.setAutoCommit(false);
            
            // Delete existing genres for the user
            deleteStmt.setString(1, username);
            deleteStmt.executeUpdate();
            
            // Insert new genres
            for (String genre : genresList) {
                insertStmt.setString(1, username);
                
                int genreID = TTDao.getGenreByName(genre);
                
                insertStmt.setInt(2, genreID);
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
            
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors (e.g., rollback)
        }
    }
	
}