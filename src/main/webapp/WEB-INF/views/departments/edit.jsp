<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${department.title} | Department Settings">
<ol class="breadcrumb">
  <%@ include file="_department_crumbs.jsp" %>
  <li class="active">Settings</li>
</ol>
<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":settings"/></jsp:include>

<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#edit_department"><strong>Edit Settings<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="edit_department" class="panel-collapse collapse in">
	  <div class="panel-body">
        <%@ include file="_form.jsp" %>
	  </div>
	</div>
  </div>
</div>

<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#edit_professors"><strong>Department Professors<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="edit_professors" class="panel-collapse collapse in">
	  <div class="panel-body">
	  
	    <table class="table table-hover">
	      <thead>
		    <tr>
			  <th>Name</th>
			  <th>Email</th>
			  <th>Admin?</th>
			  <th>Edit</th>
			</tr>
		  </thead>
		  <tbody>
		   <%-- //TODO: ADD department.department_professors.includes(:user) function in repository"> --%>
		   <c:forEach var="dp" items="${department_professors}" varStatus="loop">
			  <tr>
			    <td>${department_professor_users[loop.index].getFull_preferred_name()}</td>
			    <td><a href="mailto:${department_professor_users[loop.index].email}">${department_professor_users[loop.index].email}</a></td>
				<td>
				  <c:if test="${dp.admin}">
					<span class="glyphicon glyphicon-wrench" title="Admin" aria-label="Admin"></span>
				  </c:if>
				</td>
				<td><button data-toggle="collapse" data-target="#prof${dp.id}" class="btn btn-default">Edit</button></td> 
			  </tr>
			  <tr id="prof${dp.id}" class="collapse">
			    <td colspan=3>
			      <t:form_with url="${UrlUtils.DEPARTMENT_DEPARTMENT_PROFESSOR_PATH}" id="${dp.id}" secondId="${department.id}" method="patch" classBS="form-inline"> 
			      	<div class="form-group">
  					  <input name="department_professor[admin]" type="hidden" value="0"><input type="checkbox" value="${dp.admin}" checked="checked" name="department_professor[admin]" id="department_professor_admin">
  					  <label for="department_professor_Admin">Admin?</label>
					</div>
					<div class="form-group">
  					  <input type="submit" name="commit" value="Save" class="btn btn-success" data-disable-with="Save">
					</div>
			      </t:form_with>
				</td>
				<td>
					<t:link_to path="${UrlUtils.DEPARTMENT_DEPARTMENT_PROFESSOR_PATH}" classBS="btn btn-danger" id="${dp.id}" secondId="${department.id}" confirm="Are you sure?">Remove</t:link_to>
				</td>
			  </tr>
		   </c:forEach>
		  </tbody>
	    </table>
	  
	    <a data-toggle="collapse" href="#create_prof" class="btn btn-default">Add Professor</a>
        <div id="create_prof" class="collapse collapse-in">
        <t:form_with url="${UrlUtils.DEPARTMENT_DEPARTMENT_PROFESSORS_PATH}" secondId="${department.id}">
		    <div class="form-group">
			  <label for="department_professor_Emails (separate multiple with commas)">Emails (separate multiple with commas)</label>
			  <input class="form-control" type="text" name="department_professor[emails]" id="department_professor_emails">
			</div>

			<div class="form-group">
			  <input name="department_professor[admin]" type="hidden" value="0"><input type="checkbox" value="1" name="department_professor[admin]" id="department_professor_admin">
			  <label for="department_professor_Admin">Admin?</label>
			</div>
			
			<div class="actions">
			  <input type="submit" name="commit" value="Add" class="btn btn-success" data-disable-with="Add">
			</div>
		</t:form_with>
		</div>		
	  </div>
    </div>
  </div>
</div>

</t:layoutj>