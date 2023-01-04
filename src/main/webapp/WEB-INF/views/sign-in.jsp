<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css" />
   <link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
  rel="stylesheet"
	/>
	<!-- Font Awesome -->
	<script src="https://kit.fontawesome.com/2912a97a77.js"></script>
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
      
      <a href="${pageContext.request.contextPath}/request_password_reset">
  		<button class="btn" id="forgotPassword">Forgot password</button>
	  </a>
	  
    </div>
    </div>
  <footer>
	<%@include file="components/_fix_refresh_resubmission.jsp"%> 
  </footer>
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
  </body>
</html>
