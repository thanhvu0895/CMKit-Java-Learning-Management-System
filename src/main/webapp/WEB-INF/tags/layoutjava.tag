<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageTitle" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <title> Kit | ${pageTitle}</title>
    
    <!-- Favicon first, then CSS, then Font Awesome Theme, then Bootstrap CSS  -->
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/application.css"/>
	<script src="https://kit.fontawesome.com/2912a97a77.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        
    <!-- Viewport first, then refresh resubmission fix-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../views/components/_fix_refresh_resubmission.jsp"%>

  </head>
  
  <body>
	
	<!-- Login Navigation bar -->
    <nav class="navbar navbar-default bg-light">
      <div class="container-fluid">
      	<!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header d-flex align-items-center">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="logo" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""/></a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Kit</a>
        </div>
        
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="d-flex align-items-center">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/login">
            <span><i class="fa-solid fa-right-to-bracket"></i></span>
            Login
          </a>
        </div>
        
     
      </div><!-- /.container-fluid -->
    </nav>
	<!-- End Login Navigation bar -->

	<!-- BODY BEGIN -->
	<div class="container">
		 <!-- SHOW NOTICE AND ALERT-->
		 <c:if test = "${not empty sessionScope.notice}">
		 	<div class="alert alert-success">${sessionScope.notice}</div>
		 </c:if>
		 <% session.removeAttribute("notice"); %> 
		 <c:if test = "${not empty notice}">
		 	<div class="alert alert-success">${notice}</div>
		 </c:if>
		 <c:if test="${not empty alert}">
		      <div class="alert alert-danger">${alert}</div>
		 </c:if>
	   	<!-- END Notice and Alert-->

	   	 <jsp:doBody/>   	 
  	</div>
  	<!-- End Body -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
  </body>
</html>
