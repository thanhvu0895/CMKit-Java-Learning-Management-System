<%@page language="java" pageEncoding="UTF-8"
	import="codingmentor.javabackend.k3.Utils.UrlUtils"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="Users">
	<h1>User Management</h1>
	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" href="#all_users"><strong>All Users<span class="caret"></span>
					</strong></a>
				</h4>
			</div>
			<div id="all_users" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-hover" data-toggle="table"
						data-classes="table-no-bordered" data-sort-name="Email"
						data-sort-order="asc" data-search="true">
						<thead>
							<tr>
								<th data-sortable="true" data-field="Email">Email</th>
								<th data-sortable="true">Admin</th>
								<th data-sortable="true">Status</th>
								<th data-sortable="true">Name</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach var="user" items="${users}">
						  	<tr>
							<td>${user.email}</td>
							<td>
						      <c:if test="${user.admin}"><span class="glyphicon glyphicon-wrench" title="Admin"aria-label="Admin"></span></c:if>
							</td>
									<td>
									  <c:choose>
										<c:when test="${user.disabled}">
										  <span class="glyphicon glyphicon-ban-circle"title="Disabled" aria-label="Disabled"></span>
										</c:when>
										<c:when test="${!user.set_up}">
										  <span class="glyphicon glyphicon-off" title="Not Set Up" aria-label="Not Set Up"></span>
										  <t:link_to path="${UrlUtils.RESEND_USER_INVITE_PATH}" id="${user.id}" method="post" classBS="btn btn-default">Resend Invite</t:link_to>
										</c:when>
										</c:choose>
									</td>
									<td>
									  ${user.getFull_name()}
									</td>
									<td><t:link_to path="${UrlUtils.USER_EDIT_ADMIN_PATH}" id="${user.id}" classBS="btn btn-primary">Edit</t:link_to></td>
									<td><t:link_to path="${UrlUtils.USERS_PATH}/:id" id="${user.id}" confirm="Are you sure you want to delete this user?"  classBS="btn btn-danger">Delete</t:link_to></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<br>
	
	<button data-toggle="collapse" data-target="#new_user" class="btn btn-primary">Invite User</button>
	<div id="new_user" class="collapse">
	  <div class="panel panel-default">
		<div class="panel-body">
			<t:form_with url="${UrlUtils.CREATE_USER_INVITE_PATH}">
				<div class="form-group">
					<label for="Email">Email</label>
					<input class="form-control" type="email" name="email" id="email" />
				</div>
				<div class="form-group">
		  			<input name="admin" type="hidden" value="0" />
		  			<input type="checkbox" value="1" name="admin" id="admin" />
		  			<label for="Admin">Admin</label>
				</div>
				<div class="actions">
					<input type="submit" name="commit" value="Invite" class="btn btn-success" data-disable-with="Invite" />
				</div>
			</t:form_with>	
		</div>
	  </div>
	</div>
</t:layoutj>
