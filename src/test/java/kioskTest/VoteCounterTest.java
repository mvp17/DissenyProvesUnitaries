package kioskTest;

import data.Party;
import kiosk.VoteCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;


public class VoteCounterTest {
    VoteCounter voteCounter;
    HashSet<Party> listParties;
    //preguntar si estan be aquestos testos

    @BeforeEach
    void initializeVoteCounter(){
        Party party = new Party("PP");
        Party party1 = new Party("PSOE");
        listParties = new HashSet<>();
        listParties.add(party); listParties.add(party1);
        voteCounter = new VoteCounter(listParties);
    }

    @Test
    void countPartyTest() throws Exception {
        Party party = new Party("PP");
        Party party1 = new Party("PSOE");

        voteCounter.countParty(party);
        assertEquals(voteCounter.getVotesFor(party), 1);
        voteCounter.countParty(party);
        assertEquals(voteCounter.getVotesFor(party), 2);

        assertEquals(voteCounter.getVotesFor(party1), 0);
    }

    @Test
    void countPartyExceptionTest(){
        Party party = new Party("PdeCat");
        assertThrows(Exception.class, ()-> voteCounter.countParty(party));
    }
}
