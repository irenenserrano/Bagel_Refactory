package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;

class OrderTest {

    // testing an order of one bag, each category, each possible size
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_MultipleBagAmounts.class)
    void getPrice_assertEquals_MultipleBagCombinations(Order order, BigDecimal cost) {
        assertEquals(cost, order.getPrice());
    }

    private static Bag BAG_PLAIN_3;
    private static Bag BAG_ONION_3;
    private static Bag BAG_SESAME_3;
    private static Bag BAG_POPPY_3;

    private static Bag BAG_ASIAGO_3;
    private static Bag BAG_BLUEBERRY_3;
    private static Bag BAG_CINNAMON_3;

    private static Bag BAG_OLDPLAIN_3;
    private static Bag BAG_OLDTOMATO_3;
    private static Bag BAG_OLDEVERYTHING_3;
    private static Bag BAG_OLDASIAGO_3;

    @BeforeAll
    public static void setup() {
        BAG_PLAIN_3 = makeBag(Type.PLAIN, 3);
        BAG_ONION_3 = makeBag(Type.ONION, 3);
        BAG_SESAME_3 = makeBag(Type.SESAME_SEED, 3);
        BAG_POPPY_3 = makeBag(Type.POPPY_SEED, 3);

        BAG_ASIAGO_3 = makeBag(Type.ASIAGO, 3);
        BAG_BLUEBERRY_3 = makeBag(Type.BLUEBERRY, 3);
        BAG_CINNAMON_3 = makeBag(Type.CINNAMON_RAISIN, 3);

        Bagel plain = new Bagel(Type.PLAIN);
        plain.markDown();
        BAG_OLDPLAIN_3 = new Bag(plain, 3);
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        BAG_OLDTOMATO_3 = new Bag(tomato, 3);
        Bagel everything = new Bagel(Type.EVERYTHING);
        everything.markDown();
        BAG_OLDEVERYTHING_3 = new Bag(everything, 3);
        Bagel asiago = new Bagel(Type.ASIAGO);
        asiago.markDown();
        BAG_OLDASIAGO_3 = new Bag(asiago, 3);

    }

    @Test
    void equals_True_SameSingleBag() {
        assertEquals(makeOrder(BAG_PLAIN_3), makeOrder(BAG_PLAIN_3));
        assertEquals(makeOrder(BAG_ONION_3), makeOrder(BAG_ONION_3));
        assertEquals(makeOrder(BAG_ASIAGO_3), makeOrder(BAG_ASIAGO_3));
        assertEquals(makeOrder(BAG_BLUEBERRY_3), makeOrder(BAG_BLUEBERRY_3));
        assertEquals(makeOrder(BAG_OLDPLAIN_3), makeOrder(BAG_OLDPLAIN_3));
        assertEquals(makeOrder(BAG_OLDTOMATO_3), makeOrder(BAG_OLDTOMATO_3));
    }

    @Test
    void equals_True_SameBagsDiffOrder() {
        System.out.println("SameBagsDiffOrder");
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_BLUEBERRY_3),
                makeOrder(BAG_BLUEBERRY_3, BAG_PLAIN_3));
        assertEquals(makeOrder(BAG_OLDTOMATO_3, BAG_CINNAMON_3, BAG_ONION_3),
                makeOrder(BAG_CINNAMON_3, BAG_OLDTOMATO_3, BAG_ONION_3));
        // assertEquals(makeOrder(BAG_POPPY_3, BAG_OLDEVERYTHING_3, BAG_SESAME_3),
        // makeOrder(BAG_SESAME_3, BAG_OLDEVERYTHING_3, BAG_POPPY_3));
        assertEquals(makeOrder(BAG_ASIAGO_3, BAG_CINNAMON_3, BAG_OLDPLAIN_3),
                makeOrder(BAG_OLDPLAIN_3, BAG_ASIAGO_3, BAG_CINNAMON_3));
    }

    @Test
    void equals_False_SameBagelDiffAmount() {
        assertNotEquals(makeOrder(BAG_PLAIN_3), makeOrder(makeBag(Type.PLAIN, 4)));
        assertNotEquals(makeOrder(BAG_CINNAMON_3), makeOrder(makeBag(Type.CINNAMON_RAISIN,5)));
        assertNotEquals(makeOrder(BAG_POPPY_3, BAG_OLDEVERYTHING_3), makeOrder(makeBag(Type.POPPY_SEED, 4), BAG_OLDEVERYTHING_3));
        assertNotEquals(makeOrder(BAG_SESAME_3, BAG_OLDASIAGO_3), makeOrder(BAG_OLDASIAGO_3, makeBag(Type.SESAME_SEED, 12)));
    }

    private static Bag makeBag(Bagel.Type type, int quantity) {
        return new Bag(new Bagel(type), quantity);
    }// end makeBagArguments

    private static Order makeOrder(Bag... bags) {
        return Order.of(bags);
    }// end makeOrderArguments

    static class OrderArgumentsProvider_MultipleBagAmounts implements ArgumentsProvider {
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        Bag bag3old = new Bag(blueberry, 3);
        Bag bag6old = new Bag(blueberry, 6);
        Bag bag13old = new Bag(blueberry, 13);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            blueberry.markDown();
            return Stream.of(
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3)), new BigDecimal("1.50")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6)), new BigDecimal("2.85")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13)), new BigDecimal("6.00")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3)), new BigDecimal("2.10")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6)), new BigDecimal("3.99")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13)), new BigDecimal("8.40")),
                    Arguments.of(makeOrder(bag3old), new BigDecimal("1.05")),
                    Arguments.of(makeOrder(bag6old), new BigDecimal("2.00")),
                    Arguments.of(makeOrder(bag13old), new BigDecimal("4.20")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 3)),
                            new BigDecimal("3.60")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 3)),
                            new BigDecimal("2.55")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 3)),
                            new BigDecimal("3.15")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6), new Bag(blueberry, 6)),
                            new BigDecimal("5.99")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13)),
                            new BigDecimal("14.40")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), new Bag(blueberry, 13)),
                            new BigDecimal("10.20")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13), new Bag(blueberry, 13)),
                            new BigDecimal("12.60")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6)),
                            new BigDecimal("5.49")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 6)),
                            new BigDecimal("3.50")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 13)),
                            new BigDecimal("5.70")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 6)),
                            new BigDecimal("4.95")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 13)),
                            new BigDecimal("8.10")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 6)),
                            new BigDecimal("4.10")),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 13)),
                            new BigDecimal("6.30")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6)),
                            new BigDecimal("3.90")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 13)),
                            new BigDecimal("7.05")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 6)),
                            new BigDecimal("5.04")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 13)),
                            new BigDecimal("9.45")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), new BigDecimal("15.45")),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), new BigDecimal("18.60")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 6)), new BigDecimal("7.89")),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 13)), new BigDecimal("12.30")),
                    Arguments.of(makeOrder(new Bag(blueberry, 6), makeBag(Type.PLAIN, 13),
                            makeBag(Type.ASIAGO, 13)), new BigDecimal("16.40")),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 3), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            new BigDecimal("8.64")),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            new BigDecimal("18.50")),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 3),
                                    new Bag(blueberry, 13), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            new BigDecimal("16.29")),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            new BigDecimal("12.83")));
        }// end stream
    }// end OneBagEachCategory
}
