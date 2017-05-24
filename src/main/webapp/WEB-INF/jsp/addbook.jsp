<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="car.tp4.entity.Book"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Collection"%>
<%@ page session="true"%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>

    <script>
        $(function(){
            $("#includedContent").load("${pageContext.request.contextPath}/html/navbar.html");
        });
    </script>

    <title>Library</title>



</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <ul class="nav navbar-nav">

            <li>
                <a href="/books">
                    <span class="glyphicon glyphicon-book"></span> E-Library
                </a>
            </li>

            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Display
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/displayauthors">Display Authors</a></li>
                    <li><a href="/books">Display Books</a></li>
                    <li><a href="/sortbyyearasc">Sort by year ASC</a></li>
                    <li><a href="/sortbyyeardesc">Sort by year DESC</a></li>
                </ul>
            </li>



            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Find
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/findauthor">Find Author</a></li>
                    <li><a href="/finder">Find Title</a></li>
                </ul>
            </li>


        </ul>


       <form class="navbar-form navbar-left" method="post" action="/finder">
            <div class="form-group">
                <input type="text" class="form-control" name="finder" placeholder="Author or Title...">
            </div>
            <button type="submit" class="btn btn-default">Find</button>
        </form>

        <ul class="nav navbar-nav navbar-right">

            <li>
                <a href="/cart">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart
                </a>
            </li>

            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/stock">Stock Books</a></li>
                    <li><a href="/addbook">Add New Book</a></li>
                </ul>
            </li>

            <li><a href="#"><span class="glyphicon glyphicon-user"></span>
                <c:if test="${ !empty sessionScope.user }">
                    ${ sessionScope.user }
                </c:if>
            </a>
            </li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
            </li>
        </ul>
    </div>
</nav>


    <%
        String author = request.getParameter("author");
        String title = "";
        int year = 0;
        int quantity = 0;

        if (author != null && !author.isEmpty()) {
            title = request.getParameter("title");
            year = Integer.parseInt(request.getParameter("year"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
    %>

<div class="container">
    <h2>ADD Book</h2>


    <form method="post" action="/addbook">
        <div class="form-group">

            <div class="col-xs-3">
                <label>Author:</label>
                <input type="text" maxlength="100" class="form-control" name="author" id="author" maxlength="100" value="<%if (author != null) {
				out.print(author);
			}%>">
            </div>
        </div>

        <div class="form-group">

            <div class="col-xs-3">
                <label>Title:</label>
                <input type="text" maxlength="100" class="form-control" name="title" id="title" value="<%=title%>">
            </div>
        </div>

        <div class="form-group">


            <div class="col-xs-2">
                <label>Year:</label>
                <input type="number" class="form-control" name="year" id="year" min="-2600" max="3000" value="<%=year%>">
            </div>

        </div>

        <div class="form-group">


            <div class="col-xs-2">
                <label>Quantity:</label>
                <input type="number" class="form-control" name="quantity" id="quantity" min="1" max="1000000" value="<%=quantity%>">
            </div>

        </div>
        <br>
        <div class="col-xs-2">
            <button type="submit" class="btn btn-success">Add</button>
        </div>
    </form>
</div>

<br>
    <div class="container">

    <div class="table-responsive">

        <table class="table">

            <thead>
            <tr class="info">
                <th>Author</th>
                <th>Title</th>
                <th>Year</th>
                <th>Quantity</th>
                <th>Order</th>
                <th>Add to Cart</th>
            </tr>
            </thead>
            <tbody>

            <% if (author != null && !author.isEmpty()) {
                        out.print("<tr class=\"danger\">");
                        out.print("<td>" + author + "</td><td>" + title + "</td>");
                        out.print("<td>" + year + "</td><td>" + quantity + "</td>");
                        out.print("<td>");

                        out.print(
                                "<div class=\"form-group\">" +
                                        "<select class=\"\" id=\"Order\">" +
                                        "<option value=\"1\">1</option>" +
                                        "<option value=\"2\">2</option>" +
                                        "<option value=\"3\">3</option>" +
                                        "<option value=\"4\">4</option>" +
                                        "<option value=\"5\">5</option>" +
                                        "</select>");

                        out.print("</td>");

                        out.print("<td>");
                        out.print("<button type=\"button\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-plus\"></span></button>");
                        out.print("</td></tr>");
                }
            %>

            <%
                if (author != null && !author.isEmpty()) {

                    Collection <Book> books = (Collection <Book>) request.getAttribute("books");

                    for (Book book : books) {

                        out.print("<tr class=\"success\">");

                        out.print("<td>" + book.getAuthor() + "</td><td>" + book.getTitle() + "</td>");
                        out.print("<td>" + book.getYear() + "</td><td>" + book.getQuantity() + "</td>");

                        out.print("<td>");

                        out.print(
                                "<div class=\"form-group\">\n" +
                                        "<select class=\"\" id=\"Order\">\n" +
                                        "<option value=\"1\">1</option>" +
                                        "<option value=\"2\">2</option>" +
                                        "<option value=\"3\">3</option>" +
                                        "<option value=\"4\">4</option>" +
                                        "<option value=\"5\">5</option>" +
                                        "</select>");

                        out.print("</td>");

                        out.print("<td>");
                        out.print("<button type=\"button\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-plus\"></span></button>");
                        out.print("</td></tr>");

                    }
                }
            %>

            </tbody>
        </table>

    </div>

    </div>

</div>



</body>
</html>
