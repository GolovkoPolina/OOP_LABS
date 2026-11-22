package first;
    final class FreezerRule implements IStorageRule
    {
        private static final double FreezerTemperature = -18.0;

        public boolean canStore(Product product)
        {
            if (product == null || product.getRequiredStorageTemperature() == null)
                return false;

            double requiredTemperature = product.getRequiredStorageTemperature();

            return requiredTemperature < 0 && FreezerTemperature <= requiredTemperature;
        }
    }

