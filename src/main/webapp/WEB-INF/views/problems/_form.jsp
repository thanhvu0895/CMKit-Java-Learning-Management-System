<t:form_with url="${not empty problem ? UrlUtils.ASSIGNMENT_PROBLEMS_PATH : UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH}" secondId="${assignment.id}" id="${problem.id}"  method="${not empty problem ? 'PATCH' : ''}">

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