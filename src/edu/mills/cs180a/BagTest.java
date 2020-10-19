package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;

class BagTest {
    // testing under bulk minimum for each category
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
    void getTotalPrice_assertEquals_3BagelsEach(double price, Bag bag) {
        assertEquals(price, bag.getTotalPrice());
    }

    //testing bulk minimum for each category
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
    void getTotalPrice_assertEquals_3OldFashionedBagels(double price, Bag bag) {
        assertEquals(price, bag.getTotalPrice());
    }
    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
    void getTotalPrice_assertEquals_3DayOldBagels(double price, Bag bag) {
        assertEquals(price, bag.getTotalPrice());
    }

    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getPerBagelPrice.class)
    void getPerBagelPrice_assertEquals_CorrectPricePerBagel(double price, Bag bag) {
        assertEquals(price, bag.getPerBagelPrice());
    }

    @ParameterizedTest
    void getPerBagelPrice_assertEquals_DayOldBagel()
    {
        Bagel old = new Bagel(Type.ASIAGO);
        old.markDown();
        assertEquals(.35, old);
    }

    private static Bag bag;
    @BeforeAll
    static void setup() {
        bag = new Bag(new Bagel(Type.SESAME_SEED), 3);
    }

    void equals_True_SameCategorySamePrice() {
        Bag test = new Bag(new Bagel(Type.SUN_DRIED_TOMATO),3);
        assertEquals(bag, test);
        assertEquals(test,bag);
    }

    void equals_False_DiffCategoryDiffPrice() {
        Bag test = new Bag(new Bagel(Type.POPPY_SEED),3);
        assertNotEquals(bag,test);
    }
}// end BagTest class


class BagArgumentsProvider_getTotalPrice implements ArgumentsProvider {
    Bagel oldFashioned = new Bagel(Type.PLAIN);
    Bagel gourmet = new Bagel(Type.ASIAGO);
    Bagel old = new Bagel(Type.BLUEBERRY);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        old.markDown();
        return Stream.of(
                Arguments.of(new Bag(oldFashioned, 3), 1.50),
                Arguments.of(new Bag(gourmet,3), 2.10),
                Arguments.of(new Bag(old,3), 1.05)
                );
    }// end stream

}// end BagArgumentsProvider_getTotalPrice

class BagArgumentsProvider_getPerBagelPrice implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        // TODO Auto-generated method stub
        return Stream.of(
                Arguments.of(new Bagel(Type.BLUEBERRY),70),
                Arguments.of(new Bagel(Type.CINNAMON_RAISIN),70),
                Arguments.of(new Bagel(Type.EVERYTHING), 50),
                Arguments.of(new Bagel(Type.ONION),50)
                );
    }

}// end BagArgumentsProvider_getPerBagelPrice
