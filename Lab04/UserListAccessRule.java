import java.util.ArrayList;

/**
 * Represents a rule that says only certain people have access to a resource.
 * 
 * @author Jordan Rios
 */
public class UserListAccessRule implements AccessRule {

	private ArrayList<String> userNames;

	/**
	 * Constructs a UserListAccessRule object.
	 */
	public UserListAccessRule() {
		userNames = new ArrayList<>();
	}

	/**
	 * Adds a user to an array list of user names.
	 * 
	 * @param userName The user name you want to be added
	 */
	public void addUser(String userName) {
		if (!userNames.contains(userName)) {
			userNames.add(userName);
		}
	}

	/**
	 * Removes a user from an array list of user names.
	 * 
	 * @param userName The user name you want to be removed
	 */
	public void removeUser(String userName) {
		if (userNames.contains(userName)) {
			userNames.remove(userName);
		}
	}

	@Override
	public boolean canRead(User obj) {
		if (userNames.contains(obj.getUserName())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		if (userNames.size() == 0) {
			return "users named " + userNames.toString();
		} else {
			return "users named " + userNames.toString();
		}

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			UserListAccessRule other = (UserListAccessRule) obj;
			if (!userNames.equals(other.userNames)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public int hashCode() {
		return userNames.hashCode();
	}

}
