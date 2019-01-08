package kiosk;

import data.DigitalSignature;
import data.Party;
import data.MailAddress;
import services.ElectoralOrganismImpl;
import services.MailerServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class VotingKiosk {

    private ElectoralOrganismImpl eoi;
    private MailerServiceImpl msi;
    private VoteCounter voteCounter;
    private Set<Party> parties;
    private Party party;

    public VotingKiosk(){
        parties = new HashSet<>();
        parties.add(new Party("Partit 1"));
        parties.add(new Party("Partit 2"));
        voteCounter = new VoteCounter(parties);
    }

    public void setElectoralOrganism(ElectoralOrganismImpl eO) {
        eoi = eO;
    }

    public void setMailerService(MailerServiceImpl mService){
        msi = mService;
    }

    public void vote(Party party) {
        this.party = party;
        eoi.disableVoter(eoi.nif);
        sendeReceipt(msi.address);
        voteCounter.scrutinize(party);
    }

    public void sendeReceipt(MailAddress address) {
        DigitalSignature digitalSignature = eoi.askForDigitalSignature(party);
        msi.send(address,digitalSignature);
    }
}