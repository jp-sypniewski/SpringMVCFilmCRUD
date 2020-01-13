<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="css" href="mvcfilmsite.css" />
<meta charset="UTF-8">
<title>Film Search Results</title>
</head>
<body id="searchjsp" style= "text-align:center">


  <c:choose>
    <c:when test="${! empty films}">
    
    
    
      <ul id= " idkeywordjsp" style= "list-style:none; padding:3cm" >
        <c:forEach var="film" items="${films}">
        <li style="font-size:175%">
      ${film.title }
      <br/><br/>
      
        <form action="GetFilmById.do" method="GET">
    <input type="hidden" name="id" value= "${film.id }" /> 
    <input type="submit" value="Show All Film Data" />
        </form>
        <br/>
        
        <form action="editFilm.do" method="GET">
    <input type="hidden" name="film" value= "${film.id }" /> 
    <input type="submit" value="Edit Film Data" />
         </form>
         <br/>
  
        <form action="deleteFilm.do" method="POST">
    <input type="hidden" name="film" value= "${film.id }" /> 
<!--     <input type="submit" value="Delete Film ONLY works on user-added films" />
 -->    </form>
 <br/>
 
        </li>
        
    </c:forEach>
      </ul>
      </c:when>
    <c:otherwise>
      <p id="nonefound" style= "font-size: 400%">No films found</p>
    </c:otherwise>
  </c:choose>

  	<a style= "text-align:center; font-size: 250%; text-decoration: none; text-shadow: 2px 2px 4px #000000;" href="index.html">&#128073 &#127968</a>
  <br><br><br><br>






</body>
</html>