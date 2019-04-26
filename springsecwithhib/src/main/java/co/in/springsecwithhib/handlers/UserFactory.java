package co.in.springsecwithhib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.in.springsecwithhib.model.User;
import co.in.springsecwithhib.model.UserSecurity;

public class UserFactory {

	private UserFactory() {

	}

	public static UserSecurity create(final User user) {

		return new UserSecurity(user, getAuthFromUser(user));
	}

	public static List<String> getAuthFromUser(final User user) {

		final List<String> auths = new ArrayList<>();
		user.getRoles().forEach(role -> role.getAuthorities().forEach(auth -> auths.add(auth.getName())));
		auths.stream().distinct().collect(Collectors.toList());
		return auths;
	}

}
