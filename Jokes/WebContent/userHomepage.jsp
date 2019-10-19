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
<title>welcome home</title>
</head>
<body>
<div class="topnav">
  <a class="active" href="profilePage">Profile</a>
  <a href="userHome">Home</a>
  <a href="makePost">Post</a>
  <a href="queries">Queries</a>
</div>

<img src="https://i.pinimg.com/originals/c7/e0/72/c7e072e0303cdc40fd6b480e5e7b8d85.jpg" alt="Avatar" class="avatar">
    <div align="left">
    </br>
    </br>
<div class="topnav">
  <form action="search" method="get">
  <a>Search: <input type="text" name="keyword" size="15"/><input type="submit" value="Search!"></a></form>
</div>
        <table border="1" cellpadding="5">
            <caption><h2>Favorite Jokes</h2></caption>
            <tr>
                <th>Title</th>
                
     
            </tr>
            <c:forEach var="hasFavoriteJokes" items="${jokes}">
                <tr>
                   <td>
                   <c:out value="${hasFavoriteJokes.title}"/>
                    <a href= "jokePage?jokeId=<c:out value = '${hasFavoriteJokes.jokeId}' />">See Joke!
                    <a href= "deleteJoke?id=<c:out value = '${hasFavoriteJokes.jokeId}' />">Delete Joke
                  </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    </div>
    <left>
       <h2>Favorite Friends</h2>
    <div align="left">
        <table border="1" cellpadding="5">
            <tr>
                <th>User</th>
                
            </tr>
            <c:forEach var="hasFavoriteUsers" items="${members}">
                <tr>
                    <td><a href="visitUser?userId=<c:out value='${hasFavoriteUsers.userId}' />"><c:out value="${hasFavoriteUsers.userId}" /></a>
                	<a href= "deleteFriend?id=<c:out value = '${hasFavoriteUsers.userId}' />">Delete Friend
  					
				</div>
				</td>
				</tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>

