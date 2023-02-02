<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section}) | Gradebook">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Gradebook</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":gradebook"/></jsp:include>

<br>

<t:link_to path="${UrlUtils.KLASS_GRADEBOOK_CSV_PATH}" classBS="btn btn-default" secondId="${klass.id}">Download as CSV</t:link_to>

<div class="well pull-right">
  <input type="checkbox" id="showColors" onchange="toggleColors()">  Show Colors
</div>

<br><br>

</div> <%--  Close page's responsive container so that table can use entire width --%>
<center>
<div style="width:95vw;height:100vh;overflow:scroll"><%-- class="table-responsive">--%>
<table class="table table-bordered" style="border-collapse: separate;">
  <thead>
  
  <tr id="header1" style="position:sticky;top:0;background:white;z-index:1">
	  <th style="border-right: solid;border-bottom:hidden;background:white;left:0;position:sticky"></th>
	  <c:forEach var="cat" items="${klass_grade_categories}">
	    <th class="text-center" colspan="" style="border-right: solid"> ${cat.title } (${cat.weight}%) </th>
	  </c:forEach>
	  <th style="border-left:double;border-bottom:hidden"></th>
  </tr>
	
  <tr id="header2" style="position:sticky;top:100px;background:white;z-index:1">
	<th style="border-right:solid;background:white;left:0;position:sticky"> Name </th>
	
	<th></th>
	
	<th style="border-left:dotted"> ${cat.title} Total </th>
	<th style="border-right:solid"> ${cat.title} Percent </th>
	
	<th style="border-left:double">Total Grade</th>
  </tr>
  </thead>
  <tbody>
  <tr>
  </tr>
   </tbody>
</table>
</div>
</center>

<script>

  // Toggle the coloration of table cells based on grade
  function toggleColors()
  {
    cells = document.getElementsByClassName("recolorable");
	
	if(document.getElementById("showColors").checked)
	{
	  for(c of cells)
	  {
	    c.style.backgroundColor = c.getAttribute("data-grade-color");
	  }
	}
	else
	{
	  for(c of cells)
	  {
	    c.style.backgroundColor = "white";
	  }
	}
  
  }

  toggleColors();
  
  // Fix bad height on multi-stickied table headers
  document.getElementById("header2").style.top = document.getElementById("header1").offsetHeight+"px"
  
</script>

<div> <!-- Reopen main layout div -->
	  
  

</t:layoutj>