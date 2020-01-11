package com.skilldistillery.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.entities.Film;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

@Controller
public class FilmController {
	
	@Autowired
	private DatabaseAccessorObject dao;
	
	public DatabaseAccessorObject getDao() {
		return dao;
	}

	public void setDao(DatabaseAccessorObject dao) {
		this.dao = dao;
	}

	@RequestMapping(path="GetFilmById.do", params="id")
	public ModelAndView showFilmById(@RequestParam("id") String s) {
		ModelAndView mv = new ModelAndView();
		int idInt = Integer.parseInt(s);
		mv.addObject("film", dao.findFilmById(idInt));
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}
	
	@RequestMapping(path="addFilm.do", method= RequestMethod.POST)
	public ModelAndView addFilm(@ModelAttribute("film") Film film) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", dao.createFilm(film));
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}

}
