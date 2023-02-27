package com.conapp.tournament.manager;

import java.util.HashSet;

import com.conapp.tournament.dto.Team;
import com.conapp.tournament.dto.Tournament;
import com.conapp.tournament.repository.TournamentRepository;

public class ManagerModel implements ManagerControllerToModel{

    private ManagerModelToController managerController;
    private TournamentRepository repo;
    private Tournament currentTour;

    ManagerModel(ManagerModelToController managerController)
    {
        this.managerController = managerController;
        repo = TournamentRepository.getInstance();
    }

    public void addTournament(String name, int noOfTeams, HashSet<Team> teams)
    {
        repo.addTournament(name, noOfTeams, teams);
    }

    public boolean getTournaments()
    {
        return managerController.printTournaments(repo.getTournaments());
    }

    @Override
    public boolean checkTournamentExists(int n) {
        currentTour = repo.getTournament(n);
        boolean b = currentTour!=null;
        if(!b)
            managerController.editFailure("Tournament does not exist");
        return currentTour!=null;
    }

    @Override
    public String getRoundName() {
        return currentTour.getRoundName();
    }

    @Override
    public String getCurrentMatch() {
       return currentTour.getCurrentMatch();
    }

    @Override
    public void updateScore(String homeScore, String homeOvers, String awayScore, String awayOvers) {
        currentTour.updateScore(homeScore, homeOvers, awayScore, awayOvers);
    }

    @Override
    public void viewTournament(int n) {
        currentTour.printTournament();
    }

    @Override
    public boolean deleteTournament(int n) {
        if(n>=0 && n<repo.getTournaments().size())
        {
            repo.deleteTournament(n);
            return true;
        }
        return false;
    }
}
