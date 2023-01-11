<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Departments">
<h1>Departments</h1>

<table class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-search="true">
  <thead>
    <tr>
      <th>Title</th>
    </tr>
  </thead>

  <tbody>
      <tr>
        <td><a href="/departments/1/courses">trét</a></td>
      </tr>
      <tr>
        <td><a href="/departments/2/courses">abc</a></td>
      </tr>
      <tr>
        <td><a href="/departments/3/courses">abc</a></td>
      </tr>
      <tr>
        <td><a href="/departments/4/courses">test</a></td>
      </tr>
  </tbody>
</table>

<br>

<a class="btn btn-primary" href="/departments/new">New Department</a>
	
</t:layoutj>