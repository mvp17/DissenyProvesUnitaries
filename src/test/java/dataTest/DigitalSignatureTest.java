package dataTest;

import data.DigitalSignature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitalSignatureTest {

    private DigitalSignature digsign;

    @Test
    void partyExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            digsign = new DigitalSignature(null);
        });
    }
}