package kioskTest;

import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.MailerService;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest {

    private VotingKiosk votingKiosk;
    private ElectoralOrganismMock electoralOrganismMock;
    private MailerServiceSpy mailerServiceSpy;
    private Set<Party> parties;

    @BeforeEach
    void setUp(){
        parties = new HashSet<>();
        setUpListParties(parties);

        votingKiosk = new VotingKiosk();

        electoralOrganismMock = new ElectoralOrganismMock();
        setUpElectoralOrganism(electoralOrganismMock);
        votingKiosk.setElectoralOrganism(electoralOrganismMock);

        mailerServiceSpy = new MailerServiceSpy();
        votingKiosk.setMailerService(mailerServiceSpy);

        votingKiosk.setVoteCounter(new VoteCounter(parties));//passem la llista de partits valids desde aqui

    }

    private void setUpListParties(Set<Party> parties){
        Party p1 = new Party("Primer");
        Party p2 = new Party("Segon");
        Party p3 = new Party("Tercer");
        Party p4 = new Party("Quart");
        parties.add(p1);
        parties.add(p2);
        parties.add(p3);
        parties.add(p4);
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

        public boolean canVote(Nif nif){
            return !voters.contains(nif) && conditions(nif);
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
        Nif n1 = new Nif("74824586T");
        Nif n2 = new Nif("35478908Y");
        Nif n3 = new Nif("12345678K");
        Nif n4 = new Nif("74185296R");
        assertFalse(votingKiosk.getElectoralOrganism().canVote(n2));
        assertTrue(votingKiosk.getElectoralOrganism().canVote(n3));
        assertTrue(votingKiosk.getElectoralOrganism().canVote(n1));
        assertFalse(votingKiosk.getElectoralOrganism().canVote(n4));
    }

    @Test
    void voteTest() {
        Party p1 = new Party("Primer");
        votingKiosk.vote(p1);
        assertEquals(1,votingKiosk.getVoteCounter().getVotesFor(p1));
        votingKiosk.vote(p1);
        assertEquals(2,votingKiosk.getVoteCounter().getVotesFor(p1));
    }

    @Test
    void voteNullTest(){
        Party test = new Party("null");
        votingKiosk.vote(test);
        assertEquals(1,votingKiosk.getVoteCounter().getNulls());
        Party test1 = new Party("null");
        votingKiosk.vote(test1);
        assertEquals(2,votingKiosk.getVoteCounter().getNulls());
    }

    @Test
    void voteBlankTest(){
        Party test = new Party("");
        votingKiosk.vote(test);
        assertEquals(1,votingKiosk.getVoteCounter().getBlanks());
        Party test1 = new Party("");
        votingKiosk.vote(test1);
        assertEquals(2,votingKiosk.getVoteCounter().getBlanks());
    }

    @Test
    void disableVoterTest(){
        Party p1 = new Party("Primer");
        Nif n1 = new Nif("12345678K");
        if(votingKiosk.getElectoralOrganism().canVote(n1)){
            votingKiosk.vote(p1);
            votingKiosk.getElectoralOrganism().disableVoter(n1);
        }
        assertFalse(votingKiosk.getElectoralOrganism().canVote(n1));
    }

    @Test
    void disableVoterTest1(){
        Party p1 = new Party("Primer");
        Nif n1 = new Nif("12345678K");
        if(votingKiosk.getElectoralOrganism().canVote(n1)){
            votingKiosk.vote(p1);
            votingKiosk.getElectoralOrganism().disableVoter(n1);
        }
        assertFalse(votingKiosk.getElectoralOrganism().canVote(n1));

        Party p2 = new Party("Segon");
        Nif n2 = new Nif("74824586T");
        if(votingKiosk.getElectoralOrganism().canVote(n2)){
            votingKiosk.vote(p2);
            votingKiosk.getElectoralOrganism().disableVoter(n2);
        }
        assertFalse(votingKiosk.getElectoralOrganism().canVote(n2));
    }


    @Test
    void sendeReceiptTest(){
        //votingKiosk.setMailerService(mailerServiceSpy);

        Party p1 = new Party("Primer");
        Nif n1 = new Nif("12345678K");
        MailAddress address1 = new MailAddress("mvp17@gmail.com");

        if(votingKiosk.getElectoralOrganism().canVote(n1)){
            votingKiosk.vote(p1);
            votingKiosk.getElectoralOrganism().disableVoter(n1);
            votingKiosk.sendeReceipt(address1);
        }
        assertEquals(1,mailerServiceSpy.mailsSent);

        Nif n2 = new Nif("74824586T");
        Party p2 = new Party("Segon");
        MailAddress address2 = new MailAddress("pvm71@tmail.com");

        if(votingKiosk.getElectoralOrganism().canVote(n2)){
            votingKiosk.vote(p2);
            votingKiosk.getElectoralOrganism().disableVoter(n2);
            votingKiosk.sendeReceipt(address2);
        }
        assertEquals(2,mailerServiceSpy.mailsSent);
    }
}