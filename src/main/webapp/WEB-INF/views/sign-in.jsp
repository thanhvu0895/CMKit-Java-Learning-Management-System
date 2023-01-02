<%@ page contentType="text/html;charset=UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css" />
   	<%@include file="components/css-js.jsp"%> 
  </head>
  <body>
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
        <a href="${pageContext.request.contextPath}">
          <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""/>
          </div>
          <a class="navbar-brand">Kit</a>
        </div></a>
        <div class="d-flex align-items-center">
          <a class="dropdown-item" href="#">
            <span><i class="fa-solid fa-right-to-bracket"></i></span>
            Login
          </a>
        </div>
      </div>
    </nav>
	
	<!-- SHOW NOTICE AND ALERT-->
 	<div class="container">
	   <c:if test="${not empty notice}">
	       <div class="alert alert-success">${notice}</div>
	   </c:if>
		<c:if test="${not empty alert}">
	       <div class="alert alert-danger">${alert}</div>
		</c:if>
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
	<%@include file="components/_fix_refresh_resubmission.jsp"%> 
  </footer>
  </body>
</html>
