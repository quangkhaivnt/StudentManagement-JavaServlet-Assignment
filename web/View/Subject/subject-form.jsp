<%-- 
    Document   : subject-form
    Created on : May 24, 2019, 11:15:32 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Student Management</title>
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
                        <c:if test="${subject != null}">
                            <div class="panel-heading">
                            Update Subject
                        </div>
                        </c:if>
                        <c:if test="${subject == null}">
                            <div class="panel-heading">
                            Create Subject
                        </div>
                        </c:if>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <c:if test="${subject != null}">
                                        <form action="update" method="post" id="formdemo">
                                    </c:if>
                                     <c:if test="${subject == null}">
                                         <form action="insert" method="post" id="formdemo">
                                    </c:if>   
                                    <c:if test="${subject != null}">
                                        <input type="hidden" name="id" value="<c:out value='${subject.id}'/>" />
                                    </c:if>
                                        <div class="form-group">
                                            <label>Subject Code</label>
                                            <input type="text" name="subjectCode" value="<c:out value='${subject.subjectCode}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Subject Name</label>
                                            <input type="text" name="subjectName" value="<c:out value='${subject.subjectName}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select name="status" class="form-control">
                                                <option value="-1">Select Status</option>
                                                <option value="0">Đang sử dụng</option>
                                                <option value="1">Tạm Khóa</option>
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
                   subjectName :{
                       required: true
                   }
               },
               messages :{
                   studentCode : {
                       required : "Student Code không được để trống"
                   },
                   subjectName {
                       required : "Subject Name không được để trống"
                   }
               }
           });
        });
    </script>
</body>

</html>
