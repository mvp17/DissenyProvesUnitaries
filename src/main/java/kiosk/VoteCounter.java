package kiosk;

import data.Party;

import java.util.Set;

public class VoteCounter {

    private int nulls, blanks, totalVotes;
    private Set<Party> listParty;

    public VoteCounter(Set<Party> validParties){
        listParty.addAll(validParties);
    }

    public void countParty(Party party){
        party.addVote();
        totalVotes+=1;
    }

    public void countNull(){
        this.nulls +=1;
        this.totalVotes+=1;
    }

    public void countBlank() {
        this.blanks+=1;
        this.totalVotes+=1;
    }

    public void scrutinize(Party party) {//TODO
    }

    public int getVotesFor(Party party) {
        return party.getVotes();
    }

    public int getNulls() {
        return this.nulls;
    }

    public int getBlanks() {
        return this.blanks;
    }

    public int getTotal() { return this.totalVotes; }
}