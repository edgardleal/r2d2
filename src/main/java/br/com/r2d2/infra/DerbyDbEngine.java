/**
 * 
 */
package br.com.r2d2.infra;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PreDestroy;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * 
 * gerencia as transações com o banco derby
 * 
 * @author edgardleal
 * 
 */
@Component
@ApplicationScoped
public class DerbyDbEngine {
	private Connection con;
	private List<ParameterizedCommand> commands;
	private Thread executor;

	static {
		// new org.apache.derby.jdbc.ClientDriver();
		System.out.println(String.format("Carregando o driver Derby... "));
		new org.apache.derby.jdbc.EmbeddedDriver();
	}

	public DerbyDbEngine() {
		super();
		File file = new File(String.format("%s/.r2d2",
				System.getProperty("user.home")));
		if (!file.exists())
			file.mkdirs();

		commands = new ArrayList<ParameterizedCommand>();
		PreparedStatement stm = null;
		try {
			stm = getConnection().prepareStatement(
					"Select 1 from version where 1 = 2");
			stm.executeQuery();
		} catch (Exception e) {
			setup();
		} finally {
			if (stm != null)
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			close(con);
		}

	}

	private Connection getConnection() {
		try {
			con = DriverManager.getConnection(String.format(
					"jdbc:derby:%s/.r2d2/r2d2.db;create=true;",
					System.getProperty("user.home")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public DerbyDbEngine execute(String... command) {
		StringBuilder builder = new StringBuilder();
		for (String string : command) {
			builder.append(string);
		}
		synchronized (commands) {
			commands.add(new ParameterizedCommand(builder.toString()));
		}
		checkExecução();
		return this;
	}

	public DerbyDbEngine execute(ParameterizedCommand command) {
		synchronized (commands) {
			commands.add(command);
		}
		checkExecução();
		return this;
	}

	private void checkExecução() {
		if (executor != null)
			return;
		executor = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (commands) {
					while (commands.size() > 0) {
						executeImediate(commands.remove(commands.size() - 1));
					}
					executor = null;
				}
			}
		});
		executor.start();
	}

	private void setup() {
		try {
			executeImediate(new ParameterizedCommand( "create table groupid(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
					" groupid varchar(200), groupPath varchar(255),files integer, downloads integer, " +
					" constraint pk_group  primary key(id))"));
			executeImediate(new ParameterizedCommand("create table artifact(groupid integer, artifact varchar(100), " +
					"  constraint pk_artifact primary key(groupid, artifact) )"));
			executeImediate(new ParameterizedCommand("create table version(groupid integer, artifact varchar(100), version varchar(50),downloads double, " +
					" constraint pk_version  primary key(groupid, artifact, version) )"));
			executeImediate(new ParameterizedCommand("create table download(\"date\" timestamp,groupid integer,artifact varchar(100),version varchar(50), " +
					" constraint pk_download  primary key(\"date\", groupid, artifact, version) )"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private synchronized void executeImediate(ParameterizedCommand command) {
		PreparedStatement stm = null;
		try {
			stm = getConnection().prepareStatement(command.getCommand());

			for (int i = 1; i <= command.getParameters().length; i++)
				stm.setObject(i, command.getParameters()[i - 1]);

			stm.execute();
		} catch (Exception e) {
			System.out.println(String.format(
					"Erro ao executar o command: [%s]", command.getCommand()));
			e.printStackTrace();
		} finally {
			closeStatement(stm);
			close(con);
		}
	}

	private void close(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		con = null;
		this.con = null;
	}

	private void closeStatement(PreparedStatement stm) {
		try {
			if (stm != null)
				stm.close();
			stm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void finalize() throws Throwable {
		System.out.println(String.format("DerbyDbEngine finalizado pelo GC "));
		if (con != null) {
			con.close();
			System.out.println(String
					.format("Conexão com o derby finalizada pelo GC"));
		}
		super.finalize();
	}

	@PreDestroy
	public void close() {
		System.out.println(String
				.format("Finalizando a conexão com o banco de dados "));
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		con = null;
	}

	public static void main(String[] args) {
		DerbyDbEngine dbEngine = new DerbyDbEngine();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2013);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 31);
		c.set(Calendar.HOUR_OF_DAY, 9);
		for (int i = 0; i < 9000; i++) {
			dbEngine.executeImediate(new ParameterizedCommand(
					String.format(
							"INSERT INTO DW.DIM_TIME(\"HOUR\", \"DAY\",\"MONTH\", \"YEAR\") VALUES(%d,%d,%d,%d) ",
							c.get(Calendar.HOUR_OF_DAY),
							c.get(Calendar.DAY_OF_MONTH),
							c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR))));
			c.add(Calendar.HOUR, 1);
		}
	}

}