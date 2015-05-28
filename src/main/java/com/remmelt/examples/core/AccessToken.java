package com.remmelt.examples.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AccessToken {
	@JsonProperty("access_token_id")
	@NotNull
	private UUID accessTokenId;

	@JsonProperty("user_id")
	@NotNull
	private Long userId;

	@JsonProperty("last_access_utc")
	@NotNull
	private DateTime lastAccessUTC;

	public AccessToken(UUID tokenId, Long userId, DateTime lastAccessUTC) {
		this.accessTokenId = tokenId;
		this.userId = userId;
		this.lastAccessUTC = lastAccessUTC;
	}

	public AccessToken withLastAccessUTC(DateTime dateUTC) {
		this.lastAccessUTC = dateUTC;
		return this;
	}

	public UUID getAccessTokenId() {
		return accessTokenId;
	}

	public void setAccessTokenId(UUID accessTokenId) {
		this.accessTokenId = accessTokenId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public DateTime getLastAccessUTC() {
		return lastAccessUTC;
	}

	public void setLastAccessUTC(DateTime lastAccessUTC) {
		this.lastAccessUTC = lastAccessUTC;
	}
}