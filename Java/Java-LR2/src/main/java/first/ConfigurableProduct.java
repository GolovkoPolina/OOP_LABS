
package first;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

final class ConfigurableProduct extends Product {

    private final List<IProductFeature> features;

    public ConfigurableProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, IProductFeature... features) {

        this(article, name, price, quantity, rest, weight, volume,
                fragile, validateFeatures(features));
    }

    private ConfigurableProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, List<IProductFeature> validatedFeatures) {

        super(article, name, price, quantity, rest, weight, volume, fragile);
        this.features = validatedFeatures;
    }

    private static List<IProductFeature> validateFeatures(IProductFeature[] features) {

        Objects.requireNonNull(features);

        IProductFeature[] copy = features.clone();

        for (IProductFeature feature : copy) {
            if (feature == null) {
                throw new IllegalArgumentException(
                        "Характеристика товара не может быть null");
            }
        }

        return Collections.unmodifiableList(Arrays.asList(copy));
    }

    @Override
    public boolean requiresCooling() {

        for (IProductFeature feature : features) {
            if (feature.requiresCooling()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public double calculateCost() {

        double total = 0;

        for (IProductFeature feature : features) {
            total += feature.calculateExtraCost(this);
        }

        return total;
    }
}
