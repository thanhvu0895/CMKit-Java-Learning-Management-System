<%@page import="java.util.Arrays"%>
<%@page import="codingmentor.javabackend.k3.model.PastKlass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="pastKlassList"
	value='<%=Arrays.asList(new PastKlass("Backend Super Intensive", "BE101", "Autumn 2022", 1, 89),
		new PastKlass("Data Structures and Algorithms", "COMP210", "Fall 2019", 1, 80.52),
		new PastKlass("Class Diagram Design", "COMP215", "Spring 2022", 2, 95.61),
		new PastKlass("Web Development", "COMP490", "Fall 2022", 1, 99.28))%>'></c:set>

<t:layoutj pageTitle="Classes">
	<h1>Classes</h1>

	<div class="panel-group">
		<div class="panel panel-default" style="border: 1px solid">
			<div class="panel-heading" style="border-bottom: 1px solid">
				<h4 class="panel-title">
					<a data-toggle="collapse" href="#student_classes"><strong>Student
							Classes<span class="caret"></span>
					</strong></a>
				</h4>
			</div>
			<div id="student_classes" class="panel-collapse collapse in">

				<!-- Create a new current class table to separate from past classes -->

				<div style="padding: 0" class="panel-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#current_student_classes"><strong>Current
										Classes<span class="caret"></span>
								</strong></a>
							</h4>
						</div>
						<div id="current_student_classes" class="panel-collapse collapse">


							<table class="table table-hover table-responsive table-bordered">
								<thead>
									<tr>
										<th>Course Title</th>
										<th>Course code</th>
										<th>Semester</th>
										<th>Section</th>
										<th style="width: 20%">Assignment
											Status</th>
										<th style="width: 20%">Next
											Assignment Due</th>
										<th style="width: 20%">Grade</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td><a href="/klasses/6">Intro to Programming,
												pre-Hybrid</a></td>
										<td>COMP 110</td>
										<td>Spring 2019</td>
										<td>1</td>
										<td>1 not submitted</td>
										<td>Assignment 1: due in 5 days - 11pm 22/01/2023</td>
										<td>

											<div class="progress"
												style="position: relative; text-align: center">
												<div class="progress-bar" role="progressbar"
													aria-hidden="true"
													style="width: 89.0%; background-color: rgb(112.2, 255, 0)"></div>
												<span style="position: absolute; left: 0; right: 0">
													89.0% </span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- Current classes table end here -->

				<div style="padding: 0" class="panel-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#prev_student_classes"><strong>Past
										Classes<span class="caret"></span>
								</strong></a>
							</h4>
						</div>
						<div id="prev_student_classes" class="panel-collapse collapse">


							<table class="table table-hover table-responsive table-bordered">
								<thead>
									<tr>
										<th>Course Title</th>
										<th>Course code</th>
										<th>Semester</th>
										<th>Section</th>
										<th style="width: 20%">Grade</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="klass" items="${pastKlassList}">
										<tr>
											<td><a
												href="${pageContext.request.contextPath}/klasses_details.jsp?name=${klass.name}&code=${klass.code}&semester=${klass.semester}&section=${klass.section}">${klass.name}</a></td>
											<td>${klass.code}</td>
											<td>${klass.semester}</td>
											<td>${klass.section}</td>
											<td>
												<div class="progress"
													style="position: relative; text-align: center">
													<div class="progress-bar" role="progressbar"
														aria-hidden="true"
														style="width: ${klass.grade}%; background-color: rgb(112.2, 255, 0)"></div>
													<span style="position: absolute; left: 0; right: 0">
														${klass.grade}% </span>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>
</t:layoutj>