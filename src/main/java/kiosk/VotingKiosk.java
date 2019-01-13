package kiosk;

import data.DigitalSignature;
import data.Party;
import data.MailAddress;
import services.ElectoralOrganism;
import services.MailerService;


import java.util.HashSet;
import java.util.Set;

public class VotingKiosk {

    private ElectoralOrganism eO;
    private MailerService mService;
    private VoteCounter voteCounter; //todo no es pot fer un getter per aquest
    //private Set<Party> parties;
    private Party party; //for method askForDigitalSignature

    public ElectoralOrganism getElectoralOrganism(){
        return eO;
    }

    public VoteCounter getVoteCounter(){
        return voteCounter;
    }

    public VotingKiosk(){
        //parties = new HashSet<>();
    }

    public void setElectoralOrganism(ElectoralOrganism eO) {
        this.eO = eO;
    }

    public void setVoteCounter(VoteCounter vc){
        this.voteCounter = vc;
    }

    public void setMailerService(MailerService mService){
        this.mService = mService;
    }

    public void vote(Party party) {
        this.party = party;
        voteCounter.scrutinize(party);
    }

    public void sendeReceipt(MailAddress address) {
        DigitalSignature digitalSignature = eO.askForDigitalSignature(party); //party of method vote
        mService.send(address, digitalSignature);
    }
}