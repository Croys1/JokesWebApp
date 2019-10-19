<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post a Joke</title>
<style type="text/css">
  <%@include file="main.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</head>
<body>
<div class="topnav">
  <a href="profilePage">Profile</a>
  <a href="userHome">Home</a>
  <a class="active"href="makePost<c:out value='${username}' />">Post</a>
  <a href="queries">Queries</a>
</div>
<br>
<br>
<div class="topnav">
  <form action="search" method="get">
  <a>Search: <input type="text" name="keyword" size="15"/><input type="submit" value="Search!"></a></form>
  </div>
	<div align="center">
	 <form action = "addPost" method ="post">
	 <table border="1" cellpadding="5">
        <caption><h2>Post a Joke</h2></caption>
        <tr>
        	<th>Title: </th>
        	<td>
        		<textarea name="title" cols="40" rows="1"></textarea>
        	</td>
        </tr>
        <tr>
        	<th>Description: </th>
        	<td>
        		<textarea name="description" cols="40" rows="5"></textarea>
        	</td>
        </tr>
        <tr>
            <th>Tags: </th>
            <td>
        		<textarea name="tags" cols="40" rows="1"></textarea>
        	</td>
        </tr>
        <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Post!" />
                </td>
            </tr>
        </table>
        </form>
	</div>

</body>
</html>