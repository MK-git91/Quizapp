package model.logic;

public class CorrJudg {
	
	// 引数にanswerIdを受け取り、Aか判定し、文字列を返すメソッド
	 public static String Corrjudgment(String button) {
		 String decision = "不正解";
		 if (button.equals("A")) {
				decision = "正解";
			}
		 
		    return decision; 
		}
}
