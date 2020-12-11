package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;
import edu.mills.cs180a.OrderTest.OrderArgumentsProvider_MultipleBagAmounts;

class OrderTest {

    // testing an order of one bag, each category, each possible size
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_MultipleBagAmounts.class)
    void getPrice_assertEquals_MultipleBagCombinations(Order order, double expectedPrice) {
        assertEquals(expectedPrice, order.getPrice().doubleValue());
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
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_BLUEBERRY_3),
                makeOrder(BAG_BLUEBERRY_3, BAG_PLAIN_3));
        assertEquals(makeOrder(BAG_OLDTOMATO_3, BAG_CINNAMON_3, BAG_ONION_3),
                makeOrder(BAG_CINNAMON_3, BAG_OLDTOMATO_3, BAG_ONION_3));
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
    }

    private static Order makeOrder(Bag bag, Bag... bags) {
        return Order.of(bag, bags);
    }

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
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3)), 1.5),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6)), 2.85),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13)), 6.0),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3)), 2.1),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6)), 3.99),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13)), 8.4),
                    Arguments.of(makeOrder(bag3old), 1.05),
                    Arguments.of(makeOrder(bag6old), 2.0),
                    Arguments.of(makeOrder(bag13old), 4.2),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 3)),
                            3.6),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 3)),
                            2.55),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 3)),
                            3.15),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6), new Bag(blueberry, 6)),
                            5.99),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13)),
                            14.4),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), new Bag(blueberry, 13)),
                            10.2),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13), new Bag(blueberry, 13)),
                            12.6),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6)),
                            5.49),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 6)),
                            3.5),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 13)),
                            5.7),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 6)),
                            4.95),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 13)),
                            8.1),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 6)),
                            4.1),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 13)),
                            6.3),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6)),
                            3.9),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 13)),
                            7.05),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 6)),
                            5.04),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 13)),
                            9.45),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), 15.45),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), 18.6),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 6)), 7.89),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 13)), 12.30),
                    Arguments.of(makeOrder(new Bag(blueberry, 6), makeBag(Type.PLAIN, 13),
                            makeBag(Type.ASIAGO, 13)), 16.40),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 3), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            8.64),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            18.50),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 3),
                                    new Bag(blueberry, 13), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            16.29),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            12.83));
        }
    }
}
