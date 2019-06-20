<%-- 
    Document   : user-form
    Created on : May 24, 2019, 12:29:21 PM
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
                        <c:if test="${user != null}">
                            <div class="panel-heading">
                            Update User
                        </div>
                        </c:if>
                        <c:if test="${user == null}">
                            <div class="panel-heading">
                            Create User
                        </div>
                        </c:if>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <c:if test="${user != null}">
                                        <form action="update" method="post" id="formdemo">
                                    </c:if>
                                     <c:if test="${user == null}">
                                         <form action="insert" method="post" id="formdemo">
                                    </c:if>   
                                    <c:if test="${user != null}">
                                        <input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
                                    </c:if>
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input type="text" name="username" id="username" value="<c:out value='${user.username}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="text" name="password" id="password" value="<c:out value='${user.password}'/>" class="form-control" required="">
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
                   username : {
                       required: true,
                       minlength : 6 
                   },
                   password :{
                       required: true,
                       minlength : 6
                   }
               },
               messages :{
                   username : {
                       required : "Username không được để trống",
                       minlength : "Username phải có ít nhất 6 ký tự"
                   },
                   password: {
                       required : "Mật khẩu không được để trống",
		       minlength : "Mật khẩu phải có ít nhất 8 ký tự"
                   }
               }
           });
        });
    </script>
</body>

</html>

