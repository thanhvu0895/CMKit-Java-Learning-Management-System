<t:form_with url="${UrlUtils.COURSES_PATH}">
  <input type="hidden" name="department" id="department" value="${department.id}" />
  
  <div class="form-group">
    <label for="course_title">Title</label>
    <input class="form-control" type="text" name="course[title]" id="course_title" />
  </div>

  <div class="form-group">
	    <label for="course_course_code">Course code</label>
	    <input class="form-control" type="text" name="course[course_code]" id="course_course_code" />
	  </div>
	  
	  <div class="form-group">
	    <input name="course[active]" type="hidden" value="0" /><input type="checkbox" value="1" checked="checked" name="course[active]" id="course_active" />
		<label for="course_Active">Active</label>
	  </div>
	
	  <div class="actions">
	    <input type="submit" name="commit" value="Create Course" class="btn btn-success" data-disable-with="Create Course" />
	  </div>
</t:form_with>