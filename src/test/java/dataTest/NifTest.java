package dataTest;

import data.Nif;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTest {

    private Nif nif;

    @Test
    void partyExceptionTest(){
        //case nif null
        assertThrows(IllegalArgumentException.class, () -> nif = new Nif(null));

        //case nif size > 9
        assertThrows(IllegalArgumentException.class, () -> nif = new Nif("492571195G"));

        //case nif last character is not an uppercase letter
        assertThrows(IllegalArgumentException.class, () -> nif = new Nif("492571955"));

        //case in nif there are more than one letter
        assertThrows(IllegalArgumentException.class, () -> nif = new Nif("4925G7195L"));

        //case nif last character is a lowercase letter
        assertThrows(IllegalArgumentException.class, () -> nif = new Nif("49257195l"));

    }
}
