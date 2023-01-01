<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <title>Register page</title>
    <link rel="stylesheet" href="../assets/css/register.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous"
    />
    <script src="https://kit.fontawesome.com/2912a97a77.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
            <img src="../assets/images/logo.png" alt="" />
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
