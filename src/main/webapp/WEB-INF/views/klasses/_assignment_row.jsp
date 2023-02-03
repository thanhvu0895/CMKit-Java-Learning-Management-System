 <tr>
	<td><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${a.id}">${a.title}</t:link_to></td> 
	<td>${not empty grade_categories[loop.index].title ? grade_categories[loop.index].title : "None"}</td>
	<td>
	  <c:choose><c:when test="${assigneds[loop.index].due_date != null}">
	 	${assigneds[loop.index].getAdjustedMaxGrade()}
	  </c:when><c:otherwise>
		${a.getPointValue() == 0 ? 0 : a.getPointValue()}
	  </c:otherwise></c:choose>
  	</td>
  	<td>${a.getAssignmentType()}</td>
  	  	
  	<c:choose><c:when test="${assigneds[loop.index].due_date != null}">
  	  <td>${assigneds[loop.index].formatDueDate()}</td>
  	  <td>
  	    ASSIGNED GRADERS MAP
  	  </td>
  	  <td width="30%">ASSIGNMENT STATUS</td>
  	  <td>
 	  	  <c:choose><c:when test="${a.getAssignmentType() == 'student_file' || a.getAssignmentType() == 'student_repo'}">
	  		<t:link_to path="${UrlUtils.SUBMISSIONS_PATH}?assigned=:id" classBS="btn btn-primary" id="${assigneds[loop.index].id}">Submissions & Grading</t:link_to>
	  	  </c:when><c:when test="${a.getAssignmentType() == 'professor_file' }">
	  	  	<t:link_to path="${UrlUtils.SUBMISSIONS_PATH}?assigned=:id" classBS="btn btn-primary" id="${assigneds[loop.index].id}">Upload & Grade</t:link_to>
	  	  </c:when><c:otherwise>
	  	 	<t:link_to path="${UrlUtils.SUBMISSIONS_PATH}?assigned=:id" classBS="btn btn-primary" id="${assigneds[loop.index].id}">Grade</t:link_to> 
	  	  </c:otherwise>
	  	  </c:choose>
  	  </td>
  	</c:when><c:otherwise>
	<td>-</td>
	<td>-</td>
	<td>
		-
	</td>
	<td><t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGN_PATH}?class=:id" classBS="btn btn-default" id="${klass.id}" secondId="${a.id}">Assign</t:link_to></td>
	</c:otherwise></c:choose>
</tr>