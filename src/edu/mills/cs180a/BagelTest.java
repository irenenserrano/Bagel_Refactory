package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import edu.mills.cs180a.Bagel.Type;

class BagelTest {
    private static Bagel PLAIN;
    private static Bagel ONION;
    private static Bagel ASIAGO;
    private static Bagel BLUEBERRY;
    private static Bagel OLD_1;
    private static Bagel OLD_2;

    @BeforeAll
    public static void setup() {
        PLAIN = new Bagel(Type.PLAIN);
        ONION = new Bagel(Type.ONION);
        ASIAGO = new Bagel(Type.ASIAGO);
        BLUEBERRY = new Bagel(Type.BLUEBERRY);
        OLD_1 = new Bagel(Type.CINNAMON_RAISIN);
        OLD_1.markDown();
        OLD_2 = new Bagel(Type.EVERYTHING);
        OLD_2.markDown();
    }

    // Reflexive - all categories
    void equals_true_SameBagelSameTypeSameCategory() {
        // Old Fashioned
        assertEquals(PLAIN.getCategory(), PLAIN.getCategory());
        assertEquals(PLAIN.getType(), PLAIN.getType());

        // Gourmet
        assertEquals(ASIAGO.getCategory(), ASIAGO.getCategory());
        assertEquals(ASIAGO.getType(), ASIAGO.getType());

        // Day Old
        assertEquals(OLD_1.getCategory(), OLD_1.getCategory());
        assertEquals(OLD_1.getType(), OLD_1.getType());
    }

    // Symmetric - all categories
    void equals_true_DifferentBagelSameCategory() {

    }
}
