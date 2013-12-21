/**
 * 
 */
package br.com.r2d2.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.view.Results;
import br.com.r2d2.infra.Config;
import br.com.template.DefaultController;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 26/11/2013 14:16:14
 * @version 0.0.0.0
 * 
 */
@Resource
public class LibController extends DefaultController {

	private Config config;

	public LibController(Result result, Validator validator, Config config) {
		super(result, validator);
		this.config = config;
	}

	@Path("/lib/{group*}/{artifact}/{version}")
	public Download get(String group, String artifact, String version) {
		try {
			String fileName = getJarFileName(artifact, version);
			return new FileDownload(getJarFilePath(group, artifact, version),
					"application/java-archive", fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.redirectTo(ErrorController.class).view(ex);
			return null;
		}
	}

	private File getJarFilePath(String group, String artifact, String version) {
		return new File(String.format("%s/%s/%s/%s/%s",
				config.repositoryFolder(), group, artifact, version,
				getJarFileName(artifact, version)));
	}

	private String getJarFileName(String artifact, String version) {
		return String.format("%s-%s.jar", artifact, version);
	}

	public void status() {
		result.use(Results.http()).body("Ok");
	}

	@Override
	public void ajaxList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajaxGet(String codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajaxCombo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void view(String codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<?> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String codigo) throws SQLException {
		// TODO Auto-generated method stub

	}

}
