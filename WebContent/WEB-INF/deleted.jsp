<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Search Results</title>
</head>
<body>


  <c:choose>
    <c:when test="${deleted}">
      Film Successfully Deleted!
      
      <!-- <a href= "editFilm.do">Edit Film </a> -->
    </c:when>
    <c:otherwise>
      <p>Film unable to be deleted</p>
    </c:otherwise>
  </c:choose>
  <a href= "index.html">home</a>

</body>
</html>