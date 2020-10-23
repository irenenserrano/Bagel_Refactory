package edu.mills.cs180a;

import java.math.BigDecimal;
import java.util.Objects;

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
    private static final BigDecimal BULK_DISCOUNT_MULTIPLIER =
            BigDecimal.valueOf(1).subtract(BULK_DISCOUNT_PERCENTAGE);

    private final Bagel bagel;
    private final int quantity;

    /**
     * Constructs a bag with the given quantity of the given type of bagel.
     *
     * @param bagel the type of bagel
     * @param quantity the quantity
     * @throws IllegalArguementException if the quantity passed is out of range from 1 to 13
     */
    public Bag(Bagel bagel, int quantity) {
        this.bagel = bagel;
        this.quantity = quantity;
        if (this.quantity > 13) {
            throw new IllegalArgumentException("Orders must be under 13 bagels");
        }

        if (this.getQuantity() < 1) {
            throw new IllegalArgumentException("Not a valid order");
        }

    }// end constructor

    /**
     * Gets the bagel held in this bag.
     *
     * @return the bagel
     */
    public Bagel getBagel() {
        return bagel;
    }// end getBagel

    /**
     * Gets the number of bagels in this bag.
     *
     * @return the number of bagels in this bag
     */
    public int getQuantity() {
        return quantity;
    }// end getQuantity

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
    }// end getTotalPrice

    /**
     * Returns the price associated with the bagel type.
     *
     * @return price based on bagel type
     */
    public BigDecimal getPerBagelPrice() {
        return bagel.getCategory().getPrice();
    }// end getPerBagelPrice

    @Override
    public String toString() {
        return bagel.toString();
    }// end toString

    @Override
    public boolean equals(Object object) {
        // check to see that the object passed in is a Bag
        if (!(object instanceof Bag))
            return false;

        Bagel bagel = ((Bag) object).getBagel();
        BigDecimal singlePrice = ((Bag) object).getPerBagelPrice();
        int amount = ((Bag) object).getQuantity();
        BigDecimal totalPrice = ((Bag) object).getTotalPrice();

        if (bagel.equals(this.getBagel()) && singlePrice == this.getPerBagelPrice()
                && amount == this.getQuantity() && totalPrice == this.getTotalPrice())
            return true;
        return false;
    }// end equals

    @Override
    public int hashCode() {
        return Objects.hash(getBagel(), getQuantity());
    }
}// end class

