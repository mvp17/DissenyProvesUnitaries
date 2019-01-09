package kioskTest;

import data.Party;
import kiosk.VoteCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.NotContainsElement;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;


public class VoteCounterTest {
    VoteCounter voteCounter;
    HashSet<Party> listParties;

    @BeforeEach
    void setUp(){
        Party party = new Party("PP");
        Party party1 = new Party("PSOE");
        listParties = new HashSet<>();
        listParties.add(party); listParties.add(party1);
        voteCounter = new VoteCounter(listParties);
    }

    @Test
    void countPartyTest() {
        Party party = new Party("PP");
        Party party1 = new Party("PSOE");
        voteCounter.countParty(party);
        assertEquals(voteCounter.getVotesFor(party), 1);
        voteCounter.countParty(party);
        assertEquals(voteCounter.getVotesFor(party), 2);
        assertEquals(voteCounter.getVotesFor(party1), 0);
        assertEquals(3,voteCounter.getTotal());
    }

    @Test
    void scrutinizeForCountNullsAndCountBlanksTest(){
        Party party = new Party("null");
        Party party1 = new Party("null");
        Party party2 = new Party("null");
        Party party3 = new Party("");
        Party party4 = new Party("");
        voteCounter.scrutinize(party);
        voteCounter.scrutinize(party1);
        voteCounter.scrutinize(party2);
        voteCounter.scrutinize(party3);
        voteCounter.scrutinize(party4);
        assertEquals(3, voteCounter.getNulls());
        assertEquals(2, voteCounter.getBlanks());

        assertEquals(5, voteCounter.getTotal());


    }

    @Test
    void countPartyExceptionTest(){
        Party party = new Party("PdeCat");
        assertThrows(NotContainsElement.class, ()-> voteCounter.countParty(party));
        assertEquals(0,voteCounter.getTotal());
    }
}