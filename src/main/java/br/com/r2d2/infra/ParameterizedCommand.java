/**
 * 
 */
package br.com.r2d2.infra;

/**
 * @author edgardleal
 * 
 */
public class ParameterizedCommand {
	private String command;
	private Object[] parameters;

	public ParameterizedCommand(String command, Object... parameters) {
		super();
		this.command = command;
		this.parameters = parameters;
	}

	public ParameterizedCommand(String command) {
		super();
		this.command = command;
		this.parameters = new Object[0];
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

}
