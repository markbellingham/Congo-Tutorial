package com.congo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.model.MusicCategories;
import com.congo.model.MusicRecordings;
import com.congo.model.MusicTracks;

public class MusicDAO {
	
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;
	private Connection conn = null;
	private ArrayList<MusicRecordings> albums = null;
	
	// SQL Query Strings
	private static String FIND_ALL_ALBUMS = "SELECT * FROM Music_Recordings ORDER BY artist_name";
	private static String FIND_ALBUM_TRACKS = "SELECT * FROM Music_Tracks WHERE recording_id = ? ORDER BY id";
	private static String FIND_SINGLE_ALBUM = "SELECT * FROM Music_Recordings WHERE recording_id = ?";
	private static String FIND_ALL_CATEGORIES = "SELECT * FROM Music_Categories";
	private static String FIND_ALBUMS_BY_CATEGORY = "SELECT * FROM Music_Recordings WHERE category LIKE ?";
	private static String FIND_ALBUMS_BY_PRICE = "SELECT * FROM Music_Recordings WHERE price >= ? AND price <= ?";
	private static String FIND_ALBUMS_BY_ARTIST = "SELECT * FROM Music_Recordings WHERE artist_name LIKE ?";
	
	/**
	 * Method used by the other functions to create an ArrayList of the albums
	 * @param resultSet
	 * @return ArrayList of type MusicRecordings
	 * @throws SQLException
	 */
	private ArrayList<MusicRecordings> createAlbumArray(ResultSet resultSet) throws SQLException {
		albums = new ArrayList<MusicRecordings>();
		while(resultSet.next()) {
			MusicRecordings album = new MusicRecordings();
			album.setRecordingId(resultSet.getInt("recording_id"));
			album.setArtistName(resultSet.getString("artist_name"));
			album.setTitle(resultSet.getString("title"));
			album.setCategory(resultSet.getString("category"));
			album.setImageName(resultSet.getString("image_name"));
			album.setNum_tracks(resultSet.getInt("num_tracks"));
			album.setPrice(resultSet.getFloat("price"));
			album.setStockCount(resultSet.getInt("stock_count"));
			albums.add(album);
		}
		return albums;
	}
	
	public ArrayList<MusicRecordings> findAllAlbums() {
		albums = new ArrayList<MusicRecordings>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALL_ALBUMS);
			resultSet = pstmt.executeQuery();
			albums = createAlbumArray(resultSet);
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}		
		return albums;
	}
	
	public MusicRecordings findSingleAlbum(int recordingId) {
		MusicRecordings album = new MusicRecordings();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_SINGLE_ALBUM);
			pstmt.setInt(1, recordingId);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				album.setRecordingId(resultSet.getInt("recording_id"));
				album.setArtistName(resultSet.getString("artist_name"));
				album.setTitle(resultSet.getString("title"));
				album.setCategory(resultSet.getString("category"));
				album.setImageName(resultSet.getString("image_name"));
				album.setNum_tracks(resultSet.getInt("num_tracks"));
				album.setPrice(resultSet.getFloat("price"));
				album.setStockCount(resultSet.getInt("stock_count"));
			}
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}		
		return album;
	}
	
	public ArrayList<MusicTracks> findAlbumTracks(int recordingId) {
		ArrayList<MusicTracks> tracks = new ArrayList<MusicTracks>();
		int trackNumber = 1;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALBUM_TRACKS);
			pstmt.setInt(1, recordingId);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				MusicTracks track = new MusicTracks();
				track.setRecordingId(recordingId);
				track.setTitle(resultSet.getString("title"));
				int duration = resultSet.getInt("duration");
				track.setDuration(duration);
				track.setStrDuration(MusicTracks.formatDuration(duration));
				track.setTrackNumber(trackNumber);
				tracks.add(track);
				trackNumber++;
			}
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return tracks;
	}
	
	public ArrayList<MusicCategories> findAllCategories() {
		ArrayList<MusicCategories> categories = new ArrayList<MusicCategories>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALL_CATEGORIES);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				MusicCategories category = new MusicCategories();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return categories;
	}
	
	public ArrayList<MusicRecordings> findAlbumsByCategory(String category) {
		albums = new ArrayList<MusicRecordings>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALBUMS_BY_CATEGORY);
			pstmt.setString(1, category);
			resultSet = pstmt.executeQuery();
			albums = createAlbumArray(resultSet);
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	public ArrayList<MusicRecordings> findAlbumsByPrice(float lowerPrice, float higherPrice) {
		albums = new ArrayList<MusicRecordings>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALBUMS_BY_PRICE);
			pstmt.setFloat(1, lowerPrice);
			pstmt.setFloat(2, higherPrice);
			resultSet = pstmt.executeQuery();
			albums = createAlbumArray(resultSet);
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	public ArrayList<MusicRecordings> findAlbumsByArtist(String artist) {
		albums = new ArrayList<MusicRecordings>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALBUMS_BY_ARTIST);
			pstmt.setString(1, "%" + artist + "%");
			resultSet = pstmt.executeQuery();
			albums = createAlbumArray(resultSet);
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating 
	 * the possibility of errors from concurrent threads accessing the database
	 */
	private static MusicDAO instance = null;
	
	private MusicDAO() {}
	
	public static synchronized MusicDAO getInstance() {
		if(instance == null) {
			instance = new MusicDAO();
		}
		return instance;
	}

	/**
	 * Main method for testing the DAO
	 */
	public static void main(String[] args) {
		MusicDAO mdao = MusicDAO.getInstance();
		ArrayList<MusicRecordings> albums = mdao.findAllAlbums();
		System.out.println(albums);
	}

}
