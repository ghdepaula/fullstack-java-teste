package br.com.contabilizei.main;

import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.glassfish.jersey.servlet.ServletContainer;
import br.com.contabilizei.config.RestApplication;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.dto.TipoEmpresaDTO;
import br.com.contabilizei.dto.TipoImpostoDTO;
import br.com.contabilizei.services.AnexoService;
import br.com.contabilizei.services.TipoEmpresaService;
import br.com.contabilizei.services.TipoImpostoService;

public class Main {

	private static final String CTX_BACK_END_APP = "/contabilizei";
	private static final String CTX_FRONT_END_APP = "/";
	private static final String PATH_FRONT_END_APP = "C:\\Users\\cliente\\workspace\\fullstack-front-end";

	public static void main(String[] args) throws Exception, LifecycleException {
		Main.start();
	}

	public static void start() throws ServletException, LifecycleException, MalformedURLException, Exception {

		String appBase = ".";
		Tomcat tomcat = new Tomcat();
		tomcat.getHost().setAppBase(appBase);

		// Define port number for the web application
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8081";
		}
		// Bind the port to Tomcat server and enable naming
		tomcat.setPort(Integer.valueOf(webPort));
		
		// Define a web application ctxBack and bind web.xml file location.
		Context ctxBack = tomcat.addWebapp(CTX_BACK_END_APP , appBase);
		ctxBack.setReloadable(true);
		
	    ServletContainer servletContainer = new ServletContainer(new RestApplication());
	    
	    Tomcat.addServlet(ctxBack, "jersey-servlet", servletContainer);
	    
	    ctxBack.addServletMapping("/rest/", "jersey-servlet");
	    
	    FilterDef corsFilter = new FilterDef();
	    corsFilter.setFilterName(CorsFilter.class.getName());
	    corsFilter.setFilterClass(CorsFilter.class.getName());
	    corsFilter.addInitParameter("cors.allowed.origins", "*");
	    corsFilter.addInitParameter("cors.allowed.methods", "GET,POST,HEAD,OPTIONS,PUT,DELETE");
	    corsFilter.addInitParameter("cors.allowed.headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
	    corsFilter.addInitParameter("cors.exposed.headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
	    corsFilter.addInitParameter("cors.support.credentials", "true");
	    corsFilter.addInitParameter("cors.preflight.maxage", "10");
	    ctxBack.addFilterDef(corsFilter);
	    
	    FilterMap filterMap = new FilterMap();
	    filterMap.setFilterName(CorsFilter.class.getName());
	    filterMap.addURLPattern("/*");
	    
	    ctxBack.addFilterMap(filterMap);
		
		// Define a web application ctxFront.
		Context ctxFront = tomcat.addWebapp(CTX_FRONT_END_APP , PATH_FRONT_END_APP);
		ctxFront.addWelcomeFile("index.html");
		ctxFront.setReloadable(true);

		//Initialize server
		tomcat.start();
		defineApplicationDefaultSettings();
		tomcat.getServer().await();
	}
	
	public static void defineApplicationDefaultSettings(){
		
		initializeAnexoSettings();
		initializeTipoEmpresaSettings();
		initializeTipoImpostoSettings();
		
	}
	
	private static void initializeAnexoSettings(){
		
		AnexoDTO anexo1 = new AnexoDTO();
		anexo1.setCodAnexo(1L);
		anexo1.setDescricaoAnexo("Comércio");
		anexo1.setAliquotaAnexo(6d);
		anexo1.setStatusAnexo(Boolean.TRUE);
		
		AnexoDTO anexo2 = new AnexoDTO();
		anexo2.setCodAnexo(2L);
		anexo2.setDescricaoAnexo("Indústria");
		anexo2.setAliquotaAnexo(8.5d);
		anexo2.setStatusAnexo(Boolean.TRUE);
		
		AnexoDTO anexo3 = new AnexoDTO();
		anexo3.setCodAnexo(3L);
		anexo3.setDescricaoAnexo("Prestação de serviços");
		anexo3.setAliquotaAnexo(11d);
		anexo3.setStatusAnexo(Boolean.TRUE);
		
		try {
			AnexoService service = new AnexoService();
			
			service.insert(anexo1);
			service.insert(anexo2);
			service.insert(anexo3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Erro ao inicializar as configurações de Anexo da aplicação !");
		}
	}
	
	private static void initializeTipoEmpresaSettings(){
		
		TipoEmpresaDTO tipoEmpresa1 = new TipoEmpresaDTO();
		tipoEmpresa1.setCodTipoEmpresa(1L);
		tipoEmpresa1.setDescricaoTipoEmpresa("SIMPLES NACIONAL");
		tipoEmpresa1.setStatusTipoEmpresa(Boolean.TRUE);
		
		TipoEmpresaDTO tipoEmpresa2 = new TipoEmpresaDTO();
		tipoEmpresa2.setCodTipoEmpresa(1L);
		tipoEmpresa2.setDescricaoTipoEmpresa("LUCRO PRESUMIDO");
		tipoEmpresa2.setStatusTipoEmpresa(Boolean.TRUE);
		
		try {
			TipoEmpresaService service = new TipoEmpresaService();
			service.insert(tipoEmpresa1);
			service.insert(tipoEmpresa2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Erro ao inicializar as configurações de Tipos de Empresa da aplicacão !");
		}
		
	}
	
	private static void initializeTipoImpostoSettings(){
		
		TipoImpostoDTO tipoImposto1 = new TipoImpostoDTO();
		tipoImposto1.setCodTipoImposto(1L);
		tipoImposto1.setDescricaoTipoImposto("IRPJ");
		tipoImposto1.setAliquotaTipoImposto(4.8d);
		tipoImposto1.setStatusTipoImposto(Boolean.TRUE);
		
		TipoImpostoDTO tipoImposto2 = new TipoImpostoDTO();
		tipoImposto2.setCodTipoImposto(1L);
		tipoImposto2.setDescricaoTipoImposto("IRPJ");
		tipoImposto2.setAliquotaTipoImposto(4.8d);
		tipoImposto1.setStatusTipoImposto(Boolean.TRUE);
		
		TipoImpostoDTO tipoImposto3 = new TipoImpostoDTO();
		tipoImposto3.setCodTipoImposto(1L);
		tipoImposto3.setDescricaoTipoImposto("IRPJ");
		tipoImposto3.setAliquotaTipoImposto(4.8d);
		tipoImposto1.setStatusTipoImposto(Boolean.TRUE);
		
		try {
			
			TipoImpostoService service = new TipoImpostoService();
			service.insert(tipoImposto1);
			service.insert(tipoImposto2);
			service.insert(tipoImposto3);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Erro ao inicializar as configurações de Tipos de Imposto da aplicação !");
		}
		
	}

}