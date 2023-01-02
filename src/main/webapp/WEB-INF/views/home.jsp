<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <title>Student home page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous"
    />
    <script src="https://kit.fontawesome.com/2912a97a77.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
  
  <section>
  <!-- SHOW USER NAME IF LOGGED IN -->
    <c:if test="${not empty LOGIN_USER}">
        <div>User EMAIL:  ${LOGIN_USER}</div>
    </c:if>
    
   </section>

   	
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
          </div>
          <a href="${pageContext.request.contextPath}/home" class="navbar-brand">My Classes</a>
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
              <a href="${pageContext.request.contextPath}/logout"><button class="dropdown-item">
                <span><i class="fa-solid fa-right-to-bracket"></i></span>
                Logout
              </button></a>
            </div>
          </div>
        </div>
      </div>
    </nav>

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
  </body>
</html>