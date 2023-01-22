<t:form_with url="${UrlUtils.DEPARTMENTS_PATH}/:id" id="${department.id}" method="PATCH"> 
  <div class="form-group">
    <label for="department_title">Title</label>
    <input class="form-control" type="text" value="${department.title}" name="department[title]" id="department_title">
  </div>

  <div class="actions">
    <input type="submit" name="commit" value="Update Department" class="btn btn-success" data-disable-with="Update Department">
  </div>
</t:form_with>
  