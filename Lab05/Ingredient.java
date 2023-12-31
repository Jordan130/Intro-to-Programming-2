
/**
 * Class that stores information a type of foodstuff that can be used in a recipe.
 * 
 * @author Maria Jump, Chad Hogg
 */
public class Ingredient implements Comparable<Ingredient> {

	/** The amount of the ingredient. */
	private double quantity;
	/** The unit of measures associated with the quantity. */
	private String unit;
	/** The name of the ingredient. */
	private String type;
	/** The amount of energy in one unit of the ingredient. */
	private int calories;

	/**
	 * Constructs a new Ingredient.
	 * 
	 * @param quantityValue
	 *            The initial quantity value.
	 * @param unitValue
	 *            The initial unit value.
	 * @param typeValue
	 *            The initial type value.
	 * @param caloriesValue
	 *            The initial calories values.
	 */
	public Ingredient(double quantityValue, String unitValue, String typeValue, int caloriesValue) {
		quantity = quantityValue;
		unit = unitValue;
		type = typeValue;
		calories = caloriesValue;
	}

	/**
	 * Gets the quantity of this ingredient.
	 * 
	 * @return The quantity.
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Gets the unit of measure for this ingredient.
	 * 
	 * @return The unit.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Gets the type of this ingredient.
	 * 
	 * @return The type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the amount of calories per unit for this ingredient.
	 * 
	 * @return The calories.
	 */
	public int getCalories() {
		return calories;
	}

	@Override
	public String toString() {
		String amount = quantity + " " + unit;
		return String.format("%16s %s", amount, type);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		Ingredient other = (Ingredient)obj;
		if(quantity != other.quantity || !unit.equals(other.unit) || !type.equals(other.type) || calories != other.calories) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(quantity) + unit.hashCode() * 7 + type.hashCode() * 19 + calories * 31;
	}
	
	@Override
	public int compareTo(Ingredient other) {
		int result = type.compareTo(other.type);
		if(result == 0) {
			result = unit.compareTo(other.unit);
			if(result == 0) {
				result = calories - other.calories;
				if(result == 0) {
					if(quantity < other.quantity) {
						result = -1;
					}
					else if(quantity > other.quantity) {
						result = 1;
					}
					else {
						result = 0;
					}
				}
			}
		}
		return result;
	}
}
