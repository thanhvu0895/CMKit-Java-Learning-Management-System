<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:form_with_copy action="">
	<label for="user_First Name:">First name: ${user.first_name }</label>
	<t:formcheckbox value="${user.admin}"></t:formcheckbox>
	<div class="actions">
	<div class="actions">
    	<input type="submit" name="commit" value="Save" class="btn btn-success" data-disable-with="Save" />
  	</div>
  	</div>
</t:form_with_copy>