<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layoutj pageTitle="Edit User">
<t:form_with action="${UrlUtils.USERS_PATH}/${user.id}">
  <div class="form-group">
    <label for="user_First Name:">First name:</label>
    <input class="form-control" type="text" value="${user.first_name}" name="user[first_name]" id="user_first_name" />
  </div>
  
  <div class="form-group">
    <label for="user_Last Name:">Last name:</label>
    <input class="form-control" type="text" value="${user.last_name}" name="user[last_name]" id="user_last_name" />
  </div>

  <div class="form-group">
    <label for="user_Preferred Name / Nickname (optional):">Preferred name / nickname (optional):</label>
    <input class="form-control" type="text" value="${user.preferred_name}" name="user[preferred_name]" id="user_preferred_name" />
  </div>
  
  <div class="form-group">
	<input name="user[admin]" type="hidden" value="${user.admin}" /><input type="checkbox" value="${user.admin}" checked="${user.admin}" name="user[admin]" id="user_admin" />
	<label for="user_Admin">Admin</label>
  </div>

  <div class="form-group">
  	<c:if test="${user.admin}">
  	</c:if>
	<input name="user[disabled]" type="hidden" value="${user.disabled}" /><input type="checkbox" value="${user.disabled}" name="user[disabled]" id="user_disabled" />
	<label for="user_Disabled">Disabled</label>
  </div>
  
  <div class="actions">
    <input type="submit" name="commit" value="Save" class="btn btn-success" data-disable-with="Save" />
  </div>
</t:form_with>
</t:layoutj>