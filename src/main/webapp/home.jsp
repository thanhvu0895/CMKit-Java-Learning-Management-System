<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>Home page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css" />
    <!-- Required meta tags -->
	<!-- Font Awesome -->
	<script src="https://kit.fontawesome.com/2912a97a77.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  </head>
  <body>
   <nav class="navbar bg-light">
     <div class="container-fluid">
       <div class="d-flex align-items-center">
         <div class="logo">
           <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""/></a>
         </div>
         <a class="navbar-brand" href="${pageContext.request.contextPath}/">Kit</a>
       </div>
       <div class="d-flex align-items-center">
         <a class="dropdown-item" href="${pageContext.request.contextPath}/login">
           <span><i class="fa-solid fa-right-to-bracket"></i></span>
           Login
         </a>
       </div>
     </div>
   </nav>
    
	<div class="container">
	<!-- SHOW NOTICE AND ALERT-->	
   	<!-- END -->
	<div class="wrapper">
      <form action="" method ="post">
        <h1 class="mt-4 mb-4">Log In</h1>
         <input type="hidden" name="origin" value="${LOGIN_USER}">
            <c:if test="${not empty error}">
                <div>* SHOW ERROR: ${error}</div>
            </c:if>
        <p>Email</p>
        <input type="email" name="email" class="form-control"/>
        <p>Password</p>
        <input type="password" name="password"  class="form-control"/>
        <button class="btn mt-3">Login</button><br />
      </form>
      <button class="btn" id="forgotPassword">Forgot password</button>
    </div>
    </div>

    <footer>
   		<%@include file="/WEB-INF/views/components/_fix_refresh_resubmission.jsp"%>
    </footer>     
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>