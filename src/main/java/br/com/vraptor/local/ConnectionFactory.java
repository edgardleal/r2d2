/**
 * 
 */
package br.com.vraptor.local;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PreDestroy;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

/**
 * 
 */
@Component
@RequestScoped
public class ConnectionFactory implements ComponentFactory<Connection> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static int leaveConnectionsCount = 0;
	private Connection con;

	@Override
	public Connection getInstance() {
		if (con == null) {
			init();
		}
		return con;
	}

	public void init() {
		try {
			DataSource ds = (DataSource) new InitialContext()
					.lookup("java:/Sistema");
			con = ds.getConnection();

			synchronized (this) {
				leaveConnectionsCount++;
			}
		} catch (NamingException | SQLException e) {
			logger.error("Erro ao obter a conexo com o banco", e);
		}
	}

	@PreDestroy
	public void close() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
				synchronized (this) {
					logger.info(String.format(
							"Conexão fechada. Conexões pendentes: [%s]",
							--leaveConnectionsCount));
				}
			}
			con = null;
		} catch (SQLException e) {
			logger.error("Erro ao fechar a conexo", e);
		}
	}
}
