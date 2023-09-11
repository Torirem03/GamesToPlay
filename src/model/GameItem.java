package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author torirem - aaustin5
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */
@Entity
@Table(name="Games to play")
public class GameItem {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="GENRE")
	private String genre;
	@Column(name="TITLE")
	private String title;
	
	public GameItem() {
		super();
	}
	
	public GameItem(String title, String genre) {
		super();
		this.genre = genre;
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the name
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param name the name to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String returnGameDetails() {
		return this.title + " - " + this.genre;
	}

}
