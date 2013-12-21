/**
 * 
 */
package br.com.r2d2.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.r2d2.model.UserModel;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 17:26:04
 * @version 0.0.0.0
 * 
 */
@Component
public class UserDao {
	private static UserModel defaultUser = new UserModel();
	static {
		defaultUser.setUserName("admin");
		defaultUser.setPassword("admin");
	}

	public boolean checkUser(UserModel userModel) {
		return defaultUser.equals(userModel);
	}
}
