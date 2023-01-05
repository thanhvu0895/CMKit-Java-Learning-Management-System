<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageTitle" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
	<title> Kit | ${pageTitle}</title>
    
    <!-- Favicon first, then CSS, then Font Awesome Theme, then Bootstrap CSS  -->
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css"/>
	 
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">        
	<!-- Viewport first, then refresh resubmission fix-->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="../views/components/_fix_refresh_resubmission.jsp"%>
  </head>
  
   <body>
	<!-- Login Navigation bar -->
	<nav class="navbar navbar-default bg-light navbar-expand-md">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header d-flex align-items-center">
				<a class="logo" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/images/logo.png"/></a>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Kit</a>
			</div>
			<button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target=".navbar-collapse" aria-expanded="false">
					<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav navbar-right"> 
					<li class="nav-item"><a href="${pageContext.request.contextPath}/login" class="nav-link">
						<img src="${pageContext.request.contextPath}/assets/images/right-to-bracket-solid.svg"/> Log In</a>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav> 
	<!-- End Login Navigation bar -->

	<jsp:doBody/>   	 
  	<!-- End Body -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
