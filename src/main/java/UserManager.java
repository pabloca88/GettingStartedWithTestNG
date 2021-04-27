import java.util.ArrayList;
import java.util.Collection;

public class UserManager {

	private Collection<String> userStorage;

	public UserManager() {
		userStorage = new ArrayList<>();
	}

	public boolean addUser(String userEmail) {


		return userStorage.add(userEmail);
	}

	public String getUser(String userEmail) {
		for (String s : userStorage) {
			if (s.equals(userEmail)){
				return s;
			}
		}
		return null;
	}

	public boolean deleteUser(final String userMail) {
		return userStorage.removeIf(e -> e.equals(userMail));
	}


}
