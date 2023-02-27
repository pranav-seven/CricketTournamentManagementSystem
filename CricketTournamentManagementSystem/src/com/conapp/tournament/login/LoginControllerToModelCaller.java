package com.conapp.tournament.login;

import java.util.List;
import com.conapp.tournament.dto.User;
import com.conapp.tournament.dto.Admin;

public interface LoginControllerToModelCaller {
    boolean checkUsername(String username);
    void addUser(String name, String emailid, String username, String password);
	List<Admin> getAdminsList();
    List<User> getUsersList();
}
