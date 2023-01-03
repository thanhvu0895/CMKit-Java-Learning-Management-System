<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <title>Student home page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css" />
   	<%@include file="components/css-js.jsp"%>
  </head>
  <body>
  	
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
          <a href="${pageContext.request.contextPath}/" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
           </a>
          </div>
          <a href="${pageContext.request.contextPath}/" class="navbar-brand">My Classes</a>
        </div>
        <div>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">User</button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">
                <span><i class="fa-solid fa-gear"></i></span>
                Account Settings
              </a>
              <a class="dropdown-item" href="#">
                <span><i class="fa-solid fa-bell"></i></span>
                Notification Settings
              </a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/ssh_keys">
                <span><i class="fa-solid fa-lock"></i></span>
                SSH Keys
              </a>
              <hr />
              <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">
                <span><i class="fa-solid fa-right-to-bracket"></i></span>
                Logout
              </a>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <div class="container">
    <!-- SHOW NOTICE AND ALERT-->
	 <% if (request.getSession().getAttribute("notice") != null){%>
		 <div class="alert alert-success">Logged in! Welcome, ${LOGIN_USER}</div>
		<%}		
		request.getSession().removeAttribute("notice"); %>
   	<!-- END -->
    

    <section class="container justify-content-center mt-4">
      <h1>Classes</h1>
      <div class="panel panel-default" id="student">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" href="#student_classes" aria-expanded="true" class=""
              ><strong>Student Classes<span class="caret"></span></strong
            ></a>
          </h4>
        </div>
        <div id="student_classes" class="panel-collapse collapse in" aria-expanded="true" style="">
          <table class="table table-hover">
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

            <tbody></tbody>
          </table>

          <div class="panel-body">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" href="#prev_student_classes" class="" aria-expanded="true"
                    ><strong>Past Classes<span class="caret"></span></strong
                  ></a>
                </h4>
              </div>
              <div id="prev_student_classes" class="panel-collapse collapse in" aria-expanded="true" style="">
                <table class="table table-hover">
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
                      <td><a href="${pageContext.request.contextPath}/klasses">Backend Super Intensive</a></td>
                      <td>BE101</td>
                      <td>Autumn 2022</td>
                      <td>1</td>
                      <td>
                        <div class="progress" style="position: relative; text-align: center">
                          <div class="progress-bar" role="progressbar" aria-hidden="true" style="width: 89%; background-color: rgb(112.2, 255, 0)"></div>
                          <span style="position: absolute; left: 0; right: 0"> 89.0% </span>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td><a href="${pageContext.request.contextPath}/klasses">Data Structures (Online)</a></td>
                      <td>COMP 210</td>
                      <td>Fall 2019</td>
                      <td>1</td>
                      <td>
                        <div class="progress" style="position: relative; text-align: center">
                          <div class="progress-bar" role="progressbar" aria-hidden="true" style="width: 80.52%; background-color: rgb(198.696, 255, 0)"></div>
                          <span style="position: absolute; left: 0; right: 0"> 80.52% </span>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td><a href="${pageContext.request.contextPath}/klasses">Design and Analysis of Algorithms</a></td>
                      <td>COMP 215</td>
                      <td>Spring 2022</td>
                      <td></td>
                      <td>
                        <div class="progress" style="position: relative; text-align: center">
                          <div class="progress-bar" role="progressbar" aria-hidden="true" style="width: 95.61%; background-color: rgb(44.778, 255, 0)"></div>
                          <span style="position: absolute; left: 0; right: 0"> 95.61% </span>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td><a href="${pageContext.request.contextPath}/klasses">Web Development</a></td>
                      <td>COMP 487/490</td>
                      <td>Fall 2022</td>
                      <td>1</td>
                      <td>
                        <div class="progress" style="position: relative; text-align: center">
                          <div class="progress-bar" role="progressbar" aria-hidden="true" style="width: 99.28%; background-color: rgb(7.344, 255, 0)"></div>
                          <span style="position: absolute; left: 0; right: 0"> 99.28% </span>
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
    </section>
    </div>
  </body>
</html>