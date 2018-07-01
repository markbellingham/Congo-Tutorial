package com.congo.model;

public class Customer {
	private int custId;
	private String fName;
	private String lName;
	private String address1;
	private String address2;
	private String city;
	private String postcode;
	private String phone;
	private String email;
	private String password;
	private int admin;
	
	private boolean loggedIn;
	
	public Customer() {
		super();
	}

	public Customer(int custId, String fName, String lName, String address1, String address2, String city,
			String postcode, String phone, String email, String password, int admin) {
		super();
		this.custId = custId;
		this.fName = fName;
		this.lName = lName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}
	
	public Customer(int custId, String fName, String lName, String address1, String address2, String city,
			String postcode, String phone, String email, String password, int admin, boolean loggedIn) {
		super();
		this.custId = custId;
		this.fName = fName;
		this.lName = lName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.admin = admin;
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", fName=" + fName + ", lName=" + lName + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", postcode=" + postcode + ", phone=" + phone
				+ ", email=" + email + ", password=" + password + ", admin=" + admin + ", loggedIn=" + loggedIn + "]";
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
		
}
