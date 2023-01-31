<t:form_with url="${not empty assignment ? UrlUtils.ASSIGNMENT_PATH.concat('/:id') : UrlUtils.ASSIGNMENT_PATH}" id="${assignment.id}"  method="${not empty assignment ? 'PATCH' : ''}">

  <c:if test="${empty assignment}">
  <div class="field">
    <input value="${course.id}" type="hidden" name="assignment[course_id]" id="assignment_course_id" />
  </div>
  </c:if>

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
	<textarea class="form-control" name="assignment[description]" id="assignment_description"><c:if test="${not empty assignment}">${assignment.description}</c:if></textarea>
  </div>
  
 <c:choose>
 
 <c:when test="${not empty assignment}">
 <div class="form-group">
	<label for="assignment_Grade Category">Grade category</label>
	<select class="form-control" name="assignment[grade_category_id]" id="assignment_grade_category_id">
	<c:forEach var="agc" items="${assignment_grade_categories}">
	 <c:choose>
		<c:when test="${agc.id != grade_category.id}"><option value="${agc.id}">${agc.title}</option></c:when> 
		<c:otherwise><option value="${agc.id}" selected="selected">${agc.title}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
	</select>
  </div>
 </c:when>
 <c:otherwise>
  <div class="form-group">
	<label for="assignment_Grade Category">Grade category</label>
	<select class="form-control" name="assignment[grade_category_id]" id="assignment_grade_category_id">
	<c:forEach var="agc" items="${assignment_grade_categories}"> 
	 <option value="${agc.id}">${agc.title}</option>
	</c:forEach>
	</select>
  </div>
 </c:otherwise>
 </c:choose>
<c:if test="${empty assignment}">
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
</c:if>

<c:if test="${empty assignment || assignment.assignment_type == 0}">
  <div class="form-group" id="fileOrLinkDiv">
	<label for="assignment_Students will:">Students will:</label>
	<select class="form-control" onchange="showOrHideFiletypes();" id="fileOrLinkSelect" name="assignment[file_or_link]">
	  <option value="upload_files_only" <c:if test="${assignment.file_or_link == 0}">selected</c:if>>Upload files only</option>
	  <option value="provide_urls_only" <c:if test="${assignment.file_or_link == 1}">selected</c:if>>Provide urls only</option>
	  <option value="both" <c:if test="${assignment.file_or_link == 2}">selected</c:if>>Both</option>
	</select>
  </div>
	
  <div class="form-group" id="allowedFileTypes">
	<label for="assignment_Allowed Filetypes (optional; separate multiple types with commas, for example: 'pdf,doc,docx')">Allowed filetypes (optional; separate multiple types with commas, for example: pdf, doc, docx)</label>
	<input class="form-control" type="text" value="${assignment.permitted_filetypes}" name="assignment[permitted_filetypes]" id="assignment_permitted_filetypes" />
  </div>
  
  <div class="form-group" id="fileLimit">
	<label id="limitLabel" for="assignment_Maximum Files and Links (instructors &amp; graders can exceed this limit):">Maximum files and links (instructors &amp; graders can exceed this limit):</label>
	<input class="form-control" type="number" value="${not empty assignment ? assignment.file_limit : 1}" name="assignment[file_limit]" id="assignment_file_limit" min="0" max="1000000000"/>
  </div>
	
  <script>showOrHideFiletypes()</script>
</c:if>
  <div class="actions">
	<input type="submit" name="commit" value="${not empty assignment && empty param.copy ? 'Update Assignment' : 'Create Assignment'}" class="btn btn-primary" data-disable-with="${not empty assignment ? 'Update Assignment' : 'Create Assignment'}"/>
  </div>
  
 <c:if test="${not empty param.copy}">
 	<input type="hidden" name="copy" id="copy" value="${assignment.id}">
 </c:if>
</t:form_with>