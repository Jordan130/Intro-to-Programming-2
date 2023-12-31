import java.util.*;

public class Recipe implements Comparable<Recipe> {

	private String name;
	private String category;
	private Set<Ingredient> ingredients;
	private List<String> instructions;

	public Recipe(String name, String category) {
		this.name = name;
		this.category = category;
		ingredients = new HashSet<>();
		instructions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public void addIngredient(Ingredient obj) {
		ingredients.add(obj);
	}

	public void addStep(String step) {
		instructions.add(step);
	}

	public void insertStep(String step, int number) {
		instructions.add(number, step);
	}

	public double getTotalCalories() {
		double total = 0.0;
		for(Ingredient i: ingredients) {
			double q = i.getQuantity();
			total += i.getCalories() * q;
		}
		return total;
	}

	public Recipe getDoubled() {
		Recipe copy = new Recipe(name, category);
		copy.instructions.addAll(instructions);
		Set<Ingredient> temp = new HashSet<>();
		Iterator<Ingredient> iter = ingredients.iterator();
		while (iter.hasNext()) {
			Ingredient i = iter.next();
			Ingredient fake = new Ingredient(i.getQuantity() * 2, i.getUnit(), i.getType(), i.getCalories());
			temp.add(fake);
		}
		copy.ingredients.addAll(temp);
		return copy;

	}

	public boolean canMake(Set<Ingredient> pantry) {
		int count = 0;
		boolean result = false;
		for (Ingredient ing : ingredients) {
			for (Ingredient pantryIng : pantry) {
				if (pantryIng.getType().equals(ing.getType()) && pantryIng.getUnit().equals(ing.getUnit())
						&& pantryIng.getQuantity() >= ing.getQuantity()) {
					count++;
				}

				if (count == ingredients.size()) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return 1 * name.hashCode() + 10 * category.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		} else {
			Recipe other = (Recipe) obj;
			if (!name.equals(other.name)) {
				return false;
			} 
			else if (!category.equals(other.category)) {
				return false;
			}
			else {
				return true;
			}
		}
	}

	@Override
	public int compareTo(Recipe other) {
		if(category.compareTo(other.category) > 0) {
			return 1;
		}
		if(category.compareTo(other.category) < 0) {
			return -1;
		}
		else {
			if(name.compareTo(other.name) > 0) {
				return 1;
			}
			if(name.compareTo(other.name) < 0) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\nCategory: " + category + "\n\n\tIngredients: " + "idk" + "\n\n\tInstructions: " + "idk";
	}
		
}
