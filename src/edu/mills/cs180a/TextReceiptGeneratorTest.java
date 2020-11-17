package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import edu.mills.cs180a.Bagel.Type;

class TextReceiptGeneratorTest {

    private static final TextReceiptGenerator GENERATOR = TextReceiptGenerator.getInstance();

    private static Bag makeBag(Bagel.Type type, int quantity) {
        return new Bag(new Bagel(type), quantity);
    }

    private static Bag PLAIN_7 = makeBag(Type.PLAIN, 7);
    private static Bag RAISIN_13 = makeBag(Type.CINNAMON_RAISIN, 13);
    private static Bagel ASIAGO = new Bagel(Type.ASIAGO);
    private static Bag OLD_3 = new Bag(ASIAGO, 3);

    @Test
    void generateBody_CorretOutput_ValidBody() {
        ASIAGO.markDown();
        String output1 = "Plain \n \tQuantity: 7 \n \tPrice each: $0.50 \n Total: $3.32 \n";
        String output2 = "Cinnamon Raisin \n \tQuantity: 13 \n \tPrice each: $0.70 \n Total: $8.40 \n";
        String output3 = "Asiago \n \tQuantity: 3 \n \tPrice each: $0.35 \n Total: $1.05 \n";
        assertEquals(output1, GENERATOR.generateBody(PLAIN_7));
        assertEquals(output2, GENERATOR.generateBody(RAISIN_13));
        assertEquals(output3, GENERATOR.generateBody(OLD_3));
    }

    @Test
    void generateSavings_CorrectOutput_ValidSavings() {
        String output1 = "\t You saved $0.18 through our volume discount program. \n";
        String output2 = "\t You saved $0.70 through our volume discount program. \n";
        assertEquals(output1, GENERATOR.generateSavings(PLAIN_7));
        assertEquals(output2, GENERATOR.generateSavings(RAISIN_13));
    }

    @Test
    void generateFooter_CorretOutput_ValidFooter() {
        ASIAGO.markDown();
        String output1 = "TOTAL: $3.32 \n Thank you for shopping at Bagel Refactory!";
        assertEquals(output1, GENERATOR.generateFooter(BigDecimal.valueOf(3.32)));
    }

}
