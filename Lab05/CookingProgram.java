import java.util.Set;

/**
 * Demonstrates use of the Cookbook class.
 * 
 * @author Chad Hogg
 */
public class CookingProgram {

	/**
	 * Runs the program.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		Cookbook cookbook = buildCookbook();
		System.out.println("********** All recipes **********");
		System.out.println(cookbook);
		System.out.println();
		System.out.println();
		System.out.println("********** Doubled Chicken Noodle Soup **********");
		System.out.println(cookbook.getRecipe("Chicken Noodle Soup").getDoubled());
		System.out.println("********** What you can make with what you have **********");
		System.out.println("(Should be Grilled Cheese Sandwich and Toast.)");
		Set<Recipe> canMake = cookbook.getRecipesYouCanMake(buildPantry());
		for(Recipe recipe : canMake) {
			System.out.println("\t" + recipe.getName());
		}
	}
	
	/**
	 * Creates a small cookbook for demonstration purposes.
	 * 
	 * @return A cookbook with a few recipes.
	 */
	private static Cookbook buildCookbook() {
		Cookbook book = new Cookbook();

		Recipe cheeseQuesadillas = new Recipe("Cheese Quesadilla", "Appetizers");
		cheeseQuesadillas.addIngredient(new Ingredient(0.5, "cups", "corn oil", 1962));
		cheeseQuesadillas.addIngredient(new Ingredient(8, "", "flour tortillas", 80));
		cheeseQuesadillas.addIngredient(new Ingredient(2, "cups", "shredded cheddar", 455));
		cheeseQuesadillas.addIngredient(new Ingredient(0.5, "cups", "minced scallion", 32));
		cheeseQuesadillas.addIngredient(new Ingredient(0.25, "cups", "minced green chiles", 23));
		cheeseQuesadillas.addIngredient(new Ingredient(0.25, "cups", "salsa", 36));
		cheeseQuesadillas.addStep("Put 1 tablespoon of oil in a medium skillet over medium heat.  When hot, put a tortilla in the skillet.");
		cheeseQuesadillas.addStep("Top with a quarter of the cheese, scallion, chiles, and salsa, then with another tortilla.");
		cheeseQuesadillas.addStep("Cook until the cheese begins to melt, about 2 minutes.  Turn and cook until the cheese is melted and both sides are toasted, another 2 to 3 minutes.");
		cheeseQuesadillas.addStep("Cut into wedges and serve.");
		book.addRecipe(cheeseQuesadillas);
		
		Recipe chickenNoodleSoup = new Recipe("Chicken Noodle Soup", "Soups");
		chickenNoodleSoup.addIngredient(new Ingredient(4, "cups", "chicken stock", 86));
		chickenNoodleSoup.addIngredient(new Ingredient(2, "cups", "cooked egg noodles", 221));
		chickenNoodleSoup.addIngredient(new Ingredient(2, "cups", "diced cooked chicken meat", 335));
		chickenNoodleSoup.addIngredient(new Ingredient(0.5, "cups", "diced carrot", 53));
		chickenNoodleSoup.addIngredient(new Ingredient(0.5, "cups", "diced celery", 30));
		chickenNoodleSoup.addIngredient(new Ingredient(0.5, "cups", "diced onion", 64));
		chickenNoodleSoup.addIngredient(new Ingredient(1, "tbsp", "vegetable oil", 124));
		chickenNoodleSoup.addStep("Heat the oil in a saucepan.  Cook carrot, celery, and onion for 5 minutes.");
		chickenNoodleSoup.addStep("Add the stock and bring to a boil.");
		chickenNoodleSoup.addStep("Add noodles and chicken, stir.");
		book.addRecipe(chickenNoodleSoup);
		
		Recipe garlicBread = new Recipe("Garlic Bread", "Side Dishes");
		garlicBread.addIngredient(new Ingredient(10, "slides", "bread", 79));
		garlicBread.addIngredient(new Ingredient(2, "tbsp", "butter", 102));
		garlicBread.addIngredient(new Ingredient(1, "cloves", "garlic", 12));
		garlicBread.addStep("Lay slices of bread on baking sheet and spread butter on them.");
		garlicBread.addStep("Chop garlic into small pieces and sprinkle over bread.");
		garlicBread.addStep("Put under broiler for 1 minute.");
		book.addRecipe(garlicBread);
		
		Recipe grilledCheese = new Recipe("Grilled Cheese Sandwich", "Entrees");
		grilledCheese.addIngredient(new Ingredient(2, "slices", "bread", 79));
		grilledCheese.addIngredient(new Ingredient(2, "slices", "American cheese", 104));
		grilledCheese.addIngredient(new Ingredient(0.5, "tbsp", "butter", 102));
		grilledCheese.addStep("Heat a flat pan over low heat.");
		grilledCheese.addStep("Butter one side of bread and put that side down in pan.");
		grilledCheese.addStep("Add cheese slices, then butter other piece of bread and add with buttered side up.");
		grilledCheese.addStep("Cook for about 4 minutes, flip, and cook for another 2 minutes.");
		book.addRecipe(grilledCheese);

		Recipe prosciuttoAndMelon = new Recipe("Prosciutto and Melon", "Appetizers");
		prosciuttoAndMelon.addIngredient(new Ingredient(1, "", "canteloupe", 186));
		prosciuttoAndMelon.addIngredient(new Ingredient(10, "slices", "prosciutto", 249));
		prosciuttoAndMelon.addStep("Cut the melon into eighths, scrape out the seeds, and remove the rind.");
		prosciuttoAndMelon.addStep("Cut the melon slices into chunks.");
		prosciuttoAndMelon.addStep("Cut the prosciutto into pieces roughly the same size as the melon.");
		prosciuttoAndMelon.addStep("Use a toothpick to skewer a piece of prosciutto and a chunk of melon and repeat until all are used up.");
		book.addRecipe(prosciuttoAndMelon);
		
		Recipe sauteedChicken = new Recipe("Sauteed Chicken", "Entrees");
		sauteedChicken.addIngredient(new Ingredient(1, "lb", "thin-sliced chicken breasts", 480));
		sauteedChicken.addIngredient(new Ingredient(1, "", "egg", 78));
		sauteedChicken.addIngredient(new Ingredient(0.5, "cups", "bread crumbs", 427));
		sauteedChicken.addIngredient(new Ingredient(2, "tbsp", "olive oil", 119));
		sauteedChicken.addStep("Heat the oil in a skillet over medium heat.");
		sauteedChicken.addStep("Toss chicken in egg, then dredge in bread crumbs.");
		sauteedChicken.addStep("Cook for about 5 minutes, turning once.");
		book.addRecipe(sauteedChicken);
		
		Recipe toast = new Recipe("Toast", "Side Dishes");
		toast.addIngredient(new Ingredient(1, "slices", "bread", 79));
		toast.addIngredient(new Ingredient(0.25, "tbsp", "butter", 102));
		toast.addStep("Spread butter on one side of the bread.");
		toast.insertStep("Toast the bread.", 0);
		book.addRecipe(toast);
		
		return book;
	}
	
	/**
	 * Creates a small collection of ingredients you have for demonstration purposes.
	 * 
	 * @return A set of ingredients you have.
	 */
	private static Set<Ingredient> buildPantry() {
		Ingredient bread = new Ingredient(8, "slices", "bread", 79);
		Ingredient butter = new Ingredient(8, "tbsp", "butter", 102);
		Ingredient american = new Ingredient(4, "slices", "American cheese", 104);
		Ingredient garlic = new Ingredient(2, "cloves", "garlic", 12);
		return Set.of(bread, butter, american, garlic);
	}
}
