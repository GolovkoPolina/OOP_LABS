
namespace laba1Nikitin
{
    internal sealed class RefrigeratorRule : IStorageRule
    {
        private const double RefrigeratorTemperature = 4;

        public bool CanStore(Product product)
        {
            if (product == null)
                return false;

            if (product.RequiredStorageTemperature.HasValue)
                return RefrigeratorTemperature <= product.RequiredStorageTemperature.Value;

            return product.RequiresCooling;
        }
    }
}
