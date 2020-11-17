package edu.mills.cs180a;

import java.math.BigDecimal;

public abstract class ReceiptGenerator {
    private final String receiptHeader;
    private final String receiptBody;
    private final String receiptSavings;
    private final String receiptFooter;

    protected ReceiptGenerator(String receiptHeader, String receiptBody, String receiptSavings, String receiptFooter) {
        this.receiptHeader = receiptHeader;
        this.receiptBody = receiptBody;
        this.receiptSavings = receiptSavings;
        this.receiptFooter = receiptFooter;
    }

    /**
     * Generates a printed receipt containing the contents, discounts, and total final price
     *
     * @return s Generated receipt of order
     */
    public String generateReceipt(Order order) {
        StringBuilder sb = new StringBuilder();
        BigDecimal total = new BigDecimal("00.00");
        sb.append(receiptHeader);
        for (Bag bag : order.getBags()) {
            total = total.add(bag.getTotalPrice());
            sb.append(generateBody(bag));
            if (bag.getTotalPrice()
                    .compareTo(bag.getPerBagelPrice()
                            .multiply(BigDecimal.valueOf(bag.getQuantity()))) < 0) {
                sb.append(generateSavings(bag));
            }
        }
        sb.append(generateFooter(total.doubleValue()));
        return sb.toString();
    }// end generateReciept

    protected String generateBody(Bag bag) {
        String bagelType = bag.getBagel().getType().toString();
        int quantity = bag.getQuantity();
        double pricePer = bag.getPerBagelPrice().doubleValue();
        double total = bag.getTotalPrice().doubleValue();

        return String.format(receiptBody, bagelType, quantity, pricePer, total);
    }

    protected String generateSavings(Bag bag) {
        double savings =
                (bag.getPerBagelPrice().multiply(BigDecimal.valueOf(bag.getQuantity()))
                        .subtract(bag.getTotalPrice())).doubleValue();
        return String.format(receiptSavings, savings);
    }

    protected String generateFooter(double total) {
        return String.format(receiptFooter, total);
    }
}