package com.congo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.model.MusicRecordings;
import com.congo.model.MusicTracks;

public class AdminDAO {
	
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	
	// SQL Statements
	private static String DELETE_ALBUM_TRACKS = "DELETE FROM Music_Tracks WHERE recording_id = ?";
	private static String DELETE_ALBUM = "DELETE FROM Music_Recordings WHERE recording_id = ?";
	private static String UPDATE_ALBUM = "UPDATE Music_Recordings SET artist_name = ?, title = ?, category = ?, image_name = ?, num_tracks = ?, price = ?, stock_count = ? WHERE recording_id = ?";
	private static String INSERT_ALBUM = "INSERT INTO Music_Recordings (recording_id, artist_name, title, category, image_name, num_tracks, price, stock_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static String INSERT_ALBUM_TRACKS = "INSERT INTO Music_Tracks (id, recording_id, title, duration) VALUES (?, ?, ?, ?)";
	private static String GET_RECORDING_IDS = "SELECT recording_id FROM Music_Recordings";
	private static String GET_TRACK_RECORDING_IDS = "SELECT recording_id FROM Music_Tracks";
	private static String GET_TRACK_IDS = "SELECT id FROM Music_Tracks";
	
	public int deleteAlbumTracks(int recordingId) {
		int deleted = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(DELETE_ALBUM_TRACKS);
			pstmt.setInt(1, recordingId);
			deleted = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return deleted;
	}
	
	public int deleteAlbum(int recordingId) {
		int deleted = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(DELETE_ALBUM);
			pstmt.setInt(1, recordingId);
			deleted = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return deleted;
	}
	
	public int updateAlbum (MusicRecordings album) {
		int success = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(UPDATE_ALBUM);
			pstmt.setString(1, album.getArtistName());
			pstmt.setString(2, album.getTitle());
			pstmt.setString(3, album.getCategory());
			pstmt.setString(4, album.getImageName());
			pstmt.setInt(5, album.getNum_tracks());
			pstmt.setFloat(6, album.getPrice());
			pstmt.setInt(7, album.getStockCount());
			pstmt.setInt(8, album.getRecordingId());
			success = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return success;
	}
	
	public int insertAlbum (MusicRecordings album) {
		int success = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(INSERT_ALBUM);
			pstmt.setInt(1, album.getRecordingId());
			pstmt.setString(2, album.getArtistName());
			pstmt.setString(3, album.getTitle());
			pstmt.setString(4, album.getCategory());
			pstmt.setString(5, album.getImageName());
			pstmt.setInt(6, album.getNum_tracks());
			pstmt.setFloat(7, album.getPrice());
			pstmt.setInt(8, album.getStockCount());
			success = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return success;
	}
	
	public int insertAlbumTracks(ArrayList<MusicTracks> tracks) {
		int success = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(INSERT_ALBUM_TRACKS);
			for (MusicTracks track : tracks) {
				success = 0;
				pstmt.setInt(1, track.getId());
				pstmt.setInt(2, track.getRecordingId());
				pstmt.setString(3, track.getTitle());
				pstmt.setInt(4, track.getDuration());
				success = pstmt.executeUpdate();
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return success;
	}
	
	public ArrayList<Integer> getRecordingIds() {
		ArrayList<Integer> recordingIds = new ArrayList<Integer>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(GET_RECORDING_IDS);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				recordingIds.add(resultSet.getInt("recording_id"));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}		
		return recordingIds;
	}
	
	public ArrayList<Integer> getTrackRecordingIds() {
		ArrayList<Integer> recordingIds = new ArrayList<Integer>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(GET_TRACK_RECORDING_IDS);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				recordingIds.add(resultSet.getInt("recording_id"));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}		
		return recordingIds;
	}
	
	public ArrayList<Integer> getTrackIds() {
		ArrayList<Integer> trackIds = new ArrayList<Integer>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(GET_TRACK_IDS);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				trackIds.add(resultSet.getInt("id"));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}		
		return trackIds;
	}

	public static void main(String[] args) {

	}
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating 
	 * the possibility of errors from concurrent threads accessing the database
	 */
	private static AdminDAO instance = null;
	
	private AdminDAO() {}
	
	public static synchronized AdminDAO getInstance() {
		if(instance == null) {
			instance = new AdminDAO();
		}
		return instance;
	}

}
