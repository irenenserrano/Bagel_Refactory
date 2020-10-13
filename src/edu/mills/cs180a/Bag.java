package edu.mills.cs180a;

/**
 * A bag of any quantity of a single type of bagel.
 *
 * @author Ellen Spertus
 *
 */
public class Bag {
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
            itemPrice = .5; // 50 cents
        } else if (bagel.getCategory().equals("gourmet")) {
            itemPrice = .7; // 70 cents
        } else if (bagel.getCategory().equals("discounted")) {
            itemPrice = .35; // 35 cents
        } else {
            throw new IllegalArgumentException("Illegal category: " + bagel.getCategory());
        }
        double totalPrice;
        if (quantity == 13) {
            totalPrice = itemPrice * 12; // Baker's dozen
        } else if (quantity >= 6) {
            totalPrice = itemPrice * quantity * .95; // 5% discount
        } else {
            totalPrice = itemPrice * quantity;
        }
        return totalPrice;
    }

    // for student to write
    public double getPerBagelPrice() {
        if (bagel.getCategory().equals("old-fashioned")) {
            return .5;
        } else if (bagel.getCategory().equals("gourmet")) {
            return .7;
        } else if (bagel.getCategory().equals("discounted")) {
            return .35;
        } else {
            throw new IllegalArgumentException("Illegal category");
        }
    }
}
