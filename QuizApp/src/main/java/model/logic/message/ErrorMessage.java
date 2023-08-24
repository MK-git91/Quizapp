package model.logic.message;

import model.logic.LoginLogic;

public class ErrorMessage {

	public static String getErrMessIfEmpty(String input, String str) {
		String eMessage = "空";
		if (input == "") {
			eMessage = str + "が未入力です。";
		}

		return eMessage;
	}

	public static String getErrMessverifyLogin(String userName, String passWord) {
		String eMessage = "空";
		if (!LoginLogic.verifyLogin(userName, passWord)) {
			eMessage = "ユーザー名またはパスワードは間違っています。";
		}

		return eMessage;
	}
}
