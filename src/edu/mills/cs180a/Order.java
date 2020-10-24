package edu.mills.cs180a;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * A Bagel Refactory order consisting of one or more instances of {@link Bag}.
 *
 * @author Ellen Spertus
 */
public class Order {
    private static List<Bag> bags;

    /**
     * Constructs an order given any amount of bags
     *
     * @param bags
     */
    public static void Orderof(Bag... bagsofBagels) {
        bags = List.of(bagsofBagels);
    }// end constructor

    /**
     * Generates the total price of a single order based on the contents of each bag
     *
     * @return price
     */
    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.valueOf(0);
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
        String s = "";
        BigDecimal total = BigDecimal.valueOf(0);
        for (Bag bag : bags) {
            total = total.add(bag.getTotalPrice());
            s += bag.getBagel().getType() + "\n" + "\tquantity: " + bag.getQuantity() + "\n"
                    + "\tprice each: $"
                    + bag.getPerBagelPrice() + "\n" + "\total: $" + "\ttotal: "
                    + bag.getTotalPrice() + "\n";
            if (bag.getTotalPrice()
                    .compareTo(bag.getPerBagelPrice()
                            .multiply(BigDecimal.valueOf(bag.getQuantity()))) < 0) {
                s += "\tYou saved $"
                        + (bag.getPerBagelPrice().multiply(BigDecimal.valueOf(bag.getQuantity()))
                                .subtract(bag.getTotalPrice()))
                        + " through our volume discount program.\n";
            }
        }
        s += "TOTAL: $" + total + "\n";
        s += "Thank you for shopping at the Bagel Refactory!\n";
        return s;
    }// end generateReciept

    @Override
    public String toString() {
        return bags.toString();
    }

    public boolean equals(Order order1, Order order2) {
        BigDecimal price1 = order1.getPrice();
        BigDecimal price2 = order2.getPrice();

        if (price1 == price2)
            return true;
        return false;
    }// end equals

    @Override
    public int hashCode() {
        int result = 0;
        for (Bag bag : bags) {
            result = Objects.hash(bag.getBagel(), bag.getQuantity(), bag.getPerBagelPrice(),
                    bag.getTotalPrice());
        }
        return result;
    }
}// end class
