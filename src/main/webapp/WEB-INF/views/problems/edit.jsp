<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="Edit ${problem.title}">
<ol class="breadcrumb">
  <%@ include file="../assignments/_assignment_crumbs.jsp" %>
  <li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
  <li><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEMS_PATH}" secondId="${assignment.id}">Rubric</t:link_to></li>
  <li class="active">${problem.title}</li>
</ol>

<h2>Editing Problem "${problem.title}"</h2>

<%@ include file="_form.jsp" %>

<h3>Rubric Items</h3>
These become check boxes on the grading page for this problem.
<table class= "table table-hover">
	<thead>
		<tr>
			<td>Option</td>
			<td>Value</td>
			<td>Edit</td>
			<td>Delete</td>
			<td>Move</td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${rubric_items}" var="r">
		<tr>
			<td style="word-wrap: break-word;min-width: 300px;max-width: 300px;">${r.title}</td>
			<td>${r.points}</td>
			<td><button data-toggle="collapse" data-target="#item${r.id}" class="btn btn-default">Edit</button></td>
			<td><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_RUBRIC_ITEM_PATH}" classBS="btn btn-danger" id="${r.id}" secondId="${assignment.id}" thirdId="${problem.id}" confirm="">Delete</t:link_to></td>
			<td>
			<t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_RUBRIC_ITEM_MOVE_DOWN_PATH}" classBS="btn btn-default" id="${r.id}" secondId="${assignment.id}" thirdId="${problem.id}" method="POST"><span class="glyphicon glyphicon-arrow-down"></span></t:link_to>
			<t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_RUBRIC_ITEM_MOVE_UP_PATH}" classBS="btn btn-default" id="${r.id}" secondId="${assignment.id}" thirdId="${problem.id}" method="POST"><span class="glyphicon glyphicon-arrow-up"></span></t:link_to>
			</td>
		</tr>
		<tr id="item${r.id}" class="collapse">
		  <td colspan=5>
			<div>
			<t:form_with url="${UrlUtils.ASSIGNMENT_PROBLEM_RUBRIC_ITEM_PATH}" id="${r.id}" secondId="${assignment.id}" thirdId="${problem.id}" method="PATCH">
			<div class="form-group">
			  <label for="Text">Text</label>
			  <input class="form-control" value="${r.title}" type="text" name="title" id="title">
					
			  <label for="Points">Points</label>
			  <input step="any" class="form-control" value="${r.points}" type="number" name="points" id="points"><br>
			  <input type="submit" name="commit" value="Save" class="btn btn-success" data-disable-with="Save">
			</div>
			
			</t:form_with>
			</div>
		  </td>
		</tr>			
	 	</c:forEach>
	</tbody>
</table>

<button data-toggle="collapse" data-target="#new_option" class="btn btn-primary">Add Option</button>

<div id="new_option" class="collapse">
	<div class="panel panel-default">
	<div class="panel-body">
	<t:form_with url="${UrlUtils.ASSIGNMENT_PROBLEM_RUBRIC_ITEMS_PATH}" id="${problem.id}" secondId="${assignment.id}">
			<div class="form-group">
			<label for="Text">Text</label>
			<input class="form-control" type="text" name="title" id="title">
		</div>

		<div class="form-group">
			<label for="Points">Points</label>
			<input step="any" class="form-control" type="number" name="points" id="points">
		</div>
		
		<div class="actions">
			<input type="submit" name="commit" value="Add" class="btn btn-success" data-disable-with="Add">
		</div>
	</t:form_with>
	</div>
	</div>
</div>

<br><br><br>
<h3> Reusable Comments </h3>
<table class="table table-striped" id="reusableCommentsTable">
  <thead>
    <tr>
	  <th>Comment</th>
	  <th>Delete</th>
	</tr>
  </thead>
  <tbody>
  <c:forEach items="${reusable_comments}" var="rc">
  <tr>
	    <td><span style="white-space:pre"><c:out value="${rc.comment}" escapeXml="false"/></span></td>
		<td>
		  <t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_REUSABLE_COMMENT_PATH}" classBS="btn btn-danger rc-deleter" id="${rc.id}" secondId="${assignment.id}" thirdId="${problem.id}"><span class="glyphicon glyphicon-trash" aria-alt="Delete"></span></t:link_to>
		</td>
	  </tr>
   </c:forEach>
  </tbody>
</table>
<h4> Add a reusable comment: </h4>

<t:form_with url="${UrlUtils.ASSIGNMENT_PROBLEM_REUSABLE_COMMENTS_PATH}" id="${problem.id}" secondId="${assignment.id}" formId="addReusableComment">
	<div class="form-group">
		<label for="reusable_comment_Comment">Comment</label>
		<textarea class="form-control" name="reusable_comment[comment]" id="reusable_comment_comment"></textarea>
	</div>
	
	<div class="actions">
		<input type="submit" name="commit" value="Add" class="btn btn-success" data-disable-with="Add">
	</div>
</t:form_with>


<%-- Handle this form with javascript --%>
<script>

// HTML for a delete button
// replace _RCID_ with the ID to delete
/* deleteButtonHTML = "<t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_REUSABLE_COMMENT_PATH}" classBS="btn btn-danger rc-deleter" id="${rc.id}" secondId="${assignment.id}" thirdId="${problem.id}" confirm="Reusable comment deleted!"><span class="glyphicon glyphicon-trash" aria-alt="Delete"></span></t:link_to>" */
// ----- Scripts for adding new reusable comments -----

var d = null

addReusableComment.addEventListener('ajax:success', function(event) {
  var detail = event.detail;
  var data = detail[0], status = detail[1], xhr = detail[2];
  d = detail
  
  
  r = reusableCommentsTable.insertRow()
  
  c = r.insertCell()
  c.innerHTML = reusable_comment_comment.value
  
  c = r.insertCell()
  c.innerHTML = deleteButtonHTML.replace("_RCID_",data.id)
  
  addDeleteButtonListeners(c.children[0])
  
  reusable_comment_comment.value = ""
  
  alert("Reusable Comment Added!")
})

addReusableComment.addEventListener('ajax:error', function(event) {
  var detail = event.detail;
  var data = detail[0], status = detail[1], xhr = detail[2];
  
  // Come up with an error string
  str = "Failed to save reusable comment: "
  for(e in data)
  {
    str+=e
	str+= " " + data[e]
  }
  
  alert(str)
})


// ------ Scripts for deleting reusable comments -----

function addDeleteButtonListeners(db)
{
  db.addEventListener('ajax:success', function(event) {
    var detail = event.detail;
    var data = detail[0], status = detail[1], xhr = detail[2];

	//Remove this row
	this.parentElement.parentElement.remove()
	
    alert("Reusable comment deleted!")
  })
  
  db.addEventListener('ajax:error', function(event) {
    var detail = event.detail;
    var data = detail[0], status = detail[1], xhr = detail[2];
     
    alert("Failed to delete reusable comment.")
  })
}

deleteButtons = document.getElementsByClassName("rc-deleter")
for(db of deleteButtons)
{
  addDeleteButtonListeners(db)
}

</script>
</t:layoutj>
<% //TODO: FIGUREOUT how to get send event message from java to ajax%>