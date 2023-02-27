package com.conapp.tournament.dto;

public class Team {
    String teamName;

    public Team(String name)
    {
        teamName = name;
    }

    public String toString()
    {
        return teamName;
    }
}
