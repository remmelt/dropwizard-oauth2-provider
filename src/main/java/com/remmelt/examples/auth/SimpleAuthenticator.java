package com.remmelt.examples.auth;

import com.google.common.base.Optional;
import com.remmelt.examples.core.AccessToken;
import com.remmelt.examples.db.AccessTokenDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.UUID;

@AllArgsConstructor
public class SimpleAuthenticator implements Authenticator<String, Long> {
	public static final int ACCESS_TOKEN_EXPIRE_TIME_MIN = 30;
	private AccessTokenDAO accessTokenDAO;

	@Override
	public Optional<Long> authenticate(String accessTokenId) throws AuthenticationException {
		// Check input, must be a valid UUID
		UUID accessTokenUUID;
		try {
			accessTokenUUID = UUID.fromString(accessTokenId);
		} catch (IllegalArgumentException e) {
			return Optional.absent();
		}

		// Get the access token from the database
		Optional<AccessToken> accessToken = accessTokenDAO.findAccessTokenById(accessTokenUUID);
		if (accessToken == null || !accessToken.isPresent()) {
			return Optional.absent();
		}

		// Check if the last access time is not too far in the past (the access token is expired)
		Period period = new Period(accessToken.get().getLastAccessUTC(), new DateTime());
		if (period.getMinutes() > ACCESS_TOKEN_EXPIRE_TIME_MIN) {
			return Optional.absent();
		}

		// Update the access time for the token
		accessTokenDAO.setLastAccessTime(accessTokenUUID, new DateTime());

		// Return the user's id for processing
		return Optional.of(accessToken.get().getUserId());
	}
}
