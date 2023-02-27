package com.conapp.tournament.dto;

import java.util.ArrayList;
import java.util.HashSet;

public class Tournament {
    private String name;
    private int noOfTeams;
    // private HashSet<Team> teams;
    private int noOfRounds;
    private Round[] rounds;
    private int currentRound;
    private String champion;

    public Tournament(String name, int noOfTeams, HashSet<Team> teams)
    {
        this.name = name;
        // this.teams = teams;
        this.noOfTeams = noOfTeams;
        champion = "N/A";
        while(noOfTeams>1) {
            noOfTeams = noOfTeams>>1;
            noOfRounds++;
        }
        rounds = new Round[noOfRounds];
        currentRound = noOfRounds-1;
        noOfTeams = this.noOfTeams;
        for(int i=noOfRounds-1; i>=0; i--) {
            String roundName = null;
            switch(i)
            {
                case 0: roundName = "Final"; break;
                case 1: roundName = "Semi-final"; break;
                case 2: roundName = "Quarter-final"; break;
                default: roundName = "Round of "+noOfTeams;
            }
            rounds[i] = new Round(i, noOfTeams, roundName);
            noOfTeams = noOfTeams>>1;
        }
        boolean home = true;
        for(Team team : teams)
        {
            rounds[currentRound].addTeamsInMatch(team, home);
            home = !home;
        }
    }

    public void updateScore(String homeRun, String homeOvers, String awayRun, String awayOvers)
    {
        int remainingGames = rounds[currentRound].updateScore(homeRun, homeOvers, awayRun, awayOvers);
        if(remainingGames==0){
            if(currentRound>0)
            {
                currentRound--;
                ArrayList<Team> winners = rounds[currentRound+1].getWinners();
                boolean home = true;
                for(Team team : winners)
                {
                    rounds[currentRound].addTeamsInMatch(team, home);
                    home = !home;
                }
            }
            else if(currentRound==0)
            {
                champion = rounds[currentRound].getWinners().get(0).toString();
                currentRound--;
            }
        }
    }

    public String toString() {
        return String.format("%-26s", name)+String.format("%-15s", noOfTeams)+champion;
    }

    public String getRoundName() {
        if(currentRound<0)
            return null;
        return rounds[currentRound].getRoundName();
    }

    public String getCurrentMatch() {
        
        return rounds[currentRound].getCurrentMatch();
    }

    public void printTournament() {
        for(int i=noOfRounds-1; i>=0; i--)
        {
            System.out.println(rounds[i].getRoundName());
            System.out.println("--------------------");
            for(Match match : rounds[i].getMatches())
            {
                System.out.println(match);
                System.out.println("--------------------");
            }
        }
    }
}
