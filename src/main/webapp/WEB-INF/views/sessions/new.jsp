<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Log In">
	<div class="page-header">
		<h1> Log In </h1>
	</div>
	
	<form action="./login" method="post">
	 	<div class="form-group">
			<label for="email">Email</label>
			<input class="form-control" type="email" name="email" id="email" />
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input class="form-control" type="password" name="password" id="password" />
		</div>
		<input type="submit" name="commit" value="Log In" class="btn btn-primary" data-disable-with="Log In" />
	</form>
	<br><br>
	<a class="btn btn-default" href="./request_password_reset">Forgot Password</a>
</t:layoutj>