package dataTest;

import data.Party;
import org.junit.jupiter.api.Test;
import data.NullException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartyTest {

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> new Party(null));
    }
}