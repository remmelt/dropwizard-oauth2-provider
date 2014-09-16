package com.remmelt.examples.resources;


import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.remmelt.examples.core.AccessToken;
import com.remmelt.examples.core.User;
import com.remmelt.examples.db.AccessTokenDAO;
import com.remmelt.examples.db.UserDAO;
import com.sun.jersey.api.Responses;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/oauth2/token")
@Produces(MediaType.APPLICATION_JSON)
public class OAuth2Resource {
	private ImmutableList<String> allowedGrantTypes;
	private AccessTokenDAO accessTokenDAO;
	private UserDAO userDAO;

	public OAuth2Resource(ImmutableList<String> allowedGrantTypes, AccessTokenDAO accessTokenDAO, UserDAO userDAO) {
		this.allowedGrantTypes = allowedGrantTypes;
		this.accessTokenDAO = accessTokenDAO;
		this.userDAO = userDAO;

		log.info("Constructed OAuth2Resource with grant types {}", allowedGrantTypes);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postForToken(
			@FormParam("grant_type") String grantType,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("client_id") String clientId
	) {
		// Check if the grant type is allowed
		if (!allowedGrantTypes.contains(grantType)) {
			Response response = Response.status(Responses.METHOD_NOT_ALLOWED).build();
			throw new WebApplicationException(response);
		}

		// Try to find a user with the supplied credentials.
		Optional<User> user = userDAO.findUserByUsernameAndPassword(username, password);
		if (user == null || !user.isPresent()) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
		}

		// User was found, generate a token and return it.
		AccessToken accessToken = accessTokenDAO.generateNewAccessToken(user.get(), new DateTime());
		return accessToken.getAccessTokenId().toString();
	}

}
