/**
 * 
 */
package br.com.r2d2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.r2d2.infra.Config;
import br.com.r2d2.infra.Logger;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 16:39:56
 * @version 0.0.0.0
 * 
 */
@Resource
public class RepositoryController {
	private Result result;
	private Config config;

	public RepositoryController(Result result, Config config) {
		super();
		this.result = result;
		this.config = config;
	}

	@Path("/repository/{group*}/{artifact}/{version}/{file}")
	public Download download(String group, String artifact, String version,
			String file) {
		try {
			Logger.info("Artifact required: [%s/%s/%s]", group, artifact,
					version);
			return new FileDownload(getJarFilePath(group, artifact, version,
					file), "application/java-archive", file);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.notFound();
			return null;
		}
	}

	private File getJarFilePath(String group, String artifact, String version,
			String file) {
		File localFile = new File(String.format("%s/%s/%s/%s/%s",
				config.repositoryFolder(), group, artifact, version, file));
		if (!localFile.exists())
			downloadAFile(group, artifact, version, file, localFile);
		return localFile;
	}

	private void downloadAFile(String group, String artifact, String version,
			String file, File localFile) {
		try {
			URL url = new URL(String.format("%s/%s/%s/%s/%s",
					Config.getRemoveServer(), group, artifact, version, file));

			Logger.info("Downloading from remote server : [%s/%s]",
					url.getHost(), url.getPath());
			Logger.info("Downloading to: [%s]", localFile.getAbsoluteFile());

			File dir = new File(localFile.getParent());
			if (!dir.exists())
				dir.mkdirs();
			IOUtils.copy(url.openStream(), new FileOutputStream(localFile));
			Logger.info("Downloaded: [%s]", url.getPath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
}
