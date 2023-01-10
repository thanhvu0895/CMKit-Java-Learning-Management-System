<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils"%>

<t:layoutj pageTitle="${param.name}">

	<div class="container">
		<ol class="breadcrumb">
			<li><a href="{pageContext.request.contextPath}/klasses">Classes</a></li>
			<li class="active">${param.code} ${param.name} ${param.semester} ${param.section}</li>
		</ol>

		<center>
			<h1>${param.name}</h1>
			<h4>${param.semester} section ${param.section}</h4>
		</center>

		<div class="progress">


			<div class="progress-bar progress-bar-primary" role="progressbar"
				aria-valuenow="36" aria-valuemin="0" aria-valuemax="37"
				style="width: 97.2972972972973%">36 Graded</div>

			<div class="progress-bar progress-bar-success" role="progressbar"
				aria-valuenow="1" aria-valuemin="0" aria-valuemax="37"
				style="width: 2.7027027027027026%">1 Submitted</div>



		</div>




















		<li class="list-group-item hoverable-thing"><script>
			directorybrowser0Toggled = true
			function fvToggleIconFordirectorybrowser0() {
				if (directorybrowser0Toggled) {
					directorybrowser0Toggled = false
					document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-close"
				} else {
					directorybrowser0Toggled = true
					document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-open"
				}
			}
		</script> <a data-toggle="collapse" href="#directorybrowser0"
			onclick="fvToggleIconFordirectorybrowser0()"> <strong> <span
					class="glyphicon glyphicon-folder-open" aria-hidden="true"
					id="fvIconFordirectorybrowser0"></span> Class Files <span
					class="caret"></span>
			</strong>
		</a></li>


		<div id="directorybrowser0" class="accordian-collapse collapse in">








			<li class="list-group-item hoverable-thing">|---- <a
				target="_blank"
				href="https://www.cs.kzoo.edu/CSShared/HelpFiles/Kit/"> <span
					class="glyphicon glyphicon-link" aria-alt="(Link)"></span> Kit Help
					Files
			</a>
				<div class="pull-right">

					<a target="_blank"
						href="https://www.cs.kzoo.edu/CSShared/HelpFiles/Kit/"> <span
						class="glyphicon glyphicon-globe" aria-hidden="true"
						style="display: inline; top: 0"></span>
					</a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;


				</div>




			</li>






		</div>






		<h2>Assignments</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Title</th>
					<th>Category</th>
					<th>Due</th>
					<th>Status</th>
					<th>Submit</th>
					<th style="width: 20%">Grade</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<tr>

					<td>Attendance &amp; Participation</td>
					<td>Attendance &amp; Participation</td>

					<td>Monday, Jun 17 at 10:04PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/6332">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 90.0%; background-color: rgb(102.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								9.0/10.0 (90.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Exam, Part IV: Inheritance and Dynamic Binding
						(Mouse, Apples/Oranges)</td>
					<td>Exams</td>

					<td>Monday, Jun 10 at 11:26AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/6009">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 75.0%; background-color: rgb(255.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								4.5/6.0 (75.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Exam, Part II: Test Cases (Retirement Criteria)</td>
					<td>Exams</td>

					<td>Monday, Jun 10 at 11:25AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5991">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 87.5%; background-color: rgb(127.5, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								3.5/4.0 (87.5%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Exam, Part III: Writing Methods, Questions 9 - 13
						(Treasure Map)</td>
					<td>Exams</td>

					<td>Monday, Jun 10 at 11:24AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5940">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 11.76%; background-color: rgb(255, 0, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								2.0/17.0 (11.76%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Exam, Part III: Writing Methods, Questions 5 - 8
						(Employee Retirements)</td>
					<td>Exams</td>

					<td>Monday, Jun 10 at 11:23AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5969">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 85.29%; background-color: rgb(150.042, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								14.5/17.0 (85.29%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Exam, Part I: Basic Concepts, Boolean Logic</td>
					<td>Exams</td>

					<td>Monday, Jun 10 at 10:56AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5903">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 81.25%; background-color: rgb(191.25, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								13.0/16.0 (81.25%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Final Programming Project</td>
					<td>Programming Projects</td>

					<td>Friday, Jun 07 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5696">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								50.0/50.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>PP 6: Percolation</td>
					<td>Programming Projects</td>

					<td>Friday, May 31 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5137">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 91.07%; background-color: rgb(91.086, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								51.0/56.0 (91.07%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Lab 8: Percolation</td>
					<td>Lab assignments</td>

					<td>Wednesday, May 22 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5054">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 90.91%; background-color: rgb(92.718, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								20.0/22.0 (90.91%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>MBS Programming Project: More Fish Subclasses</td>
					<td>Programming Projects</td>

					<td>Tuesday, May 21 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/5040">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 94.29%; background-color: rgb(58.242, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								33.0/35.0 (94.29%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Breeding and Dying Fish</td>
					<td>Lab assignments</td>

					<td>Friday, May 17 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4819">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 78.95%; background-color: rgb(214.71, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								15.0/19.0 (78.95%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>SlowFish Class</td>
					<td>Programming Projects</td>

					<td>Tuesday, May 14 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4554">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								14.0/14.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>DarterFish Class</td>
					<td>Programming Projects</td>

					<td>Tuesday, May 14 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4553">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 97.37%; background-color: rgb(26.826, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								18.5/19.0 (97.37%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>DarterFish Expected &amp; Actual Results, and Analysis
						Questions</td>
					<td>Lab assignments</td>

					<td>Tuesday, May 14 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4552">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								9.0/9.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>GridPlotter Lab &amp; Programming Project</td>
					<td>Programming Projects</td>

					<td>Friday, May 10 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4363">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 89.47%; background-color: rgb(107.406, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								34.0/38.0 (89.47%) </span>
						</div>

					</td>

					<td>
						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Past
								Submissions<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/submissions/4342">2019-05-09 19:36:04
										-0400</a></li>
							</ul>
						</div>
					</td>
				</tr>

				<tr>

					<td>MBS Analysis Questions about the Fish Class</td>
					<td>Lab assignments</td>

					<td>Friday, May 10 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4413">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 93.94%; background-color: rgb(61.812, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								31.0/33.0 (93.94%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>MBS Location and Direction Analysis Questions</td>
					<td>Lab assignments</td>

					<td>Wednesday, May 08 at 08:15AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4341">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 84.62%; background-color: rgb(156.876, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								11.0/13.0 (84.62%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>GridPlotter Analysis Questions</td>
					<td>Lab assignments</td>

					<td>Tuesday, May 07 at 11:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4129">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								8.0/8.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>MBS Analysis Questions from Pat's Introduction</td>
					<td>Lab assignments</td>

					<td>Tuesday, May 07 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4128">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								13.0/13.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>MBS Analysis Questions from Demos 1 and 2</td>
					<td>Lab assignments</td>

					<td>Tuesday, May 07 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/4134">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 93.75%; background-color: rgb(63.75, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								7.5/8.0 (93.75%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Midterm, Part III</td>
					<td>Exams</td>

					<td>Wednesday, May 01 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3900">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 85.71%; background-color: rgb(145.758, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								6.0/7.0 (85.71%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Midterm, Part II</td>
					<td>Exams</td>

					<td>Wednesday, May 01 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3852">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								8.0/8.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Midterm, Part I</td>
					<td>Exams</td>

					<td>Wednesday, May 01 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3835">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 96.43%; background-color: rgb(36.414, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								13.5/14.0 (96.43%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Midterm, Part IV</td>
					<td>Exams</td>

					<td>Wednesday, May 01 at 05:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3871">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 96.15%; background-color: rgb(39.27, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								25.0/26.0 (96.15%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Lab 4: Fish with Class</td>
					<td>Lab assignments</td>

					<td>Friday, Apr 26 at 08:15AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3587">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 85.71%; background-color: rgb(145.758, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								30.0/35.0 (85.71%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Video Response</td>
					<td>Attendance &amp; Participation</td>

					<td>Tuesday, Apr 23 at 09:30AM</td>



					<td><p style="color: green">Submitted</p></td>
					<td><a class="btn btn-default" href="/submissions/3336">View</a>


					</td>
					<td>
						<div class="progress"
							style="position: relative; text-align: center">
							<span style="position: absolute; left: 0; right: 0"> -/0 </span>
						</div>
					</td>


					<td></td>
				</tr>

				<tr>

					<td>PP 3: Histogram</td>
					<td>Programming Projects</td>

					<td>Tuesday, Apr 23 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3346">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 90.0%; background-color: rgb(102.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								9.0/10.0 (90.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Aquarium Lab Series Mini-Labs (all)</td>
					<td>Lab assignments</td>

					<td>Tuesday, Apr 23 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3344">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 78.26%; background-color: rgb(221.748, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								18.0/23.0 (78.26%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Lab 3: Histogram</td>
					<td>Lab assignments</td>

					<td>Wednesday, Apr 17 at 09:00PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3100">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								10.0/10.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>PP 2: Talking Robot</td>
					<td>Programming Projects</td>

					<td>Tuesday, Apr 16 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/3090">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 93.33%; background-color: rgb(68.034, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								14.0/15.0 (93.33%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Timestamp Mini-lab and Lab 2: Talking Robot</td>
					<td>Lab assignments</td>

					<td>Wednesday, Apr 10 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2881">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								12.0/12.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>M2 Mini-Lab: Stop and Think (Spring 2019 forward)</td>
					<td>Lab assignments</td>

					<td>Tuesday, Apr 09 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2850">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 92.86%; background-color: rgb(72.828, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								6.5/7.0 (92.86%) </span>
						</div>

					</td>

					<td>
						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Past
								Submissions<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/submissions/2847">2019-04-09 01:16:56
										-0400</a></li>
							</ul>
						</div>
					</td>
				</tr>

				<tr>

					<td>Programming Project 1b</td>
					<td>Programming Projects</td>

					<td>Tuesday, Apr 09 at 09:30AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2849">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 155.56%; background-color: rgb(0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								14.0/9.0 (155.56%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Aquarium Entrance Assignment Analysis Questions</td>
					<td>Attendance &amp; Participation</td>

					<td>Saturday, Apr 06 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2705">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								4.0/4.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>F1 Mini-Lab: Javadocs Analysis (Spring 2019 forward)</td>
					<td>Lab assignments</td>

					<td>Saturday, Apr 06 at 11:59PM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2706">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								11.0/11.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Programming Project 1a</td>
					<td>Programming Projects</td>

					<td>Friday, Apr 05 at 08:15AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2516">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								8.0/8.0 (100.0%) </span>
						</div>

					</td>

					<td></td>
				</tr>

				<tr>

					<td>Lab 1: Getting Started</td>
					<td>Lab assignments</td>

					<td>Wednesday, Apr 03 at 08:15AM</td>


					<td>
						<p style="color: blue">Graded</p>
					</td>
					<td><a class="btn btn-primary" href="/submissions/2513">Feedback</a></td>
					<td>

						<div class="progress"
							style="position: relative; text-align: center">
							<div class="progress-bar" role="progressbar" aria-hidden="true"
								style="width: 100.0%; background-color: rgb(0.0, 255, 0)"></div>
							<span style="position: absolute; left: 0; right: 0">
								3.0/3.0 (100.0%) </span>
						</div>

					</td>

					<td>
						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Past
								Submissions<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/submissions/2467">2019-04-02 10:06:06
										-0400</a></li>
								<li><a href="/submissions/2475">2019-04-02 10:13:17
										-0400</a></li>
							</ul>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<p></p>
		<center>
			<h3>Current Grade: 89.0%</h3>

			<h4>Grade Summary:</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Category</th>
						<th>Category Weight</th>
						<th>Points Earned</th>
						<th>Percent</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Attendance &amp; Participation</td>
						<td>5.0%</td>
						<td>13.0/14.0</td>
						<td>92.86%</td>
					</tr>
					<tr>
						<td>Lab assignments</td>
						<td>20.0%</td>
						<td>205.0/226.0</td>
						<td>90.71%</td>
					</tr>
					<tr>
						<td>Programming Projects</td>
						<td>35.0%</td>
						<td>245.5/254.0</td>
						<td>96.65%</td>
					</tr>
					<tr>
						<td>Exams</td>
						<td>35.0%</td>
						<td>90.0/115.0</td>
						<td>78.26%</td>
					</tr>
					<tr>
						<td>Quizzes</td>
						<td>5.0%</td>
						<td>0/0</td>
						<td>100.0%</td>
					</tr>
				</tbody>
			</table>
			<p></p>



		</center>
	</div>
</t:layoutj>
