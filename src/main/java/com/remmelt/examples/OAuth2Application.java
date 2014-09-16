package com.remmelt.examples;

import com.remmelt.examples.auth.SimpleAuthenticator;
import com.remmelt.examples.db.AccessTokenDAO;
import com.remmelt.examples.db.UserDAO;
import com.remmelt.examples.health.PingHealthCheck;
import com.remmelt.examples.resources.OAuth2Resource;
import com.remmelt.examples.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.auth.oauth.OAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.joda.time.DateTimeZone;

public class OAuth2Application extends Application<Oauth2Configuration> {
	public static void main(String[] args) throws Exception {
		new OAuth2Application().run(args);
	}

	@Override
	public String getName() {
		return "oauth2-provider";
	}

	@Override
	public void initialize(Bootstrap<Oauth2Configuration> oauth2ConfigurationBootstrap) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
	}

	@Override
	public void run(Oauth2Configuration configuration, Environment environment) throws Exception {
		// Create DAOs
		AccessTokenDAO accessTokenDAO = new AccessTokenDAO();
		UserDAO userDAO = new UserDAO();

		// Register Resources
		environment.jersey().register(new PingResource());
		environment.jersey().register(new OAuth2Resource(configuration.getAllowedGrantTypes(), accessTokenDAO, userDAO));

		// Register health checks
		environment.healthChecks().register("Ping health check", new PingHealthCheck(new PingResource()));

		// Register security component
		environment.jersey().register(new OAuthProvider<>(new SimpleAuthenticator(accessTokenDAO), configuration.getBearerRealm()));
	}
}
