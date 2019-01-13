package dataTest;

import data.MailAddress;
import org.junit.jupiter.api.Test;
import data.NullException;
import static org.junit.jupiter.api.Assertions.*;

public class MailAddressTest {

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> new MailAddress(null));
    }
}