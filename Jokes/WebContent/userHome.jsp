<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
  <%@include file="main.css"%>
</style>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<title>Welcome Jokesters!</title>
</head>
<body>
<div class="topnav">
  <a href="profilePage">Profile</a>
  <a class="active" href="userHome">Home</a>
  <a href="makePost">Post</a>
  <a href="queries">Queries</a>
</div>
<br>
<br>
<div class="topnav">
  <form action="search" method="get">
  <a>Search: <input type="text" name="keyword" size="15"/><input type="submit" value="Search!"></a></form>
  </div>
<center>
<h2>Jokes</h2>
    <div align="center">
        <table border="1" cellpadding="5">
            <tr>
                <th>Date</th>
                <th>Title</th>
                <th>User</th>
            </tr>
            <c:forEach var="joke" items="${jokes}">
                <tr>
                    <td><c:out value="${joke.date}" /></td>
                    <td><a href ="jokePage?jokeId=<c:out value='${joke.jokeId}' /> "><c:out value="${joke.title}" /></a></td>
					<td> <div class="tooltip"><a href="addFriend?id=<c:out value='${joke.userId}' /> ">${joke.userId}</a>
					<span class="tooltiptext">Add this user!</span> </div> </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>
