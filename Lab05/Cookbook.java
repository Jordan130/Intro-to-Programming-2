import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * A book of recipes, organized by category.
 * 
 * @author Chad Hogg
 */
public class Cookbook {

	/** A collection of all recipes. */
	private Set<Recipe> recipes;
	
	/**
	 * Constructs a new cookbook with no recipes.
	 */
	public Cookbook() {
		recipes = new TreeSet<>();
	}
	
	/**
	 * Adds a recipe to the cookbook.
	 * 
	 * @param recipe The recipe.
	 */
	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * Gets a recipe from the cookbook.
	 * 
	 * @param name The name of the recipe.
	 * @return That recipe, or null if we do not have a recipe with that name.
	 */
	public Recipe getRecipe(String name) {
		Recipe result = null;
		Iterator<Recipe> iter = recipes.iterator();
		while(iter.hasNext() && result == null) {
			Recipe recipe = iter.next();
			if(recipe.getName().equals(name)) {
				result = recipe;
			}
		}
		return result;
	}

	/**
	 * Gets all the recipes you can make with what you have.
	 * 
	 * @param pantry A collection of all ingredients you have on hand.
	 * @return A collection of all recipes you can make with the ingredients you have.
	 */
	public Set<Recipe> getRecipesYouCanMake(Set<Ingredient> pantry) {
		Set<Recipe> canMake = new TreeSet<Recipe>();
		for(Recipe recipe : recipes) {
			if(recipe.canMake(pantry)) {
				canMake.add(recipe);
			}
		}
		return canMake;
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Recipe recipe : recipes) {
			result += recipe + "\n";
		}
		return result;
	}
}
