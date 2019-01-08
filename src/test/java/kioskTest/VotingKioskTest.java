package kioskTest;

import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;

import java.net.NetworkInterface;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest {

    private VotingKiosk votingKiosk;
    private ElectoralOrganismMock eo;
    private MailerServiceSpy mss;

    @BeforeEach
    void setUp(){
        votingKiosk = new VotingKiosk();
        eo = new ElectoralOrganismMock();
        setUpElectoralOrganism(eo);
        mss = new MailerServiceSpy();
        //setUpMailerService(mss);
        //votingKiosk.setElectoralOrganism(eo);
    }

    /*
    private void setUpMailerService(MailerServiceSpy mss){

    }*/
    private void setUpElectoralOrganism(ElectoralOrganismMock eo){
        Nif n1 = new Nif("74824586T");
        Nif n2 = new Nif("35478908Y");
        Nif n3 = new Nif("12345678K");
        Nif n4 = new Nif("74185296R");
        Nif n5 = new Nif("85961245F");
        eo.census.add(n1);
        eo.census.add(n2);
        eo.census.add(n3);
        eo.census.add(n4);
        eo.census.add(n5);
        eo.pollingStation.add(n1);
        eo.pollingStation.add(n3);
        eo.pollingStation.add(n5);
    }

    private static class MailerServiceSpy implements MailerService{
        private int mailsSent = 0;
        public void send(MailAddress address, DigitalSignature signature) {
            mailsSent+=1;
        }
    }

    private static class ElectoralOrganismMock implements ElectoralOrganism{
        private ArrayList<Nif> voters = new ArrayList<>();
        private ArrayList<Nif> census = new ArrayList<>();
        private ArrayList<Nif> pollingStation = new ArrayList<>();
        private Nif nif;

        public boolean canVote(Nif nif){
            if(voters.contains(nif))
                return false;
            if(conditions(nif)) {
                this.nif = nif;
                return true;
            }else
                return false;
        }
        private boolean conditions(Nif nif){ //Voter conditions to vote
            return census.contains(nif) && pollingStation.contains(nif);
        }

        public void disableVoter(Nif nif){
            voters.add(nif);
        }
        public DigitalSignature askForDigitalSignature(Party party){
            return null;
        }
    }

    @Test
    void voteTest(){
        Party p1 = new Party("Party 1");
        Nif n1 = new Nif("74824586T");
        Nif n2 = new Nif("35478908Y");
        Nif n3 = new Nif("12345678K");
        Nif n4 = new Nif("74185296R");
        Nif n5 = new Nif("85961245F");
        assertFalse(eo.canVote(n2));
        assertTrue(eo.canVote(n3));

    }

    @Test
    void sendeReceiptTest(){
        ElectoralOrganism eo = new ElectoralOrganismImpl();
        Party p1 = new Party("Partit 1");
        DigitalSignature digitalSignature = eo.askForDigitalSignature(p1);
        MailAddress address = new MailAddress("mvp17@gmail.com");
        mss.send(address, digitalSignature);
        assertEquals(1,mss.mailsSent);

    }

}