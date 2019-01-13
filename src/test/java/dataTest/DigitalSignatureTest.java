package dataTest;

import data.DigitalSignature;
import org.junit.jupiter.api.Test;
import data.NullException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitalSignatureTest {

    @Test
    void partyExceptionTest(){
        assertThrows(NullException.class, () -> new DigitalSignature(null));
    }
}