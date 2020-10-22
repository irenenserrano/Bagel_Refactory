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

class BagTest {
	// testing under bulk minimum/bulk minimum/buy1get1free for each category
	@ParameterizedTest
	@ArgumentsSource(BagArgumentsProvider_getTotalPrice.class)
	void getTotalPrice_assertEquals_3BagelsEach(double price, Bag bag) {
		assertEquals(price, bag.getTotalPrice());
	}

	// testing the correct price is return for one of each bagel in each old
	// fashioned and gourmet categories
	@ParameterizedTest
	@ArgumentsSource(BagArgumentsProvider_getPerBagelPriceOFG.class)
	void getPerBagelPrice_assertEquals_CorrectPricePerBagelOFG(double price, Bag bag) {
		assertEquals(price, bag.getPerBagelPrice());
	}

	// testing the correct price is return for each bagel that is marked down
	@ParameterizedTest
	@ArgumentsSource(BagArgumentsProvider_getPerBagelPriceOld.class)
	void getPerBagelPrice_assertEquals_CorrectPricePerBagelOld(double price, Bag bag) {
		assertEquals(price, bag.getPerBagelPrice());
	}

	private static Bag bag3plain;
	private static Bag bag6plain;
	private static Bag bag13plain;
	private static Bag bag3asiago;
	private static Bag bag6asiago;
	private static Bag bag13asiago;
	private static Bag bag3oldasiago;
	private static Bag bag6oldplain;
	private static Bag bag13oldasiago;
	private static Bagel PLAIN = new Bagel(Type.PLAIN);
	private static Bagel ONION = new Bagel(Type.ONION);
	private static Bagel EVERYTHING = new Bagel(Type.EVERYTHING);
	private static Bagel ASIAGO = new Bagel(Type.ASIAGO);
	private static Bagel BLUEBERRY = new Bagel(Type.BLUEBERRY);
	private static Bagel TOMATO = new Bagel(Type.SUN_DRIED_TOMATO);

	@BeforeAll
	void setup() {
		// old fashioned
		bag3plain = new Bag(PLAIN, 3);
		bag6plain = new Bag(PLAIN, 6);
		bag13plain = new Bag(PLAIN, 13);
		// gourmet
		bag3asiago = new Bag(ASIAGO, 3);
		bag6asiago = new Bag(ASIAGO, 6);
		bag13asiago = new Bag(ASIAGO, 13);
		// day old
		ASIAGO.markDown();
		PLAIN.markDown();
		bag3oldasiago = new Bag(ASIAGO, 3);
		bag6oldplain = new Bag(PLAIN, 6);
		bag13oldasiago = new Bag(ASIAGO, 13);
	}

	// equals - Reflexive, all three categories
	void equals_True_SameBagelAmountPrice() {
		// Old Fashioned
		Bag test3plain = new Bag(PLAIN, 3);
		Bag test6plain = new Bag(PLAIN, 6);
		Bag test13plain = new Bag(PLAIN, 13);

		assertEquals(bag3plain.getTotalPrice(), test3plain.getTotalPrice());
		assertEquals(test3plain.getTotalPrice(), bag3plain.getTotalPrice());
		assertEquals(bag6plain.getTotalPrice(), test6plain.getTotalPrice());
		assertEquals(test6plain.getTotalPrice(), bag6plain.getTotalPrice());
		assertEquals(bag13plain.getTotalPrice(), test13plain.getTotalPrice());
		assertEquals(test13plain.getTotalPrice(), bag13plain.getTotalPrice());

		// Gourmet
		Bag test3asiago = new Bag(ASIAGO, 3);
		Bag test6asiago = new Bag(ASIAGO, 6);
		Bag test13asiago = new Bag(ASIAGO, 13);

		assertEquals(bag3asiago.getTotalPrice(), test3asiago.getTotalPrice());
		assertEquals(test3asiago.getTotalPrice(), bag3asiago.getTotalPrice());
		assertEquals(bag6asiago.getTotalPrice(), test6asiago.getTotalPrice());
		assertEquals(test6asiago.getTotalPrice(), bag6asiago.getTotalPrice());
		assertEquals(bag13asiago.getTotalPrice(), test13asiago.getTotalPrice());
		assertEquals(test13asiago.getTotalPrice(), bag13asiago.getTotalPrice());

		// Day Old
		ASIAGO.markDown();
		PLAIN.markDown();
		Bag test3oldasiago = new Bag(PLAIN, 3);
		Bag test6oldplain = new Bag(ASIAGO, 6);
		Bag test13oldasiago = new Bag(PLAIN, 13);

		assertEquals(bag3oldasiago.getTotalPrice(), test3oldasiago.getTotalPrice());
		assertEquals(test3oldasiago.getTotalPrice(), bag3oldasiago.getTotalPrice());
		assertEquals(bag6oldplain.getTotalPrice(), test6oldplain.getTotalPrice());
		assertEquals(test6oldplain.getTotalPrice(), bag6oldplain.getTotalPrice());
		assertEquals(bag13oldasiago.getTotalPrice(), test13oldasiago.getTotalPrice());
		assertEquals(test13oldasiago.getTotalPrice(), bag13oldasiago.getTotalPrice());
	}

	// equals - Transitive, all three categories
	void equals_True_SameCategory2DiffBagel() {
		// Old Fashioned
		Bag test3onion = new Bag(ONION, 3);
		Bag test6onion = new Bag(ONION, 6);
		Bag test13onion = new Bag(ONION, 13);

		assertEquals(bag3plain.getTotalPrice(), test3onion.getTotalPrice());
		assertEquals(test3onion.getTotalPrice(), bag3plain.getTotalPrice());
		assertEquals(bag6plain.getTotalPrice(), test6onion.getTotalPrice());
		assertEquals(test6onion.getTotalPrice(), bag6plain.getTotalPrice());
		assertEquals(bag13plain.getTotalPrice(), test13onion.getTotalPrice());
		assertEquals(test13onion.getTotalPrice(), bag13plain.getTotalPrice());

		// Gourmet
		Bag test3blueberry = new Bag(BLUEBERRY, 3);
		Bag test6blueberry = new Bag(BLUEBERRY, 6);
		Bag test13blueberry = new Bag(BLUEBERRY, 13);

		assertEquals(bag3asiago.getTotalPrice(), test3blueberry.getTotalPrice());
		assertEquals(test3blueberry.getTotalPrice(), bag3asiago.getTotalPrice());
		assertEquals(bag6asiago.getTotalPrice(), test6blueberry.getTotalPrice());
		assertEquals(test6blueberry.getTotalPrice(), bag6asiago.getTotalPrice());
		assertEquals(bag13asiago.getTotalPrice(), test13blueberry.getTotalPrice());
		assertEquals(test13blueberry.getTotalPrice(), bag13asiago.getTotalPrice());

		// Day Old
		BLUEBERRY.markDown();
		ONION.markDown();
		Bag test3oldblueberry = new Bag(BLUEBERRY, 3);
		Bag test6oldonion = new Bag(ONION, 6);
		Bag test13oldblueberry = new Bag(BLUEBERRY, 13);

		assertEquals(bag3oldasiago.getTotalPrice(), test3oldblueberry.getTotalPrice());
		assertEquals(test3oldblueberry.getTotalPrice(), bag3oldasiago.getTotalPrice());
		assertEquals(bag6oldplain.getTotalPrice(), test6oldonion.getTotalPrice());
		assertEquals(test6oldonion.getTotalPrice(), bag6oldplain.getTotalPrice());
		assertEquals(bag13oldasiago.getTotalPrice(), test13oldblueberry.getTotalPrice());
		assertEquals(test13oldblueberry.getTotalPrice(), bag13oldasiago.getTotalPrice());
	}

	// equals - Symmetric, all three categories
	void equals_True_SameCategory3DiffBagel() {
		// Old Fashioned
		Bag test3onion = new Bag(ONION, 3);
		Bag test3everything = new Bag(EVERYTHING, 3);
		Bag test6onion = new Bag(ONION, 6);
		Bag test6everything = new Bag(EVERYTHING, 6);
		Bag test13onion = new Bag(ONION, 13);
		Bag test13everything = new Bag(EVERYTHING, 13);

		assertEquals(bag3plain.getTotalPrice(), test3onion.getTotalPrice());
		assertEquals(test3everything.getTotalPrice(), bag3plain.getTotalPrice());
		assertEquals(bag6plain.getTotalPrice(), test6onion.getTotalPrice());
		assertEquals(test6everything.getTotalPrice(), bag6plain.getTotalPrice());
		assertEquals(bag13plain.getTotalPrice(), test13onion.getTotalPrice());
		assertEquals(test13everything.getTotalPrice(), bag13plain.getTotalPrice());

		// Gourmet
		Bag test3blueberry = new Bag(BLUEBERRY, 3);
		Bag test3tomato = new Bag(TOMATO, 3);
		Bag test6blueberry = new Bag(BLUEBERRY, 6);
		Bag test6tomato = new Bag(TOMATO, 6);
		Bag test13blueberry = new Bag(BLUEBERRY, 13);
		Bag test13tomato = new Bag(TOMATO, 13);

		assertEquals(bag3asiago.getTotalPrice(), test3blueberry.getTotalPrice());
		assertEquals(test3tomato.getTotalPrice(), bag3asiago.getTotalPrice());
		assertEquals(bag6asiago.getTotalPrice(), test6blueberry.getTotalPrice());
		assertEquals(test6tomato.getTotalPrice(), bag6asiago.getTotalPrice());
		assertEquals(bag13asiago.getTotalPrice(), test13blueberry.getTotalPrice());
		assertEquals(test13tomato.getTotalPrice(), bag13asiago.getTotalPrice());

		// Day Old
		BLUEBERRY.markDown();
		ONION.markDown();
		EVERYTHING.markDown();
		TOMATO.markDown();
		Bag test3oldblueberry = new Bag(BLUEBERRY, 3);
		Bag test3oldtomato = new Bag(TOMATO, 3);
		Bag test6oldonion = new Bag(ONION, 6);
		Bag test6oldeverything = new Bag(EVERYTHING, 6);
		Bag test13oldblueberry = new Bag(BLUEBERRY, 13);
		Bag test13oldtomato = new Bag(TOMATO, 13);

		assertEquals(bag3oldasiago.getTotalPrice(), test3oldblueberry.getTotalPrice());
		assertEquals(test3oldtomato.getTotalPrice(), bag3oldasiago.getTotalPrice());
		assertEquals(bag6oldplain.getTotalPrice(), test6oldonion.getTotalPrice());
		assertEquals(test6oldeverything.getTotalPrice(), bag6oldplain.getTotalPrice());
		assertEquals(bag13oldasiago.getTotalPrice(), test13oldblueberry.getTotalPrice());
		assertEquals(test13oldtomato.getTotalPrice(), bag13oldasiago.getTotalPrice());
	}
}// end BagTest class

class BagArgumentsProvider_getTotalPrice implements ArgumentsProvider {
	Bagel oldFashioned = new Bagel(Type.PLAIN);
	Bagel gourmet = new Bagel(Type.ASIAGO);
	Bagel old = new Bagel(Type.BLUEBERRY);

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		old.markDown();
		return Stream.of(Arguments.of(new Bag(oldFashioned, 3), 1.50), Arguments.of(new Bag(oldFashioned, 6), 2.85),
				Arguments.of(new Bag(oldFashioned, 13), 5.70), Arguments.of(new Bag(gourmet, 3), 2.10),
				Arguments.of(new Bag(gourmet, 6), 3.99), Arguments.of(new Bag(gourmet, 13), 7.98),
				Arguments.of(new Bag(old, 3), 1.05), Arguments.of(new Bag(old, 6), 2.00),
				Arguments.of(new Bag(old, 13), 3.99));
	}// end stream

}// end BagArgumentsProvider_getTotalPrice

class BagArgumentsProvider_getPerBagelPriceOFG implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// TODO Auto-generated method stub
		return Stream.of(Arguments.of(new Bagel(Type.PLAIN), .5), Arguments.of(new Bagel(Type.POPPY_SEED), .5),
				Arguments.of(new Bagel(Type.SESAME_SEED), .5), Arguments.of(new Bagel(Type.ONION), .5),
				Arguments.of(new Bagel(Type.EVERYTHING), .5), Arguments.of(new Bagel(Type.ASIAGO), .7),
				Arguments.of(new Bagel(Type.BLUEBERRY), .7), Arguments.of(new Bagel(Type.CINNAMON_RAISIN), .7),
				Arguments.of(new Bagel(Type.SUN_DRIED_TOMATO), .7));
	}

}// end BagArgumentsProvider_getPerBagelPrice

class BagArgumentsProvider_getPerBagelPriceOld implements ArgumentsProvider {
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
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		plain.markDown();
		poppySeed.markDown();
		sesameSeed.markDown();
		onion.markDown();
		everything.markDown();
		asiago.markDown();
		blueberry.markDown();
		cinnamonRaisin.markDown();
		sunDriedTomato.markDown();
		return Stream.of(Arguments.of(plain, .35), Arguments.of(poppySeed, .35), Arguments.of(sesameSeed, .35),
				Arguments.of(onion, .35), Arguments.of(everything, .35), Arguments.of(asiago, .35),
				Arguments.of(blueberry, .35), Arguments.of(cinnamonRaisin, .35), Arguments.of(sunDriedTomato, .35));
	}

}// end BagArgumentsProvider_getPerBagelPrice
