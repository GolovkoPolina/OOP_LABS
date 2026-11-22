
package first;

 public abstract class Product
    {
        private final String article;
        private final String name;
        private double price;
        private int quantity;
        private final int rest;
        private Dimensions dimensions;
        private static int createdProducts;

        public static final double VAT = 0.13;

        protected Product(
            String article,
            String name,
            double price,
            int quantity,
            int rest,
            double weight,
            double volume,
            boolean fragile)
        {
            if (article == null || article.isBlank())
                throw new IllegalArgumentException("Артикул не может быть пустым");

            if (name == null || name.isBlank())
                throw new IllegalArgumentException("Название не может быть пустым");

            if (Double.isNaN(price) || Double.isInfinite(price) || price <= 0)
                throw new IllegalArgumentException("Цена должна быть больше нуля");

            if (quantity < 0)
                throw new IllegalArgumentException("Количество не может быть отрицательным");
            if (rest < 0)
                throw new IllegalArgumentException("Минимальный остаток не может быть отрицательным");

            Dimensions newDimensions = new Dimensions(weight, volume, fragile);

            this.article = article;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.rest = rest;
            dimensions = newDimensions;
            createdProducts++;
        }

        protected Product(
            String article,
            String name,
            double price,
            double weight,
            double volume,
            boolean fragile)
        {
            this(article, name, price, 0, 0, weight, volume, fragile);
        }

        public String getArticle()
        {
          return article;
        } 

        public String getName()
        {
          return name;
        } 

        public double getPrice()
        { 
            return price;
        }

        public int getQuantity()
        { 
            return quantity;
        }

        public int getRest() 
        { 
            return rest;
        }

        public Dimensions getDimensions()
        { return new Dimensions(
            dimensions.getWeight(), dimensions.getVolume(), dimensions.isFragile());
        }

        public boolean requiresCooling() 
        { 
            return false;
        } ///требуется ли охл но по умолч нет
        public Double getRequiredStorageTemperature()
        {
            return null; ////double? может хранить число или null
        }
        public static int getCreatedProducts()
        {
            return createdProducts;
        }

        public abstract double calculateCost();

        public double calculateStorageCost()
        {
            return dimensions.getVolume() * 100 + calculateCost();
        }

        public double priceWithTax()
        {
            return priceWithTax(VAT);
        }

        public double priceWithTax(double rate)
        {
            if (Double.isNaN(rate) || Double.isInfinite(rate) || rate < 0)
                throw new IllegalArgumentException("Ставка налога не может быть отрицательной");

            return price * (1 + rate);
        }

        public void changePrice(double newPrice)
        {
            if (Double.isNaN(newPrice) || Double.isInfinite(newPrice) || newPrice <= 0)
                throw new IllegalArgumentException("Цена должна быть больше нуля");

            price = newPrice;
        }

        public void dose(int amount)
        {
            if (amount <= 0)
                throw new IllegalArgumentException("Количество должно быть больше нуля");

           quantity = Math.addExact(quantity, amount);
        }

        public void ship(int amount)
        {
            if (amount <= 0)
                throw new IllegalArgumentException("Количество для отгрузки должно быть больше нуля");

            if (amount > quantity)
                throw new IllegalStateException("Нельзя отгрузить больше, чем есть на складе");

            quantity -= amount;
        }

        public void changeDimensions(double weight, double volume, boolean fragile)
        {
            dimensions = new Dimensions(weight, volume, fragile);
        }

         @Override
        public final String toString()
        {
            return String.format(
                "Артикул: %s, Название: %s, " +
        "Цена: %.2f, Количество: %d, " +
        "Минимальный остаток: %d, " +
        "Вес: %.2f, Объём: %.2f, " +
        "Хрупкий: %b",
        article,
        name,
        price,
        quantity,
        rest,
        dimensions.getWeight(),
        dimensions.getVolume(),
        dimensions.isFragile()
            );
        }
    }

