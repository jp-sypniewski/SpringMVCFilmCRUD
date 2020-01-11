package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class DatabaseAccessorObject implements DatabaseAccessor {

	private String user = "student";
	private String pw = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseAccessorObject() {
		
	}
	
	@Override
	public Film createFilm(Film film) {
		String sql = "insert into film"
				+ " (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost,"
				+ " rating, special_features)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pw);
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, film.getTitle());
			pstmt.setString(2, film.getDescription());
			pstmt.setInt(3, film.getReleaseYear());
			pstmt.setInt(4, film.getLanguageId());
			pstmt.setInt(5, film.getRentalDuration());
			pstmt.setDouble(6, film.getRentalRate());
			pstmt.setInt(7, film.getLength());
			pstmt.setDouble(8, film.getReplacementCost());
			pstmt.setString(9, film.getRating());
			pstmt.setString(10, film.getSpecialFeatures());

			int success = pstmt.executeUpdate();

			if (success == 1) {
				ResultSet keys = pstmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);

					if (film.getActors() != null && film.getActors().size() > 0) {

						String sqlInsertActorFilm = "insert into film_actor" + " (film_id, actor_id)"
								+ " values (?, ?);";

						pstmt = conn.prepareStatement(sqlInsertActorFilm);

						for (Actor actor : film.getActors()) {
							pstmt.setInt(1, film.getId());
							pstmt.setInt(1, actor.getId());
							pstmt.executeUpdate();
						}
					}
				}
			} else {

				film = null;
			}

			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();

			if (conn != null) {
				try {

					conn.rollback();

				} catch (SQLException rollback) {

					System.err.println("Error trying to rollback");
				}
			}

			throw new RuntimeException("Error inserting film: " + film);
		}

		return film;
	}

	@Override
	public boolean deleteFilm(Film film) {
		String sql = "delete from film where id = ?;";
		String sqlChild = "delete from film_actor where film_id = ?;";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pw);
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sqlChild);
			pstmt.setInt(1, film.getId());
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, film.getId());
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

			if (conn != null) {
				try {

					conn.rollback();

				} catch (SQLException rollback) {
					System.err.println("Error trying to rollback");
				}
			}

			return false;
		}

		return true;
	}
	
	@Override
	public boolean saveFilm(Film film) {
		String sql = "update film set"
				+ " title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?,"
				+ " rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?"
				+ " where id = ?";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, film.getTitle());
			pstmt.setString(2, film.getDescription());
			pstmt.setInt(3, film.getReleaseYear());
			pstmt.setInt(4, film.getLanguageId());
			pstmt.setInt(5, film.getRentalDuration());
			pstmt.setDouble(6, film.getRentalRate());
			pstmt.setInt(7, film.getLength());
			pstmt.setDouble(8, film.getReplacementCost());
			pstmt.setString(9, film.getRating());
			pstmt.setString(10, film.getSpecialFeatures());
			pstmt.setInt(11, film.getId());

			int success = pstmt.executeUpdate();

			if (success == 1) {
				String sqlDeleteFromFilmActor = "delete from film_actor where film_id = ?;";
				pstmt = conn.prepareStatement(sqlDeleteFromFilmActor);

				pstmt.setInt(1, film.getId());
				pstmt.executeUpdate();

				String sqlInsertActorFilm = "insert into film_actor" + " (film_id, actor_id)" + " values (?, ?);";

				pstmt = conn.prepareStatement(sqlInsertActorFilm);

				for (Actor actor : film.getActors()) {
					pstmt.setInt(1, film.getId());
					pstmt.setInt(1, actor.getId());
					pstmt.executeUpdate();
				}
				
				conn.commit();
			} else {
				conn.rollback(); // rollback if film isn't updated
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try { conn.rollback(); }
				catch (SQLException rollback) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}

		return true;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "select film.id as id, title, description, release_year, language_id,"
				+ " language.name as film_language, rental_duration, rental_rate, length, replacement_cost,"
				+ " rating, special_features, category.name as film_category" + " from film"
				+ " join language on film.language_id = language.id"
				+ " join film_category on film.id = film_category.film_id"
				+ " join category on film_category.category_id = category.id" 
				+ " where film.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getString("film_language"),
						rs.getInt("rental_duration"), rs.getDouble("rental_rate"), rs.getInt("length"),
						rs.getDouble("replacement_cost"), rs.getString("rating"), rs.getString("special_features"),
						findActorsByFilmId(rs.getInt("id")), rs.getString("film_category"));
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}
	
	public Film newFindFilmById(int filmId) {
		Film film = null;
		String sql = "select * from film where id = ?;";
		
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setId((rs.getInt(1)));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getInt(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "select * from actor where id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<Actor>();
		String sql = "select * from film_actor join actor on film_actor.actor_id = actor.id where film_id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				actorList.add(new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return actorList;
	}

	@Override
	public List<Film> findFilmsBySearch(String inputText) {
		List<Film> filmList = new ArrayList<>();

		String sql = "select film.id as id, title, description, release_year, language_id,"
				+ " language.name as film_language, rental_duration, rental_rate, length, replacement_cost,"
				+ " rating, special_features, category.name as film_category" + " from film"
				+ " join language on film.language_id = language.id"
				+ " join film_category on film.id = film_category.film_id"
				+ " join category on film_category.category_id = category.id"
				+ " where title like ? or description like ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + inputText + "%");
			pstmt.setString(2, "%" + inputText + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				filmList.add(new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getString("film_language"), // note name
																											// is the
																											// name of
																											// the
																											// language
						rs.getInt("rental_duration"), rs.getDouble("rental_rate"), rs.getInt("length"),
						rs.getDouble("replacement_cost"), rs.getString("rating"), rs.getString("special_features"),
						findActorsByFilmId(rs.getInt("id")), rs.getString("film_category")));
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return filmList;
	}

}
