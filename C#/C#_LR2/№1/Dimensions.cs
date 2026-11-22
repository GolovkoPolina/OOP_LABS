
using System;

namespace laba1Nikitin
{
    internal sealed class Dimensions
    {
        public double Weight { get; }
        public double Volume { get; }
        public bool Fragile { get; }

        public Dimensions(double weight, double volume, bool fragile)
        {
            if (double.IsNaN(weight) || double.IsInfinity(weight) || weight <= 0)
                throw new ArgumentException("Вес должен быть больше нуля", nameof(weight));

            if (double.IsNaN(volume) || double.IsInfinity(volume) || volume <= 0)
                throw new ArgumentException("Объём должен быть больше нуля", nameof(volume));

            Weight = weight;
            Volume = volume;
            Fragile = fragile;
        }
    }
}
