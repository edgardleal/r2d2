/**
 * 
 */
package br.com.r2d2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 09:07:07
 * @version 0.0.0.0
 * 
 */
@Resource
public class ErrorController {
	private Result result;

	public ErrorController(Result result) {
		super();
		this.result = result;
	}

	public void view(Exception ex) {
		ex.printStackTrace();
        result.include("ex", ex);
	}
}
