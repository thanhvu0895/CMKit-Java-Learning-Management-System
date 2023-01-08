<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Log In">
	<form action="${pageContext.request.contextPath}/change_password" accept-charset="UTF-8" data-remote="true" method="post">
		<div class="form-group">
    		<label for="Old Password">Old password</label>
			<input class="form-control" type="password" name="old_password" id="old_password" />
		</div>
  
		<div class="form-group">
			<label for="New Password">New password</label>
			<input class="form-control" type="password" name="new_password" id="new_password" />
		</div>
	  
		<div class="form-group">
			<label for="Confirm New Password">Confirm new password</label>
			<input class="form-control" type="password" name="new_password_confirmation" id="new_password_confirmation" />
		</div>
	  
		<input type="submit" name="commit" value="Change Password" class="btn btn-primary" data-disable-with="Change Password" />
	</form>
</t:layoutj>