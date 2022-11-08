package io.github.nathancorghi.contantes;

public enum Cores {

    BLUE("BLUE"),
    BLACK("BLACK"),
    GRAY("GRAY"),
    PURPLE("PURPLE"),
    RED("RED"),
    YELLOW("YELLOW");

    private final String value;

    Cores(String value) {
        this.value = value;
    }

    @Override
    public String toString() { return value; }
}
