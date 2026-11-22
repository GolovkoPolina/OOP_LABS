package laba1;

/** Неизменяемые характеристики товара. */
public final class Dimensions {
    private final double weight;
    private final double volume;
    private final boolean fragile;

    public Dimensions(double weight, double volume, boolean fragile) {
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше нуля");
        }
        if (!Double.isFinite(volume) || volume <= 0) {
            throw new IllegalArgumentException("Объём должен быть больше нуля");
        }
        this.weight = weight;
        this.volume = volume;
        this.fragile = fragile;
    }

    public double getWeight() { return weight; }
    public double getVolume() { return volume; }
    public boolean isFragile() { return fragile; }
}
