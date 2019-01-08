package kioskTest;

import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.MailerService;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest {

    private VotingKiosk votingKiosk;
    private ElectoralOrganism eo;
    private MailerService ms;

    @BeforeEach
    void setUp(){
        votingKiosk = new VotingKiosk();
        eo = new ElectoralOrganismMock();
        setUpElectoralOrganism((ElectoralOrganismMock) eo);
        votingKiosk.setElectoralOrganism(eo);
        ms = new MailerServiceSpy();
        votingKiosk.setMailerService(ms);
    }

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
        private ArrayList<Nif> voters = new ArrayList<>(); //check if the voter has just vote
        private ArrayList<Nif> census = new ArrayList<>(); //check if the voter is in the electoral census
        private ArrayList<Nif> pollingStation = new ArrayList<>(); //check if the voter is in the correct polling station
        private Nif nif;

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

        public void disableVoter(Nif nif){
            voters.add(nif);
        }
        public DigitalSignature askForDigitalSignature(Party party){
            byte[] bytes = party.getName().getBytes(StandardCharsets.UTF_8);
            return new DigitalSignature(bytes);
        }
    }

    @Test
    void canVoteTest(){
        Party p1 = new Party("Party 1");
        Nif n1 = new Nif("74824586T");
        Nif n2 = new Nif("35478908Y");
        Nif n3 = new Nif("12345678K");
        Nif n4 = new Nif("74185296R");
        assertFalse(votingKiosk.eo.canVote(n2));
        assertTrue(votingKiosk.eo.canVote(n3));
        //votingKiosk.vote(p1);
        assertTrue(votingKiosk.eo.canVote(n1));
        assertFalse(votingKiosk.eo.canVote(n4));

    }

    @Test
    void disableVoterTest() {

    }


    @Test
    void sendeReceiptTest(){

        Party p1 = new Party("Partit1");
        DigitalSignature digitalSignature = eo.askForDigitalSignature(p1);
        MailAddress address = new MailAddress("mvp17@gmail.com");
        votingKiosk.mService.send(address, digitalSignature);
        //assertEquals(1,);

    }

}