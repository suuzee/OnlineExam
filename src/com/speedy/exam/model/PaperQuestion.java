package com.speedy.exam.model;

public class PaperQuestion {
	
	String relationkey;
	
	String paperkey;
	
	String questionkey;
	
	String questionanswer;
	
	int questionorder;

	public String getRelationkey() {
		return relationkey;
	}

	public void setRelationkey(String relationkey) {
		this.relationkey = relationkey;
	}

	public String getPaperkey() {
		return paperkey;
	}

	public void setPaperkey(String paperkey) {
		this.paperkey = paperkey;
	}

	public String getQuestionkey() {
		return questionkey;
	}

	public void setQuestionkey(String questionkey) {
		this.questionkey = questionkey;
	}

	public String getQuestionanswer() {
		return questionanswer;
	}

	public void setQuestionanswer(String questionanswer) {
		this.questionanswer = questionanswer;
	}

	public int getQuestionorder() {
		return questionorder;
	}

	public void setQuestionorder(int questionorder) {
		this.questionorder = questionorder;
	}
	
	
	
}
