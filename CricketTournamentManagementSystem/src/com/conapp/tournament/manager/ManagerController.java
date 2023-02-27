package com.conapp.tournament.manager;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import com.conapp.tournament.dto.Team;
import com.conapp.tournament.dto.Tournament;

public class ManagerController implements ManagerViewToController, ManagerModelToController{
    private ManagerControllerToView managerView;
    private ManagerControllerToModel managerModel;

    ManagerController(ManagerControllerToView managerView)
    {
        this.managerView = managerView;
        this.managerModel = new ManagerModel(this);
    }

    public boolean isValidNoOfTeams(int n)
    {
        if(n<2)
            return false;
        for(int i=2; i<=n; i=i<<1)
            if(i==n)
                return true;
        return false;
    }

    public void addTournament(String name, int numberOfTeams, HashSet<Team> teams)
    {
        managerModel.addTournament(name, numberOfTeams, teams);
    }

    public boolean getTournaments()
    {
        return managerModel.getTournaments();
    }

    public boolean printTournaments(List<Tournament> tournamentList)
    {
        return managerView.printTournaments(tournamentList);
    }

    public boolean checkTournamentExists(int n) {
        if(n<0)
        {
            editFailure("Tournament does not exist");
            return false;
        }
        else
            return managerModel.checkTournamentExists(n);
    }

    @Override
    public String getRoundName() {
        return managerModel.getRoundName();
    }

    @Override
    public String getCurrentMatch() {
        return managerModel.getCurrentMatch();
    }

    @Override
    public boolean updateScore(String homeScore, String homeOvers, String awayScore, String awayOvers) {
        if(isValidScore(homeScore, homeOvers) && isValidScore(awayScore, awayOvers))
        {
            managerModel.updateScore(homeScore, homeOvers, awayScore, awayOvers);
            return true;
        }
        return false;
    }

    boolean isValidScore(String score, String overs)
    {
        return Pattern.matches("[1-9][0-9]*-([0-9]|10)", score) 
            && Pattern.matches("(([1]?[0-9])(.[0-5])?)|20", overs);
    }

    public void editFailure(String message)
    {
        managerView.editFailure(message);
    }

    @Override
    public void viewTournament(int n) {
        managerModel.viewTournament(n);
    }

    @Override
    public boolean deleteTournament(int n) {
        return managerModel.deleteTournament(n);
    }
}
