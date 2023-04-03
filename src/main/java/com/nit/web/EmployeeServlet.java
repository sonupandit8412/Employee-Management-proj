package com.nit.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.dao.EmployeeDAO;
import com.nit.emp.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private EmployeeDAO userDAO;

	    public void init() {
	        userDAO = new EmployeeDAO();
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        doGet(request, response);
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        String action = request.getServletPath();

	        try {
	            switch (action) {
	                case "/new":
	                    showNewForm(request, response);
	                    break;
	                case "/insert":
	                    insertUser(request, response);
	                    break;
	                case "/delete":
	                    deleteUser(request, response);
	                    break;
	                case "/edit":
	                    showEditForm(request, response);
	                    break;
	                case "/update":
	                    updateUser(request, response);
	                    break;
	                default:
	                    listUser(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	    }

	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, IOException, ServletException {
	        List < Employee > listUser = userDAO.selectAllUsers();
	        request.setAttribute("listUser", listUser);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
	        dispatcher.forward(request, response);
	    }

	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
	        dispatcher.forward(request, response);
	    }

	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, ServletException, IOException {
	        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
	        Employee existingUser = userDAO.selectUser(employeeId);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
	        request.setAttribute("user", existingUser);
	        dispatcher.forward(request, response);

	    }

	    private void insertUser(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, IOException {
	        String name = request.getParameter("name");
	        String address = request.getParameter("address");
	        String gender1 = request.getParameter("gender");
	        int gender=Integer.parseInt(gender1);
	        String salary1=request.getParameter("salary");
	        double salary=Double.valueOf(salary1);
	        String birthDate=request.getParameter("birthDate");
	        
	        
	        Employee newUser = new Employee(name, address,gender, salary, birthDate);
	        userDAO.insertUser(newUser);
	        response.sendRedirect("list");
	    }

	    private void updateUser(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, IOException {
	        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
	        String name = request.getParameter("name");
	        String address = request.getParameter("address");
	        String gender1 = request.getParameter("gender");
	        int gender=Integer.parseInt(gender1);
	        String salary1=request.getParameter("salary");
	        double salary=Double.valueOf(salary1);
	        String birthDate=request.getParameter("birthDate");
	        
	        Employee emp = new Employee(employeeId,name, address,gender, salary, birthDate);
	        System.out.println(emp);
	        userDAO.updateUser(emp);
	        response.sendRedirect("list");
	    }

	    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, IOException {
	        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
	        userDAO.deleteUser(employeeId);
	        System.out.println(employeeId);
	        response.sendRedirect("list");

	    }

}
