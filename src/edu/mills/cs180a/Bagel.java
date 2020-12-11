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
    protected static final BigDecimal OLD_FASHIONED_PRICE = new BigDecimal("0.50");
    protected static final BigDecimal GOURMET_PRICE = new BigDecimal("0.70");
    protected static final BigDecimal DAY_OLD_PRICE = new BigDecimal("0.35");


    enum Type {
        PLAIN("Plain", Category.OLD_FASHIONED), POPPY_SEED("Poppy Seed", Category.OLD_FASHIONED),
        SESAME_SEED("Sesame Seed", Category.OLD_FASHIONED), ONION("Onion", Category.OLD_FASHIONED),
        EVERYTHING("Everything", Category.OLD_FASHIONED), ASIAGO("Asiago", Category.GOURMET),
        BLUEBERRY("Blueberry", Category.GOURMET),
        CINNAMON_RAISIN("Cinnamon Raisin", Category.GOURMET),
        SUN_DRIED_TOMATO("Sun Dried Tomato", Category.GOURMET);

        private final String name;
        private final Category category;

        private Type(String name, Category category) {
            this.name = name;
            this.category = category;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    enum Category {
        OLD_FASHIONED(OLD_FASHIONED_PRICE), GOURMET(GOURMET_PRICE), DAY_OLD(DAY_OLD_PRICE);

        private final BigDecimal price;

        private Category(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }

    /**
     * Constructs a bagel of the given type.
     *
     * @param the type
     */
    public Bagel(Type type) {
        this.type = type;
        this.currentCategory = type.category;
    }

    /**
     * Gets the type of the bagel.
     *
     * @return the type of bagel
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the category of the bagel.
     *
     * @return the category
     */
    public Category getCategory() {
        return currentCategory;
    }

    /**
     * Marks down this bagel.
     */
    public void markDown() {
        currentCategory = Category.DAY_OLD;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Order)) {
            return false;
        }

        String type = ((Bagel) object).getType().toString();
        Category category = ((Bagel) object).getCategory();
        return type.equals(this.type) && category.equals(this.getCategory());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.currentCategory);
    }
}
