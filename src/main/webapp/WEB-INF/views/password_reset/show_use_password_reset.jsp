<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Reset Password">
	<h1>Request Password Reset</h1>
	
	<t:form_with url="${UrlUtils.SHOW_USER_PASSWORD_RESET_PATH}"> 
		<div class="field">
			<f:text_field text_field="user_id" hidden="true" value="${userid}"></f:text_field>			
		</div>
		<div class="field">
			<f:text_field text_field="token" hidden="true" value="${token}"></f:text_field>			
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
	</t:form_with>
</t:layoutj>