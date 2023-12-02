package info.jab.aoc.day2;

public enum CubeColor {
    RED(12),
    GREEN(13),
    BLUE(14);

    private final int maxValue;

    CubeColor(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public static CubeColor getColorFromName(String colorName) {
        return switch (colorName.toLowerCase()) {
            case "red" -> RED;
            case "green" -> GREEN;
            case "blue" -> BLUE;
            default -> null; // Or handle unknown colors differently
        };
    }
}
