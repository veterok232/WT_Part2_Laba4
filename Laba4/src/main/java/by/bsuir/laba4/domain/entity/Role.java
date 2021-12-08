package by.bsuir.laba4.domain.entity;

/**
 * Role enum
 */
public enum Role {
    /**
     * User value
     */
    USER("user"),

    /**
     * Admin value
     */
    ADMIN("admin");

    /**
     * Current value
     */
    private final String value;

    /**
     * Construct
     *
     * @param value current value
     */
    Role(String value) {
        this.value = value;
    }
}
