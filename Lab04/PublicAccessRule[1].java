
/**
 * All users may have access to a resource.
 * 
 * @author Jordan Rios
 */
public class PublicAccessRule implements AccessRule {

	@Override
	public boolean canRead(User obj) {
		return true;
	}

	@Override
	public String toString() {
		return "anyone";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return 2;
	}

}
