package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	void getPrice_assertEquals_OneBag(double price, Order order) {
		assertEquals(price, order.getPrice());
	}

	// testing an order of two bags, all combinations, each size
	@ParameterizedTest
	@ArgumentsSource(OrderArgumentsProvider_TwoBags.class)
	void getPrice_assertEquals_TwoBags(double price, Order order) {
		assertEquals(price, order.getPrice());
	}

	// testing an order of three bags, most combinations, each size
	@ParameterizedTest
	@ArgumentsSource(OrderArgumentsProvider_ThreeBags.class)
	void getPrice_assertEquals_ThreeBags(double price, Order order) {
		assertEquals(price, order.getPrice());
	}

	// testing an order of any size bags, random choices
	@ParameterizedTest
	@ArgumentsSource(OrderArgumentsProvider_FourBags.class)
	void getPrice_assertEquals_FourBags(double price, Order order) {
		assertEquals(price, order.getPrice());
	}

	private static Bagel plain = new Bagel(Type.PLAIN);
	private static Bagel onion = new Bagel(Type.ONION);
	private static Bagel asiago = new Bagel(Type.ASIAGO);
	private static Bagel blueberry = new Bagel(Type.BLUEBERRY);
	private static Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
	private static Bag bag3plain = new Bag(plain,3);
	private static Bag bag3asiago = new Bag(asiago, 3);
	private static Bag bag3old = new Bag(blueberry, 3);
	private static Order order1;
	private static Order order2;
	private static Order order3;
	@BeforeAll
	public static void setup() {
		order1 = new Order(bag3plain, bag3asiago);
		order2 = new Order(bag3asiago, bag3old);
		order3 = new Order(bag3plain, bag3asiago, bag3old);
	}

	// equals - Reflexive, orders of 1-3 bags
	void equals_True_SameOrderSize() {
		Bag test1 = new Order(bag3plain, bag3asiago)
	}

}// end OrderTest

class OrderArgumentsProvider_OneBagEachCategory implements ArgumentsProvider {
	Bagel plain = new Bagel(Type.PLAIN);
	Bag bag3plain = new Bag(plain, 3);
	Bag bag6plain = new Bag(plain, 6);
	Bag bag13plain = new Bag(plain, 13);

	Bagel asiago = new Bagel(Type.ASIAGO);
	Bag bag3asiago = new Bag(asiago, 3);
	Bag bag6asiago = new Bag(asiago, 6);
	Bag bag13asiago = new Bag(asiago, 13);

	Bagel blueberry = new Bagel(Type.BLUEBERRY);
	Bag bag3old = new Bag(blueberry, 3);
	Bag bag6old = new Bag(blueberry, 6);
	Bag bag13old = new Bag(blueberry, 13);

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		blueberry.markDown();
		return Stream.of(Arguments.of(new Order(bag3plain), 1.50), Arguments.of(new Order(bag6plain), 2.85),
				Arguments.of(new Order(bag13plain), 5.70), Arguments.of(new Order(bag3asiago), 2.10),
				Arguments.of(new Order(bag6asiago), 3.99), Arguments.of(new Order(bag13asiago), 8.65),
				Arguments.of(new Order(bag3old), 1.05), Arguments.of(new Order(bag6old), 2.00),
				Arguments.of(new Order(bag13old), 4.32));
	}// end stream
}// end class

class OrderArgumentsProvider_TwoBags implements ArgumentsProvider {
	Bagel plain = new Bagel(Type.PLAIN);
	Bag bag3plain = new Bag(plain, 3);
	Bag bag6plain = new Bag(plain, 6);
	Bag bag13plain = new Bag(plain, 13);

	Bagel asiago = new Bagel(Type.ASIAGO);
	Bag bag3asiago = new Bag(asiago, 3);
	Bag bag6asiago = new Bag(asiago, 6);
	Bag bag13asiago = new Bag(asiago, 13);

	Bagel blueberry = new Bagel(Type.BLUEBERRY);
	Bag bag3old = new Bag(blueberry, 3);
	Bag bag6old = new Bag(blueberry, 6);
	Bag bag13old = new Bag(blueberry, 13);

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		blueberry.markDown();
		return Stream.of(
				Arguments.of(new Order(bag3plain, bag3asiago), 3.60),
				Arguments.of(new Order(bag3plain, bag3old), 2.55),
				Arguments.of(new Order(bag3asiago, bag3old), 3.15),
				Arguments.of(new Order(bag6plain, bag6asiago), 6.84),
				Arguments.of(new Order(bag6plain, bag6old), 4.85),
				Arguments.of(new Order(bag6asiago, bag6old), 5.99),
				Arguments.of(new Order(bag13plain, bag13asiago), 13.68),
				Arguments.of(new Order(bag13plain, bag13old), 9.69),
				Arguments.of(new Order(bag13asiago, bag13old), 11.97),

				Arguments.of(new Order(bag3plain, bag6asiago), 5.49),
				Arguments.of(new Order(bag3plain, bag13asiago), 9.48),
				Arguments.of(new Order(bag3plain, bag6old), 3.50),
				Arguments.of(new Order(bag3plain, bag13old), 5.49),
				Arguments.of(new Order(bag3asiago, bag6plain), 4.95),
				Arguments.of(new Order(bag3asiago, bag13plain), 7.80),
				Arguments.of(new Order(bag3asiago, bag6old), 4.10),
				Arguments.of(new Order(bag3asiago, bag13old), 6.09),
				Arguments.of(new Order(bag3old, bag6plain), 3.90),
				Arguments.of(new Order(bag3old, bag13plain), 6.75),
				Arguments.of(new Order(bag3old, bag6asiago), 5.04),
				Arguments.of(new Order(bag3old, bag13asiago), 9.03));
	}// end stream
}// end class

class OrderArgumentsProvider_ThreeBags implements ArgumentsProvider {
	Bagel plain = new Bagel(Type.PLAIN);
	Bag bag3plain = new Bag(plain, 3);
	Bag bag6plain = new Bag(plain, 6);
	Bag bag13plain = new Bag(plain, 13);

	Bagel asiago = new Bagel(Type.ASIAGO);
	Bag bag3asiago = new Bag(asiago, 3);
	Bag bag6asiago = new Bag(asiago, 6);
	Bag bag13asiago = new Bag(asiago, 13);

	Bagel blueberry = new Bagel(Type.BLUEBERRY);
	Bag bag3old = new Bag(blueberry, 3);
	Bag bag6old = new Bag(blueberry, 6);
	Bag bag13old = new Bag(blueberry, 13);

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		blueberry.markDown();
		return Stream.of(
				Arguments.of(new Order(bag3plain, bag3asiago, bag3old), 4.65),
				Arguments.of(new Order(bag3plain, bag3asiago, bag6old), 5.60),
				Arguments.of(new Order(bag3plain, bag6asiago, bag6old), 7.49),
				Arguments.of(new Order(bag3plain, bag6asiago, bag13old), 9.48),
				Arguments.of(new Order(bag3plain, bag13asiago, bag13old), 13.47),
				Arguments.of(new Order(bag6plain, bag13asiago, bag13old), 14.82),
				Arguments.of(new Order(bag13plain, bag13asiago, bag13old), 17.67),

				Arguments.of(new Order(bag3asiago, bag6plain, bag6old), 6.95),
				Arguments.of(new Order(bag3asiago, bag6plain, bag13old), 8.94),
				Arguments.of(new Order(bag3asiago, bag13plain, bag13old), 11.79),
				Arguments.of(new Order(bag6asiago, bag13plain, bag13old), 12.54),

				Arguments.of(new Order(bag3old, bag6plain, bag6asiago), 7.89),
				Arguments.of(new Order(bag3old, bag6plain, bag13asiago), 11.88),
				Arguments.of(new Order(bag6old, bag13plain, bag13asiago), 14.73));
	}// end stream
}// end class

class OrderArgumentsProvider_FourBags implements ArgumentsProvider {
	Bagel plain = new Bagel(Type.PLAIN);
	Bag bag3plain = new Bag(plain, 3);
	Bag bag6plain = new Bag(plain, 6);
	Bag bag13plain = new Bag(plain, 13);

	Bagel asiago = new Bagel(Type.ASIAGO);
	Bag bag3asiago = new Bag(asiago, 3);
	Bag bag6asiago = new Bag(asiago, 6);
	Bag bag13asiago = new Bag(asiago, 13);

	Bagel tomato = new Bagel(Type.SUN_DRIED_TOMATO);
	Bag bag3tomato = new Bag(tomato, 3);
	Bag bag6tomato = new Bag(tomato, 6);
	Bag bag13tomato = new Bag(tomato, 13);

	Bagel blueberry = new Bagel(Type.BLUEBERRY);
	Bag bag3old = new Bag(blueberry, 3);
	Bag bag6old = new Bag(blueberry, 6);
	Bag bag13old = new Bag(blueberry, 13);

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		blueberry.markDown();
		return Stream.of(
				Arguments.of(new Order(bag3plain, bag6asiago, bag3old, bag3tomato), 8.64),
				Arguments.of(new Order(bag3tomato, bag6old, bag13plain, bag13asiago), 17.78),
				Arguments.of(new Order(bag13old, bag13plain, bag6tomato, bag3asiago), 15.78),
				Arguments.of(new Order(bag6tomato, bag6asiago, bag6old, bag6plain), 12.83));
	}// end stream
}// end class