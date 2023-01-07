<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageTitle" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <title> Kit | ${pageTitle}</title>
    <!-- Favicon, CSS, Bootstrap CSS  -->
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cmkit.css"/>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">        
    <!-- Viewport,  refresh resubmission fix-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../views/components/_fix_refresh_resubmission.jsp"%>
  </head>
  <body>

 <!-- Login Navigation bar -->
	<nav class="navbar navbar-expand-md bg-light">
		<div class="container-fluid">
			<!-- Logo and Brand Name-->
			<div class="navbar-header d-flex align-items-center">
				<a class="logo" href=""><img src="${pageContext.request.contextPath}/assets/images/logo.png"/></a>
				<a class="navbar-brand" href="">Kit</a>
			</div>
			
			<!-- Collapse Button-->	
			<button class="navbar-toggler flex-wrap mr-auto" data-bs-toggle="collapse" data-bs-target=".navbar-collapse" aria-expanded="false">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<!--  If LOGGED IN -->
			<c:choose> 
				<c:when test="${not empty sessionScope.current_user}">
				<div class="collapse navbar-collapse justify-content-end" id="top-navbar">
					<ul class="nav navbar-nav">
					<!-- If currentuser.admin || currentuser.departmentProfessors  -->
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="" class="dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.current_user}<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#"></a>
							</ul>
						</li>
					</ul>
					
				</div>
				</c:when>
				
				<c:otherwise>
				NOT LOGGED IN
			<!-- Collapse items to be displayed-->
			<div class="collapse navbar-collapse justify-content-end" id="top-navbar-nouser">
				<ul class="navbar-nav"> 
					<li class="nav-item"><a href="${pageContext.request.contextPath}/login" class="nav-link">
						<img src="${pageContext.request.contextPath}/assets/images/right-to-bracket-solid.png" width="16px" height="16px"/> Log In</a>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav> 
				</c:otherwise>
			</c:choose>
			
			

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
  	<p>${UrlUtils.SSH_KEYS} Hello WORLD</p>
			<a href="${UrlUtils.SSH_KEYS}">Test</a>
  	<!-- End Body -->
  <!-- jQuery first, then Bootstrap JS, then Popper.js, then FontAwesome -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
  </body>
</html>