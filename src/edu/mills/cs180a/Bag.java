package edu.mills.cs180a;

import java.math.BigDecimal;

/**
 * A bag of any quantity of a single type of bagel.
 *
 * @author Ellen Spertus
 *
 */
public class Bag {
	// We provide a Baker's Dozen: 13 bagels for the price of 12.
	private static final int BUY_ONE_GET_ONE_FREE_QUANTITY = 13;

	// If the Baker's Dozen discount doesn't apply, we give a percentage discount.
	private static final int BULK_DISCOUNT_MINIMUM = 6;
	private static final BigDecimal BULK_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(.05);
	private static final BigDecimal BULK_DISCOUNT_MULTIPLIER = BigDecimal.valueOf(1).subtract(BULK_DISCOUNT_PERCENTAGE);

	private final Bagel bagel;
	private final int quantity;

	/**
	 * Constructs a bag with the given quantity of the given type of bagel.
	 *
	 * @param bagel the type of bagel
	 * @param quantity the quantity
	 */
	public Bag(Bagel bagel, int quantity) {
		this.bagel = bagel;
		this.quantity = quantity;
	}

	/**
	 * Gets the bagel held in this bag.
	 *
	 * @return the bagel
	 */
	public Bagel getBagel() {
		return bagel;
	}

	/**
	 * Gets the number of bagels in this bag.
	 *
	 * @return the number of bagels in this bag
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Gets the total price for this bag of bagels. This may be less than the per-bagel price times
	 * the number of bagels because of quantity discounts.
	 *
	 * @return the total price
	 */
	public BigDecimal getTotalPrice() {
		BigDecimal undiscountedPrice = getPerBagelPrice().multiply(BigDecimal.valueOf(quantity));
		if (quantity == BUY_ONE_GET_ONE_FREE_QUANTITY) {
			return undiscountedPrice.subtract(getPerBagelPrice());
		}
		if (quantity >= BULK_DISCOUNT_MINIMUM) {
			return undiscountedPrice.multiply(BULK_DISCOUNT_MULTIPLIER);
		}
		return undiscountedPrice;
	}

	/**
	 * Returns the price associated with the bagel type.
	 *
	 * @return price based on bagel type
	 */
	public BigDecimal getPerBagelPrice() {
		return bagel.getCategory().getPrice();

		//or you could do
		//Category category = bagel.getCategory();
		//return category.getPrice();
	}
}
