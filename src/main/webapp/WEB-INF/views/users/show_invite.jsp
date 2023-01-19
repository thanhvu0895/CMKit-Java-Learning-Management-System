<%@page  trimDirectiveWhitespaces="true"
	language="java" pageEncoding="UTF-8" 
	import="codingmentor.javabackend.k3.Utils.UrlUtils" 
%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags" %>

<t:layoutj pageTitle="Create Account">

<h1> Kit Account Creation </h1>

Welcome to Kit! To create your account, please fill in the following and click "Submit".
<t:form_with url="${UrlUtils.ACCEPT_USER_INVITE_PATH}" id="${userid}">
  <div class="form-group">
  	<f:text_field text_field="token" classBS="form-control" model="user" value="${token}" hidden="true"></f:text_field>
  </div>
  
  <div class="form-group">
  	<f:label label="First name (as registered)" model="user"></f:label>
    <f:text_field text_field="first_name" value="${user.first_name}" classBS="form-control" model="user"></f:text_field>
  </div>
  
  <div class="form-group">
  	<f:label label="Last name" model="user"></f:label>
	<f:text_field text_field="last_name" value="${user.last_name}" classBS="form-control" model="user"></f:text_field>
  </div>
  
  <div class="form-group">
  	<f:label label="Preferred Name/Nickname (optional- for those who do not go by their full first name)" model="user"></f:label>
  	<f:text_field text_field="preferred_name" value="${user.preferred_name}" classBS="form-control" model="user"></f:text_field>
  </div>
  
  <div class="form-group">
	<f:label label="Password" model="user"></f:label>
	<f:text_field text_field="password" type="password" classBS="form-control" model="user"></f:text_field>
  </div>
  
  <div class="form-group">
	<f:label label="Confirm password" model="user"></f:label>
	<f:text_field text_field="password_confirmation" type="password" classBS="form-control" model="user"></f:text_field>
  </div>
  
  <f:submit submitValue="Create Account" classBS="btn btn-success"></f:submit>
</t:form_with>

</t:layoutj>