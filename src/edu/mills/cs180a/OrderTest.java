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

    // Reflexive - all categories, 1-4 bags
    void equals_True_SameBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(BAG_PLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice(),
                makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_ONION_3, BAG_SESAME_3).getPrice(),
                makeOrder(BAG_PLAIN_3, BAG_ONION_3, BAG_SESAME_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_ONION_3, BAG_SESAME_3, BAG_POPPY_3).getPrice(),
                makeOrder(BAG_PLAIN_3, BAG_ONION_3, BAG_SESAME_3, BAG_POPPY_3).getPrice());

        // Gourmet
        assertEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(BAG_ASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice(),
                makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice());
        assertEquals(makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3, BAG_CINNAMON_3).getPrice(),
                makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3, BAG_CINNAMON_3).getPrice());

        // Day Old
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDPLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice());
        assertEquals(
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3)
                .getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3)
                .getPrice());
    }

    // Symmetric - all categories, all possible sizes
    void equals_True_DiffBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_ONION_3).getPrice(), makeOrder(BAG_PLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice(),
                makeOrder(BAG_SESAME_3, BAG_POPPY_3).getPrice());
        assertEquals(makeOrder(BAG_SESAME_3, BAG_POPPY_3).getPrice(),
                makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_SESAME_3, BAG_POPPY_3, BAG_PLAIN_3).getPrice(),
                makeOrder(BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_ONION_3).getPrice(),
                makeOrder(BAG_SESAME_3, BAG_POPPY_3, BAG_PLAIN_3).getPrice());

        // Gourmet
        assertEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(BAG_BLUEBERRY_3).getPrice());
        assertEquals(makeOrder(BAG_BLUEBERRY_3).getPrice(), makeOrder(BAG_ASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice(),
                makeOrder(BAG_CINNAMON_3).getPrice());
        assertEquals(makeOrder(BAG_CINNAMON_3).getPrice(),
                makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice());

        // Day Old
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDTOMATO_3).getPrice(), makeOrder(BAG_OLDPLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice(),
                makeOrder(BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice(),
                makeOrder(BAG_OLDASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDASIAGO_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice());
    }

    // Transitive - all categories, all possible sizes
    void equals_True_3DiffBagSameTotalPrice() {
        // Old Fashioned
        assertEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_ONION_3).getPrice(), makeOrder(BAG_SESAME_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(BAG_SESAME_3).getPrice());
        assertEquals(makeOrder(BAG_POPPY_3).getPrice(), makeOrder(BAG_SESAME_3).getPrice());
        assertEquals(makeOrder(BAG_SESAME_3).getPrice(), makeOrder(BAG_PLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_POPPY_3).getPrice(), makeOrder(BAG_PLAIN_3).getPrice());

        // Gourmet
        assertEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(BAG_BLUEBERRY_3).getPrice());
        assertEquals(makeOrder(BAG_BLUEBERRY_3).getPrice(), makeOrder(BAG_CINNAMON_3).getPrice());
        assertEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(BAG_CINNAMON_3).getPrice());

        // Day Old
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDTOMATO_3).getPrice(),
                makeOrder(BAG_OLDEVERYTHING_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(),
                makeOrder(BAG_OLDEVERYTHING_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDASIAGO_3).getPrice(), makeOrder(BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDTOMATO_3).getPrice());
    }

    // Consistent - all categories, all possible sizes
    void equals_True_DiffBagSameTotalPriceLater() {
        // Old Fashioned
        assertEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_ONION_3).getPrice(), makeOrder(BAG_PLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice(),
                makeOrder(BAG_SESAME_3, BAG_POPPY_3).getPrice());
        assertEquals(makeOrder(BAG_SESAME_3, BAG_POPPY_3).getPrice(),
                makeOrder(BAG_PLAIN_3, BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_SESAME_3, BAG_POPPY_3, BAG_PLAIN_3).getPrice(),
                makeOrder(BAG_ONION_3).getPrice());
        assertEquals(makeOrder(BAG_ONION_3).getPrice(),
                makeOrder(BAG_SESAME_3, BAG_POPPY_3, BAG_PLAIN_3).getPrice());

        // Gourmet
        assertEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(BAG_BLUEBERRY_3).getPrice());
        assertEquals(makeOrder(BAG_BLUEBERRY_3).getPrice(), makeOrder(BAG_ASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice(),
                makeOrder(BAG_CINNAMON_3).getPrice());
        assertEquals(makeOrder(BAG_CINNAMON_3).getPrice(),
                makeOrder(BAG_ASIAGO_3, BAG_BLUEBERRY_3).getPrice());

        // Day Old
        assertEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDTOMATO_3).getPrice(), makeOrder(BAG_OLDPLAIN_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice(),
                makeOrder(BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDEVERYTHING_3, BAG_OLDASIAGO_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice(),
                makeOrder(BAG_OLDASIAGO_3).getPrice());
        assertEquals(makeOrder(BAG_OLDASIAGO_3).getPrice(),
                makeOrder(BAG_OLDPLAIN_3, BAG_OLDTOMATO_3, BAG_OLDEVERYTHING_3).getPrice());
    }

    // Not Null - all categories, all possible sizes
    void equals_False_Null() {
        // Old Fashioned
        assertNotEquals(makeOrder(BAG_PLAIN_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_ONION_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_SESAME_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_POPPY_3).getPrice(), makeOrder(null).getPrice());

        // Gourmet
        assertNotEquals(makeOrder(BAG_ASIAGO_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_BLUEBERRY_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_CINNAMON_3).getPrice(), makeOrder(null).getPrice());

        // Day Old
        assertNotEquals(makeOrder(BAG_OLDPLAIN_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_OLDTOMATO_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_OLDEVERYTHING_3).getPrice(), makeOrder(null).getPrice());
        assertNotEquals(makeOrder(BAG_OLDASIAGO_3).getPrice(), makeOrder(null).getPrice());
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
