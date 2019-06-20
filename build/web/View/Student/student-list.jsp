<%-- 
    Document   : student-list.jsp
    Created on : May 23, 2019, 5:40:58 PM
    Author     : admin
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <!--Search-->
                        <form action="search" method="post">
                            <div class="form-group">
                            <div class="col-sm-5">
                                <div class="input-group">
                                    <input type="text" id="name" name="name" class="form-control" placeholder="Search"/>
                                    <div class="input-group-btn">
                                        <button type="submit" id="search" value="Search" class="btn btn-default">Search</button>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </form>
                        <!-- /.panel-heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Student Code</th>
                                            <th>Name</th>
                                            <th>Birthday</th>
                                            <th>Address</th>
                                            <th>Id Number</th>
                                            <th>Phone</th>
                                            <th>Gender</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Medium Score</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="row">
                                        <c:forEach var="stu" items="${listStudent}">
                                            <tr>
                                                <td><c:out value="${stu.id}"/></td>
                                                <td><c:out value="${stu.studentCode}"/></td>
                                                <td><c:out value="${stu.name}"/></td>
                                                <td><c:out value="${stu.birthday}"/></td>
                                                <td><c:out value="${stu.address}"/></td>
                                                <td><c:out value="${stu.idNumber}"/></td>
                                                <td><c:out value="${stu.phone}"/></td>
                                                <td>
                                                   <c:choose>
                                                   <c:when test="${stu.gender == 0}">
                                                   Nam
                                                   </c:when>
                                                   <c:otherwise>
                                                   Nữ    
                                                   </c:otherwise>    
                                                   </c:choose>
                                                </td>
                                                <td><c:out value="${stu.email}"/></td>
                                                <td>
                                                    <c:choose>
                                                    <c:when test="${stu.status == 0}">
                                                    Đang theo học
                                                    </c:when>
                                                    <c:when test="${stu.status == 1}">
                                                    Đã nghỉ học
                                                    </c:when>
                                                    <c:otherwise>Xin bảo lưu</c:otherwise>   
                                                    </c:choose>
                                                </td>
                                                
                                                <td><c:out value="${stu.mediumScore}"/></td>
                                                <td>
                                                    <a href="/StudentManagement/Student/edit?id=<c:out value='${stu.id}'/>">Edit</a>
                                                      &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="/StudentManagement/Student/delete?id=<c:out value='${stu.id}'/>">Delete</a>
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

    <!-- jQuery -->
    <%@include file="/View/Share/footer.jsp" %>
    <script>
        $(document).ready(function(){
           $('.search').click(function(){
             var name = $("#name").val();
             $.ajax({
                type:"GET",
                url:"/StudentManagement/Student/searchStudent",
                data:"name=" +name,
                dataType:"json",
                async:true,
                cache:false,
                success:function(result){
                    var tr;
                    for(var i = 0; i < result.length; i++){
                       tr = $('<tr/>');
                       tr.append("<td>" + result[i].studentCode + "</td>");
                       tr.append("<td>" + result[i].name + "</td>");
                       tr.append("<td>" + result[i].birthday + "</td>");
                       tr.append("<td>" + result[i].address + "</td>");
                       tr.append("<td>" + result[i].idNumber + "</td>");
                       tr.append("<td>" + result[i].phone + "</td>");
                       tr.append("<td>" + result[i].gender + "</td>");
                       tr.append("<td>" + result[i].email + "</td>");
                       tr.append("<td>" + result[i].status + "</td>");
                       tr.append("<td>" + result[i].mediumScore + "</td>");
                       $('table').append(tr); 
                    }
                }
             });
           });
           $('.search').click(function(){
              var trLength = table.getElementsByTagName("tr").length;
              if(trLength > 0){
                  for(i = 0; i < trLength; i++){
                      table.deleteRow(0);
                  }
              }
           });
        });
    </script>

</body>

</html>

