<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <title>Password reset</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sign-in.css" />
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
            <a href="${pageContext.request.contextPath}/login"><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""/></a>
          </div>
          <a class="navbar-brand" href="${pageContext.request.contextPath}/">Kit</a>
        </div>
        <div class="d-flex align-items-center">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/login">
            <span><i class="fa-solid fa-right-to-bracket"></i></span>
            Login
          </a>
        </div>
      </div>
    </nav>

    <div class="container mt-4">
      <h1>Request Password Reset</h1>
      <form action="" accept-charset="UTF-8" data-remote="true" method="post">
        <input name="utf8" type="hidden" value="✓" /><input type="hidden" name="authenticity_token" value="dSktCb7ktl+DBPQgeX/UUCYbauGvnb8p33PlcixGBQiu9XTj/vKyBTmMq1hkXXXKHAK8gRTXJtvEp686aLOziQ==" />
        <div class="form-group">
          <label for="email">Email</label>
          <input class="form-control" type="email" name="email" id="email" />
        </div>
        <input type="submit" name="commit" value="Next" class="btn btn-primary mt-3" data-disable-with="Next" />
      </form>
    </div>
  </body>
</html>
