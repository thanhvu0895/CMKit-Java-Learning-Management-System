<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Log In">
	<form action="/users/98" accept-charset="UTF-8" method="post">
		<div class="form-group">
			<label for="user_Preferred Name / Nickname (optional):">Preferred name / nickname (optional):</label>
			<input class="form-control" type="text" value="Thanh" name="user[preferred_name]" id="user_preferred_name" />
		</div>
	  
		<div class="actions">
			<input type="submit" name="commit" value="Save" class="btn btn-success" data-disable-with="Save" />
		</div>
	</form><br><br><br>
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/change_password">Change Password</a>
</t:layoutj>




