/**
 * 
 */
package br.com.r2d2.infra.security;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.r2d2.model.UserModel;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 17:29:00
 * @version 0.0.0.0
 * 
 */
@Component
@RequestScoped
public class LogedUser implements Serializable {
	private static long logedUserCount = 0L;

	private static final long serialVersionUID = 1L;
	private UserModel user;
	private boolean loged;

	public void login(UserModel userModel) {
		user = new UserModel();
		user.setUserName(new String(userModel.getUserName()));
		user.setPassword(new String(userModel.getPassword()));
		loged = true;// make it to be used by reflection
		synchronized (this) {
			System.out.println(++logedUserCount + " users");
		}
	}

	public void logout() {
		user = null;
		loged = false;// make it to be used by reflection
		synchronized (this) {
			System.out.println(--logedUserCount + " users[logout]");
		}
	}

	@Override
	public void finalize() throws Throwable {
		if (loged)
			logout();
		super.finalize();
	}

	public boolean isLoged() {
		return loged;
	}

}
