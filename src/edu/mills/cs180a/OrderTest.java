package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class OrderTest {

    @ParameterizedTest
    @ArgumentsSource(OrderArgumentsProvider_getPrice.class)
    void getPrice_assertEquals_CorrectOrderPrice(double price, Order bags) {
        assertEquals(price, bags.getPrice());
    }

}// end OrderTest

class OrderArgumentsProvider_getPrice implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        // TODO Auto-generated method stub
        return Stream.of(

                );
    }

}