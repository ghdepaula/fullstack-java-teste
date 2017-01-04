package br.com.contabilizei.config;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.dto.TipoEmpresaDTO;
import br.com.contabilizei.dto.TipoImpostoDTO;
import br.com.contabilizei.services.AnexoService;
import br.com.contabilizei.services.TipoEmpresaService;
import br.com.contabilizei.services.TipoImpostoService;

public class App {

	private static final String CTX_BACK_END_APP = "/contabilizei";
	private static final String CTX_FRONT_END_APP = "/";
	private static final String PATH_FRONT_END_APP = "C:\\Users\\cliente\\Downloads\\fullstack-front-end";

	public static void main(String[] args) throws Exception, LifecycleException {
		App.start();
	}

	public static void start() throws ServletException, LifecycleException, MalformedURLException, Exception {

		// Define a folder to hold web application contents.
		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		// Define port number for the web application
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		// Bind the port to Tomcat server and enable naming
		tomcat.setPort(Integer.valueOf(webPort));
		
		// Define a web application ctxBack and bind web.xml file location.
		String backEndPath = new File(webappDirLocation).getAbsolutePath();
		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
		Context ctxBack = tomcat.addWebapp(CTX_BACK_END_APP , backEndPath);
		ctxBack.setConfigFile(configFile.toURI().toURL());
		ctxBack.setReloadable(true);
		
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
			System.out.println("Erro ao inicializar as configurações de Tipos de Empresa da aplicação !");
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