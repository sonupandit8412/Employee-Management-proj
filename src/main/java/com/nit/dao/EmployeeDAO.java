package com.nit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nit.emp.Employee;

public class EmployeeDAO {
	 private String jdbcURL = "jdbc:mysql://localhost:3306/empmanag?useSSL=false";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "root";

	    private static final String INSERT_USERS_SQL = "INSERT INTO employee" + "  (name, address, gender, salary, birthDate) VALUES " +
	        " (?, ?, ?, ?, ?);";

	    private static final String SELECT_USER_BY_ID = "select employeeId,name, address, gender, salary, birthDate from employee where employeeId =?";
	    private static final String SELECT_ALL_USERS = "select * from employee";
	    private static final String DELETE_USERS_SQL = "delete from employee where employeeId = ?;";
	    private static final String UPDATE_USERS_SQL = "update employee set name =?,address=?, gender =?, salary=?, birthDate=? where employeeId = ?;";

	    public EmployeeDAO() {}

	    protected Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }

	    public void insertUser(Employee user) throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); 
	        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getAddress());
	            preparedStatement.setInt(3, user.getGender());
	            preparedStatement.setDouble(4, user.getSalary());
	            preparedStatement.setString(5, user.getBirthDate());

	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public Employee selectUser(int employeeId) {
	        Employee user = null;
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
	            preparedStatement.setInt(1, employeeId);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                String name = rs.getString("name");
	                String address = rs.getString("address");
	                int gender = rs.getInt("gender");
	                Double salary=rs.getDouble("salary");
	                String birthDate=rs.getString("birthDate");
	                
	                user = new Employee(employeeId, name, address,gender, salary, birthDate);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }

	    public List < Employee > selectAllUsers() {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Employee > users = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnection();

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	            	int employeeId = rs.getInt("employeeId");
	            	String name = rs.getString("name");
	                String address = rs.getString("address");
	                int gender = rs.getInt("gender");
	                Double salary=rs.getDouble("salary");
	                String birthDate=rs.getString("birthDate");
	                
	               // user = new Employee(employeeId, name, address,gender, salary, birthDate);
	                users.add(new Employee(employeeId, name, address,gender, salary, birthDate));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }

	    public boolean deleteUser(int employeeId) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
	            statement.setInt(1, employeeId);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        System.out.println(employeeId);
	        return rowDeleted;
	    }

	    public boolean updateUser(Employee user) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnection(); 
	        		
	        	PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
	            
	        	statement.setString(1, user.getName());
	            statement.setString(2, user.getAddress());
	            statement.setInt(3, user.getGender());
	            statement.setDouble(4, user.getSalary());
	            statement.setString(5,user.getBirthDate());
	            statement.setInt(6,user.getEmployeeId());
	            
	            rowUpdated = statement.executeUpdate() > 0;
	            System.out.println(rowUpdated);
	        }
	        return rowUpdated;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}
