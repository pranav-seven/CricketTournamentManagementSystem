package com.conapp.tournament.login;

import com.conapp.tournament.dto.User;
import com.conapp.tournament.dto.Admin;
import com.conapp.tournament.repository.TournamentRepository;

import java.util.List;

public class LoginModel implements LoginControllerToModelCaller {
	private LoginModelToControllerCaller loginController;
	private TournamentRepository repo;

	LoginModel(LoginController controller)
	{
		loginController = controller;
		repo = TournamentRepository.getInstance();
	}

	public boolean checkUsername(String username)
	{
		List<User> list = repo.getUsers();
		for(User user : list)
		{
			if(user.getUsername().equals(username))
				return false;
		}
		return true;
	}

    public void addUser(String name, String emailid, String username, String password)
	{
		repo.getUsers().add(new User(name, emailid, username, password));
		loginController.signupSuccessful();
	}

    public List<Admin> getAdminsList()
    {
        return repo.getAdmins();
    }

    public List<User> getUsersList()
    {
        return repo.getUsers();
    }
}
