package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;

class OrderTest {

    // testing an order of one bag, each category, each possible size
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_OneBagEachCategory.class)
    void getPrice_assertEquals_OneBag(Order order, BigDecimal cost) {
        assertEquals(cost, order.getPrice());
    }

    // testing an order of two bags, all combinations, each size
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_TwoBags.class)
    void getPrice_assertEquals_TwoBags(Order order, BigDecimal cost) {
        assertEquals(cost, order.getPrice());
    }

    // testing an order of three bags, most combinations, each size
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_ThreeBags.class)
    void getPrice_assertEquals_ThreeBags(Order order, BigDecimal cost) {
        assertEquals(cost, order.getPrice());
    }

    // testing an order of any size bags, random choices
    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_FourBags.class)
    void getPrice_assertEquals_FourBags(Order order, BigDecimal cost) {
        assertEquals(cost, order.getPrice());
    }

    private static Bag bagPlain3;
    private static Bag bagOnion3;
    private static Bag bagSesame3;
    private static Bag bagPoppy3;

    private static Bag bagAsiago3;
    private static Bag bagBlueberry3;
    private static Bag bagCinnamon3;

    private static Bag bagOldPlain3;
    private static Bag bagOldTomato3;
    private static Bag bagOldEverything3;
    private static Bag bagOldAsiago3;

    @BeforeAll
    public static void setup() {
        bagPlain3 = makeBag(Type.PLAIN, 3);
        bagOnion3 = makeBag(Type.ONION, 3);
        bagSesame3 = makeBag(Type.SESAME_SEED, 3);
        bagPoppy3 = makeBag(Type.POPPY_SEED, 3);

        bagAsiago3 = makeBag(Type.ASIAGO, 3);
        bagBlueberry3 = makeBag(Type.BLUEBERRY, 3);
        bagCinnamon3 = makeBag(Type.CINNAMON_RAISIN, 3);

        Bagel plain = new Bagel(Type.PLAIN);
        plain.markDown();
        bagOldPlain3 = new Bag(plain, 3);
        Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
        tomato.markDown();
        bagOldTomato3 = new Bag(tomato, 3);
        Bagel everything = new Bagel(Type.EVERYTHING);
        everything.markDown();
        bagOldEverything3 = new Bag(everything, 3);
        Bagel asiago = new Bagel(Type.ASIAGO);
        asiago.markDown();
        bagOldAsiago3 = new Bag(asiago, 3);

    }

    // Reflexive - all categories, 1-4 bags
    void equals_True_SameBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(bagPlain3).getPrice(), makeOrder(bagPlain3).getPrice());
        assertEquals(makeOrder(bagPlain3, bagOnion3).getPrice(),makeOrder(bagPlain3, bagOnion3).getPrice());
        assertEquals(makeOrder(bagPlain3, bagOnion3, bagSesame3).getPrice(),makeOrder(bagPlain3, bagOnion3, bagSesame3).getPrice());
        assertEquals(makeOrder(bagPlain3, bagOnion3, bagSesame3, bagPoppy3).getPrice(),makeOrder(bagPlain3, bagOnion3, bagSesame3, bagPoppy3).getPrice());

        // Gourmet
        assertEquals(makeOrder(bagAsiago3).getPrice(), makeOrder(bagAsiago3).getPrice());
        assertEquals(makeOrder(bagAsiago3, bagBlueberry3).getPrice(),makeOrder(bagAsiago3, bagBlueberry3).getPrice());
        assertEquals(makeOrder(bagAsiago3, bagBlueberry3, bagCinnamon3).getPrice(),makeOrder(bagAsiago3, bagBlueberry3, bagCinnamon3).getPrice());

        // Day Old
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldPlain3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3, bagOldAsiago3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3, bagOldAsiago3).getPrice());
    }

    // Symmetric - all categories, all possible sizes
    void equals_True_DiffBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(bagPlain3).getPrice(), makeOrder(bagOnion3).getPrice());
        assertEquals(makeOrder(bagOnion3).getPrice(), makeOrder(bagPlain3).getPrice());
        assertEquals(makeOrder(bagPlain3, bagOnion3).getPrice(),makeOrder(bagSesame3, bagPoppy3).getPrice());
        assertEquals(makeOrder(bagSesame3, bagPoppy3).getPrice(),makeOrder(bagPlain3, bagOnion3).getPrice());
        assertEquals(makeOrder(bagSesame3, bagPoppy3, bagPlain3).getPrice(),makeOrder(bagOnion3).getPrice());
        assertEquals(makeOrder(bagOnion3).getPrice(),makeOrder(bagSesame3, bagPoppy3, bagPlain3).getPrice());

        // Gourmet
        assertEquals(makeOrder(bagAsiago3).getPrice(), makeOrder(bagBlueberry3).getPrice());
        assertEquals(makeOrder(bagBlueberry3).getPrice(), makeOrder(bagAsiago3).getPrice());
        assertEquals(makeOrder(bagAsiago3, bagBlueberry3).getPrice(),makeOrder(bagCinnamon3).getPrice());
        assertEquals(makeOrder(bagCinnamon3).getPrice(),makeOrder(bagAsiago3, bagBlueberry3).getPrice());

        // Day Old
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldTomato3).getPrice(), makeOrder(bagOldPlain3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3).getPrice(), makeOrder(bagOldEverything3, bagOldAsiago3).getPrice());
        assertEquals(makeOrder(bagOldEverything3, bagOldAsiago3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice(), makeOrder(bagOldAsiago3).getPrice());
        assertEquals(makeOrder(bagOldAsiago3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice());
    }

    // Transitive - all categories, all possible sizes
    void equals_True_3DiffBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(bagPlain3).getPrice(), makeOrder(bagOnion3).getPrice());
        assertEquals(makeOrder(bagOnion3).getPrice(), makeOrder(bagSesame3).getPrice());
        assertEquals(makeOrder(bagPlain3).getPrice(), makeOrder(bagSesame3).getPrice());
        assertEquals(makeOrder(bagPoppy3).getPrice(), makeOrder(bagSesame3).getPrice());
        assertEquals(makeOrder(bagSesame3).getPrice(), makeOrder(bagPlain3).getPrice());
        assertEquals(makeOrder(bagPoppy3).getPrice(), makeOrder(bagPlain3).getPrice());

        // Gourmet
        assertEquals(makeOrder(bagAsiago3).getPrice(), makeOrder(bagBlueberry3).getPrice());
        assertEquals(makeOrder(bagBlueberry3).getPrice(), makeOrder(bagCinnamon3).getPrice());
        assertEquals(makeOrder(bagAsiago3).getPrice(), makeOrder(bagCinnamon3).getPrice());

        // Day Old
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldTomato3).getPrice(), makeOrder(bagOldEverything3).getPrice());
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldEverything3).getPrice());
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldAsiago3).getPrice());
        assertEquals(makeOrder(bagOldAsiago3).getPrice(), makeOrder(bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldTomato3).getPrice());
    }

    // Consistent - all categories, all possible sizes
    void equals_True_DiffBagSameTotalPriceLater() {
        // Old Fashioned
        assertEquals(makeOrder(bagPlain3).getPrice(), makeOrder(bagOnion3).getPrice());
        assertEquals(makeOrder(bagOnion3).getPrice(), makeOrder(bagPlain3).getPrice());
        assertEquals(makeOrder(bagPlain3, bagOnion3).getPrice(),makeOrder(bagSesame3, bagPoppy3).getPrice());
        assertEquals(makeOrder(bagSesame3, bagPoppy3).getPrice(),makeOrder(bagPlain3, bagOnion3).getPrice());
        assertEquals(makeOrder(bagSesame3, bagPoppy3, bagPlain3).getPrice(),makeOrder(bagOnion3).getPrice());
        assertEquals(makeOrder(bagOnion3).getPrice(),makeOrder(bagSesame3, bagPoppy3, bagPlain3).getPrice());

        // Gourmet
        assertEquals(makeOrder(bagAsiago3).getPrice(), makeOrder(bagBlueberry3).getPrice());
        assertEquals(makeOrder(bagBlueberry3).getPrice(), makeOrder(bagAsiago3).getPrice());
        assertEquals(makeOrder(bagAsiago3, bagBlueberry3).getPrice(),makeOrder(bagCinnamon3).getPrice());
        assertEquals(makeOrder(bagCinnamon3).getPrice(),makeOrder(bagAsiago3, bagBlueberry3).getPrice());

        // Day Old
        assertEquals(makeOrder(bagOldPlain3).getPrice(), makeOrder(bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldTomato3).getPrice(), makeOrder(bagOldPlain3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3).getPrice(), makeOrder(bagOldEverything3, bagOldAsiago3).getPrice());
        assertEquals(makeOrder(bagOldEverything3, bagOldAsiago3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3).getPrice());
        assertEquals(makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice(), makeOrder(bagOldAsiago3).getPrice());
        assertEquals(makeOrder(bagOldAsiago3).getPrice(),makeOrder(bagOldPlain3, bagOldTomato3, bagOldEverything3).getPrice());
    }

    // Not Null - all categories, all possible sizes
    void equals_False_Null() {
        // Old Fashioned
        assertNotEquals(makeOrder(bagPlain3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagOnion3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagSesame3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagPoppy3).getPrice(),makeOrder(null).getPrice());

        // Gourmet
        assertNotEquals(makeOrder(bagAsiago3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagBlueberry3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagCinnamon3).getPrice(),makeOrder(null).getPrice());

        // Day Old
        assertNotEquals(makeOrder(bagOldPlain3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagOldTomato3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagOldEverything3).getPrice(),makeOrder(null).getPrice());
        assertNotEquals(makeOrder(bagOldAsiago3).getPrice(),makeOrder(null).getPrice());
    }

    private static Bag makeBag(Bagel.Type type, int quantity) {
        return new Bag(new Bagel(type), quantity);
    }// end makeBagArguments

    private static Order makeOrder(Bag... bags) {
        return Order.of(bags);
    }// end makeOrderArguments

    static class OrderArgumentsProvider_OneBagEachCategory implements ArgumentsProvider {
        Bagel blueberry = new Bagel(Type.BLUEBERRY);
        Bag bag3old = new Bag(blueberry, 3);
        Bag bag6old = new Bag(blueberry, 6);
        Bag bag13old = new Bag(blueberry, 13);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            blueberry.markDown();
            return Stream.of(
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3)), BigDecimal.valueOf(1.50)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6)), BigDecimal.valueOf(2.85)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13)), BigDecimal.valueOf(5.70)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3)), BigDecimal.valueOf(2.10)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6)), BigDecimal.valueOf(3.99)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13)), BigDecimal.valueOf(8.65)),
                    Arguments.of(makeOrder(bag3old), BigDecimal.valueOf(1.05)),
                    Arguments.of(makeOrder(bag6old), BigDecimal.valueOf(2.00)),
                    Arguments.of(makeOrder(bag13old), BigDecimal.valueOf(4.32)));
        }// end stream
    }// end OneBagEachCategory

    static class OrderArgumentsProvider_TwoBags implements ArgumentsProvider {
        Bagel blueberry = new Bagel(Type.BLUEBERRY);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            blueberry.markDown();
            return Stream.of(
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 3)),
                            BigDecimal.valueOf(3.60)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 3)),
                            BigDecimal.valueOf(2.55)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 3)),
                            BigDecimal.valueOf(3.15)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 6)),
                            BigDecimal.valueOf(6.84)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6), new Bag(blueberry, 6)),
                            BigDecimal.valueOf(4.85)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6), new Bag(blueberry, 6)),
                            BigDecimal.valueOf(5.99)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13)),
                            BigDecimal.valueOf(13.68)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), new Bag(blueberry, 13)),
                            BigDecimal.valueOf(9.69)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 13), new Bag(blueberry, 13)),
                            BigDecimal.valueOf(11.97)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6)),
                            BigDecimal.valueOf(5.49)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 13)),
                            BigDecimal.valueOf(9.48)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 6)),
                            BigDecimal.valueOf(3.50)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), new Bag(blueberry, 13)),
                            BigDecimal.valueOf(5.49)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 6)),
                            BigDecimal.valueOf(4.95)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 13)),
                            BigDecimal.valueOf(7.80)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 6)),
                            BigDecimal.valueOf(4.10)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), new Bag(blueberry, 13)),
                            BigDecimal.valueOf(6.09)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6)),
                            BigDecimal.valueOf(3.90)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 13)),
                            BigDecimal.valueOf(6.75)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 6)),
                            BigDecimal.valueOf(5.04)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.ASIAGO, 13)),
                            BigDecimal.valueOf(9.03)));

        }// end stream
    }// end TwoBags

    static class OrderArgumentsProvider_ThreeBags implements ArgumentsProvider {
        Bagel blueberry = new Bagel(Type.BLUEBERRY);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            blueberry.markDown();
            return Stream.of(
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 3),
                            new Bag(blueberry, 3)), BigDecimal.valueOf(4.65)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 3),
                            new Bag(blueberry, 6)), BigDecimal.valueOf(5.60)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6),
                            new Bag(blueberry, 6)), BigDecimal.valueOf(7.49)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(9.48)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(13.47)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(14.82)),
                    Arguments.of(makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(17.67)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 6),
                            new Bag(blueberry, 6)), BigDecimal.valueOf(6.95)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 6),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(8.94)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 3), makeBag(Type.PLAIN, 13),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(11.79)),
                    Arguments.of(makeOrder(makeBag(Type.ASIAGO, 6), makeBag(Type.PLAIN, 13),
                            new Bag(blueberry, 13)), BigDecimal.valueOf(12.54)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 6)), BigDecimal.valueOf(7.89)),
                    Arguments.of(makeOrder(new Bag(blueberry, 3), makeBag(Type.PLAIN, 6),
                            makeBag(Type.ASIAGO, 13)), BigDecimal.valueOf(11.88)),
                    Arguments.of(makeOrder(new Bag(blueberry, 6), makeBag(Type.PLAIN, 13),
                            makeBag(Type.ASIAGO, 13)), BigDecimal.valueOf(14.73)));
        }// end stream
    }// end ThreeBags

    static class OrderArgumentsProvider_FourBags implements ArgumentsProvider {
        Bagel blueberry = new Bagel(Type.BLUEBERRY);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            blueberry.markDown();
            return Stream.of(
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 3), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 3), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            BigDecimal.valueOf(8.64)),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 13),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 3)),
                            BigDecimal.valueOf(17.78)),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 13), makeBag(Type.ASIAGO, 3),
                                    new Bag(blueberry, 13), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            BigDecimal.valueOf(15.78)),
                    Arguments.of(
                            makeOrder(makeBag(Type.PLAIN, 6), makeBag(Type.ASIAGO, 6),
                                    new Bag(blueberry, 6), makeBag(Type.SUN_DRIED_TOMATO, 6)),
                            BigDecimal.valueOf(12.83)));
        }// end stream
    }// end FourBags
}// end OrderTest
