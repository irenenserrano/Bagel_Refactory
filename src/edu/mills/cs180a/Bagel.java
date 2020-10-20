package edu.mills.cs180a;

import java.math.BigDecimal;

/**
 * A type of bagel.
 *
 * @author Ellen Spertus
 */
public class Bagel {
	private final Type type;
	private Category currentCategory;

	enum Type {
		PLAIN("plain", Category.OLD_FASHIONED), POPPY_SEED("poppy seed",
				Category.OLD_FASHIONED), SESAME_SEED("sesame seed", Category.OLD_FASHIONED), ONION(
						"onion",Category.OLD_FASHIONED), EVERYTHING("everthing",
								Category.OLD_FASHIONED), ASIAGO("asiago",
										Category.GOURMET), BLUEBERRY("blueberry",
												Category.GOURMET), CINNAMON_RAISIN(
														"cinnamon raisin",
														Category.GOURMET), SUN_DRIED_TOMATO(
																"sun dried tomato",
																Category.GOURMET);

		private final String name;
		private final Category category;

		private Type(String name, Category category) {
			this.name = name;
			this.category = category;
		}

		@Override
		public String toString() {
			return name;
		}

	}

	enum Category {
		OLD_FASHIONED(BigDecimal.valueOf(.5)), GOURMET(BigDecimal.valueOf(.7)), DAY_OLD(BigDecimal.valueOf(.35));

		private final BigDecimal price;

		private Category(BigDecimal price) {
			this.price = price;
		}

		public BigDecimal getPrice() {
			return price;
		}
	}

	/**
	 * Constructs a bagel of the given type.
	 *
	 * @param the type
	 */
	public Bagel(Type type) {
		this.type = type;
		this.currentCategory = type.category;
	}

	/**
	 * Gets the type of the bagel.
	 *
	 * @return the type of bagel
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Gets the category of the bagel.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return currentCategory;
	}

	/**
	 * Marks down this bagel.
	 */
	public void markDown() {
		currentCategory = Category.DAY_OLD;
	}
}
