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
  <a href="userHome">Home</a>
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
	</br>
	</br>
	</br>
    	<div align="center">
	 <table border="1" cellpadding="5">
        <tr>
        	<th>Title: </th>
        	<td><c:out value="${joke.title}" /></td>
        </tr>
        <tr>
        	<th>Description: </th>
        	<td><c:out value="${joke.description}" /></td>
        </tr>
        <tr>
            <th>Tags: </th>
            <td><c:out value="${joke.tags}" /></td>
        </tr>
        <tr>
               <td> <a href= "addJoke?id=<c:out value = '${joke.jokeId}' />">Add to favorites!
        </tr>
        </table>
        </form>
	</div>
	<div>
	<h3>User Reviews</h3>
	<c:forEach var="reviews" items="${reviews}">
		
		*******************************************************<br/>
		<c:out value="${reviews.userId }" />-----<c:out value="${reviews.theDate }" /><br/>
		-------------------------------------------------------<br/>
		<c:out value="${reviews.remark }" />
		&nbsp;<br/>
		&nbsp;<br/>
		&nbsp;<br/>
		Rate: 
		<c:if test="${reviews.rate == '0'}">Poor</c:if>
		<c:if test="${reviews.rate == '1'}">Fair</c:if>
		<c:if test="${reviews.rate == '2'}">Good</c:if>
		<c:if test="${reviews.rate == '3'}">Excellent</c:if>
		<br/>
		<br/>
		<br/>
		
	</c:forEach>
	</div>
	<div>
	<h2>Post a Review For This Joke!</h2>
	<form action = "addReview?id = ${joke.jokeId } /> method ="post">
		<input type="hidden"  name="jokeId" value="${joke.jokeId }" >
	 <table border="1" cellpadding="5">
        <tr>
        	<th>Remarks: </th>
        	<td>
        		<textarea name="remarks" cols="40" rows="5"></textarea>
        	</td>
        </tr>
        <tr>
        	<td>
        	Give the joke a rating!
        	<select name="rate">
        		<option value='0'>Poor</option>
        		<option value='1'>Fair</option>
        		<option value='2'>Good</option>
        		<option value='3'>Excellent</option>
        	</select>
        	</td>
        	<td>
        		<input type="submit" value="Post!" >
        	</td>
        </tr>
        </table>
        </form>
	</div>
	</center>
</body>
</html>