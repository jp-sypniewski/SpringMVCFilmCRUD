<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Film</title>
</head>
<body>
<h3>EditFilm</h3>
  <form action="updateFilm.do" method="POST">
  
    Title    
    <input type="text" name="title" value= "${film.title}"/> 
    Description
    <input type="text" name="description" value= "${film.description}"/> 
    Release Year
    <input type="text" name="releaseYear" value= "${film.releaseYear}"/> 
    languageId
    <input type="text" name="languageId" value= "${film.languageId}"/> 
    rentalDuration
    <input type="text" name="rentalDuration" value= "${film.rentalDuration}"/> 
    rentalRate
    <input type="text" name="rentalRate" value= "${film.rentalRate}"/> 
    length
    <input type="text" name="length" value= "${film.length}"/> 
    replacementCost
    <input type="text" name="replacementCost" value= "${film.replacementCost}"/> 
    rating
    <input type="text" name="rating" value= "${film.rating}"/> 
    specialFeatures
    <input type="text" name="specialFeatures" value= "${film.specialFeatures}"/> 
        
    <input type="submit" value="Update Film Data" />

	</form>

</body>

</html>