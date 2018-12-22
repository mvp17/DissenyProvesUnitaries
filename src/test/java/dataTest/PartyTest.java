package dataTest;

import data.Party;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartyTest {

    private Party party;

    @Test
    void partyExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> party = new Party(null));
    }
}