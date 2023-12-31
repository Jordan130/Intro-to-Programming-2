import java.util.ArrayList;

/**
 * This class creates a user who will represent a person who has permission to use our system.
 * 
 * @author Jordan Rios
 **/
public class User {

	private String userName;
	private ArrayList<String> groups;

	/**
	 * This constructor creates a user that has a user name, and an empty ArrayList of groups they're associated with.
	 * 
	 * @param userName The user name
	 */
	public User(String userName) {
		this.userName = userName;
		groups = new ArrayList<>();
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Checking a certain group to see if its a member of the groups.
	 * 
	 * @param group The group you want to check to see if its a member of the current groups
	 * @return true if the array list groups contains the group the user entered, if not it will return false
	 */
	public boolean isInGroup(String group) {
		if (groups.contains(group)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds a group to the array list groups.
	 * 
	 * @param group The group you want to add to the array list groups
	 */
	public void addToGroup(String group) {
		if (!groups.contains(group)) {
			groups.add(group);
		}
	}

	/**
	 * Removes a certain group the user enters from the groups array list.
	 * 
	 * @param group The group that you want to remove from the array list called groups
	 */
	public void removeFromGroup(String group) {
		if (groups.contains(group)) {
			groups.remove(group);
		}
	}

	@Override
	public String toString() {
		if (groups.size() == 0) {
			return userName + " is a member of no groups.";
		} else {
			return userName + " is a member of " + groups + ".";
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
			User other = (User) obj;
			if (userName != other.userName) {
				return false;
			}
			if (!groups.equals(other.groups)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public int hashCode() {
		return 1 * userName.hashCode() + 10 * groups.hashCode();
	}

}
