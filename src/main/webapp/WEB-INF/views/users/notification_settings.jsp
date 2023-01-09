<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Notification Settings">
<h1> Notification Settings </h1> Notifications will be sent to your email address when the following occur. Note that notifications are set on a per-class basis and will default to off for newly added classes. <center>
  <h2> Student Notifications </h2>
</center>
<table class="table table-hover">
  <table class="table table-hover">
    <thead>
      <tr>
        <td></td>
        <th colspan=5>
          <center>Notify me when:</center>
        </th>
      </tr>
      <tr>
        <th>Class</th>
        <th>An assignment is assigned</th>
        <th>An assignment gets feedback</th>
        <th>Someone invites me to collaborate</th>
        <th>I get an extension</th>
        <th>I get a response to a regrade request</th>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
  <div class="panel-body">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href='#student_notifications_prev'>
            <strong>Past Classes <span class="caret"></span>
            </strong>
          </a>
        </h4>
      </div>
      <div id="student_notifications_prev" class="panel-collapse collapse">
        <table class="table table-hover">
          <thead>
            <tr>
              <td></td>
              <th colspan=5>
                <center>Notify me when:</center>
              </th>
            </tr>
            <tr>
              <th>Class</th>
              <th>An assignment is assigned</th>
              <th>An assignment gets feedback</th>
              <th>Someone invites me to collaborate</th>
              <th>I get an extension</th>
              <th>I get a response to a regrade request</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>COMP 110 Intro to Programming, pre-Hybrid Spring 2019 section 1</td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/239/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/239/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/239/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/239/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/239/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP 210 Data Structures (Online) Fall 2019 section 1</td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/440/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/440/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/440/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/440/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/440/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP 230 Computer Organization, pre-2021 Winter 2020 section 1</td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/507/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/507/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/507/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/507/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/507/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP483/MATH450 Cryptography Winter 2022 section </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1265/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1265/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1265/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1265/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1265/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP 320 Programming Languages Spring 2022 section </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1365/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1365/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1365/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1365/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1365/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP 215 Design and Analysis of Algorithms Spring 2022 section </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1441/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1441/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1441/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1441/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1441/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
            <tr>
              <td>COMP 487/490 Web Development Fall 2022 section 1</td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1548/toggle_assigned_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1548/toggle_graded_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1548/toggle_contributor_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1548/toggle_extension_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
              <td>
                <a class="btn btn-danger" rel="nofollow" data-method="post" href="/students/1548/toggle_regrade_response_notification"> OFF <span class="glyphicon glyphicon-volume-off"></span>
                </a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <hr>
</t:layoutj>




