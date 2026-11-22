package first;
	final class FreezeProduct extends Product {
		public FreezeProduct(String article,
			String name,
			double price,
			int quantity,
			int rest, 
			double weight, 
			double volume,
			boolean fragile) 
		{
          super(article, name, price, quantity, rest, weight, volume, fragile);
		}
        @Override 
		public boolean requiresCooling(){
            return true;
        }
        @Override 
		public Double getRequiredStorageTemperature(){
            return -18.0;
        }
        @Override 
		public double calculateCost()
		{
			return 888;
		}
	}