package services;

import data.DigitalSignature;
import data.Nif;
import data.Party;

import javax.swing.plaf.TableHeaderUI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ElectoralOrganismImpl implements ElectoralOrganism{

    private ArrayList<Nif> voters = new ArrayList<>(); //checking the list to verify a voter can vote
    public Nif nif;
    private ArrayList<Nif> census = new ArrayList<>();
    private ArrayList<Nif> pollingStation = new ArrayList<>();

    @Override
    public boolean canVote(Nif nif){
        if(!voters.contains(nif) && conditions(nif)) {
            this.nif = nif;
            return true;
        }else
            return false;
    }
    private boolean conditions(Nif nif){ //Voter conditions to vote
        return census.contains(nif) && pollingStation.contains(nif);
    }

    @Override
    public void disableVoter(Nif nif){
        voters.add(nif);
    }

    @Override
    public DigitalSignature askForDigitalSignature(Party party){
        byte[] bytes = party.getName().getBytes(StandardCharsets.UTF_8);
        return new DigitalSignature(bytes);
    }

}
