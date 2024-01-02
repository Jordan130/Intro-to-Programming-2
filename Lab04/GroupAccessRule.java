
/**
 * All members of a particular group may have access to a resource.
 * 
 * @author Jordan Rios
 */
public class GroupAccessRule implements AccessRule {

	private String groupName;

	/**
	 * Constructs a new GroupAccessRule object.
	 * 
	 * @param groupName
	 */
	public GroupAccessRule(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public boolean canRead(User obj) {
		if (obj.isInGroup(groupName)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "members of group " + groupName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		} else {
			GroupAccessRule other = (GroupAccessRule) obj;
			if (!groupName.equals(other.groupName)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public int hashCode() {
		return groupName.hashCode();
	}
}
