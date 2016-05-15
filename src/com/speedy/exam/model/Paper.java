package com.speedy.exam.model;

public class Paper {
	String paperkey;
	
	String papertype;
	
	String personkey;
	
	String creatdate;
	
	int score;
	
	String passstate;

	public String getPaperkey() {
		return paperkey;
	}

	public void setPaperkey(String paperkey) {
		this.paperkey = paperkey;
	}

	public String getPapertype() {
		return papertype;
	}

	public void setPapertype(String papertype) {
		this.papertype = papertype;
	}

	public String getPersonkey() {
		return personkey;
	}

	public void setPersonkey(String personkey) {
		this.personkey = personkey;
	}

	public String getCreatdate() {
		return creatdate;
	}

	public void setCreatdate(String creatdate) {
		this.creatdate = creatdate;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPassstate() {
		return passstate;
	}

	public void setPassstate(String passstate) {
		this.passstate = passstate;
	}
	
	
}
