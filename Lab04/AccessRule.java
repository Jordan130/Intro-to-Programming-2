
/**
 * This interface checks whether or not a user has access to a resource.
 * 
 * @author Jordan Rios
 */
public interface AccessRule {

	/**
	 * Checks if you can read / have access to a certain object entered in by the user.
	 * 
	 * @param obj The object you want to see if you can read or not
	 * @return A boolean value whether or not the user has access
	 */
	public abstract boolean canRead(User obj);
}
