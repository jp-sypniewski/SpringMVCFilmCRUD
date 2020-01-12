package com.skilldistillery.film.controller;

import java.util.List;

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

	// displays full details of a single film

	@RequestMapping(path = "GetFilmById.do", params = "id")
	public ModelAndView showFilmById(@RequestParam("id") String s) {
		ModelAndView mv = new ModelAndView();
		int idInt = Integer.parseInt(s);
		Film film = null;
		if (idInt <= 1000) {
			film = dao.findFilmById(idInt);
		} else {
			film = dao.newFindFilmById(idInt);
		}

		mv.addObject("film", film);
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}

	// displays list of films from a keyword search

	@RequestMapping(path = "GetFilmByKeyword.do", params = "keyword")
	public ModelAndView showFilmByKeyword(@RequestParam("keyword") String s) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = dao.findFilmsBySearch(s);
		mv.addObject("films", films);
		mv.setViewName("WEB-INF/searchKeyword.jsp");
		return mv;
	}

	// displays edit form pre-filled with information from a film from search

	@RequestMapping(path = "editFilm.do", params = "film", method = RequestMethod.GET)
	public ModelAndView editFilm(@RequestParam("film") String s) {
		ModelAndView mv = new ModelAndView();
		int idInt = Integer.parseInt(s);
		Film film = dao.newFindFilmById(idInt);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/edit.jsp");
		return mv;
	}

	// adds a film to db and then displays full details of that film

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(@ModelAttribute("film") Film film) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", dao.createFilm(film));
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}

	// updates a film in the db then displays full details of that film

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(@ModelAttribute("film") Film film) {
		ModelAndView mv = new ModelAndView();
		dao.saveFilm(film);
		mv.addObject("film", dao.newFindFilmById(film.getId()));
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}

	// deletes a film from the db then displays success/fail message page

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@ModelAttribute("film") String s) {
		ModelAndView mv = new ModelAndView();
		int idInt = Integer.parseInt(s);
		Film film = dao.newFindFilmById(idInt);
		boolean deleted = dao.deleteFilm(film);
		mv.addObject("deleted", deleted);
		mv.setViewName("WEB-INF/deleted.jsp");
		return mv;
	}

}
