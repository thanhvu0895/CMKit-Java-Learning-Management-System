<t:form_with url="${UrlUtils.COURSES_PATH}/:id" id="${course.id}" method="PATCH">
  <div class="form-group">
    <label for="course_title">Title</label>
    <input class="form-control" type="text" value="${course.title}" name="course[title]" id="course_title" />
  </div>

  <div class="form-group">
    <label for="course_course_code">Course code</label>
    <input class="form-control" type="text" value="${course.course_code}" name="course[course_code]" id="course_course_code" />
  </div>
  
  <div class="form-group">
    <f:check_box check_box="active" model="course" value="${course.active}"></f:check_box>
	<label for="course_Active">Active</label>
  </div>

  <div class="actions">
    <input type="submit" name="commit" value="Update Course" class="btn btn-success" data-disable-with="Update Course" />
  </div>
</t:form_with>