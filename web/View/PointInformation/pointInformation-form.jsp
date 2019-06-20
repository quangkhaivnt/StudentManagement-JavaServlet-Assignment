<%-- 
    Document   : pointInformation-form
    Created on : May 24, 2019, 12:28:54 PM
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
                        <c:if test="${point != null}">
                            <div class="panel-heading">
                            Update Point Information
                        </div>
                        </c:if>
                        <c:if test="${point == null}">
                            <div class="panel-heading">
                            Create Point Information
                        </div>
                        </c:if>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <c:if test="${point != null}">
                                        <form action="update" method="post" id="formdemo">
                                    </c:if>
                                     <c:if test="${point == null}">
                                         <form action="insert" method="post" id="formdemo">
                                    </c:if>   
                                    <c:if test="${point != null}">
                                        <input type="hidden" name="id" value="<c:out value='${point.id}'/>" />
                                    </c:if>
                                        <div class="form-group">
                                            <label>Subject Code</label>
                                            <input type="text" name="subjectCode" value="<c:out value='${point.subjectCode}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Student Code</label>
                                            <input type="text" name="studentCode" value="<c:out value='${point.studentCode}'/>" class="form-control" required="">
                                        </div>
                                        <div class="form-group">
                                            <label>Point</label>
                                            <input type="text" name="point" value="<c:out value='${point.point}'/>" class="form-control" required="">
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
                   subjectCode : {
                       required: true
                   },
                   studentCode :{
                       required: true
                   },
                   point :{
                       required:true
                   }
               },
               messages :{
                   subjectCode : {
                       required : "Subject Code không được để trống"
                   },
                   studentCode {
                       required : "Student Code không được để trống"
                   },
                   point{
                       required: "Point không được để trống"
                   }
               }
           });
        });
    </script>
</body>

</html>
