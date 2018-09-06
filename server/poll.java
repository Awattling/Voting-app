package server;

import java.io.Serializable;
import java.time.LocalDateTime;

public class poll implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id = 123323;
	LocalDateTime activeTime = LocalDateTime.parse("2018-08-28T09:00:00");
	LocalDateTime inactiveTime = LocalDateTime.parse("2018-09-29T21:00:00");
	private String question = ""; 
	String[] candidates = {"Donald Trump", "Hillary Clinton"};

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}