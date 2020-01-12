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
    <c:when test="${! empty film}">
    
      <ul>
        <li>${film.id}</li>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.releaseYear}</li>
        <li>${film.languageId}</li>
        <li>${film.rentalDuration}</li>
        <li>${film.rentalRate}</li>
        <li>${film.length}</li>
        <li>${film.replacementCost}</li>
        <li>${film.rating}</li>
        <li>${film.specialFeatures}</li>
        
        <li><c:choose>
        	<c:when test ="${! empty film.actors }">
        		Actors:
        		<ul><c:forEach var="actor" items="${film.actors}">
        			<li>${actor.firstName } ${actor.lastName }</li>
        		</c:forEach></ul>
        	</c:when>
        	<c:otherwise>
        		No actors found
        	</c:otherwise>
        </c:choose></li>
        
        <li><c:choose>
        	<c:when test ="${! empty film.category }">
        		${film.category }
        	</c:when>
        	<c:otherwise>
        		No category found
        	</c:otherwise>
        </c:choose></li>
        
      </ul>
      
        <form action="editFilm.do" method="GET">
    <input type="hidden" name="film" value= "${film.id }" /> 
    <input type="submit" value="Edit Film Data" />
  </form>
  
  <form action="deleteFilm.do" method="POST">
    <input type="hidden" name="film" value= "${film.id }" /> 
    <input type="submit" value="Delete Film ONLY works on user-added films" />
  </form>
      
      <!-- <a href= "editFilm.do">Edit Film </a> -->
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>







</body>
</html>