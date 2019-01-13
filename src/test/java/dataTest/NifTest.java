package dataTest;

import data.Nif;
import org.junit.jupiter.api.Test;
import data.NullException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTest {

    @Test
    void partyExceptionTest(){
        //case nif null
        assertThrows(NullException.class, () -> new Nif(null));

        //case nif size > 9
        assertThrows(NullException.class, () -> new Nif("492571195G"));

        //case nif last character is not an uppercase letter
        assertThrows(NullException.class, () -> new Nif("492571955"));

        //case in nif there are more than one letter
        assertThrows(NullException.class, () -> new Nif("4925G7195L"));

        //case nif last character is a lowercase letter
        assertThrows(NullException.class, () -> new Nif("49257195l"));
    }
}