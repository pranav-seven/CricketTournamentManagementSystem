package com.conapp.tournament.login;

import com.conapp.tournament.dto.User;

public interface LoginControllerToViewCaller {
	void signupSuccessful();
	void loginSuccess(User user, char type);
	void loginFailure(char type);
}
