<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Employee Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 
 
        </head>

        <body>
            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javagu`ides.net" class="navbar-brand">Employee Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employee</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New Employee
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                            <input type="hidden" name="employeeId" value="<c:out value='${user.employeeId}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Employee Name</label> <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name"  required>
                        </fieldset>

                        <fieldset class="form-group">
                            <label for="address" >Employee Address</label>
                             <input type="text" value="<c:out value='${user.address}' />" class="form-control" name="address" maxlength="150" placeholder="Address line" required>
<%--                              <textarea id="address" name="address" rows="2" cols="64" value="<c:out value='${user.address}' />" class="form-control" name="address" maxlength="150"  required></textarea>
 --%>                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label for="gender" >Employee Gender</label><br> 
                            <input type="radio" id="gender" name="gender" value=1> 1. Male &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="gender" name="gender" value=2> 2. Female
                            
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Employee Salary</label> <input type="text" value="<c:out value='${user.salary}' />" class="form-control" name="salary" pattern="[0.01-9.09]+"  maxlength="5" required>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Employee Birth Date</label> <input type="date" value="<c:out value='${user.birthDate}' />" class="form-control" name="birthDate" required>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>
</html>

