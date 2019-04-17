package com.example.momen.capstone_stage2.model;

import java.util.List;

public class ResultsItem{
	private String difficulty;
	private String question;
	private String correct_answer;
	private List<String> incorrect_answers;
	private String category;
	private String type;

	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}

	public String getDifficulty(){
		return difficulty;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setCorrectAnswer(String correct_answer){
		this.correct_answer = correct_answer;
	}

	public String getCorrectAnswer(){
		return correct_answer;
	}

	public void setIncorrectAnswers(List<String> incorrect_answers){
		this.incorrect_answers = incorrect_answers;
	}

	public List<String> getIncorrectAnswers(){
		return incorrect_answers;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	//@Override
 	//public String toString(){
	//	return
	//		"ResultsItem{" +
	//		"difficulty = '" + difficulty + '\'' +
	//		",question = '" + question + '\'' +
	//		",correct_answer = '" + correctAnswer + '\'' +
	//		",incorrect_answers = '" + incorrectAnswers + '\'' +
	//		",category = '" + category + '\'' +
	//		",type = '" + type + '\'' +
	//		"}";
	//	}
}