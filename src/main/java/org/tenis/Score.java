package org.tenis;


public enum Score {
    ZERO(0), FIFTEEN(15), THIRTY(30), FORTY(40);

    private final int value;

    Score(int value) {
        this.value = value;
    }

    public static Score next(Score current) {
        if(current==null) throw new IllegalStateException("Invalid score progression.");
        switch (current) {
            case ZERO: return FIFTEEN;
            case FIFTEEN: return THIRTY;
            case THIRTY: return FORTY;
            default: throw new IllegalStateException("Invalid score progression.");
        }
    }

    @Override
    public String toString() {
        return value == 0 ? "0" : String.valueOf(value);
    }
}
