<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:layoutjava pageTitle="Log In">
    <jsp:body>
    <c:set var="pageTitle" value="TESTTEST"/>
      <div class="wrapper">
	      <form action="" method ="post">
	        <h1 class="mt-4 mb-4">Log In</h1>
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
    </jsp:body>
</t:layoutjava>