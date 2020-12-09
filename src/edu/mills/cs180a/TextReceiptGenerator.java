package edu.mills.cs180a;

public class TextReceiptGenerator extends ReceiptGenerator{
    private static final TextReceiptGenerator INSTANCE = new TextReceiptGenerator();
    private static final String RECEIPT_HEADER = "\t Bagel Refactory";
    private static final String RECEIPT_BODY = "%s \n \tQuantity: %d \n \tPrice each: %s \n Total: %s \n";
    private static final String RECEIPT_SAVINGS = "\t You saved %s through our volume discount program. \n";
    private static final String RECEIPT_FOOTER = "TOTAL: %s \n Thank you for shopping at Bagel Refactory!";

    /**
     * Generates a single instance of a printed receipt.
     *
     * @return the instance
     */
    public static final TextReceiptGenerator getInstance() {
        return INSTANCE;
    }

    private TextReceiptGenerator() {
        super(RECEIPT_HEADER, RECEIPT_BODY, RECEIPT_SAVINGS, RECEIPT_FOOTER);
    }
}
