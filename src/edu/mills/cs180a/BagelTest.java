package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    @Test
    void equals_True_SameBagelSameTypeSameCategory() {
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

    @Test
    void equals_True_DifferentBagelSameCategory() {
        // Old Fashioned
        assertEquals(PLAIN.getCategory(), ONION.getCategory());

        // Gourmet
        assertEquals(ASIAGO.getCategory(), BLUEBERRY.getCategory());

        // Day Old
        assertEquals(OLD_1.getCategory(), OLD_2.getCategory());
    }

    @Test
    void equals_False_DifferentBagelDifferentType() {
        // Old Fashioned
        assertNotEquals(PLAIN.getType(), ONION.getType());

        // Gourmet
        assertNotEquals(ASIAGO.getType(), BLUEBERRY.getType());

        // Day Old
        assertNotEquals(OLD_1.getType(), OLD_2.getType());
    }

    @Test
    void equals_True_ThereBagelsSameCategory() {
        // Old Fashioned
        Bagel everything = new Bagel(Type.EVERYTHING);
        assertEquals(PLAIN.getCategory(), ONION.getCategory());
        assertEquals(ONION.getCategory(), everything.getCategory());
        assertEquals(PLAIN.getCategory(), everything.getCategory());

        // Gourment
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        assertEquals(ASIAGO.getCategory(), BLUEBERRY.getCategory());
        assertEquals(BLUEBERRY.getCategory(), tomato.getCategory());
        assertEquals(ASIAGO.getCategory(), tomato.getCategory());

        // Day old
        tomato.markDown();
        assertEquals(OLD_1.getCategory(), OLD_2.getCategory());
        assertEquals(OLD_2.getCategory(), tomato.getCategory());
        assertEquals(OLD_1.getCategory(), tomato.getCategory());
    }

    @Test
    void equals_False_Null() {
        // Old Fashioned
        assertNotEquals(PLAIN.getType(),null);
        assertNotEquals(PLAIN.getCategory(), null);

        // Gourmet
        assertNotEquals(ASIAGO.getType(), null);
        assertNotEquals(ASIAGO.getCategory(), null);

        // Day Old
        assertNotEquals(OLD_1.getType(), null);
        assertNotEquals(OLD_1.getCategory(), null);
    }


}
