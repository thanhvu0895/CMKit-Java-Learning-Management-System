<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <title>Feedback</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/feedback.css" />
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
    <section class="container justify-content-center">
      <div class="d-flex p-2 align-items-center mt-4 bg-light">
        <a href="">Classes</a>
        <p>/</p>
        <p>Backend Super Intensive Class 3: Winter 2022</p>
        <p>/</p>
        <p>Group Assignment #2 - Personal Project Frontend</p>
      </div>
      <h2>Submission for Group Assignment #2 - Personal Project Frontend</h2>
      <p>Please upload all your files in a zip (or any compression format)</p>
      <li class="list-group-item hoverable-thing">
        <script>
          directorybrowser0Toggled = false;
          function fvToggleIconFordirectorybrowser0() {
            if (directorybrowser0Toggled) {
              directorybrowser0Toggled = false;
              document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-close";
            } else {
              directorybrowser0Toggled = true;
              document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-open";
            }
          }
        </script>
        <a data-toggle="collapse" href="#directorybrowser0" onclick="fvToggleIconFordirectorybrowser0()" class="collapsed" aria-expanded="false">
          <strong>
            <span class="glyphicon glyphicon-folder-open" aria-hidden="true" id="fvIconFordirectorybrowser0"></span>
            Provided Files
            <span class="caret"></span>
          </strong>
        </a>
      </li>
      <div id="directorybrowser0" class="accordian-collapse collapse" aria-expanded="false" style="height: 0px"></div>
      <h4>2 Contributors:</h4>
      <ul>
        <li>Thanh Vu</li>
        <li>Khanh Vu</li>
      </ul>
      <div class="panel-group">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" href="#submission_files"><strong>Submitted &amp; Feedback Files</strong></a>
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
                  <td>ThanhVu_a2repo_s37380.rar</td>
                  <td>
                    <div class="btn-group">
                      <a class="btn btn-default" href="/submissions/37380/download?filename=ThanhVu_a2repo_s37380.rar"> <span class="glyphicon glyphicon-save"></span> Download </a>
                      <a class="btn btn-default" target="kit-file" href="/submissions/37380/download_inline?filename=ThanhVu_a2repo_s37380.rar">
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
        <br>
            Turned in: Thursday, Nov 03 at 12:26AM
            <br>
                  <span style="color:green">On Time</span>
        <br><br>
          Resubmission policy:<br>
              Resubmission allowed before deadline
            <br>
          <br>
        <br>
            <div class="panel-group">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h4 class="panel-title">
                    <a data-toggle="collapse" href="#invites" class="" aria-expanded="true"><strong>
                      I Worked With Someone Else<span class="caret"></span>
                    </strong></a>
                  </h4>
                </div>
                <div id="invites" class="panel-collapse collapse in" aria-expanded="true" style="">
                  <div class="panel-body">
                  <p>
                    If you worked with someone else, please select their name from the list below.
                    <b>This should only be used when you are turning in the same thing.
                    If you worked together but have separate things to submit, just make sure
                    you both include this information in your work.</b>
                  </p>
                  <p>
                    Once you invite someone, they should click "Submit" from the assignment list as normal,
                    then select your invitation instead of submitting something.
                  </p>
                  Maximum contributors to one submission: 2
                  </div>
                </div>
              </div>
            </div>
            <center>
            <h1><b><u> Feedback </u></b></h1>
            <table class="table table-bordered">
              <tbody>
                    <tr class="active">
                        <td><b> Rubrics </b></td>
                        <td align="right"><b> 10.0/10.0 </b></td>
                    </tr>
                    <tr>
                      <td colspan="2">
                        <table class="table table-bordered">
                          <tbody>
                          
                                  <tr class="success">
                                <td>
                                  <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                </td>
                              
                                <td>
                                  Page layout: has all basic layout including header, navigation bar, body, footer, etc
                                   </td>
                                <td>
                                  2.0
                                </td>
                              </tr>
                                  <tr class="success">
                                <td>
                                  <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                </td>
                                <td>
                                  Page load without any error
                                   </td>
                                <td>
                                  2.0
                                </td>
                              </tr>
                                  <tr class="success">
                                <td>
                                  <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                </td>
                                <td>
                                  Web page is responsive to different platforms
                                   </td>
                                <td>
                                  2.0
                                </td>
                              </tr>
                                  <tr class="success">
                                <td>
                                  <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                </td>
                              
                                <td>
                                  Webpage is interactive (buttons click performs operations as they should be)
                                   </td>
                                <td>
                                  2.0
                                </td>
                              </tr>
                                  <tr class="success">
                                <td>
                                  <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                </td>
                              
                                <td>
                                  Design is simple and easy to see, CSS styling is intentional
                                   </td>
                                <td>
                                  2.0
                                </td>
                              </tr>
                          </tbody>
                        </table>
                      </td></tr>
              </tbody>
            </table>
            
        <h4><b>Grade Summary</b></h4>
        <table class="table table-striped">
          <tbody>
              <tr>
                <td align="right">Total Points from Problems:</td>
                <td>10.0 / 10.0</td>
              </tr>
            
            <tr>
            </tr>
          </tbody>
        </table>
        
        <h3> Final Grade: 10.0 / 10.0 points (100.0%) </h3>
          </center>
          
                <button data-toggle="collapse" data-target="#request_regrade" class="btn btn-primary" onclick="this.style.display = 'none';">Request Regrade</button>
                
                <div id="request_regrade" class="collapse">
                  <div class="panel panel-primary">
                    <div class="panel-body">
                      <form action="/submissions/37380/regrade_request" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="✓"><input type="hidden" name="_method" value="create"><input type="hidden" name="authenticity_token" value="xDdPVLHPR6aziQvJ1UJIf3G9Gz1fSOAHUEQHxmrnsT2KwWg7FodI/jgYVzfNWZO2Xk/yj1hV0+8CW4Ho1yo1QQ==">
                
                        <div class="form-group">
                          <label for="Reason for request:">Reason for request:</label>
                          <textarea class="form-control" placeholder="Why does this need to be regraded?" name="reason" id="reason"></textarea>
                        </div>
        
                        <div class="actions">
                          <input type="submit" name="commit" value="Submit Request" class="btn btn-success" data-disable-with="Submit Request">
                        </div>
        </form>			</div>
                  </div>
                </div>
            </div>
    </section>
  </body>
</html>
