package edu.mills.cs180a;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A Bagel Refactory order consisting of one or more instances of {@link Bag}.
 *
 * @author Ellen Spertus
 */
public class Order {
    private List<Bag> bags;

    // constructor
    private Order(Bag...bags) {
        this.bags = Arrays.asList(bags);
    }

    /**
     * Constructs an order given any amount of bags
     *
     * @param bags
     */
    public static Order of(Bag...bagsOfBagels) {
        return new Order(bagsOfBagels);
    }

    /**
     * Generates the total price of a single order based on the contents of each bag
     *
     * @return price
     */
    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);
        for (Bag bag : bags) {
            price = price.add(bag.getTotalPrice());
        }
        return price;
    }// end getPrice

    /**
     * Generates a printed receipt containing the contents, discounts, and total final price
     *
     * @return s Generated receipt of order
     */
    public String generateReceipt() {
        return Receipt.getInstance().generateReceipt(this);
    }


    @Override
    public String toString() {
        return bags.toString();
    }

    @Override
    public boolean equals(Object object) {
        // check to see that the object is an Order
        if (!(object instanceof Order))
            return false;

        Order newOrder = (Order)object;
        Collections.sort(bags);
        Collections.sort(newOrder.bags);

        return bags.equals(newOrder.bags);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Bag bag : bags) {
            result = Objects.hash(bag.getBagel(), bag.getQuantity(), bag.getPerBagelPrice(),
                    bag.getTotalPrice());
        }
        return result;
    }

    public List<Bag> getBags() {
        return bags;
    }
}// end class

