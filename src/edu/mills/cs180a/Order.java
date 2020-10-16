package edu.mills.cs180a;

import java.util.List;

/**
 * A Bagel Refactory order consisting of one or more instances of {@link Bag}.
 *
 * @author Ellen Spertus
 */
public class Order {
    private final List<Bag> bags;

    /**
     * Constructs an order with a single bag of bagels.
     *
     * @param bag the bag
     */
    public Order(Bag bag) {
        bags = List.of(bag);
    }

    /**
     * Constructs an order with two bags of bagels.
     *
     * @param bag1 the first bag
     * @param bag2 the second bag
     */
    public Order(Bag bag1, Bag bag2) {
        bags = List.of(bag1, bag2);
    }

    /**
     * Constructs an order with three bags of bagels.
     *
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @param bag3 the third bag
     */
    public Order(Bag bag1, Bag bag2, Bag bag3) {
        bags = List.of(bag1, bag2, bag3);
    }

    // TODO: Write more constructors for more numbers of bags.

    /**
     * Returns the total price of a given order
     *
     * @return price
     */
    public double getPrice() {
        double price = 0;
        for (Bag bag : bags) {
            price += bag.getTotalPrice();
        }
        return price;
    }

    /**
     * Returns a printed receipt of a given order
     *
     * @return Recipet statement
     */
    public String generateReceipt() {
        String s = "";
        double total = 0;
        for (Bag bag : bags) {
            total += bag.getTotalPrice();
            s += bag.getBagel().getType() + "\n" + "\tquantity: " + bag.getQuantity() + "\n"
                    + "\tprice each: $" + bag.getPerBagelPrice() + "\n" + "\total: $" + "\ttotal: "
                    + bag.getTotalPrice() + "\n";
            if (bag.getTotalPrice() < bag.getPerBagelPrice() * bag.getQuantity()) {
                s += "\tYou saved $"
                        + (bag.getPerBagelPrice() * bag.getQuantity() - bag.getTotalPrice())
                        + " through our volume discount program.\n";
            }
        }
        s += "TOTAL: $" + total + "\n";
        s += "Thank you for shopping at the Bagel Refactory!\n";
        return s;
    }
}
