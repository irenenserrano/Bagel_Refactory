package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;

class BagTest {
    // testing under bulk minimum/bulk minimum/buy1get1free for each category
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
    void getTotalPrice_assertEquals_VaryingAmountsOfBagel(Bag bag, double expectedPrice) {
        assertEquals(expectedPrice, bag.getTotalPrice().doubleValue());
    }

    // testing the correct price is return for one of each bagel in each old
    // fashioned and gourmet categories
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getPerBagelPriceOFG.class)
    void getPerBagelPrice_assertEquals_CorrectPricePerBagelOFG(Bag bag, double expectedPrice) {
        assertEquals(expectedPrice, bag.getPerBagelPrice().doubleValue());
    }

    // creates a new bag object
    private static Bag makeBag(Bagel.Type type, int quantity) {
        return new Bag(new Bagel(type), quantity);
    }

    @Test
    void equals_True_SameBagelSameAmount() {
        // Old Fashioned
        assertEquals(makeBag(Type.PLAIN,3), makeBag(Type.PLAIN,3));
        assertEquals(makeBag(Type.PLAIN,6), makeBag(Type.PLAIN,6));
        assertEquals(makeBag(Type.PLAIN,13),makeBag(Type.PLAIN,13));

        // Gourmet
        assertEquals(makeBag(Type.ASIAGO,3), makeBag(Type.ASIAGO,3));
        assertEquals(makeBag(Type.ASIAGO,6), makeBag(Type.ASIAGO,6));
        assertEquals(makeBag(Type.ASIAGO,13),makeBag(Type.ASIAGO,13));

        // Day Old
        Bagel old = new Bagel(Type.BLUEBERRY);
        old.markDown();
        assertEquals(new Bag(old,3), new Bag(old,3));
        assertEquals(new Bag(old,6), new Bag(old,6));
        assertEquals(new Bag(old,13),new Bag(old,13));
    }

    @Test
    void equals_False_DiffBagelSameAmount() {
        // Old Fashioned
        assertNotEquals(makeBag(Type.PLAIN,3), makeBag(Type.ONION,3));
        assertNotEquals(makeBag(Type.PLAIN,6), makeBag(Type.EVERYTHING,6));

        // Gourmet
        assertNotEquals(makeBag(Type.ASIAGO,3), makeBag(Type.BLUEBERRY, 3));
        assertNotEquals(makeBag(Type.CINNAMON_RAISIN,6), makeBag(Type.SUN_DRIED_TOMATO,6));

        // Day  Old
        Bagel old1 = new Bagel(Type.BLUEBERRY);
        old1.markDown();
        Bagel old2 = new Bagel(Type.POPPY_SEED);
        old2.markDown();
        Bagel old3 = new Bagel(Type.ONION);
        old3.markDown();
        assertNotEquals(new Bag(old1,4), new Bag(old2,4));
        assertNotEquals(new Bag(old2,13), new Bag(old3, 13));
    }

    @Test
    void equals_False_SameBagelDiffAmount() {
        // Old Fashioned
        assertNotEquals(makeBag(Type.ONION,2), makeBag(Type.ONION,3));
        assertNotEquals(makeBag(Type.POPPY_SEED,12), makeBag(Type.POPPY_SEED,13));

        // Gourmet
        assertNotEquals(makeBag(Type.SUN_DRIED_TOMATO,4), makeBag(Type.SUN_DRIED_TOMATO, 6));
        assertNotEquals(makeBag(Type.CINNAMON_RAISIN, 12), makeBag(Type.CINNAMON_RAISIN, 13));

        // Day Old
        Bagel old1 = new Bagel(Type.EVERYTHING);
        old1.markDown();
        Bagel old2 = new Bagel(Type.ONION);
        old2.markDown();
        assertNotEquals(new Bag(old2, 3), new Bag(old2, 7));
        assertNotEquals(new Bag(old1, 12), new Bag(old1, 13));

    }

    @Test
    void equals_False_SameTypeMarkedDown() {
        // Old Fashioned
        Bagel plain = new Bagel(Type.PLAIN);
        plain.markDown();
        assertNotEquals(makeBag(Type.PLAIN, 4), new Bag(plain,4));

        // Gourmet
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        assertNotEquals(makeBag(Type.SUN_DRIED_TOMATO, 13), new Bag(tomato,13));
    }

    @Test
    void equals_False_Null() {
        // Old Fashioned
        assertNotEquals(makeBag(Type.POPPY_SEED,4), null);

        // Gourmet
        assertNotEquals(makeBag(Type.CINNAMON_RAISIN, 12), null);

        // Day Old
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        assertNotEquals(makeBag(Type.SUN_DRIED_TOMATO, 1), null);
    }

    private static Arguments makeArguments(Bagel.Type type, int quantity, double price) {
        return Arguments.of(new Bag(new Bagel(type), quantity), price);
    }

    static class BagArgumentsProvider_getTotalPrice implements ArgumentsProvider {
        Bagel old = new Bagel(Type.BLUEBERRY);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            old.markDown();
            return Stream.of(
                    makeArguments(Type.PLAIN, 3, 1.5),
                    makeArguments(Type.PLAIN, 6, 2.85),
                    makeArguments(Type.PLAIN, 13, 6.0),
                    makeArguments(Type.ASIAGO, 3, 2.1),
                    makeArguments(Type.ASIAGO, 6, 3.99),
                    makeArguments(Type.ASIAGO, 13, 8.40),
                    Arguments.of(new Bag(old, 3), 1.05),
                    Arguments.of(new Bag(old, 6), 2.00),
                    Arguments.of(new Bag(old, 13), 4.2));
        }
    }

    static class BagArgumentsProvider_getPerBagelPriceOFG implements ArgumentsProvider {
        Bagel plain = new Bagel(Type.PLAIN);
        Bagel poppySeed = new Bagel(Type.POPPY_SEED);
        Bagel sesameSeed = new Bagel(Type.SESAME_SEED);
        Bagel onion = new Bagel(Type.ONION);
        Bagel everything = new Bagel(Type.EVERYTHING);
        Bagel asiago = new Bagel(Type.ASIAGO);
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        Bagel cinnamonRaisin = new Bagel(Type.CINNAMON_RAISIN);
        Bagel sunDriedTomato = new Bagel(Type.SUN_DRIED_TOMATO);
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            plain.markDown();
            poppySeed.markDown();
            sesameSeed.markDown();
            onion.markDown();
            everything.markDown();
            asiago.markDown();
            blueberry.markDown();
            cinnamonRaisin.markDown();
            sunDriedTomato.markDown();
            return Stream.of(
                    makeArguments(Type.PLAIN, 1, .5),
                    makeArguments(Type.POPPY_SEED, 1, .5),
                    makeArguments(Type.SESAME_SEED, 1, .5),
                    makeArguments(Type.ONION, 1, .5),
                    makeArguments(Type.EVERYTHING, 1, .5),
                    makeArguments(Type.ASIAGO, 1, .7),
                    makeArguments(Type.BLUEBERRY, 1, .7),
                    makeArguments(Type.CINNAMON_RAISIN, 1, .7),
                    makeArguments(Type.SUN_DRIED_TOMATO, 1, .7),
                    Arguments.of(new Bag(plain, 1), .35),
                    Arguments.of(new Bag(poppySeed,1), .35),
                    Arguments.of(new Bag(sesameSeed,1), .35),
                    Arguments.of(new Bag(onion,1), .35),
                    Arguments.of(new Bag(everything,1), .35),
                    Arguments.of(new Bag(asiago,1), .35),
                    Arguments.of(new Bag(blueberry,1), .35),
                    Arguments.of(new Bag(cinnamonRaisin,1), .35),
                    Arguments.of(new Bag(sunDriedTomato,1), .35));
        }
    }
}
