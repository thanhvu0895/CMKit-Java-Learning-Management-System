<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Classes">
	<h1>Classes</h1>

	<div class="panel-group">
	  <div class="panel panel-default">
		<div class="panel-heading">
		  <h4 class="panel-title">
			<a data-toggle="collapse" href="#student_classes"><strong>Student Classes<span class="caret"></span></strong></a>
		  </h4>
		</div>
		<div id="student_classes" class="panel-collapse collapse in">
		  
			<table class= "table table-hover">
			  <thead>
				<tr>
				  <th>Course Title</th>
				  <th>Course code</th>
				  <th>Semester</th>
				  <th>Section</th>
				  <th style="width: 20%">Grade</th>
				  <th style="width: 40%">Assignment Status</th>
				</tr>
			  </thead>

			  <tbody>
			  </tbody>
			</table>
		  
		    <div class="panel-body">
			  <div class="panel panel-default">
			    <div class="panel-heading">
				  <h4 class="panel-title">
				    <a data-toggle="collapse" href="#prev_student_classes"><strong>Past Classes<span class="caret"></span></strong></a>
				  </h4>
			    </div>
			  <div id="prev_student_classes" class="panel-collapse collapse">
			    
					
					<table class= "table table-hover">
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
						  <tr>
								<td><a href="/klasses/6">Intro to Programming, pre-Hybrid</a></td>
								<td>COMP 110</td>
							<td>Spring 2019</td>
							<td>1</td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:89.0%;background-color:rgb(112.2,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          89.0%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/18">Data Structures (Online)</a></td>
								<td>COMP 210</td>
							<td>Fall 2019</td>
							<td>1</td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:80.52%;background-color:rgb(198.696,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          80.52%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/21">Computer Organization, pre-2021</a></td>
								<td>COMP 230</td>
							<td>Winter 2020</td>
							<td>1</td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:92.94%;background-color:rgb(72.012,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          92.94%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/56">Cryptography</a></td>
								<td>COMP483/MATH450</td>
							<td>Winter 2022</td>
							<td></td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:99.09%;background-color:rgb(9.282,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          99.09%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/60">Programming Languages</a></td>
								<td>COMP 320</td>
							<td>Spring 2022</td>
							<td></td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:98.0%;background-color:rgb(20.4,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          98.0%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/62">Design and Analysis of Algorithms</a></td>
								<td>COMP 215</td>
							<td>Spring 2022</td>
							<td></td>
							<td>
							  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:95.61%;background-color:rgb(44.778,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          95.61%
						        </span>
						      </div>
						    </td>
						  </tr>
						  <tr>
								<td><a href="/klasses/68">Web Development</a></td>
								<td>COMP 487/490</td>
							<td>Fall 2022</td>
							<td>1</td>
							<td>
		  
							  <div class="progress" style="position: relative; text-align: center">
						        <div class="progress-bar" role="progressbar" aria-hidden="true" style=width:99.28%;background-color:rgb(7.344,255,0)></div>
						        <span style="position:absolute; left: 0; right: 0">
						          99.28%
						        </span>
						      </div>
						    </td>
						  </tr>
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