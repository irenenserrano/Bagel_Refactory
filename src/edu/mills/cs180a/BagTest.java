package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.math.BigDecimal;
import java.util.stream.Stream;
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
    void getTotalPrice_assertEquals_DiffBagelAmounts(Bag bag, BigDecimal cost) {
        assertEquals(cost, bag.getTotalPrice());
    }

    // testing the correct price is return for one of each bagel in each old
    // fashioned and gourmet categories
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getPerBagelPriceOFG.class)
    void getPerBagelPrice_assertEquals_CorrectPricePerBagelOFG(Bag bag, BigDecimal cost) {
        assertEquals(cost, bag.getPerBagelPrice());
    }

    private static Bag makeBag(Bagel.Type type, int quantity) {
        return new Bag(new Bagel(type), quantity);
    }// end makeBagArguments

    // equals - Reflexive, all three categories, all the possible sizes
    void equals_True_SameBagelSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeBag(Type.PLAIN, 3).getTotalPrice(),
                makeBag(Type.PLAIN, 3).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 6).getTotalPrice(),
                makeBag(Type.PLAIN, 6).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 13).getTotalPrice(),
                makeBag(Type.PLAIN, 13).getTotalPrice());

        // Gourmet
        assertEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(),
                makeBag(Type.ASIAGO, 3).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(),
                makeBag(Type.ASIAGO, 6).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(),
                makeBag(Type.ASIAGO, 13).getTotalPrice());

        // Day Old
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        blueberry.markDown();
        assertEquals(new Bag(blueberry, 3).getTotalPrice(), new Bag(blueberry, 3).getTotalPrice());
        assertEquals(new Bag(blueberry, 6).getTotalPrice(), new Bag(blueberry, 6).getTotalPrice());
        assertEquals(new Bag(blueberry, 13).getTotalPrice(),
                new Bag(blueberry, 13).getTotalPrice());
    }// end SameBagelAmountPrice

    // equals - Symmetric, all three categories,the possible sizes
    void equals_True_DiffBagelSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeBag(Type.PLAIN, 3).getTotalPrice(),
                makeBag(Type.ONION, 3).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 3).getTotalPrice(),
                makeBag(Type.PLAIN, 3).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 6).getTotalPrice(),
                makeBag(Type.ONION, 6).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 6).getTotalPrice(),
                makeBag(Type.PLAIN, 6).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 13).getTotalPrice(),
                makeBag(Type.ONION, 13).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 13).getTotalPrice(),
                makeBag(Type.PLAIN, 13).getTotalPrice());


        // Gourmet
        assertEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 3).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 3).getTotalPrice(),
                makeBag(Type.ASIAGO, 3).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 6).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 6).getTotalPrice(),
                makeBag(Type.ASIAGO, 6).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 13).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 13).getTotalPrice(),
                makeBag(Type.ASIAGO, 13).getTotalPrice());

        // Day Old
        Bagel onion = new Bagel(Type.ONION);
        onion.markDown();
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        assertEquals(new Bag(onion, 3).getTotalPrice(), new Bag(tomato, 3).getTotalPrice());
        assertEquals(new Bag(tomato, 3).getTotalPrice(), new Bag(onion, 3).getTotalPrice());
        assertEquals(new Bag(onion, 6).getTotalPrice(), new Bag(tomato, 6).getTotalPrice());
        assertEquals(new Bag(tomato, 6).getTotalPrice(), new Bag(onion, 6).getTotalPrice());
        assertEquals(new Bag(onion, 13).getTotalPrice(), new Bag(tomato, 13).getTotalPrice());
        assertEquals(new Bag(tomato, 13).getTotalPrice(), new Bag(onion, 13).getTotalPrice());
    }

    // equals - Transitive, all three categories, all the possible sizes
    void equals_True_3DiffBagelSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeBag(Type.PLAIN, 3).getTotalPrice(),
                makeBag(Type.ONION, 3).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 3).getTotalPrice(),
                makeBag(Type.EVERYTHING, 3).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 3).getTotalPrice(),
                makeBag(Type.EVERYTHING, 3).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 6).getTotalPrice(),
                makeBag(Type.ONION, 6).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 6).getTotalPrice(),
                makeBag(Type.EVERYTHING, 6).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 6).getTotalPrice(),
                makeBag(Type.EVERYTHING, 6).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 13).getTotalPrice(),
                makeBag(Type.ONION, 13).getTotalPrice());
        assertEquals(makeBag(Type.ONION, 13).getTotalPrice(),
                makeBag(Type.EVERYTHING, 13).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 13).getTotalPrice(),
                makeBag(Type.EVERYTHING, 13).getTotalPrice());

        // Gourmet
        assertEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 3).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 3).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 3).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 3).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 6).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 6).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 6).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 6).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 13).getTotalPrice());
        assertEquals(makeBag(Type.BLUEBERRY, 13).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 13).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(),
                makeBag(Type.CINNAMON_RAISIN, 13).getTotalPrice());

        // Day Old
        Bagel plain = new Bagel(Type.PLAIN);
        plain.markDown();
        Bagel asiago = new Bagel(Type.ASIAGO);
        asiago.markDown();
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        blueberry.markDown();

        assertEquals(new Bag(plain, 3).getTotalPrice(), new Bag(asiago, 3).getTotalPrice());
        assertEquals(new Bag(asiago, 3).getTotalPrice(), new Bag(blueberry, 3).getTotalPrice());
        assertEquals(new Bag(plain, 3).getTotalPrice(), new Bag(blueberry, 3).getTotalPrice());
        assertEquals(new Bag(plain, 6).getTotalPrice(), new Bag(asiago, 6).getTotalPrice());
        assertEquals(new Bag(asiago, 6).getTotalPrice(), new Bag(blueberry, 6).getTotalPrice());
        assertEquals(new Bag(plain, 6).getTotalPrice(), new Bag(blueberry, 6).getTotalPrice());
        assertEquals(new Bag(plain, 13).getTotalPrice(), new Bag(asiago, 13).getTotalPrice());
        assertEquals(new Bag(asiago, 13).getTotalPrice(), new Bag(blueberry, 13).getTotalPrice());
        assertEquals(new Bag(plain, 13).getTotalPrice(), new Bag(blueberry, 13).getTotalPrice());
    }

    // equals - Consistent, all three categories, all the possible sizes
    void equals_True_DiffBagelSameTotalPriceLater() {
        // Old Fashioned
        assertEquals(makeBag(Type.PLAIN, 3).getTotalPrice(),
                makeBag(Type.ONION, 3).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 6).getTotalPrice(),
                makeBag(Type.ONION, 6).getTotalPrice());
        assertEquals(makeBag(Type.PLAIN, 13).getTotalPrice(),
                makeBag(Type.ONION, 13).getTotalPrice());

        // Gourmet
        assertEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 3).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 6).getTotalPrice());
        assertEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(),
                makeBag(Type.BLUEBERRY, 13).getTotalPrice());

        // Day Old
        Bagel onion = new Bagel(Type.ONION);
        onion.markDown();
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        assertEquals(new Bag(onion, 3).getTotalPrice(), new Bag(tomato, 3).getTotalPrice());
        assertEquals(new Bag(onion, 6).getTotalPrice(), new Bag(tomato, 6).getTotalPrice());
        assertEquals(new Bag(onion, 13).getTotalPrice(), new Bag(tomato, 13).getTotalPrice());
    }

    // equals - Not Null, all three categories,the possible sizes
    void equals_False_Null() {
        // Old Fashioned
        assertNotEquals(makeBag(Type.PLAIN, 3).getTotalPrice(), null);
        assertNotEquals(makeBag(Type.PLAIN, 6).getTotalPrice(), null);
        assertNotEquals(makeBag(Type.PLAIN, 13).getTotalPrice(), null);

        // Gourmet
        assertNotEquals(makeBag(Type.ASIAGO, 3).getTotalPrice(), null);
        assertNotEquals(makeBag(Type.ASIAGO, 6).getTotalPrice(), null);
        assertNotEquals(makeBag(Type.ASIAGO, 13).getTotalPrice(), null);

        // Day Old
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        blueberry.markDown();
        assertNotEquals(new Bag(blueberry, 3).getTotalPrice(), null);
        assertNotEquals(new Bag(blueberry, 6).getTotalPrice(), null);
        assertNotEquals(new Bag(blueberry, 13).getTotalPrice(),null);
    }


    private static Arguments makeArguments(Bagel.Type type, int quantity, BigDecimal price) {
        return Arguments.of(new Bag(new Bagel(type), quantity), price);
    }

    static class BagArgumentsProvider_getTotalPrice implements ArgumentsProvider {
        Bagel old = new Bagel(Type.BLUEBERRY);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            old.markDown();
            return Stream.of(
                    makeArguments(Type.PLAIN, 3, new BigDecimal("1.50")),
                    makeArguments(Type.PLAIN, 6, new BigDecimal("2.85")),
                    makeArguments(Type.PLAIN, 13, new BigDecimal("6.00")),
                    makeArguments(Type.ASIAGO, 3, new BigDecimal("2.10")),
                    makeArguments(Type.ASIAGO, 6, new BigDecimal("3.99")),
                    makeArguments(Type.ASIAGO, 13, new BigDecimal("8.40")),
                    Arguments.of(new Bag(old, 3), new BigDecimal("1.05")),
                    Arguments.of(new Bag(old, 6), new BigDecimal("1.99")),
                    Arguments.of(new Bag(old, 13), new BigDecimal("4.20")));
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
                    makeArguments(Type.PLAIN, 1, new BigDecimal(".50")),
                    makeArguments(Type.POPPY_SEED, 1, new BigDecimal(".50")),
                    makeArguments(Type.SESAME_SEED, 1, new BigDecimal(".50")),
                    makeArguments(Type.ONION, 1, new BigDecimal(".50")),
                    makeArguments(Type.EVERYTHING, 1, new BigDecimal(".50")),
                    makeArguments(Type.ASIAGO, 1, new BigDecimal(".70")),
                    makeArguments(Type.BLUEBERRY, 1, new BigDecimal(".70")),
                    makeArguments(Type.CINNAMON_RAISIN, 1, new BigDecimal(".70")),
                    makeArguments(Type.SUN_DRIED_TOMATO, 1, new BigDecimal(".70")),
                    Arguments.of(new Bag(plain, 1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(poppySeed,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(sesameSeed,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(onion,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(everything,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(asiago,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(blueberry,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(cinnamonRaisin,1), BigDecimal.valueOf(.35)),
                    Arguments.of(new Bag(sunDriedTomato,1), BigDecimal.valueOf(.35)));
        }
    }
}
