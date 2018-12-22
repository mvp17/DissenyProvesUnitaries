package kiosk;

import data.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class VoteCounter {

    private int nulls, blanks, totalVotes;
    private ArrayList<Party> listParty;
    private HashMap<Party, Integer> votes;

    public VoteCounter(Set<Party> validParties){
        listParty = new ArrayList<>();
        listParty.addAll(validParties);
        votes = new HashMap<>();
    }

    public void countParty(Party party) throws Exception{

        if(!listParty.contains(party))
            throw new Exception("El partit no és a la llista inicial");

        if(votes.containsKey(party))
            votes.put(party, votes.get(party)+1); //intValue()
        else
            votes.put(party, 1);

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
        //preguntar que ha de fer exactament
    }

    public int getVotesFor(Party party) {
        if(!votes.containsKey(party))
            return 0;
        return votes.get(party); //intValue()
    }

    public int getNulls() {
        return nulls;
    }

    public int getBlanks() {
        return blanks;
    }

    public int getTotal() { return totalVotes; }
}