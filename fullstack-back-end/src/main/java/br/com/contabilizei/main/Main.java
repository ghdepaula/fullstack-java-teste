
package br.com.contabilizei.main;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.services.AnexoService;
import br.com.contabilizei.services.RegimesTributariosService;
import br.com.contabilizei.services.TributoService;

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
		initializeRegimeTributarioAndTributosSettings();
		
	}
	
	private static void initializeAnexoSettings(){
		
		AnexoDTO comercio = new AnexoDTO();
		comercio.setCodAnexo(1L);
		comercio.setDescricaoAnexo("Comércio");
		comercio.setAliquotaAnexo(6d);
		comercio.setStatusAnexo(Boolean.TRUE);
		
		AnexoDTO industria = new AnexoDTO();
		industria.setCodAnexo(2L);
		industria.setDescricaoAnexo("Indústria");
		industria.setAliquotaAnexo(8.5d);
		industria.setStatusAnexo(Boolean.TRUE);
		
		AnexoDTO servicos = new AnexoDTO();
		servicos.setCodAnexo(3L);
		servicos.setDescricaoAnexo("Prestação de serviços");
		servicos.setAliquotaAnexo(11d);
		servicos.setStatusAnexo(Boolean.TRUE);
		
		try {
			AnexoService service = new AnexoService();
			
			service.insert(comercio);
			service.insert(industria);
			service.insert(servicos);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Erro ao inicializar as configurações de Anexo da aplicação !");
		}
	}
	
	private static void initializeRegimeTributarioAndTributosSettings(){
		
		TributoDTO irpj = new TributoDTO();
		irpj.setCodTributo(1L);
		irpj.setDescricaoTributo("IRPJ");
		irpj.setAliquotaTributo(4.8d);
		
		TributoDTO iss = new TributoDTO();
		iss.setCodTributo(2L);
		iss.setDescricaoTributo("ISS");
		iss.setAliquotaTributo(2d);
		
		TributoDTO cofins = new TributoDTO();
		cofins.setCodTributo(3L);
		cofins.setDescricaoTributo("COFINS");
		cofins.setAliquotaTributo(3d);
		
		TributoDTO tributoSimplesNacional = new TributoDTO();
		tributoSimplesNacional.setCodTributo(4L);
		tributoSimplesNacional.setDescricaoTributo("SIMPLES NACIONAL");
		tributoSimplesNacional.setAliquotaTributo(0d);
		
		RegimeTributarioDTO simplesNacional = new RegimeTributarioDTO();
		simplesNacional.setDescricaoRegimeTributario("SIMPLES NACIONAL");
		simplesNacional.setEnabledAnexos(Boolean.TRUE);
		
		RegimeTributarioDTO lucroPresumido = new RegimeTributarioDTO();
		lucroPresumido.setDescricaoRegimeTributario("LUCRO PRESUMIDO");
		lucroPresumido.setEnabledAnexos(Boolean.FALSE);
		
		try {
			
			TributoService tributoService = new TributoService();
			RegimesTributariosService regimesTributariosService = new RegimesTributariosService();
			
			tributoService.insert(irpj);
			tributoService.insert(iss);
			tributoService.insert(cofins);
			tributoService.insert(tributoSimplesNacional);
			
			List<TributoDTO> tributosSimples = new ArrayList<TributoDTO>();
			List<TributoDTO> tributosLucroPresumido = new ArrayList<TributoDTO>();
			
			tributosSimples.add(tributoSimplesNacional);
			
			tributosLucroPresumido.add(irpj);
			tributosLucroPresumido.add(iss);
			tributosLucroPresumido.add(cofins);
			
			lucroPresumido.setTributos(tributosLucroPresumido);
			simplesNacional.setTributos(tributosSimples);
			
			regimesTributariosService.insert(simplesNacional);
			regimesTributariosService.insert(lucroPresumido);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Erro ao inicializar as configurações de Regime Tributário e Tributos da aplicacão !");
		}
		
	}

}