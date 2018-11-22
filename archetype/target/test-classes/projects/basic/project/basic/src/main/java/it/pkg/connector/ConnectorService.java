package it.pkg.connector;

import com.citibanamex.bne.jdbcclient.service.JdbcService;
import it.pkg.services.ProxyService;
import com.citibanamex.bne.tuxedoclient.service.TuxedoService;

public interface ConnectorService { 
	public JdbcService getJdbcService();
	public TuxedoService getTuxedoService();
	public ProxyService getProxyService();
}
