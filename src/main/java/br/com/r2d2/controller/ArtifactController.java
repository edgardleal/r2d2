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
 * @since 29/11/2013 11:07:01
 * @version 0.0.0.0
 * 
 */
@Resource
public class ArtifactController {
	private Result result;

	public ArtifactController(Result result) {
		super();
		this.result = result;
	}

	@Path({ "/artifact/{groupId*}/{artifact}/{version}",
			"/artifact/{groupId*}/{artifact}/{version}/" })
	public void view(String groupId, String artifact, String version) {
		try {
			result.include("download", String.format("%s/%s/%s/%s-%s.jar",
					groupId, artifact, version, artifact, version));
			result.include("groupId", groupId.replace('/', '.'));
			result.include("artifact", artifact);
			result.include("version", version);
		} catch (Exception ex) {
			result.redirectTo(ErrorController.class).view(ex);
		}
	}
	
	public void register(){
		
	}
}
