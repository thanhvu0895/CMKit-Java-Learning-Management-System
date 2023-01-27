<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#grade_cats"><strong>Grade Categories<span class="caret"></span></strong></a>
	  </h4>
	</div>
	
	<div id="grade_cats" class="panel-collapse collapse in">
	  <div class="panel-body">
	  
		<p>
		  <table class="table table-hover">
			<thead>
			  <tr>
				<td>Category</td>
				<td>Weight</td>
				<td>Edit</td>
				<td>Delete</td>
			  </tr>
			</thead>
			<tbody>
			<c:forEach var="gc" items="${course_grade_categories}">
			  <tr> 
			  	<td>${gc.title}</td>
				<td>${gc.weight}%</td>
				<td><t:link_to path="${UrlUtils.EDIT_GRADE_CATEGORY_PATH}" classBS="btn btn-default"  id="${gc.id}">Edit</t:link_to></td>
			   <c:choose>
			   <c:when test="${gc.isUsedByAssignment()}">
				<td><t:link_to path="${UrlUtils.GRADE_CATEGORIES_PATH}/:id" classBS="btn btn-danger" id="${gc.id}" confirm="Assignments are using this grade category." disable="disabled">Delete</t:link_to></td>
			   </c:when>
			   <c:otherwise>
				<td><t:link_to path="${UrlUtils.GRADE_CATEGORIES_PATH}/:id" classBS="btn btn-danger" id="${gc.id}" confirm="Are you sure you want to delete this category?">Delete</t:link_to></td>
			   </c:otherwise>
			   </c:choose>
			  </tr>
			</c:forEach>
			
			</tbody>
		  </table>
		</p>
		<p>
		<t:link_to path="${UrlUtils.NEW_GRADE_CATEGORY_PATH}?course=:id" classBS="btn btn-primary"  id="${course.id}">New Grade Category</t:link_to>
		</p>
	  </div>
	</div>
  </div>
</div> 
