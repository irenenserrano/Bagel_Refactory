package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ReceiptGeneratorTest {

    @Test
    void formatMoney_CorrectOutput_DollarAmount() {
        String output = ReceiptGenerator.formatMoney(BigDecimal.valueOf(15));
        assertTrue(output.equals("$15") || output.equals("$15.00"));
    }

    @Test
    void formatMoney_CorrectOutput_SubdollarAmount() {
        String output = ReceiptGenerator.formatMoney(BigDecimal.valueOf(.15));
        assertTrue(output.equals("$0.15") || output.equals("$.15"));
    }

}
