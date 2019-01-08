package kiosk;

import data.DigitalSignature;
import data.Party;
import data.MailAddress;
import services.ElectoralOrganism;
import services.MailerService;


import java.util.HashSet;
import java.util.Set;

public class VotingKiosk {

    public ElectoralOrganism eo;
    public MailerService mService;
    private VoteCounter voteCounter;
    private Set<Party> parties;
    private Party party;

    public VotingKiosk(){
        parties = new HashSet<>();
        parties.add(new Party("Partit 1"));
        parties.add(new Party("Partit 2"));
        voteCounter = new VoteCounter(parties);
    }

    public void setElectoralOrganism(ElectoralOrganism eO) {
        this.eo = eO;
    }

    public void setMailerService(MailerService mService){
        this.mService = mService;
    }

    public void vote(Party party) {
        this.party = party;
        //eo.disableVoter();
        //sendeReceipt(mService.);
        voteCounter.scrutinize(party);
    }

    public void sendeReceipt(MailAddress address) {
        DigitalSignature digitalSignature = eo.askForDigitalSignature(party);
        mService.send(address,digitalSignature);
    }
}