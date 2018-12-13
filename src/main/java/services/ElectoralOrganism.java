package services;

import data.Nif;
import data.DigitalSignature;
import data.Party;

public interface ElectoralOrganism {
    boolean canVote(Nif nif);
    void disableVoter(Nif nif);
    DigitalSignature askForDigitalSignature(Party party);
}
