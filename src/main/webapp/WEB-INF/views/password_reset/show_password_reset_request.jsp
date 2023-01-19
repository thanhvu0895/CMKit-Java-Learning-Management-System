<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Request Password Reset">
	<h1>Request Password Reset</h1>
	
	<t:form_with url="${UrlUtils.SHOW_REQUEST_PASSWORD_RESET_PATH}"> 
		<div class="form-group">
			<label for="email">Email</label> 
			<input class="form-control" type="email" name="email" id="email" />
		</div>
		<input type="submit" name="commit" value="Next" class="btn btn-primary" data-disable-with="Next" />
	</t:form_with>
</t:layoutj>