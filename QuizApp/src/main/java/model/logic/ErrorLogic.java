package model.logic;

public class ErrorLogic {
	
	
	 public int lenCheck(String input, int min, int max) {
		 
		    // 全角の場合の対処も考慮して、何バイト分の長さであるかを取得
		    int length = input.length();
		    if(length < min) { // 最小文字数よりも少なかった場合
		        return length - min;
		    }
		    if(length > max) { // 最大文字数よりも多かった場合
		        return length - max;
		    }
		    return 0; // 許容内であった場合
		}
	 
	 public String getErrorMessageLen(String input, int min, int max) {
		 
		 String eMessage = "空";
		 //int len = lenCheck(input, min, max);
		 
		 
		 return eMessage;
		 
		}
	 
}
