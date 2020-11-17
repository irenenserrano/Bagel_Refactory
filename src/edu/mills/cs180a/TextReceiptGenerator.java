package edu.mills.cs180a;

public class TextReceiptGenerator extends ReceiptGenerator{
    private static final TextReceiptGenerator INSTANCE = new TextReceiptGenerator();
    private static final String RECEIPT_HEADER = "\t Bagel Refactory";
    private static final String RECEIPT_BODY = "%s \n \tQuantity: %d \n \tPrice each: $%f \n Total: $%f \n";
    private static final String RECEIPT_SAVINGS = "\t You saved $%f through our volume discount program. \n";
    private static final String RECEIPT_FOOTER = "TOTAL: $%f \n Thank you for shopping at Bagel Refactory!";

    public static final TextReceiptGenerator getInstance() {
        return INSTANCE;
    }

    private TextReceiptGenerator() {
        super(RECEIPT_HEADER, RECEIPT_BODY, RECEIPT_SAVINGS, RECEIPT_FOOTER);
    }
}
