package com.congo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.model.Customer;
import com.congo.model.CustomerOrderDetails;
import com.congo.model.CustomerOrders;
import com.congo.model.MusicRecordings;

public class CustomerDAO {
	
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;
	private Connection conn = null;
	private ArrayList<Customer> customers = null;
	private Customer customer = null;
	
	// SQL statements
	private static String FIND_ALL_CUSTOMERS = "SELECT * FROM customers";
	private static String FIND_ONE_CUSTOMER = "SELECT * FROM customers WHERE email = ?";
	private static String INSERT_CUSTOMER_INTO_DATABASE = "INSERT INTO customers (fname, lname, address1, address2, city, postcode, phone, email, password, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String INSERT_ORDER = "INSERT INTO orders (custid, order_date, order_total) VALUES (?, ?, ?)";
	private static String INSERT_ORDER_DETAILS = "INSERT INTO order_details (orderid, recording_id, price, order_quantity, total) VALUES (?, ?, ?, ?, ?)";
	private static String REDUCE_STOCK_COUNT = "UPDATE Music_Recordings SET stock_count = stock_count - ? WHERE recording_id = ?";
	private static String FIND_CUSTOMER_ORDERS = "SELECT * FROM orders WHERE custid = ? ORDER BY orderid DESC";
	private static String FIND_CUSTOMER_ORDER_DETAILS = "SELECT * FROM orders o, order_details od, Music_Recordings mr WHERE o.orderid = od.orderid AND od.recording_id = mr.recording_id AND custid = ? ORDER BY order_date";
	
	public ArrayList<Customer> createCustomerArray(ResultSet resultSet) throws SQLException {
		customers = new ArrayList<Customer>();
		while(resultSet.next()) {
			customer = new Customer();
			customer.setCustId(resultSet.getInt("custid"));
			customer.setfName(resultSet.getString("fname"));
			customer.setlName(resultSet.getString("lname"));
			customer.setAddress1(resultSet.getString("address1"));
			customer.setAddress2(resultSet.getString("address2"));
			customer.setCity(resultSet.getString("city"));
			customer.setPostcode(resultSet.getString("postcode"));
			customer.setPhone(resultSet.getString("phone"));
			customer.setEmail(resultSet.getString("email"));
			customer.setPassword(resultSet.getString("password"));
			customer.setAdmin(resultSet.getInt("admin"));
			customers.add(customer);
		}
		return customers;
	}
	
	public ArrayList<Customer> findAllCustomers() {
		customers = new ArrayList<Customer>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ALL_CUSTOMERS);
			resultSet = pstmt.executeQuery();
			customers = createCustomerArray(resultSet);
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return customers;
	}
	
	public Customer findOneCustomer(String email) {
		customer = new Customer();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_ONE_CUSTOMER);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				customer.setCustId(resultSet.getInt("custid"));
				customer.setfName(resultSet.getString("fname"));
				customer.setlName(resultSet.getString("lname"));
				customer.setAddress1(resultSet.getString("address1"));
				customer.setAddress2(resultSet.getString("address2"));
				customer.setCity(resultSet.getString("city"));
				customer.setPostcode(resultSet.getString("postcode"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPassword(resultSet.getString("password"));
				customer.setAdmin(resultSet.getInt("admin"));
			}
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return customer;
	}
	
	public boolean checkEmail(String email) {
		CustomerDAO cdao = CustomerDAO.getInstance();
		Customer customer = cdao.findOneCustomer(email);
		if(customer.getfName() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public int insertCustomerIntoDatabase(Customer customer) {
		int result = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(INSERT_CUSTOMER_INTO_DATABASE);
			pstmt.setString(1, customer.getfName());
			pstmt.setString(2, customer.getlName());
			pstmt.setString(3, customer.getAddress1());
			pstmt.setString(4, customer.getAddress2());
			pstmt.setString(5, customer.getCity());
			pstmt.setString(6, customer.getPostcode());
			pstmt.setString(7, customer.getPhone());
			pstmt.setString(8, customer.getEmail());
			pstmt.setString(9, customer.getPassword());
			pstmt.setInt(10, customer.getAdmin());			
			result = pstmt.executeUpdate();			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return result;
	}
	
	public int insertOrder(Customer customer, float grandTotal) {
		int orderId = 0;
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, customer.getCustId());
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			pstmt.setFloat(3, grandTotal);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    orderId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return orderId;
	}
	
	public void insertOrderDetails(int orderId, ArrayList<MusicRecordings> albums) {
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(INSERT_ORDER_DETAILS);
			for(MusicRecordings album : albums) {
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, album.getRecordingId());
				pstmt.setFloat(3, album.getPrice());
				pstmt.setInt(4, album.getQuantity());
				pstmt.setFloat(5, album.getTotalPrice());
				pstmt.executeUpdate();
				reduceStockCount(album.getRecordingId(), album.getQuantity());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
	}
	
	private void reduceStockCount(int recordingId, int quantity) {
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(REDUCE_STOCK_COUNT);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, recordingId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
	}
	
	public ArrayList<CustomerOrders> customerOrders(int custId) {
		ArrayList<CustomerOrders> orders = new ArrayList<CustomerOrders>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_CUSTOMER_ORDERS);
			pstmt.setInt(1, custId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomerOrders custOrder = new CustomerOrders();
				custOrder.setOrderId(rs.getInt("orderid"));
				custOrder.setOrderDate(rs.getDate("order_date"));
				custOrder.setOrderTotal(rs.getFloat("order_total"));
				orders.add(custOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return orders;
	}
	
	public ArrayList<CustomerOrderDetails> customerOrderDetails(int custId) {
		ArrayList<CustomerOrderDetails> custOrderArray = new ArrayList<CustomerOrderDetails>();
		try {
			conn = new DBConnection().openConnection();
			pstmt = conn.prepareStatement(FIND_CUSTOMER_ORDER_DETAILS);
			pstmt.setInt(1, custId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomerOrderDetails custOrder = new CustomerOrderDetails();
				custOrder.setOrderId(rs.getInt("orderid"));
				custOrder.setCustId(custId);
				custOrder.setOrderDate(rs.getDate("order_date"));
				custOrder.setOrderTotal(rs.getFloat("order_total"));
				custOrder.setRecordingId(rs.getInt("recording_id"));
				custOrder.setPrice(rs.getFloat("price"));
				custOrder.setOrderQuantity(rs.getInt("order_quantity"));
				custOrder.setItemTotal(rs.getFloat("total"));
				custOrder.setArtistName(rs.getString("artist_name"));
				custOrder.setTitle(rs.getString("title"));
				custOrder.setCategory(rs.getString("category"));
				custOrder.setNumTracks(rs.getInt("num_tracks"));
				custOrderArray.add(custOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return custOrderArray;
	}

	public static void main(String[] args) {
		CustomerDAO cdao = CustomerDAO.getInstance();
		Customer customer = cdao.findOneCustomer("john@thebeatles.com");
		System.out.println(customer);
	}
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating 
	 * the possibility of errors from concurrent threads accessing the database
	 */
	private static CustomerDAO instance = null;
	
	private CustomerDAO() {}
	
	public static synchronized CustomerDAO getInstance() {
		if(instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	}

}
