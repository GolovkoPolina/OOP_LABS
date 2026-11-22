
namespace laba1Nikitin
{
    internal interface IProductFeature
    {
        double CalculateExtraCost(Product product);
        bool RequiresCooling { get; }
    }
}
