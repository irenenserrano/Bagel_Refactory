package edu.mills.cs180a;

import java.util.List;

/**
 * A type of bagel. There should be only one instance of this class for any
 * bagel type (e.g., "sesame seed").
 *
 * @author Ellen Spertus
 */
public class Bagel {
	private static List<String> OLD_FASHIONED_TYPES = List.of("plain", "poppy seed", "sesame seed", "onion",
			"everything");
	private static List<String> GOURMET_TYPES = List.of("asiago", "blueberry", "cinnamon raisin", "sun-dried tomato");
	public static List<String> CATEGORIES = List.of("old-fashioned", "gourmet", "day-old");
	private final String type;
	private String category;

	/**
	 * Constructs a bagel of the given type.
	 *
	 * @param type one of "plain", "poppy seed", "sesame seed", "onion",
	 *             "everything", "asiago", "blueberry", "raisin", or "sun-dried
	 *             tomato"
	 * @throws IllegalArgumentException if the type is not legal
	 */
	public Bagel(String type) {
		if (OLD_FASHIONED_TYPES.contains(type)) {
			category = "old-fashioned";
		} else if (GOURMET_TYPES.contains(type)) {
			category = "gourmet";
		} else {
			throw new IllegalArgumentException("Illegal bagel type: " + type);
		}
		this.type = type;
	}

	/**
	 * Gets the type of the bagel.
	 *
	 * @return the type of bagel
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the category of the bagel. This is "old-fashioned", "gourmet", or
	 * "discounted".
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Marks down this bagel.
	 */
	public void markDown() {
		category = "day-old";
	}
}
