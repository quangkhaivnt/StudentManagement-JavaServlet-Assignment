<%-- 
    Document   : student-form
    Created on : May 23, 2019, 5:41:17 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <!-- Bootstrap Core CSS -->
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
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <c:if test="${student != null}">
                            <div class="panel-heading">
                            Update Student
                        </div>
                        </c:if>
                        <c:if test="${student == null}">
                            <div class="panel-heading">
                            Create Student
                        </div>
                        </c:if>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <c:if test="${student != null}">
                                    <form action="update" method="post">
                                    </c:if>
                                     <c:if test="${student == null}">
                                    <form action="insert" method="post">
                                    </c:if>   
                                    <c:if test="${student != null}">
                                        <input type="hidden" name="id" value="<c:out value='${student.id}'/>" />
                                    </c:if>
                                        <div class="form-group">
                                            <label>Student Code</label>
                                            <input type="text" name="studentCode" value="<c:out value='${student.studentCode}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input type="text" name="name" value="<c:out value='${student.name}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Birthday</label>
                                            <input type="text" name="birthday" value="<c:out value='${student.birthday}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Address</label>
                                            <input type="text" name="address" value="<c:out value='${student.address}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Id Number</label>
                                            <input type="text" name="idNumber" value="<c:out value='${student.idNumber}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input type="text" name="phone" value="<c:out value='${student.phone}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Gender</label>
                                            <select name="gender" class="form-control">
                                                <option value="-1">Select Gender</option>
                                                <option value="0">Nam</option>
                                                <option value="1">Nữ</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input type="text" name="email" value="<c:out value='${student.email}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select name="status" class="form-control">
                                                <option value="-1">Select Status</option>
                                                <option value="0">Đang theo học</option>
                                                <option value="1">Đã nghỉ học</option>
                                                <option value="2">Xin bảo lưu</option>
                                            </select>
                                        </div>
                                        
                                        <button type="submit" class="btn btn-default">Save</button>
                                        <button type="reset" class="btn btn-default">Reset</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <%@include file="/View/Share/footer.jsp" %>
    <script>
        $(document).ready(function(){
           $('#formdemo').validate({
               rules: {
                   studentCode : {
                       required: true
                   },
                   name :{
                       required: true
                   },
                   birthday :{
                       required: true
                   },
                   address :{
                       required: true
                   },
                   idNumber :{
                       required: true
                   },
                   phone :{
                       required: true
                   },
                   email :{
                       required: true
                   }
               },
               messages :{
                   studentCode : {
                       required : "Student Code không được để trống"
                   },
                   name {
                       required : "Name không được để trống"
                   },
                   birthday {
                       required : "Birthday không được để trống"
                   },
                   address {
                       required : "Address không được để trống"
                   },
                   idNumber {
                       required : "Id Number không được để trống"
                   },
                   phone {
                       required : "Phone không được để trống"
                   },
                   email {
                       required : "Email không được để trống"
                   }
               }
           });
        });
    </script>
</body>

</html>

