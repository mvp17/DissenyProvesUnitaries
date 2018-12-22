package services;

import data.DigitalSignature;
import data.Nif;
import data.Party;
import java.nio.charset.StandardCharsets;

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
        byte[] bytes = party.getName().getBytes(StandardCharsets.UTF_8);
        DigitalSignature digitalSignature = new DigitalSignature(bytes);
        return digitalSignature;
    }

}
