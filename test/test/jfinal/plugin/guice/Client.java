package test.jfinal.plugin.guice;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Client {
	@Inject
	@Named("config.server.ip")
	private String IP;

	@Inject
	@Named("config.server.port")
	private int port;

	@Inject
	@Named("config.server.username")
	private String username;

	@Inject
	@Named("config.server.password")
	private String password;

	public void work() {
		System.out.println("ip=" + IP);
		System.out.println("port=" + port);
		System.out.println("username=" + username);
		System.out.println("password=" + password);
	}
}