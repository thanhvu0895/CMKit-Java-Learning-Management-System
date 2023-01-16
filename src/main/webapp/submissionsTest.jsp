<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="Submission/Feedback">

	<div class="container">



		<ol class="breadcrumb">
			<li><a href="/klasses">Classes</a></li>
			<li><a href="/klasses/18">COMP 210 Data Structures (Online):
					Fall 2019 1</a></li>
			<li>Project 4: BLAST</li>
			<li class="active">View Submission</li>
		</ol>


		<h2>Submission for Project 4: BLAST</h2>

		<p>
			Implement a simple version of the BLAST algorithm. More information
			in the course <a
				href="http://www.cs.kzoo.edu/cs210/projects/DNASequenceMatching/BLASTppWithDesign.html">webpage</a>
		</p>
















		<li class="list-group-item hoverable-thing"><script>
			directorybrowser0Toggled = false
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
					class="glyphicon glyphicon-folder-close" aria-hidden="true"
					id="fvIconFordirectorybrowser0"></span> Provided Files <span
					class="caret"></span>
			</strong>
		</a></li>


		<div id="directorybrowser0" class="accordian-collapse collapse ">









		</div>






		<h4>3 Contributors:</h4>
		<ul>
			<li>Daniel Alspector-Kelly</li>
			<li>Thanh Vu</li>
			<li>Donny Brown</li>
		</ul>




		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#submission_files"><strong>Submitted
								&amp; Feedback Files</strong></a>
					</h4>
				</div>
				<div id="submission_files" class="panel-collapse collapse in">

					<table class="table table-hover">
						<thead>
							<tr>
								<th>File Type</th>
								<th>File name</th>
								<th>Download/View</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Submitted</td>
								<td>Daniel-AlspectorKelly_Blastfinal_s9541.zip</td>
								<td>
									<div class="btn-group">
										<a class="btn btn-default"
											href="/submissions/9541/download?filename=Daniel-AlspectorKelly_Blastfinal_s9541.zip">
											<span class="glyphicon glyphicon-save"></span> Download
										</a> <a class="btn btn-default" target="kit-file"
											href="/submissions/9541/download_inline?filename=Daniel-AlspectorKelly_Blastfinal_s9541.zip">
											<span class="glyphicon glyphicon-eye-open"></span> View
										</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
		</div>



		<br> Turned in: Tuesday, Nov 26 at 07:59PM <br> <span
			style="color: green">On Time</span> <br> <br> Resubmission
		policy:<br> Resubmission allowed until assignment is graded <br>
		<br> <br>




		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#invites"><strong> I
								Worked With Someone Else<span class="caret"></span>
						</strong></a>
					</h4>
				</div>
				<div id="invites" class="panel-collapse collapse">
					<div class="panel-body">
						<p>
							If you worked with someone else, please select their name from
							the list below. <b>This should only be used when you are
								turning in the same thing. If you worked together but have
								separate things to submit, just make sure you both include this
								information in your work.</b>
						</p>
						<p>Once you invite someone, they should click "Submit" from
							the assignment list as normal, then select your invitation
							instead of submitting something.</p>
						Maximum contributors to one submission: 3





					</div>
				</div>
			</div>
		</div>


		<center>
			<h1>
				<b><u> Feedback </u></b>
			</h1>

			<table class="table table-bordered">


				<tbody>
					<tr class="active">
						<td><b> Object Oriented Desing/Abstraction </b></td>
						<td align="right"><b> 5.0/5.0 </b></td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="table table-bordered">
								<tbody>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Appropiate use of Database, MatchElement, BLASTMain,
											BLASTApp, DNASequence, Location classes, and other classes
											you might create</td>
										<td>2.5</td>



									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Correct use of data abstraction: declaring objects,
											using accurate access modifiers, encapsulation, interfaces
											(if applicable), and so on</td>
										<td>2.5</td>



									</tr>

									<tr>
										<td><span class="glyphicon glyphicon-unchecked"
											aria-hidden="true"></span></td>
										<td><span style="color: grey">Point adjustments in
												Comments </span></td>
										<td><span style="color: grey">0.0</span></td>


									</tr>



								</tbody>
							</table>
						</td>
					</tr>

					<tr class="active">
						<td><b> Implementation </b></td>
						<td align="right"><b> 20.0/20.0 </b></td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="table table-bordered">
								<tbody>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Reading virus data and query data into structure</td>
										<td>2.5</td>



									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Creating HashMap of n-letter words</td>
										<td>3.0</td>



									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Scan query and find matches: check if n-letter words
											are in HashMap</td>
										<td>7.0</td>



									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Extend matches (avoiding repeated matches)</td>
										<td>5.0</td>



									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Report results</td>
										<td>2.5</td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-unchecked"
											aria-hidden="true"></span></td>
										<td><span style="color: grey">Point Adjustments in
												Comments </span></td>
										<td><span style="color: grey">0.0</span></td>
									</tr>
									<tr class="warning">
										<td><span class="glyphicon glyphicon-comment"
											aria-hidden="true"></span></td>
										<td colspan="2">
											<p>Well done! I little hard to figure out you use a file
												search prompt (dialog box) as opposed to the regular dialog
												box with the name of the file (file reader class)</p>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>

					<tr class="active">
						<td><b> Internal Documentation and Style </b></td>
						<td align="right"><b> 5.0/5.0 </b></td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="table table-bordered">
								<tbody>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Appropriate internal documentation at the top of all
											files</td>
										<td>2.0</td>
									</tr>

									<tr class="success">
										<td><span class="glyphicon glyphicon-check"
											aria-hidden="true"></span></td>

										<td>Appropriate internal comments, variable names,
											indentation in all files</td>
										<td>3.0</td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-unchecked"
											aria-hidden="true"></span></td>
										<td><span style="color: grey">Point Adjustments in
												Comments </span></td>
										<td><span style="color: grey">0.0</span></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>

			<h4>
				<b>Grade Summary</b>
			</h4>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td align="right">Total Points from Problems:</td>
						<td>30.0 / 30.0</td>
					</tr>

					<tr>
					</tr>
				</tbody>
			</table>

			<h3>Final Grade: 30.0 / 30.0 points (100.0%)</h3>
		</center>

		<button data-toggle="collapse" data-target="#request_regrade"
			class="btn btn-primary" onclick="this.style.display = 'none';">Request
			Regrade</button>

		<div id="request_regrade" class="collapse">
			<div class="panel panel-primary">
				<div class="panel-body">
					<form action="/submissions/9541/regrade_request"
						accept-charset="UTF-8" data-remote="true" method="post">
						<input name="utf8" type="hidden" value="✓"><input
							type="hidden" name="_method" value="create"><input
							type="hidden" name="authenticity_token"
							value="h9+p9SDwQjlNWt+nxlDwWPEpFqsQQ7p7/1gwexP31acgbOddvSxwzkDpWh5niSQ+eAcBHQm0gQwizEX4GkK00g==">

						<div class="form-group">
							<label for="Reason for request:">Reason for request:</label>
							<textarea class="form-control"
								placeholder="Why does this need to be regraded?" name="reason"
								id="reason"></textarea>
						</div>

						<div class="actions">
							<input type="submit" name="commit" value="Submit Request"
								class="btn btn-success" data-disable-with="Submit Request">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</t:layoutj>
