<c:choose>
<c:when test="${not empty problem}">
<t:form_with url="${UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH}" id="${problem.id}" secondId="${assignment.id}" method="PATCH">

<div class="form-group">
    <label for="problem_title">Title</label>
    <input class="form-control" type="text" name="problem[title]" id="problem_title" value="${problem.title}"/>
  </div>

  <div class="form-group">
    <label for="problem_points">Points</label>
    <input step="any" class="form-control" type="number" value="${problem.points}" name="problem[points]" id="problem_points" />
  </div>

  <div class="form-group">
    <label for="problem_Grader Notes (visible only to grader, will show simple HTML formatting):">Grader notes (visible only to grader, will show simple html formatting):</label>
    <textarea class="form-control" name="problem[grader_notes]" id="problem_grader_notes">${problem.grader_notes}</textarea>
    
  </div>

  <div class="actions">
    <input type="submit" name="commit" value="Update Problem" class="btn btn-primary" data-disable-with="Update Problem" />
  </div>

</t:form_with>
</c:when>

<c:otherwise>

<t:form_with url="${UrlUtils.ASSIGNMENT_PROBLEMS_PATH}" secondId="${assignment.id}">

<div class="form-group">
    <label for="problem_title">Title</label>
    <input class="form-control" type="text" name="problem[title]" id="problem_title" />
  </div>

  <div class="form-group">
    <label for="problem_points">Points</label>
    <input step="any" class="form-control" type="number" name="problem[points]" id="problem_points" />
  </div>

  <div class="form-group">
    <label for="problem_Grader Notes (visible only to grader, will show simple HTML formatting):">Grader notes (visible only to grader, will show simple html formatting):</label>
    <textarea class="form-control" name="problem[grader_notes]" id="problem_grader_notes"></textarea>
  </div>

  <div class="actions">
    <input type="submit" name="commit" value="Create Problem" class="btn btn-primary" data-disable-with="Create Problem" />
  </div>

</t:form_with>

</c:otherwise>
</c:choose>

