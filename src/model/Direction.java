package model;

/**
 * The Direction enum represents the possible movement directions in a maze game.
 */
public enum Direction {
    UP('w'), LEFT('a'), DOWN('s'), RIGHT('d');

    /**
     * character input that represent the direction
     */
    private final char key;

    /**
     * Constructs a Direction enum with the corresponding keyboard input key.
     *
     * @param key The keyboard input key associated with the direction.
     */
    Direction(char key) {
        this.key = key;
    }

    /**
     * retrieves a key.
     * @return The keyboard input key associated with the direction.
     * @pure
     */
    public char getKey() {
        return key;
    }
}
