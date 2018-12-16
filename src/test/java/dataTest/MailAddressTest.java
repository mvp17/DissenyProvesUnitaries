package dataTest;

import data.MailAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MailAddressTest {

    private MailAddress mail;

    @Test
    void partyExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            mail = new MailAddress(null);
        });
    }
}