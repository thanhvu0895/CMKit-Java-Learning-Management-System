<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <title>Register page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css" />
    <%@include file="components/css-js.jsp"%>
  </head>
  <body>
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
          </div>
          <a class="navbar-brand">Kit</a>
        </div>
        <div class="d-flex align-items-center">
          <a href="./login.html">Already registered? Login here</a>
        </div>
      </div>
    </nav>

    <div class="wrapper">
      <form action="POST">
        <h1 class="mt-4 mb-4">Create a CM-Kit student account</h1>
        <p>Full name</p>
        <input type="text" id="fullName" class="form-control" />
        <p>Student Email</p>
        <input type="email" id="emailAddress" class="form-control" />
        <p>Create a Password</p>
        <input type="password" id="password" class="form-control" />
        <button class="btn mt-3">Register</button><br />
      </form>
    </div>
  </body>
</html>
