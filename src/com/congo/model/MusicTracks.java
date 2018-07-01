package com.congo.model;

public class MusicTracks {
	
	private int id;
	private int recordingId;
	private String title;
	private int duration;
	private String strDuration;
	private int trackNumber;
	
	// Constructor that uses all fields
	public MusicTracks(int id, int recordingId, String title, int duration, String strDuration, int trackNumber) {
		super();
		this.id = id;
		this.recordingId = recordingId;
		this.title = title;
		this.duration = duration;
		this.strDuration = strDuration;
		this.trackNumber = trackNumber;
	}

	// Constructor that is used in MusicDAO.findAlbumTracks()
	public MusicTracks(int recordingId, String title, String strDuration, int trackNumber) {
		super();
		this.recordingId = recordingId;
		this.title = title;
		this.strDuration = strDuration;
		this.trackNumber = trackNumber;
	}

	public MusicTracks() {
		super();
	}

	@Override
	public String toString() {
		return "MusicTracks [id=" + id + ", recordingId=" + recordingId + ", title=" + title + ", duration=" + duration
				+ ", strDuration=" + strDuration + ", trackNumber=" + trackNumber + "]";
	}

	public int getId() { return id;	}
	public void setId(int id) { this.id = id; }

	public int getRecordingId() { return recordingId; }
	public void setRecordingId(int recordingId) { this.recordingId = recordingId; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public int getDuration() { return duration; }
	public void setDuration(int duration) { this.duration = duration; }
	
	public String getStrDuration() { return strDuration; }
	public void setStrDuration(String strDuration) { this.strDuration = strDuration; }
	
	public int getTrackNumber() { return trackNumber; }
	public void setTrackNumber(int trackNumber) { this.trackNumber = trackNumber; }

	public static String formatDuration(int duration) {
		int minutes = duration / 60;
		int seconds = duration % 60;
		String formattedDuration = minutes + "m" + String.format("%02d", seconds) + "s";
		return formattedDuration;
	}
	
}
