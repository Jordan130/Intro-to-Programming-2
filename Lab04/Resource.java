import java.security.*;

/**
 * Represents something that we wish to control access to.
 * 
 * @author Jordan Rios
 */
public class Resource {

	private String resourceName;
	private String contents;
	private AccessRule accessRule;

	/**
	 * Constructs a new Resource object.
	 * 
	 * @param resourceName The name of the resource
	 * @param contents     The contents inside of the resource
	 * @param accessRule   The rule about who is authorized to read the resource
	 */
	public Resource(String resourceName, String contents, AccessRule accessRule) {
		this.resourceName = resourceName;
		this.contents = contents;
		this.accessRule = accessRule;
	}

	/**
	 * Gets the resource name.
	 * 
	 * @return The name of the resource
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Checks to see if the user is authorized to read the resource, if not it
	 * throws a AccessControlException.
	 * 
	 * @param obj The object you want to see if you can access the contents of
	 * @return The contents of the object
	 */
	public String getContents(User obj) {
		if (!accessRule.canRead(obj)) {
			throw new AccessControlException("This user is not authorized to read the resource.");
		} else {
			return contents;
		}
	}

	@Override
	public String toString() {
		String s = "";
		s += resourceName + ", with hidden contents, accessible";
		if (accessRule instanceof PublicAccessRule) {
			s += " accessible by anyone";
		} else if (accessRule instanceof GroupAccessRule) {
			s += " by members of group administrators";
		} else {
			s += " by special people that only exist during testing";
		}
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		} else {
			Resource other = (Resource) obj;
			if (!resourceName.equals(other.resourceName)) {
				return false;
			} else if (!contents.equals(other.contents)) {
				return false;
			} else if (!accessRule.equals(other.accessRule)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public int hashCode() {
		return 1 * resourceName.hashCode() + 10 * contents.hashCode() + 100 * accessRule.hashCode();
	}
}
