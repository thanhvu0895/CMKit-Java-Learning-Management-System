<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
  <c:when test="${not empty department}">
  <t:form_with url="${UrlUtils.DEPARTMENTS_PATH}/:id" id="${department.id}" method="PATCH"> 
	  <div class="form-group">
	    <label for="department_title">Title</label>
	    <input class="form-control" type="text" value="${department.title}" name="department[title]" id="department_title">
	  </div>
	
	  <div class="actions">
	    <input type="submit" name="commit" value="Update Department" class="btn btn-success" data-disable-with="Update Department">
	  </div>
  </t:form_with>
 </c:when>
  
  <c:otherwise>
	<t:form_with url="${UrlUtils.DEPARTMENTS_PATH}">
		<div class="form-group">
			<label for="department_title">Title</label>
			<input class="form-control" type="text" name="department[title]" id="department_title" />
		</div>
	  
		<div class="actions">
			<input type="submit" name="commit" value="Create Department" class="btn btn-success" data-disable-with="Create Department" />
		</div>
	</t:form_with> 
  </c:otherwise>
</c:choose>


 <%-- <h1>DONE</h1> --%>