package model.data;

import java.io.Serializable;

public class Answer implements Serializable {
	private String ansNumber;
	private String answer;
	private String correct;

	public String getAnsNumber() {
		return ansNumber;
	}

	public void setAnsNumber(String ansNumber) {
		this.ansNumber = ansNumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

}
