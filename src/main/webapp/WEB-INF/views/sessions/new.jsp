<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Log In">
	<div class="page-header">
		<h1> Log In </h1>
	</div>
	<t:form_with action="${UrlUtils.LOGIN_PATH}">
	 	<div class="form-group">
			<label for="email">Email</label>
			<input class="form-control" type="email" name="email" id="email" />
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input class="form-control" type="password" name="password" id="password" />
		</div>
		<input type="submit" name="commit" value="Log In" class="btn btn-primary" data-disable-with="Log In" />
	</t:form_with>
	<br><br>
	
	<t:link_to path="${UrlUtils.SHOW_REQUEST_PASSWORD_RESET_PATH}" classBS="btn btn-default">Forgot Password</t:link_to>
</t:layoutj>