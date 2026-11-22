
namespace laba1Nikitin
{
    internal sealed class FreezerRule : IStorageRule
    {
        private const double FreezerTemperature = -18;

        public bool CanStore(Product product)
        {
            if (product == null || !product.RequiredStorageTemperature.HasValue)
                return false;

            double requiredTemperature = product.RequiredStorageTemperature.Value;

            return requiredTemperature < 0 && FreezerTemperature <= requiredTemperature;
        }
    }
}
