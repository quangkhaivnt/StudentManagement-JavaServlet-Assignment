<%-- 
    Document   : subject-list
    Created on : May 24, 2019, 11:15:21 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <!-- /.panel-heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Subject Code</th>
                                            <th>Subject Name</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="sub" items="${listSubject}">
                                            <tr>
                                                <td><c:out value="${sub.id}"/></td>
                                                <td><c:out value="${sub.subjectCode}"/></td>
                                                <td><c:out value="${sub.subjectName}"/></td>
                                                <td>
                                                    <c:choose>
                                                    <c:when test="${sub.status == 0}">
                                                     Đang sử dụng
                                                    </c:when>
                                                    <c:otherwise>
                                                        Tạm khóa
                                                    </c:otherwise>    
                                                    </c:choose> 
                                                </td>
                                                <td>
                                                    <a href="/StudentManagement/Subject/edit?id=<c:out value='${sub.id}'/>">Edit</a>
                                                      &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="/StudentManagement/Subject/delete?id=<c:out value='${sub.id}'/>">Delete</a>
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

