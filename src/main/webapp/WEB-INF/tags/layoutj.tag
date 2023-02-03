<%@tag import="java.util.Enumeration"%>
<%@ tag trimDirectiveWhitespaces="true" language="java"
	pageEncoding="ISO-8859-1"%>
<%@ attribute name="pageTitle" required="true"%>
<%@tag import="codingmentor.javabackend.k3.Utils.UrlUtils"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%
Enumeration<String> attributes = request.getSession().getAttributeNames();
while (attributes.hasMoreElements()) {
    String attribute = (String) attributes.nextElement();
    System.out.println(attribute+" : "+request.getSession().getAttribute(attribute));
}
%> 
<html lang="en">
<head>
<title>Kit | ${pageTitle}</title>
<!-- Favicon, Bootstrap, Application CSS -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/application.css">	
<!-- Viewport,  refresh resubmission fix-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="./_fix_refresh_resubmission.jsp"%>
</head>
<body>
	<!-- Login Navigation bar -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display-->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}">Kit</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<c:choose>
				<c:when test="${not empty current_user}">
					<div class="collapse navbar-collapse" id="top-navbar">
						<ul class="nav navbar-nav">
							<!-- IF CURRENT USER IS ADMIN OR DEPARTMENT PROFESSOR-->
							<c:if test="${current_user.admin || current_user.isDepartmentProfessor()}">
							
								<li class="dropdown">
								  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Departments <span class="caret"></span></a>
								  <ul class="dropdown-menu">
									<!--  DEPARTMENT PROFESSOR's DEPARTMENT's LIST -->
									<c:forEach var="department" items="${current_user.getDepartments()}">
									  <li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${department.id}">${department.title}</t:link_to></li>
									</c:forEach>
									
								    <c:if test="${current_user.admin}">
									 <li role="separator" class="divider"></li>
									 <li><t:link_to path="${UrlUtils.DEPARTMENTS_PATH}">All Departments</t:link_to></li>
									 </c:if>
								  </ul>
								</li>
							</c:if>
							<!-- END IF USER IS ADMIN OR DEPARTMENT PROFESSOR-->
							<li><t:link_to path="${UrlUtils.KLASSES_PATH}">My Classes</t:link_to></li>
							<c:if test="${current_user.admin}">
								<li><t:link_to path="${UrlUtils.USERS_PATH}">Users<span class="sr-only">(current)</span></t:link_to></li>
							</c:if>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${current_user.preferred_first_name} <span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><t:link_to path="${UrlUtils.USER_EDIT_SELF_PATH}">
									<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						Account Settings</t:link_to></li>
									<li><t:link_to
											path="${UrlUtils.NOTIFICATION_SETTINGS_PATH}">
											<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
						Notification Settings
					</t:link_to></li>
									<li><t:link_to path="${UrlUtils.SSH_KEYS_PATH}">
											<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
						SSH Keys
					</t:link_to></li>
									<li role="separator" class="divider"></li>
									<li><t:link_to path="${UrlUtils.LOGOUT_PATH}">
											<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
						Log Out
					</t:link_to></li>
								</ul></li>
						</ul>
					</div>
				</c:when>

				<c:otherwise>
					<div class="collapse navbar-collapse" id="top-navbar-nouser">
						<ul class="nav navbar-nav navbar-right">
							<li><t:link_to path="${UrlUtils.LOGIN_PATH}"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Log In</t:link_to></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-- End Login Navigation bar -->

	<!-- BODY BEGIN -->
	<div class="container">
		<!-- SHOW NOTICE AND ALERT-->
		<c:if test="${not empty sessionScope.notice}">
			<div class="alert alert-success">${sessionScope.notice}</div>
		</c:if>
		<c:remove var="notice"/>
		
		<c:if test="${not empty alert}">
			<div class="alert alert-danger">${alert}</div>
		</c:if>
		<c:remove var="alert"/>

		<!-- END Notice and Alert-->
		<jsp:doBody />
	</div>
	<!-- End Body -->
	<!-- jQuery first, then Bootstrap JS, then others-->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
		integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>
