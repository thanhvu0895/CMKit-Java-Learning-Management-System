<%@ page contentType="text/html;charset=UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <title>Login page</title>
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
  <section>
 <c:if test="${not empty ERROR}">
        <div>User EMAIL:  ${ERROR}</div>
    </c:if>
    
   </section>
   
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
          </div>
          <a class="navbar-brand">Kit</a>
        </div>
        <div class="d-flex align-items-center">
          <a class="dropdown-item" href="#">
            <span><i class="fa-solid fa-right-to-bracket"></i></span>
            Login
          </a>
        </div>
      </div>
    </nav>

    <div class="wrapper">
      <form action="" method ="post">
        <h1 class="mt-4 mb-4">Please Login</h1>
         <input type="hidden" name="origin" value="${LOGIN_USER}">
            <c:if test="${not empty error}">
                <div>* SHOW ERROR: ${error}</div>
            </c:if>
        <p>Email</p>
        <input type="email" name="email" class="form-control" />
        <p>Password</p>
        <input type="password" name="password"  class="form-control" />
        <button class="btn mt-3">Login</button><br />
        <button class="btn" id="forgotPassword">Forgot password</button>
      </form>
    </div>
  </body>
</html>
