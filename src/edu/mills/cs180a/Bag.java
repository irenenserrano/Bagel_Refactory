package edu.mills.cs180a;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import edu.mills.cs180a.Bagel.Type;

/**
 * A bag of any quantity of a single type of bagel.
 *
 * @author Ellen Spertus
 *
 */
public class Bag implements Comparable<Bag> {
    // We provide a Baker's Dozen: 13 bagels for the price of 12.
    private static final int BUY_ONE_GET_ONE_FREE_QUANTITY = 13;

    // If the Baker's Dozen discount doesn't apply, we give a percentage discount.
    private static final int BULK_DISCOUNT_MINIMUM = 6;
    private static final BigDecimal BULK_DISCOUNT_PERCENTAGE = new BigDecimal(".05");
    private static final BigDecimal BULK_DISCOUNT_MULTIPLIER =
            new BigDecimal("1.00").subtract(BULK_DISCOUNT_PERCENTAGE);

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

        if (this.quantity < 1) {
            throw new IllegalArgumentException("Not a valid order");
        }
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
        BigDecimal undiscountedPrice = getPerBagelPrice().multiply(new BigDecimal(quantity));
        if (quantity == BUY_ONE_GET_ONE_FREE_QUANTITY) {
            return undiscountedPrice.subtract(getPerBagelPrice());
        }
        if (quantity >= BULK_DISCOUNT_MINIMUM && quantity != 13) {
            return undiscountedPrice.multiply(BULK_DISCOUNT_MULTIPLIER,
                    new MathContext(3));
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
    }

    @Override
    public String toString() {
        return "Bag of " + getQuantity() + " " + bagel.toString() + " bagels at "
                + getPerBagelPrice() + " cents a piece.\nTotal Price: " + getTotalPrice();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Bag))
            return false;

        Bag bag = (Bag) object;
        Type bagelType = bag.getBagel().getType();
        BigDecimal singlePrice = bag.getPerBagelPrice();
        int amount = bag.getQuantity();

        if (bagelType.equals(this.bagel.getType()) && singlePrice.equals(getPerBagelPrice())
                && amount == this.quantity) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bagel, quantity, getPerBagelPrice(), getTotalPrice());
    }

    @Override
    public int compareTo(Bag bag) {
        String str = bag.toString();

        return str.compareTo(this.toString());

    }
}

