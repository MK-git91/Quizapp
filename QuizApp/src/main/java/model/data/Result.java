package model.data;

import java.io.Serializable;

public class Result implements Serializable {
	private int userNumber;
	private int resultNumber;
	private int quizNumber;
	private String ansNumber;
	private String correct;

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}

	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}

	public String getAnsNumber() {
		return ansNumber;
	}

	public void setAnsNumber(String ansNumber) {
		this.ansNumber = ansNumber;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

}
