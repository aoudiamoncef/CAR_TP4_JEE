<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>

    <%
        if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("password") != null) {
            response.sendRedirect("/books");
        }
    %>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <ul class="nav navbar-nav">

            <li>
                <a href="/">
                    <span class="glyphicon glyphicon-book"></span> E-Library
                </a>
            </li>

        </ul>

    </div>
</nav>


<div class="container">
    <div class="card card-container">
        <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
        <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
        <p id="profile-name" class="profile-name-card"></p>
        <form class="form-signin" method="post" action="/login">

            <span id="reauth-email" class="reauth-email"></span>
            <input type="email" id="inputEmail" name="email"class="form-control" maxlength="255" placeholder="Email address" required autofocus>

            <input type="password" id="inputPassword" name="password" class="form-control" maxlength="255" placeholder="Password" required>

            <div id="remember" class="checkbox">
                <label>
                    <input type="checkbox" id="loginButton" value="remember-me"> Remember me
                </label>
            </div>

            <button class="btn btn-lg btn-primary btn-block btn-signin" id="loginForm" type="submit">Sign in</button>
        </form><!-- /form -->
    </div><!-- /card-container -->
</div><!-- /container -->

</body>
</html>
