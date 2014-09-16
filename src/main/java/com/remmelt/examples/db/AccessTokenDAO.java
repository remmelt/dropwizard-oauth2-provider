package com.remmelt.examples.db;

import com.google.common.base.Optional;
import com.remmelt.examples.core.AccessToken;
import com.remmelt.examples.core.User;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccessTokenDAO {
	private static Map<UUID, AccessToken> accessTokenTable = new HashMap<>();

	public Optional<AccessToken> findAccessTokenById(final UUID accessTokenId) {
		AccessToken accessToken = accessTokenTable.get(accessTokenId);
		if (accessToken == null) {
			return Optional.absent();
		}
		return Optional.of(accessToken);
	}

	public AccessToken generateNewAccessToken(final User user, final DateTime dateTime) {
		AccessToken accessToken = new AccessToken(UUID.randomUUID(), user.getId(), dateTime);
		accessTokenTable.put(accessToken.getAccessTokenId(), accessToken);
		return accessToken;
	}

	public void setLastAccessTime(final UUID accessTokenUUID, final DateTime dateTime) {
		AccessToken accessToken = accessTokenTable.get(accessTokenUUID);
		AccessToken updatedAccessToken = accessToken.withLastAccessUTC(dateTime);
		accessTokenTable.put(accessTokenUUID, updatedAccessToken);
	}
}
