<%-- 
    Document   : pointInformation-list
    Created on : May 24, 2019, 12:28:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            List Point Information
                        </div>
                        <!-- /.panel-heading -->
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
                                            <th>Subject Code</th>
                                            <th>Student Code</th>
                                            <th>Point</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="point" items="${listPointInformation}">
                                            <tr>
                                                <td><c:out value="${point.id}"/></td>
                                                <td><c:out value="${point.subjectCode}"/></td>
                                                <td><c:out value="${point.studentCode}"/></td>
                                                <td><c:out value="${point.point}"/></td>
                                                <td>
                                                    <a href="/StudentManagement/PointInformation/edit?id=<c:out value='${point.id}'/>">Edit</a>
                                                      &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="/StudentManagement/PointInformation/delete?id=<c:out value='${point.id}'/>">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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

    <%@include file="/View/Share/footer.jsp" %>

</body>

</html>
