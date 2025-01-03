package main;

/**
 * Enum representing the allowed product categories.
 */
public enum ProductCategory {
    KIDS("Kids"),
    ELECTRONICS("Electronics"),
    OFFICE("Office"),
    CLOTHING("Clothing");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ProductCategory fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < values().length) {
            return values()[ordinal];
        }
        throw new IllegalArgumentException("Invalid ordinal for ProductCategory");
    }



}
