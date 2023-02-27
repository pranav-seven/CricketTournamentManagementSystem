package com.conapp.tournament.manager;

import java.util.HashSet;

import com.conapp.tournament.dto.Team;

public interface ManagerControllerToModel {

    void addTournament(String name, int numberOfTeams, HashSet<Team> teams);

    boolean getTournaments();

    boolean checkTournamentExists(int n);

    String getRoundName();

    String getCurrentMatch();

    void updateScore(String homeScore, String homeOvers, String awayScore, String awayOvers);

    void viewTournament(int n);

    boolean deleteTournament(int n);

}
