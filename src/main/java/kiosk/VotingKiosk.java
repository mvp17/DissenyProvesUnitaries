package kiosk;

import data.Party;
import data.MailAddress;
import services.ElectoralOrganismImpl;
import services.MailerServiceImpl;

public class VotingKiosk {

    private ElectoralOrganismImpl eoi;
    private MailerServiceImpl msi;

    public VotingKiosk(){
    }

    public void setElectoralOrganism(ElectoralOrganismImpl eO) {
        eoi = eO;
    }

    public void setMailerService(MailerServiceImpl mService){
        msi = mService;
    }

    public void vote(Party party) {
    }

    public void sendeReceipt(MailAddress address) {
    }
}