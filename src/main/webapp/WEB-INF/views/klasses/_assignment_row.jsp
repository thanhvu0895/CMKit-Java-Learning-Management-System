<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${not empty param.kgc}"></c:if>
 <tr>
  <%-- <% asd = @assigneds.where(assignment_id: a.id).first %> --%>
   <td><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${a.id}">${a.title}</t:link_to></td> 
   <td>${not empty kgc[loop.index] ? a.grade_category.title : "None"}</td>
  <td>
  <%--   <% if asd %>
      <%= asd.get_adjusted_max_grade %>
	<% else %>
	  <%= a.get_point_value %>
	<% end %> --%>
  </td>
  <td>${a.getAssignmentType()}</td>
  <%-- <% if asd %> --%>
	<%-- <td><%= asd.due_date.strftime("%A, %b %d at %I:%M%p") %></td> --%>
	<td>
	  <%-- <%= asd.assigned_graders.map{|ag| ag.user}.map{|u| u.get_full_preferred_name}.join(", ") %> --%>
	</td>
	<%-- <td width="30%"><%= render 'klasses/assignment_status', assigned: asd, assignment: a%></td> --%>
	<td>
	<%-- 	<% if a.student_file? || a.student_repo? %>
		  <%= link_to 'Submissions & Grading', submissions_path(assigned: asd.id), class: "btn btn-primary" %>
		<% elsif a.professor_file? %>
		  <%= link_to 'Upload & Grade', submissions_path(assigned: asd.id), class: "btn btn-primary" %>
		<% else %>
		  <%= link_to 'Grade', submissions_path(assigned: asd.id), class: "btn btn-primary" %>
		<% end %> --%>
	</td>
  <%-- <% else %> --%>
	<td>-</td>
	<td>-</td>
	<td>
	  -
	</td>
	<%-- <td><%= link_to 'Assign', assignment_assign_path(a, class: @klass.id), class: "btn btn-default" %></td> --%>
  <%-- <% end %> --%>
</tr>
