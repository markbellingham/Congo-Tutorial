package com.congo.model;

public class MusicRecordings {
	
	private int recordingId;
	private String artistName;
	private String title;
	private String category;
	private String imageName;
	private int num_tracks;
	private float price;
	private int stockCount;
	private int quantity;
	private float totalPrice;
	
	public MusicRecordings(int recordingId, String artistName, String title, String category, String imageName,
			int num_tracks, float price, int stockCount) {
		super();
		this.recordingId = recordingId;
		this.artistName = artistName;
		this.title = title;
		this.category = category;
		this.imageName = imageName;
		this.num_tracks = num_tracks;
		this.price = price;
		this.stockCount = stockCount;
	}
	
	public MusicRecordings(int recordingId, String artistName, String title, String category, String imageName,
			int num_tracks, float price, int stockCount, int quantity, float totalPrice) {
		super();
		this.recordingId = recordingId;
		this.artistName = artistName;
		this.title = title;
		this.category = category;
		this.imageName = imageName;
		this.num_tracks = num_tracks;
		this.price = price;
		this.stockCount = stockCount;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public MusicRecordings() {
		super();
	}

	@Override
	public String toString() {
		return "MusicRecordings [recordingId=" + recordingId + ", artistName=" + artistName + ", title=" + title
				+ ", category=" + category + ", imageName=" + imageName + ", num_tracks=" + num_tracks + ", price="
				+ price + ", stockCount=" + stockCount + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

	public int getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getNum_tracks() {
		return num_tracks;
	}

	public void setNum_tracks(int num_tracks) {
		this.num_tracks = num_tracks;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}	
	
}
