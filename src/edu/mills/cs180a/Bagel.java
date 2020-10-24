package edu.mills.cs180a;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A type of bagel.
 *
 * @author Ellen Spertus
 */
public class Bagel {
    private final Type type;
    private Category currentCategory;
    // Visible for Testing
    protected static final BigDecimal OLD_FASHIONED_PRICE = BigDecimal.valueOf(0.5);
    protected static final BigDecimal GOURMET_PRICE = BigDecimal.valueOf(0.7);
    protected static final BigDecimal DAY_OLD_PRICE = BigDecimal.valueOf(0.35);


    enum Type {
        PLAIN("plain", Category.OLD_FASHIONED), POPPY_SEED("poppy seed",
                Category.OLD_FASHIONED), SESAME_SEED("sesame seed", Category.OLD_FASHIONED), ONION(
                        "onion", Category.OLD_FASHIONED), EVERYTHING("everthing",
                                Category.OLD_FASHIONED), ASIAGO("asiago",
                                        Category.GOURMET), BLUEBERRY("blueberry",
                                                Category.GOURMET), CINNAMON_RAISIN(
                                                        "cinnamon raisin",
                                                        Category.GOURMET), SUN_DRIED_TOMATO(
                                                                "sun dried tomato",
                                                                Category.GOURMET);

        private final String name;
        private final Category category;

        private Type(String name, Category category) {
            this.name = name;
            this.category = category;
        }// end constructor

        @Override
        public String toString() {
            return name;
        }// end toString

    }// end enum

    enum Category {
        OLD_FASHIONED(OLD_FASHIONED_PRICE), GOURMET(GOURMET_PRICE), DAY_OLD(DAY_OLD_PRICE);

        private final BigDecimal price;

        private Category(BigDecimal price) {
            this.price = price;
        }// end constructor

        public BigDecimal getPrice() {
            return price;
        }// end getPrice
    }// end enum

    /**
     * Constructs a bagel of the given type.
     *
     * @param the type
     */
    public Bagel(Type type) {
        this.type = type;
        this.currentCategory = type.category;
    }// end constructor

    /**
     * Gets the type of the bagel.
     *
     * @return the type of bagel
     */
    public Type getType() {
        return type;
    }// end getType

    /**
     * Gets the category of the bagel.
     *
     * @return the category
     */
    public Category getCategory() {
        return currentCategory;
    }// end get Category

    /**
     * Marks down this bagel.
     */
    public void markDown() {
        currentCategory = Category.DAY_OLD;
    }// end markDown

    @Override
    public String toString() {
        return type.toString();
    }// end toString

    @Override
    public boolean equals(Object object) {
        // check to see that the object is a Bagel
        if(!(object instanceof Order))
            return false;

        String type = ((Bagel) object).getType().toString();
        Category category = ((Bagel) object).getCategory();
        if (type.equals(this.type) && category.equals(this.getCategory()))
            return true;
        return false;
    }// end equals

    @Override
    public int hashCode() {
        return Objects.hash(this.type, getCategory());
    }
}// end class
