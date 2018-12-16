package services;

import data.DigitalSignature;
import data.Nif;
import data.Party;

public class ElectoralOrganismImpl implements ElectoralOrganism{

    @Override
    public boolean canVote(Nif nif){
        return false;
    }

    @Override
    public void disableVoter(Nif nif){

    }

    @Override
    public DigitalSignature askForDigitalSignature(Party party){
        DigitalSignature dgsign = new DigitalSignature(party.getName());
        return dgsign;
    }
}
