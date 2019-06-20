<%-- 
    Document   : user-list
    Created on : May 24, 2019, 12:29:12 PM
    Author     : admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="/View/Share/head.jsp" %>

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <%@include file="/View/Share/content.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Student Management</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            List User
                        </div>
                        <form action="search" method="post">
                            <div class="form-group">
                            <div class="col-sm-5">
                                <div class="input-group">
                                    <input type="text" name="search" class="form-control" placeholder="Search"/>
                                    <div class="input-group-btn">
                                        <button type="submit" value="Search" class="btn btn-default">Search</button>
                                    </div>
                                </div>
                            </div>
                            </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Username</th>
                                            <th>Password</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${listUser}">
                                            <tr>
                                                <td><c:out value="${user.id}"/></td>
                                                <td><c:out value="${user.username}"/></td>
                                                <td><c:out value="${user.password}"/></td>
                                                <td>
                                                    <a href="/StudentManagement/User/edit?id=<c:out value='${user.id}'/>">Edit</a>
                                                      &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="/StudentManagement/User/delete?id=<c:out value='${user.id}'/>">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                </form>
                            </div>
                        </div>
                            </div>
                        </div>
                </div>
            </div>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <%@include file="/View/Share/footer.jsp" %>

</body>

</html>

