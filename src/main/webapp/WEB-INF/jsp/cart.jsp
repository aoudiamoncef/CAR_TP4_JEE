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
                <input type="text" class="form-control" name="finder" placeholder="Title...">
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


<div class="container">
    <h2>Cart</h2>

    <div class="table-responsive">

        <table class="table">

            <thead>
            <tr class="info">
                <th>Author</th>
                <th>Title</th>
                <th>Year</th>
                <th>Quantity</th>
                <th>id</th>
                <th>Selected</th>
                <th>Add/Remove</th>

                <td>
                    <form method="post" action="/cart" >
                    <input type="submit" class="btn btn-info" name="btnorder" value="Order">
                    </form>
                </td>

            </tr>
            </thead>
            <tbody>

            <%
                Collection<Book> books = (Collection<Book>) request.getAttribute("cart");

                for (Book book: books) {

                    out.print("<tr class=\"success\">");

                    out.print("<td>" + book.getAuthor() + "</td><td>" + book.getTitle() + "</td>");
                    out.print("<td>" + book.getYear() + "</td><td>" + book.getQuantity() + "</td>");

                    out.print("<form method=\"post\" action=\"/cart\">");

                    out.print("<td>" + book.getId()+"</td>");


                    out.print("<td>");

                    out.print(
                                    "<select name=\"number\" id=\"Order\">\n" +
                                    "<option value=\"1\">1</option>" +
                                    "<option value=\"2\">2</option>" +
                                    "<option value=\"3\">3</option>" +
                                    "<option value=\"4\">4</option>" +
                                    "<option value=\"5\">5</option>" +
                                    "</select>");




                    out.print("</td>");

                    out.print("<input type=\"number\" class=\"form-control\" id=\"idbook\" name=\"idbook\" value=\""+book.getId()+"\" style=\"display: none;\">");



                    out.print("<td>");

                    out.print("<button type=\"submit\" name=\"btnadd\" value=\"btnadd\"  class=\"btn btn-success\">" +
                            "<span class=\"glyphicon glyphicon-plus\"></span></button>");

                    out.print("<button type=\"submit\" name=\"btndelete\" value=\"btndelete\" class=\"btn btn-danger\">" +
                            "<span class=\"glyphicon glyphicon-trash\"></span></button>");

                    out.print("</form>");

                    out.print("</td>" +
                            "<td></td></tr>");

                }
            %>


            </tbody>
        </table>

    </div>
</div>

</body>
</html>
