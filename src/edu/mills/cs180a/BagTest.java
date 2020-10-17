package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import edu.mills.cs180a.Bagel.Type;

class BagTest {

    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
    void getTotalPrice_assertEquals_CorrectPricePerBag(double price, Bag bag) {
        assertEquals(price, bag.getTotalPrice());
    }

    @ParameterizedTest
    @ArgumentsSource(BagArgumentsProvider_getPerBagelPrice.class)
    void getPerBagelPrice_assertEquals_CorrectPricePerBagel(double price, Bag bag) {
        assertEquals(price, bag.getPerBagelPrice());
    }

}// end BagTest class


class BagArgumentsProvider_getTotalPrice implements ArgumentsProvider {

    // if you wanted to break up this process into two parts you can do the following
    //Bag bag = new Bag(new Bagel(Type.PLAIN),2);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        // TODO Auto-generated method stub
        return Stream.of(
                //Arguments.of(bag, 100)
                Arguments.of(new Bag(new Bagel(Type.PLAIN), 2), 100),
                Arguments.of(new Bag(new Bagel(Type.ASIAGO),3), 210)
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
