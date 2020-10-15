package edu.mills.cs180a;

/**
 * A bag of any quantity of a single type of bagel.
 *
 * @author Ellen Spertus
 *
 */
public class Bag {
    private static final int BUY_ONE_GET_ONE_FREE_QUANTITY = 13;
    private static final int HALF_DOZEN = 6;
    private static final int DOZEN = 13;
    private static final double DISCOUNT_PERCENT = .95;
    private static final double DISCOUNTED_PRICE = .35;
    private static final double GOURMET_PRICE = .7;
    private static final double OLD_FASHIONED_PRICE = .5;
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
        double itemPrice;
        if (bagel.getCategory().equals("old-fashioned")) {
            itemPrice = OLD_FASHIONED_PRICE; // 50 cents
        } else if (bagel.getCategory().equals("gourmet")) {
            itemPrice = GOURMET_PRICE; // 70 cents
        } else if (bagel.getCategory().equals("discounted")) {
            itemPrice = DISCOUNTED_PRICE; // 35 cents
        } else {
            throw new IllegalArgumentException("Illegal category: " + bagel.getCategory());
        }
        double totalPrice;
        if (quantity == BUY_ONE_GET_ONE_FREE_QUANTITY) {
            totalPrice = itemPrice * DOZEN; // Baker's dozen
        } else if (quantity >= HALF_DOZEN) {
            totalPrice = itemPrice * quantity * DISCOUNT_PERCENT; // 5% discount
        } else {
            totalPrice = itemPrice * quantity;
        }
        return totalPrice;
    }

    /**
     * Returns the price associated with the bagel type.
     *
     * @return price based on bagel type
     */
    public double getPerBagelPrice() {
        if (bagel.getCategory().equals("old-fashioned")) {
            return OLD_FASHIONED_PRICE;
        } else if (bagel.getCategory().equals("gourmet")) {
            return GOURMET_PRICE;
        } else if (bagel.getCategory().equals("discounted")) {
            return DISCOUNTED_PRICE;
        } else {
            throw new IllegalArgumentException("Illegal category");
        }
    }
}
