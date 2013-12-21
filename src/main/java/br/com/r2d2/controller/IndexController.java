/**
 * 
 */
package br.com.r2d2.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 26/11/2013 14:44:53
 * @version 0.0.0.0
 * 
 */
@Resource
public class IndexController {
	private Result result;

	public IndexController(Result result) {
		super();
		this.result = result;
	}

	@Path("/")
	public void index() {
		result.include("status","Ok");
	}
}
