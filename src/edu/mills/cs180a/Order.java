package edu.mills.cs180a;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Bagel Refactory order consisting of one or more instances of {@link Bag}.
 *
 * @author Ellen Spertus
 */
public class Order {
    private List<Bag> bags;

    // constructor
    private Order(List<Bag> bags) {
        this.bags = bags;
    }

    /**
     * Constructs an order given one or more bags.
     *
     * @param bag one bag
     * @param bagsOfBagels varying amount of bags
     */
    public static Order of(Bag bag, Bag... bagsOfBagels) {
        List<Bag> listOfBags = new ArrayList<Bag>();
        listOfBags.add(bag);
        for (Bag b : bagsOfBagels) {
            listOfBags.add(b);
        }

        return new Order(listOfBags);
    }


    /**
     * Generates the total price of this order based on the contents of each bag.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);
        for (Bag bag : bags) {
            price = price.add(bag.getTotalPrice());
        }
        return price;
    }

    /**
     * Generates a printed receipt containing the contents, discounts, and total final price.
     *
     * @return an instance of a receipt for this order.
     */
    public String generateReceipt() {
        return TextReceiptGenerator.getInstance().generateReceipt(this);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Order of:\n");
        for (int i = 0; i < bags.size(); i++) {
            str.append("\tItem ").append(i).append(": ").append(bags.get(i).toString())
            .append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Order)) {
            return false;
        }

        Order newOrder = (Order) object;
        Collections.sort(bags);
        Collections.sort(newOrder.bags);

        return bags.equals(newOrder.bags);
    }

    @Override
    public int hashCode() {
        return bags.hashCode();
    }

    public List<Bag> getBags() {
        return bags;
    }
}

