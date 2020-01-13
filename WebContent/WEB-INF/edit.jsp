<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="css" href="mvcfilmsite.css" />
<meta charset="UTF-8">
<title>Edit Film</title>
</head>

<body class= "editpage">
<h3 style= "font-size: 250%; ">EditFilm</h3>
  <form style= "font-size: 190%" action="updateFilm.do" method="POST">
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

  <a style= "font-size: 130%; color: yellow; text-decoration: none" href="index.html">&#128073 &#127968</a>

</body>

</html>