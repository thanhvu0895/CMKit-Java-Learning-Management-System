<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${assignment.title} | Submissions">

<ol class="breadcrumb">
<%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li class="active">Submissions</li>
</ol>

<jsp:include page="../assigneds/_tabs.jsp"><jsp:param name="current" value=":submissions"/></jsp:include>

<center>
<br>
<c:choose><c:when test="${assigned.hide_grades}">
 <div class="alert alert-warning">
    Grades and feedback currently hidden from students. Grades also do not currently count towards overall grades.
    ASSIGNMENT_ASSIGNED_TOGGLE_HIDE_GRADES_PATH
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_TOGGLE_HIDE_GRADES_PATH}" classBS="btn btn-success" method="PATCH" id="${assigned.id}" secondId="${assignment.id}">Un-hide Grades</t:link_to>
 </div>
</c:when><c:otherwise>
<t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_TOGGLE_HIDE_GRADES_PATH}" classBS="btn btn-warning" method="PATCH" id="${assigned.id}" secondId="${assignment.id}">Hide Grades From Students</t:link_to>
</c:otherwise></c:choose>

<c:if test="${assignment.studentResponsible()}">
  <br><br>
  
  Notify me on new submissions:
  <%-- <%= render 'users/notification_toggle_button', property: current_user.notify_grader_new_submissions.where(assigned: @assigned).any?, link: toggle_submitted_to_grader_notification_path(@assigned, return_url: request.original_url)%> --%>

</c:if>

<br>
Notify me on regrade requests:
<%-- <%= render 'users/notification_toggle_button', property: current_user.notify_grader_regrade_request.where(assigned: @assigned).any?, link: toggle_regrade_request_notification_path(@assigned, return_url: request.original_url)%> --%>

<br><br>

<c:choose><c:when test="${assignment.getAssignmentType().equals('student_file') || assignment.getAssignmentType().equals('professor_file')}">
 <p>
	<strong>Submitted files also available in this repository: </strong>
	<%-- <kbd><%= get_repo_url @assigned.repo %></kbd> --%>
	<br><br>
	
	<div class="btn btn-group">
      <%-- <% if session[:enable_file_viewer] %>
        <%= link_to disable_file_viewer_path, method: :post, class: "btn btn-default" do %><span class="glyphicon glyphicon-eye-close"></span>  Disable File Viewer<%end%>
        <%= link_to file_viewer_settings_path(return_url: request.original_url), class: "btn btn-default" do %><span class="glyphicon glyphicon-cog"></span>  Settings<%end%>
	  <% else %>
        <%= link_to enable_file_viewer_path, method: :post, class: "btn btn-default" do %><span class="glyphicon glyphicon-eye-open"></span>  Enable File Viewer<%end%>
        <%= link_to file_viewer_settings_path(return_url: request.original_url), class: "btn btn-default" do %><span class="glyphicon glyphicon-cog"></span>  Settings<%end%>
	  <% end %> --%>
	</div>
	
	<t:link_to path="${UrlUtils.ASSIGNMENT_BULK_DOWNLOAD_PATH}" classBS="btn btn-default" id="${assigned.id}" secondId="${assignment.id}">
		<span class="glyphicon glyphicon-download" aria-hidden="true"></span><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>  Download All (zip)
	</t:link_to>
	<span style="color:grey"> | </span>
	
	<t:link_to path="${UrlUtils.ASSIGNMENT_VIEW_BULK_UPLOAD_PATH}" classBS="btn btn-default" id="${assigned.id}" secondId="${assignment.id}">
		<span class="glyphicon glyphicon-upload" aria-hidden="true"></span><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>  Bulk Upload
	</t:link_to>
	
	<t:link_to path="${UrlUtils.ASSIGNMENT_VIEW_BULK_SORT_PATH}" classBS="btn btn-default" id="${assigned.id}" secondId="${assignment.id}">
		<span class="glyphicon glyphicon-transfer" aria-hidden="true"></span>  Sort Bulk Uploads
	</t:link_to>
	  
<%-- 	<%=link_to assignment_view_bulk_sort_path(@assigned.assignment, @assigned), class: "btn btn-default", disabled: Dir.entries(@assigned.repo.get_repository_read_directory+File::SEPARATOR+"unsorted").size<=3 do %>
	  <span class="glyphicon glyphicon-transfer" aria-hidden="true"></span>  Sort Bulk Uploads
	<% end %> --%>
	

</c:when><c:when test="${assignment.getAssignmentType().equals('student_repo') }">
 <button class="btn btn-default" data-toggle="collapse" href="#cloneAll" onclick="this.style.display = 'none';">Clone All</button>
  
  <div class="collapse collapse-in" id="cloneAll">
    <div class="well">
	  Click the button below to download a shell script (designed for unix-like consoles) to clone all repositories at once.
	  To use it, place it where you want the repositories to be cloned and run it with <kbd><%-- <%= "sh clone-all"+@assigned.id.to_s+".sh" %> --%></kbd>.
	  If your SSH key is password protected, you may still have to type your password repeatedly.
	  
	    <%-- <%# Apparently you can't use send_data as a response to a form, or some browsers just ignore the response. Have to do this in a hacky javascript way %> --%>
	  
	   <br>
      <input type="checkbox" id="cloneIncludePrevious" onchange="cloneToggleParams()"><b>  Include Previous Submissions </b>
	  <br>
      <input type="checkbox" id="cloneIncludeIncomplete" onchange="cloneToggleParams()"><b>  Include Incomplete Submissions </b>
	  <br>
	  
	  <a id="cloneDownload" class="btn btn-success">Download Script</a>
	  
	  <script>
	    clone_all_target = "<t:link_to path="${UrlUtils.ASSIGNMENT_CLONE_ALL_PATH}" id="${assigned.id}" secondId="${assignment.id}"></t:link_to>?"
		
		function cloneToggleParams()
		{
		  str = clone_all_target
		  if(document.getElementById('cloneIncludePrevious').checked)
		  {
			str += "&include_prev=1"
		  }
		  
		  if(document.getElementById('cloneIncludeIncomplete').checked)
		  {
		    str += "&include_incomplete=1"
		  }
		  
		  document.getElementById('cloneDownload').href = str
		}
		
		cloneToggleParams()
	  </script>

	</div>
	
  </div>
  <br><br>
  </c:when></c:choose>
  <%-- <% has_rubric = @assigned.assignment.problems.any? %> --%>
  <table class="table table-bordered">
<thead>
  <tr>
    <th colspan="${assignment.incompletePossible() ? 7 : 6}">
	  <center>Show:</center>
	</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="active"><center>
	  <input type="checkbox" id="showNoSubmission" onchange="hideRows()" checked>  Not Yet Submitted
	</center></td>
	
	<td class="danger"><center>
      <input type="checkbox" id="showOverdue" onchange="hideRows()" checked>  Overdue
	</center></td>
	
	<c:if test="${assignment.incompletePossible()}">
	  <td class="active"><center>
	    <input type="checkbox" id="showIncomplete" onchange="hideRows()" checked>  Incomplete
	  </center></td>	
	</c:if>
		
	<c:choose><c:when test="${has_rubric}">
	  <td class="warning"><center>
        <input type="checkbox" id="showReady" onchange="hideRows()" checked>  Needs Grading
	  </center></td>	
	</c:when>
	<c:otherwise>
	  <td><center>
        <input type="checkbox" id="showReady" onchange="hideRows()" checked> Ungraded
	  </center></td>
	</c:otherwise></c:choose>
	
	<td class="success"><center>
      <input type="checkbox" id="showGraded" onchange="hideRows()" checked>  Graded
	</center></td>
	
	<td class="info"><center>
      <input type="checkbox" id="showRegrade" onchange="hideRows()" checked>  Regrade Requested
	</center></td>
	
	<td><center>
	  <input type="checkbox" id="showPrevious" onchange="hideRows()">  Previous Submissions
	</center></td>
  </tr>
</tbody></table>

</center>
  
 <table class="table table-hover" data-classes="table-no-bordered" data-toggle="table" data-search="true" maintainMetaData="true" maintainSelected="true" id="subsTable" data-sort-name="user" data-sort-order="asc">
  <thead>
    <tr>
      <th data-sortable="true" data-field="user">User(s)</th>
      <th data-sort-name="_submitted_data.raw-time" data-sortable="true" data-field="submitted">Submitted</th>
	  <th>Extension</th>
	  <th data-sort-name="_grade_data.raw-grade" data-sortable="true" data-field="grade">Problems Graded / Grade</th>
	  <c:if test="${assignment.getAssignmentType().equals('student_repo')}">
	    <th data-field="repo" data-sortable="true">Repository</th>
	  </c:if>
  	  <th></th>
    </tr>
  </thead>
  
  
</t:layoutj>
<% //TODO: FINISH REMAINING FUNCTION for checking if else%>
<% //TODO: MISSING CONTRIBUTORS AND SUBMISSIONS%>