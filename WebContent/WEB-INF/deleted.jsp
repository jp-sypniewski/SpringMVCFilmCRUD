<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html >
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="mvcfilmsite.css">
<head>
<meta charset="UTF-8">
<title>Film Search Results</title>
</head>
<body class= "deleted" style= "font-size: 300%">


  <c:choose>
    <c:when  test="${deleted}" >
      Film Successfully Deleted!
      
      <!-- <a href= "editFilm.do">Edit Film </a> -->
    </c:when>
    <c:otherwise>
      <p>Film unable to be deleted</p>
    </c:otherwise>
  </c:choose>
  <br/>
  <br/>
  <a style= "color:yellow" href= "index.html">&#128073 &#127968</a>

</body>
</html>