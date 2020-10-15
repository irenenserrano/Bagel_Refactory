package edu.mills.cs180a;

/**
 * A bag of any quantity of a single type of bagel.
 *
 * @author Ellen Spertus
 *
 */
public class Bag {
    private static final double DAY_OLD_BAGEL_PRICE = .35;

    private static final double GOURMET_BAGEL_PRICE = .7;

    private static final double OLD_FASHIONED_BAGEL_PRICE = .5;

    // We provide a Baker's Dozen: 13 bagels for the price of 12.
    private static final int BUY_ONE_GET_ONE_FREE_QUANTITY = 13;

    // If the Baker's Dozen discount doesn't apply, we give a percentage discount.
    private static final int BULK_DISCOUNT_MINIMUM = 6;
    private static final double BULK_DISCOUNT_PERCENTAGE = .05;
    private static final double BULK_DISCOUNT_MULTIPLIER = 1 - BULK_DISCOUNT_PERCENTAGE;

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
    public double getTotalPrice() {
        double undiscountedPrice = quantity * getPerBagelPrice();
        if (quantity == BUY_ONE_GET_ONE_FREE_QUANTITY) {
            return undiscountedPrice - getPerBagelPrice();
        }
        if (quantity >= BULK_DISCOUNT_MINIMUM) {
            return undiscountedPrice * BULK_DISCOUNT_MULTIPLIER;
        }
        return undiscountedPrice;
    }

    // for student to write
    public double getPerBagelPrice() {
        if (bagel.getCategory().equals("old-fashioned")) {
            return OLD_FASHIONED_BAGEL_PRICE;
        } else if (bagel.getCategory().equals("gourmet")) {
            return GOURMET_BAGEL_PRICE;
        } else if (bagel.getCategory().equals("day-old")) {
            return DAY_OLD_BAGEL_PRICE;
        } else {
            throw new IllegalArgumentException("Illegal category");
        }
    }
}
