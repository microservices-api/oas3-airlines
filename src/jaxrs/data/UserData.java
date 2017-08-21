package jaxrs.data;

import java.util.ArrayList;
import java.util.List;

import jaxrs.model.User;

public class UserData {
	static List<User> users = new ArrayList<User>();

	  static {
	    users.add(createUser(1, "Bob1", "Bob", "Smith",
	        "bobsm@test.com", "123-456-7890", 1));
	    users.add(createUser(2, "Martha2", "Martha", "Jones",
	        "marthaj@test.com", "123-456-7890", 2));
	    users.add(createUser(3, "Jess3", "Jessica", "Greene",
	        "jessgr@test.com", "123-456-7890", 3));
	    users.add(createUser(4, "Tom4", "Tom", "Brown",
	        "tomb@test.com", "123-456-7890", 1));
	    users.add(createUser(5, "Jack4", "Jack", "Thomson",
	        "jackth@test.com", "123-456-7890", 2));
	    users.add(createUser(6, "Helga5", "Helga", "Miller",
	        "helgam@test.com", "123-456-7890", 3));
	    users.add(createUser(7, "Demi6", "Demi", "Moore",
	        "demim@test.com", "123-456-7890", 1));
	    users.add(createUser(8, "Emma7", "Emma", "Watson",
	        "emmaw@test.com", "123-456-7890", 2));
	    users.add(createUser(9, "Sherlock8", "Sherlock", "Holmes",
	        "sherlockh@test.com", "123-456-7890", 3));
	    users.add(createUser(10, "Doctor9", "Doctor", "Frankenstein",
	        "franky@test.com", "123-456-7890", 1));
	    users.add(createUser(11, "Marry10", "Marry", "Shelly",
	        "marrysh@test.com", "123-456-7890", 1));

	  }

	  public User findUserByName(String username) {
	    for (User user : users) {
	      if (user.getUsername().equals(username)) {
	        return user;
	      }
	    }
	    return null;
	  }

	  public void addUser(User user) {
	    if(user.getUsername() == null)
	      return;
	    if (users.size() > 0) {
	      for (int i = users.size() - 1; i >= 0; i--) {
	        if (users.get(i).getUsername().equals(user.getUsername())) {
	          users.remove(i);
	        }
	      }
	    }
	    users.add(user);
	  }

	  public boolean removeUser(String username) {
	    if (users.size() > 0) {
	      for (int i = users.size() - 1; i >= 0; i--) {
	        if (users.get(i).getUsername().equals(username)) {
	          users.remove(i);
	          return true;
	        }
	      }
	    }
	    return false;
	  }

	  private static User createUser(int id, String username, String firstName,
	      String lastName, String email, String phone, int status) {
	    User user = new User();
	    user.setId(id);
	    user.setUsername(username);
	    user.setPassword("XXXXXX");
	    user.setFirstName(firstName);
	    user.setLastName(lastName);
	    user.setEmail(email);
	    user.setPhone(phone);
	    user.setUserStatus(status);

	    return user;
	  }
}
