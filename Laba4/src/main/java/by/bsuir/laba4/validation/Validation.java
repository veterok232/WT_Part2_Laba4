package by.bsuir.laba4.validation;

import java.util.Map;

/**
 * Validation class
 */
public class Validation {
    /**
     * User name
     */
    private static final String USERNAME = "username";

    /**
     * Refill
     */
    private static final String REFILL = "refill";

    /**
     * Room number
     */
    private static final String ROOM_NUMBER = "roomNumber";

    /**
     * Room id
     */
    private static final String ROOM_ID = "roomId";

    /**
     * Id pattern
     */
    private static final String ID_PATTERN = "^([1-9]{1}[0-9]{0,10})$";

    /**
     * User name pattern
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

    /**
     * Price pattern
     */
    private static final String PRICE_PATTERN = "^([1-9]{1}[0-9]{0,8})$";

    /**
     * Room number pattern
     */
    private static final String ROOM_NUMBER_PATTERN = "^([0-9]{3})$";

    /**
     * Invalid data
     */
    private String invalidData;

    /**
     * Get pattern by type
     *
     * @param type pattern type
     *
     * @return String
     */
    private String definePattern(String type) {
        switch (type) {
            case USERNAME:
                return USERNAME_PATTERN;
            case REFILL:
                return PRICE_PATTERN;
            case ROOM_NUMBER:
                return ROOM_NUMBER_PATTERN;
            case ROOM_ID:
                return ID_PATTERN;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * Check that input data is valid
     *
     * @param inputData input data
     *
     * @return boolean
     */
    public boolean isValid(Map<String, String> inputData) {
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String value = entry.getValue();
            if (value == null) {
                return false;
            }

            String key = entry.getKey();
            String pattern = definePattern(key);
            if (!value.matches(pattern)) {
                invalidData = key;
                return false;
            }
        }
        return true;
    }

    /**
     * Getter for invalid data
     *
     * @return String
     */
    public String getInvalidData() {
        return invalidData;
    }
}
