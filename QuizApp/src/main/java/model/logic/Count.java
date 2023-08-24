package model.logic;

import java.util.ArrayList;
import java.util.List;

public class Count {
	
	public static List<Integer> quizCount() {
		List<Integer> quizNum = new ArrayList<>();
		
		for (int i = 1; i < 18; i++) {
			quizNum.add(i);
		}
		//Collections.shuffle(quizNum);
		quizNum.subList(5, quizNum.size()).clear();
		
		return quizNum;
	}
}
