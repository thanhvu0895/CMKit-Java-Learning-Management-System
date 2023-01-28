<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:form_with url="${not empty assignment ? UrlUtils.ASSIGNMENT_PATH.concat('/:id') : UrlUtils.ASSIGNMENT_PATH}" id="${assignment.id}"  method="${not empty assignment ? 'PATCH' : ''}">

  <div class="field">
    <input value="${course.id}" type="hidden" name="assignment[course_id]" id="assignment_course_id" />
  </div>

 <c:if test="${not empty klass}">
  <div class="field">
	<input type="hidden" value="${klass.id}" name="assignment[klass_id]" id="assignment_klass_id" />
  </div>
 </c:if>	


  <div class="form-group">
    <label for="assignment_title">Title</label>
    <input class="form-control" <c:if test="${not empty assignment}">value="${assignment.title}"</c:if> type="text" name="assignment[title]" id="assignment_title" />
  </div>
  
  <div class="form-group">
	<label for="assignment_Description or Instructions">Description or instructions</label>
	<textarea class="form-control" <c:if test="${not empty assignment}">value="${assignment.description}"</c:if> name="assignment[description]" id="assignment_description"></textarea>
  </div>
  
 <div class="form-group">
	<label for="assignment_Grade Category">Grade category</label>
	<select class="form-control" name="assignment[grade_category_id]" id="assignment_grade_category_id">
	<c:forEach var="agc" items="${assignment_grade_categories}"> 
	 <option value="${agc.id}">${agc.title}</option>
	</c:forEach>
	</select>
  </div>


<script>
  function showOrHideFiletypes()
  {
    if(document.getElementById('assignmentTypeSelect').value=='student_file')
	{
	  document.getElementById("fileOrLinkDiv").style.display='';
	  document.getElementById("fileLimit").style.display='';
	  fol = document.getElementById('fileOrLinkSelect').value
	  if(fol=='provide_urls_only')
	  {
	    document.getElementById("allowedFileTypes").style.display='none';
		document.getElementById("limitLabel").innerText = "Maximum number of URLs (instructors & graders can exceed this limit):"
	  }
	  else
	  {
	    document.getElementById("allowedFileTypes").style.display='';
		if(fol=='upload_files_only')
		{
		  document.getElementById("limitLabel").innerText = "Maximum number of files (instructors & graders can exceed this limit):"
		}
		else
		{
		  document.getElementById("limitLabel").innerText = "Maximum number of files and/or URLs (instructors & graders can exceed this limit):"
		}
	  }
	}
	else
	{
	  document.getElementById("allowedFileTypes").style.display='none';
	  document.getElementById("fileLimit").style.display='none';
	  document.getElementById("fileOrLinkDiv").style.display='none';
	}
  }
</script>
	
	
  <div class="form-group">
	<label for="assignment_Assignment Type">Assignment type</label>
	<select class="form-control" onchange="showOrHideFiletypes();" id="assignmentTypeSelect" name="assignment[assignment_type]">
	  <option value="student_file">Student file</option>
	  <option value="student_repo">Student repo</option>
	  <option value="professor_file">Professor file</option>
	  <option value="grade_only">Grade only</option>
	</select>
  </div>
  
  <div class="form-group" id="fileOrLinkDiv">
	<label for="assignment_Students will:">Students will:</label>
	<select class="form-control" onchange="showOrHideFiletypes();" id="fileOrLinkSelect" name="assignment[file_or_link]"><option value="upload_files_only">Upload files only</option>
	  <option value="provide_urls_only">Provide urls only</option>
	  <option value="both">Both</option>
	</select>
  </div>
	
  <div class="form-group" id="allowedFileTypes">
	<label for="assignment_Allowed Filetypes (optional; separate multiple types with commas, for example: 'pdf,doc,docx')">Allowed filetypes (optional; separate multiple types with commas, for example: pdf, doc, docx)</label>
	<input class="form-control" type="text" name="assignment[permitted_filetypes]" id="assignment_permitted_filetypes" />
  </div>
  
  <div class="form-group" id="fileLimit">
	<label id="limitLabel" for="assignment_Maximum Files and Links (instructors &amp; graders can exceed this limit):">Maximum files and links (instructors &amp; graders can exceed this limit):</label>
	<input class="form-control" type="number" value="1" name="assignment[file_limit]" id="assignment_file_limit" />
  </div>
	
  <script>showOrHideFiletypes()</script>
  
  <div class="actions">
	<input type="submit" name="commit" value="Create Assignment" class="btn btn-primary" data-disable-with="Create Assignment" />
  </div>
  
  <c:if test="${params == ':copy'}">
  
  </c:if>
</t:form_with>