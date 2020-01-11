package com.skilldistillery.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

@Controller
public class FilmController {
	
	@Autowired
	private DatabaseAccessorObject dao;
	
	@RequestMapping("search.do")
	public String returnToSearch() {
		return "WEB-INF/search.jsp";
	}

}
