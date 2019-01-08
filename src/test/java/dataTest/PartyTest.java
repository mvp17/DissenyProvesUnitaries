package dataTest;

import data.Party;
import org.junit.jupiter.api.Test;
import services.NullException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartyTest {

    private Party party;

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> party = new Party(null));
    }
}