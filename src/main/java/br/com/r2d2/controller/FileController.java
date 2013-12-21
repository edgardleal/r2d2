/**
 * 
 */
package br.com.r2d2.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.r2d2.infra.Config;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 09:03:13
 * @version 0.0.0.0
 * 
 */
@Resource
public class FileController {

	private Result result;
	private Config config;

	public FileController(Result result, Config config) {
		super();
		this.config = config;
		this.result = result;
	}

	@Path("/file")
	public void navigate() {
		result.redirectTo(getClass()).navigate(null);
	}

	@Path("file/{path*}")
	public void navigate(String path) {
		try {

			File file;
			if (path != null && !path.isEmpty())
				file = new File(config.repositoryFolder(), path);
			else
				file = config.repositoryFolder();
			List<File> files = Arrays.asList(file.listFiles());
			String folderPath = file.getAbsolutePath().replace(
					config.repositoryFolder().getAbsolutePath(), "");
			result.include("status", "ok");
			result.include("paths", folderPath.split("/"));
			result.include("folder", folderPath);
			result.include("files", files);

		} catch (Exception ex) {
			result.redirectTo(ErrorController.class).view(ex);
		}
	}

	public void list(File file) {
		List<File> files = Arrays.asList(file.listFiles());
		result.include("status", "ok");
		result.include(
				"folder",
				file.getAbsolutePath().replace(
						config.repositoryFolder().getAbsolutePath(), ""));
		result.include("files", files);
	}

}
