/**
 * 
 */
package br.com.r2d2.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.r2d2.dao.UserDao;
import br.com.r2d2.infra.security.LogedUser;
import br.com.r2d2.model.UserModel;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 17:23:27
 * @version 0.0.0.0
 * 
 */
@Resource
public class LoginController {
	private UserDao userDao;
	private LogedUser logedUser;
	private Result result;

	public LoginController(UserDao userDao, LogedUser logedUser, Result result) {
		super();
		this.result = result;
		this.logedUser = logedUser;
		this.userDao = userDao;
	}

	@Path({ "/login", "/login/" })
	public void login() {

	}

	@Post
	public void autenticar(UserModel userModel) {
		if (userDao.checkUser(userModel)) {
			logedUser.login(userModel);
			result.redirectTo(IndexController.class).index();
		} else
			result.redirectTo(getClass()).login();
	}
}
