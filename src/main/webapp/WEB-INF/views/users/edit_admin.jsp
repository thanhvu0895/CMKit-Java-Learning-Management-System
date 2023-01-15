<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Edit User">
<t:form_with url="${UrlUtils.USERS_PATH}/${user.id}" method="edit_admin" }>

  <div class="form-group">
  	<f:label label="First Name:" model="user"></f:label>
  	<f:text_field text_field="first_name" classBS="form-control" model="user" value="${user.first_name}"></f:text_field>
  </div>
  
  <div class="form-group">
  	<f:label label="Last Name:" model="user"></f:label>
  	<f:text_field text_field="last_name" classBS="form-control" model="user" value="${user.last_name}"></f:text_field>
  </div>

  <div class="form-group">
    <f:label label="Preferred Name / Nickname (optional):" model="user"></f:label>
    <f:text_field text_field="preferred_name" classBS="form-control" model="user" value="${user.preferred_name}"></f:text_field>
  </div>
  
  <div class="form-group">
  	<f:check_box check_box="admin" model="user" value="${user.admin}"></f:check_box>
	<label for="user_Admin">Admin</label>
  </div>

  <div class="form-group">
  	<f:check_box check_box="disabled" model="user" value="${user.disabled}"></f:check_box>
	<label for="user_Disabled">Disabled</label>
  </div>
  
  <div class="actions">
    <f:submit submitValue="Save" classBS="btn btn-success"></f:submit>
  </div>
</t:form_with>
</t:layoutj>