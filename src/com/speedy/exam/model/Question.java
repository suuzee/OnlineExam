package com.speedy.exam.model;

public class Question {
	String questionkey;
	String questiontype;
	String industry;
	String category;
	int questionnumber;
	String content;
	String alternativeanswer;
	String correntanswer;
	int score;
	String questionanswer;
	String [] alternativeanswers;
	
	
	public String getQuestionanswer() {
		return questionanswer;
	}
	public void setQuestionanswer(String questionanswer) {
		this.questionanswer = questionanswer;
	}
	public String[] getAlternativeanswers() {
		return alternativeanswers;
	}
	public void setAlternativeanswers(String[] alternativeanswers) {
		this.alternativeanswers = alternativeanswers;
	}
	public String getQuestionkey() {
		return questionkey;
	}
	public void setQuestionkey(String questionkey) {
		this.questionkey = questionkey;
	}
	public String getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuestionnumber() {
		return questionnumber;
	}
	public void setQuestionnumber(int questionnumber) {
		this.questionnumber = questionnumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAlternativeanswer() {
		return alternativeanswer;
	}
	public void setAlternativeanswer(String alternativeanswer) {
		this.alternativeanswer = alternativeanswer;
	}
	public String getCorrentanswer() {
		return correntanswer;
	}
	public void setCorrentanswer(String correntanswer) {
		this.correntanswer = correntanswer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
