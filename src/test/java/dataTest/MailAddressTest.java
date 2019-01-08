package dataTest;

import data.MailAddress;
import org.junit.jupiter.api.Test;
import services.NullException;

import static org.junit.jupiter.api.Assertions.*;

public class MailAddressTest {

    private MailAddress mail;

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> mail = new MailAddress(null));
    }
}