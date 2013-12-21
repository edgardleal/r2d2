/**
 * 
 */
package br.com.r2d2.model;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.r2d2.infra.Config;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 29/11/2013 07:50:59
 * @version 0.0.0.0
 * 
 */
public class ArtifactModel {
	private String groupId;
	private String artifactId;
	private String version;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ArtifactModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtifactModel(String groupId, String artifactId, String version) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	public String getGroupIdPath() {
		StringBuilder builder = new StringBuilder('/');
		for (String s : groupId.split("\\.")) {
			builder.append(s).append('/');
		}
		return builder.toString();
	}

	public String getUrlPath() {
		return String.format("/%s/%s/%s-%s.jar", getGroupIdPath(),
				getArtifactId(), getArtifactId(), getVersion());
	}
	
	public String getFilePath(){
		String mavenDir = new Config().repositoryFolder().getAbsolutePath();
		return String.format("%s%s", mavenDir, getUrlPath());
	}
	
	public URL getURL(String host, String protocol){
		try {
			if(!host.endsWith("/"))
				host = String.format("%s/", host);
			return new URL(String.format("%s%s%s", protocol, host, getUrlPath()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artifactId == null) ? 0 : artifactId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtifactModel other = (ArtifactModel) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
