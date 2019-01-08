package dataTest;

import data.DigitalSignature;
import org.junit.jupiter.api.Test;
import services.NullException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitalSignatureTest {

    private DigitalSignature digsign;

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> digsign = new DigitalSignature(null));
    }
}