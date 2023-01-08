<%@ tag import="codingmentor.javabackend.k3.Utils.UrlUtils"
	language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="pageTitle" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Kit | ${pageTitle}</title>
<!-- Favicon, Bootstrap CSS, Font-Awesome  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2912a97a77.js"></script>
<!-- Viewport,  refresh resubmission fix-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../views/components/_fix_refresh_resubmission.jsp"%>
</head>
<body>
	<!-- Login Navigation bar -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display-->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./">Kit</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
  <c:choose>
  	<c:when test="${not empty sessionScope.current_user}">
  	<div class="collapse navbar-collapse" id="top-navbar">
		<ul class="nav navbar-nav">
			<!-- IF CURRENT USER IS ADMIN -->
			
			<!-- END IF USER IS ADMIN -->
			<li><a href=${pageContext.request.contextPath}/klasses>My Classes</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Thanh <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/users/edit_self">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						Account Settings
					</a></li>
					<li><a href="${pageContext.request.contextPath}/users/notification_settings">
						<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
						Notification Settings
					</a></li>
					<li><a href="${pageContext.request.contextPath}/ssh_keys">
						<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
						SSH Keys
					</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="${pageContext.request.contextPath}/logout">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
						Log Out
					</a></li>
 				</ul>
			</li>
  		</ul>
	</div>	
  	</c:when>
  	
  	<c:otherwise>
			<div class="collapse navbar-collapse" id="top-navbar-nouser">
			    <ul class="nav navbar-nav navbar-right">
					<li>
						<a href=".${UrlUtils.SIGN_IN}">
						<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
						Log In
						</a>
					</li>
				</ul>
			</div> 	  	
  	</c:otherwise>
  </c:choose>
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
	<!-- jQuery first, then Bootstrap JS, then others-->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
