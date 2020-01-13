<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Film</title>
</head>
<body>
<h3>EditFilm</h3>
  <form action="updateFilm.do" method="POST">
  	<input type="hidden" name="id" value= "${film.id}"/>
    Title:
    <input type="text" name="title" value= "${film.title}"/> <br>
    Description:
    <input type="text" name="description" value= "${film.description}"/> <br>
    Release Year:
    <input type="text" name="releaseYear" value= "${film.releaseYear}"/> <br>
    Language ID:
    <input type="text" name="languageId" value= "${film.languageId}"/> <br>
    Rental Duration:
    <input type="text" name="rentalDuration" value= "${film.rentalDuration}"/> <br>
    Rental Rate:
    <input type="text" name="rentalRate" value= "${film.rentalRate}"/> <br>
    Length:
    <input type="text" name="length" value= "${film.length}"/> <br>
    Replacement Cost:
    <input type="text" name="replacementCost" value= "${film.replacementCost}"/> <br>
    Rating:
    <input type="text" name="rating" value= "${film.rating}"/> <br>
    Special Features:
    <input type="text" name="specialFeatures" value= "${film.specialFeatures}"/> <br>
        
    <input type="submit" value="Update Film Data" />

  </form>
  
  
  <br><br><br><br>

  <a href="index.html">Return Home</a>

</body>

</html>