package book.dal;

import book.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying BlogBooks table in your
 * MySQL instance. This is used to store {@link BlogBooks} into your MySQL instance and 
 * retrieve {@link BlogBooks} from MySQL instance.
 */
public class BooksDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static BooksDao instance = null;
	public BooksDao() {
		connectionManager = new ConnectionManager();
	}
	public static BooksDao getInstance() {
		if(instance == null) {
			instance = new BooksDao();
		}
		return instance;
	}

	public Books createBook(Books books) throws SQLException {
		String insertUser = "INSERT INTO Books(ISBN,Title,Author,PublishedYear,Genre) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, books.getISBN());
			insertStmt.setString(2, books.getTitle());
			insertStmt.setString(3, books.getAuthor());
			insertStmt.setString(4, books.getPublishedYear());
			insertStmt.setString(5, books.getGenre());
			insertStmt.executeUpdate();
			return books;
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
	
	public Books getBookByISBN(String ISBN) throws SQLException {
		String selectBook =
			"SELECT ISBN, Title, Author, PublishedYear, Genre " +
			"FROM Books WHERE ISBN=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, ISBN);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultISBN = results.getString("ISBN");
				String title = results.getString("Title");
				String author = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				Books book = new Books(resultISBN, title, author, publishedYear, genre);
				return book;
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
		return null;
	}
	
	public List<Books> getBookByName(String title) throws SQLException {
		String selectBook =
			"SELECT ISBN, Title, Author, PublishedYear, Genre " +
			"FROM Books WHERE Title LIKE ? LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Books> ListBooks = new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, "%" + title + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String ISBN = results.getString("ISBN");
				String resultTitle = results.getString("Title");
				String author = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				Books book = new Books(ISBN, resultTitle, author, publishedYear, genre);
	            ListBooks.add(book);    
			}
			return ListBooks;
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
		
	}
	
	public List<Books> getBookByAuthor(String author) throws SQLException {
		String selectBook =
			"SELECT ISBN, Title, Author, PublishedYear, Genre " +
			"FROM Books WHERE Author LIKE ? LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Books> ListBooks = new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, "%" + author + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String ISBN = results.getString("ISBN");
				String title = results.getString("Title");
				String resultauthor = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				Books book = new Books(ISBN, title, resultauthor, publishedYear, genre);
				ListBooks.add(book);    
			}
			return ListBooks;
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
		
	}
	
	public List<Books> getBooks() throws SQLException {
		String selectBook =
				"SELECT ISBN, Title, Author, PublishedYear, Genre FROM Books " 
				+ "WHERE ISBN <> \"000000NULL\" "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Books> ListBooks = new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			//selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String ISBN = results.getString("ISBN");
				String resultTitle = results.getString("Title");
				String author = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				Books book = new Books(ISBN, resultTitle, author, publishedYear, genre);
				ListBooks.add(book);    
			}
			return ListBooks;
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
		
	}
	
	public List<Books> getTopBooksByUserGenre(String UserName) throws SQLException {
	    String selectBook = "SELECT * FROM Books " +
	                        "INNER JOIN ( " +
	                        "SELECT sl.ISBN, SUM(CheckoutCount) as cc FROM SeattleLibrary AS sl " +
	                        "INNER JOIN ( " +
	                        "SELECT * FROM Books " +
	                        "WHERE Genre LIKE ? OR Genre LIKE ? OR Genre LIKE ? " +
	                        ") as fb " +
	                        "ON sl.ISBN = fb.ISBN " +
	                        "GROUP BY ISBN " +
	                        "ORDER BY cc DESC " +
	                        ") as sb " +
	                        "ON sb.ISBN = Books.ISBN " +
	                        "LIMIT 5;";
	    
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    
	    UserFavouritesDao UFDao = UserFavouritesDao.getInstance();
	    List<String> UserFav = new ArrayList<>();
	    
	    UserFav = UFDao.getUserFavouritesByUserName(UserName);
	    
	    String var0 = UserFav.size() > 0 ? UserFav.get(0) : "genre";
	    String var1 = UserFav.size() > 1 ? UserFav.get(1) : "genre";
	    String var2 = UserFav.size() > 2 ? UserFav.get(2) : "genre";
	    
	    List<Books> topBooks = new ArrayList<>();
	    
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectBook);
	        
	        // Properly set the parameters
	        selectStmt.setString(1, "%" + var0 + "%");
	        selectStmt.setString(2, "%" + var1 + "%");
	        selectStmt.setString(3, "%" + var2 + "%");
	        
	        results = selectStmt.executeQuery();
	        while (results.next()) {
	            String ISBN = results.getString("ISBN");
	            String title = results.getString("Title");
	            String author = results.getString("Author");
	            String publishedYear = results.getString("PublishedYear");
	            String resultGenre = results.getString("Genre");
	            Books book = new Books(ISBN, title, author, publishedYear, resultGenre);
	            topBooks.add(book);
	        }
	        return topBooks;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (selectStmt != null) {
	            selectStmt.close();
	        }
	        if (results != null) {
	            results.close();
	        }
	    }
	}
	
	public Books getBookDetailsByISBN(String ISBN) throws SQLException {
		String selectBook = "SELECT b.ISBN,b.title,b.author,b.publishedyear,b.genre,a.imgurl,a.rating as ARating \r\n"
				+ ",g.rating as GRating,n.NYTAvgRank as NRating,SUM(s.CheckoutCount) as checkout\r\n"
				+ "FROM Books as b\r\n"
				+ "LEFT JOIN AmazonKindle as a\r\n"
				+ "ON a.ISBN = b.ISBN\r\n"
				+ "LEFT JOIN GoodReads as g\r\n"
				+ "ON g.ISBN = b.ISBN\r\n"
				+ "LEFT JOIN NYTBestSeller as n\r\n"
				+ "ON n.ISBN = b.ISBN\r\n"
				+ "LEFT JOIN SeattleLibrary as s\r\n"
				+ "ON s.ISBN = b.ISBN\r\n"
				+ "WHERE b.ISBN = ?\r\n"
				+ "GROUP BY b.ISBN,b.title,b.author,b.publishedyear,b.genre,a.imgurl\r\n"
				+ ",a.rating,g.rating,n.NYTAvgRank;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, ISBN);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultISBN = results.getString("ISBN");
				String title = results.getString("Title");
				String author = results.getString("Author");
				String publishedYear = results.getString("PublishedYear");
				String genre = results.getString("Genre");
				String imgURL = results.getString("imgURL");
				Float aRating = results.getFloat("ARating");
				Float gRating = results.getFloat("GRating");
				Float nRating = results.getFloat("NRating");
				int checkout = results.getInt("checkout");
				
				Books book = new Books(resultISBN, title, author, publishedYear, genre, imgURL, aRating, gRating, nRating, checkout);
				return book;
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
		return null;
	}

}

