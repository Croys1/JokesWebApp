<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<title>Welcome Jokesters!</title>
</head>
<body>
<div class="topnav">
  <a href="profilePage">Profile</a>
  <a href="userHome">Home</a>
  <a href="makePost">Post</a>
  <a class="active "href="queries">Queries</a>
</div>
<br>
<br>
<div class="topnav">
  <form action="search" method="get">
  <a>Search: <input type="text" name="keyword" size="15"/><input type="submit" value="Search!"></a></form>
  </div>
<title>Queries</title>
</head>
<br>
<br>
<body>
    <div class="card">
    <h1>Query Selection</h1>
    Select a query from the list below and the results will be displayed at the bottom of the page.
        <table class="table">
            <tr>
            	<th>Queries</th>
            	<th>Description</th>
            	<th>Selection</th>
            </tr>
            <tr>
            <form action="queries?Qid=2" method="post">
            	<td><input type="submit" value="Query#2"></td>
            	<td>List the users who post at least two jokes that are posted on the same day, one has a tag of "X" and another has a tag of "Y". </td>
           		<td>
           		Enter a tag for X:
           		<input type="text" name="tagX">
           		<br>
           		Enter a tag for Y:
           		<input type="text" name="tagY">
           		</td>
        	</form>
            </tr>
            <tr>
            <form action="queries?Qid=3" method="post">
            	<td><input type="submit" value="Query#3"></td>
            	<td>List all the jokes posted by user X, such that all the comments are "Excellent" or "good" for these jokes.</td>
           		<td>
            	Select a user for X:
				<select name="memberSelection">
					<c:forEach items="${allMembers }" var="member">
					<option value="${member.userId}">${member.userId}</option>
					</c:forEach>
        		</select>
        		</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=4" method="post">
            	<td><input type="submit" value="Query#4"></td>
            	<td>List the users who posted the most number of jokes since 3/1/2018 (inclusive); if there is a tie, list all the users who have a tie.</td>
           		<td>N/A</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=5" method="post">
            	<td><input type="submit" value="Query#5"></td>
            	<td>List the other users who are befriended by both users X and Y.</td>
           		<td>
            	Select a user for X:
				<select name="memberX">
					<c:forEach items="${allMembers }" var="member">
					<option value="${member.userId}">${member.userId}</option>
					</c:forEach>
        		</select>
        		<br>
        		Select a user for Y:
				<select name="memberY">
					<c:forEach items="${allMembers }" var="member">
					<option value="${member.userId}">${member.userId}</option>
					</c:forEach>
        		</select>
        		</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=6" method="post">
            	<td><input type="submit" value="Query#6"></td>
            	<td>Display all the users who never posted any "excellent" jokes: a joke is excellent if at least three reviews are excellent.</td>
           		<td>N/A</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=7" method="post">
            	<td><input type="submit" value="Query#7"></td>
            	<td>Display all the users who never posted a "poor" review.</td>
           		<td>N/A</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=8" method="post">
            	<td><input type="submit" value="Query#8"></td>
            	<td>Display all the users who posted some reviews but each of them is "poor".</td>
           		<td>N/A</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=9" method="post">
            	<td><input type="submit" value="Query#9"></td>
            	<td>Display those users such that each joke they posted so far never received any "poor" reviews.</td>
           		<td>N/A</td>
        	</form>
            </tr>
             <tr>
            <form action="queries?Qid=10" method="post">
            	<td><input type="submit" value="Query#10"></td>
            	<td>List a user pair (A, B) such that they always gave each other "excellent" review for every single joke they posted.</td>
           		<td></td>
        	</form>
            </tr>
        </table>
    </div>
<div>
    <center>
    <c:choose>
    <c:when test="${Qid=='2'}">
   	<h2>Query#2</h2>
   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
    <c:when test="${Qid=='3'}">
    <h2>Query#3</h2>
    	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="joke" items="${jokes }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${joke.userId}'/>"><c:out value="${joke.userId}"/></a></td>
       		<td><a href="jokePage?jokeId=<c:out value='${joke.jokeId}'/>"><c:out value="${joke.title }"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
    </c:when>
    <c:when test="${Qid=='4'}">
   	<h2>Query#4</h2>
   	   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
    </c:when>
    <c:when test="${Qid=='5'}">
   	<h2>Query#5</h2>
   	   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
   	<c:when test="${Qid=='6'}">
   	<h2>Query#6</h2>
   	   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
    <c:when test="${Qid=='7'}">
   	<h2>Query#7</h2>
   		<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
   	<c:when test="${Qid=='8'}">
   	<h2>Query#8</h2>
   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
   	<c:when test="${Qid=='9'}">
   	<h2>Query#9</h2>
   	   	<div align="center">
        <table border="1" cellpadding="5">
        <tr>
        	<th>Results</th>
        </tr>
       	<c:forEach var="members" items="${members }">
       	<tr>
       		<td><a href="visitUser?userId=<c:out value='${members.userId}'/>"><c:out value="${members.userId}"/></a></td>
       	</tr>
       	</c:forEach>
        </table>
    	</div>
   	</c:when>
   	<c:when test="${Qid=='10'}">
   	<h2>Query#10</h2>
   	</c:when>
    </c:choose>
    </center>
</div>

</body>
</html>