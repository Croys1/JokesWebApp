<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style type="text/css">
  <%@include file="main.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</head>
<body>
	<div>
	<center>
		<h1>Welcome Members!</h1>
		<img src="https://i.kym-cdn.com/entries/icons/mobile/000/022/940/mockingspongebobbb.jpg" alt="SB">
		<h2>pLeAsE LoG In BeLoW</h2>
		<form action = "login" method ="post">
		<table border="1" cellpadding="5">      
            <tr>
                <th>Username:</th>
                <td>
                    <input type="text" name="username"  size="30"/>
                </td>
            </tr>
            <tr>
                <th>Password:</th>
                <td>
                    <input type="password" name="password" size="30"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login!" />
                </td>
            </tr>
        </table>
		</form>
		<h2>Or CrEaTe aN AcCoUnT!</h2>
		<form action = "register" method ="post">
		<table border="1" cellpadding="5">   
		 <tr>
                <th>First Name:</th>
                <td>
                    <input type="text" name="firstName" size="30"/>
                </td>
            </tr>
             <tr>
                <th>Last Name:</th>
                <td>
                    <input type="text" name="lastName" size="30"/>
                </td>
            </tr>
             <tr>
                <th>E-mail:</th>
                <td>
                    <input type="text" name="email" size="30"/>
                </td>
            </tr>   
             <tr>
                <th>Gender:</th>
                <td>
                    <input type="text" name="gender" size="30"/>
                </td>
            </tr>
             <tr>
                <th>Age:</th>
                <td>
                    <input type="int" name="age" size="30"/>
                </td>
            </tr>
            <tr>
                <th>Username:</th>
                <td>
                    <input type="text" name="username" size="30"/>
                </td>
            </tr>
            <tr>
                <th>Password:</th>
                <td>
                    <input type="password" name="password" size="30"/>
                </td>
            </tr>
            <tr>
                <th>Confirm Password:</th>
                <td>
                    <input type="password" name="confirmPassword" size="30"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="register" />
                </td>
            </tr>
        </table>
		</form>
	</center>
	</div>


</body>
</html>