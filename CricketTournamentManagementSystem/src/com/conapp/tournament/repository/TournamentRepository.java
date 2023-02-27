package com.conapp.tournament.repository;

import com.conapp.tournament.dto.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class TournamentRepository {
    private static TournamentRepository repoInstance;
    private List<Admin> adminList;
    private List<User> userList;
    private List<Tournament> tournamentList;

    private TournamentRepository()
    {
        adminList = new ArrayList<>();
        userList = new ArrayList<>();
        tournamentList = new ArrayList<>();
        adminList.add(new Admin("MS Dhoni", "msd@gmail.com", "msd", "captaincool"));
        adminList.add(new Admin("Nedumaaran", "maaran@gmail.com", "maaran", "flightkuowner"));
        userList.add(new User("Vikram", "vikramakakarnan@gmail.com", "vikram", "aarambikalangala!"));
        userList.add(new User("Amar", "amarchief@gmail.com", "amar", "blacksquad"));
        userList.add(new User("Sandhanam", "drsandhanam@gmail.com", "drsandhanam", "methdoctor"));
        userList.add(new User("Thor, son of Odin", "thorodinson@gmail.com", "thor", "pointbreak"));
    }
 
    public static TournamentRepository getInstance()
    {
        if(repoInstance==null)
            repoInstance = new TournamentRepository();
        return repoInstance;
    }

    public void addTournament(String name, int noOfTeams, HashSet<Team> teams)
    {
        tournamentList.add(new Tournament(name, noOfTeams, teams));
    }

    public List<User> getUsers()
    {
        return userList;
    }

    public List<Admin> getAdmins()
    {
        return adminList;
    }

    public List<Tournament> getTournaments() {
        return tournamentList;
    }

    public Tournament getTournament(int n)
    {
        if(n>=tournamentList.size())
            return null;
        return tournamentList.get(n);
    }

    public void deleteTournament(int n) {
        tournamentList.remove(n);
    }

}
