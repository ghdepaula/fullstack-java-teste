# Desafio de Notas Fiscais e Cálculo de Impostos

## Tecnologias e ferramentas utilizadas

**Back-End** 
* Java 8
* Jersey Framework - 2.14
* JAX-RS - 2.0.1
* Hibernate - 5.1.0.Final
* Tomcat 8 Embdded - 8.0.15
* HSQLDB - 1.8.0.7
* Maven

**Front-End**
* HTML5
* Bootstrap - 3.3.7 [(https://github.com/twbs/bootstrap)](https://github.com/twbs/bootstrap)
* JavaScript
* Font Awesome - 4.7.0 [(https://github.com/FortAwesome/Font-Awesome)](https://github.com/FortAwesome/Font-Awesome)
* Angular - 1.5.9 [(https://github.com/angular/angular.js)](https://github.com/angular/angular.js)
* JQuery - 3.1.10 [(https://jquery.com)](https://jquery.com)
* Bootstrap Datepicker - 1.6.4 [(https://github.com/eternicode/bootstrap-datepicker)](https://github.com/eternicode/bootstrap-datepicker)
* jQuery Mask Plugin - 1.14.3 [(https://igorescobar.github.io/jQuery-Mask-Plugin/)](https://igorescobar.github.io/jQuery-Mask-Plugin/)

## Ferramentas, softwares e requisitos para execução do projeto

* [Eclipse IDE](https://eclipse.org/) - Versão mais atual da IDE já vem por padrão com o [plugin](http://www.eclipse.org/m2e/) necessário para integração de projetos Maven.
* JDK8 [instalado e configurado](http://www.devmedia.com.br/instalacao-e-configuracao-do-pacote-java-jdk/23749).
* Git [instalado e configurado](https://git-scm.com/book/pt-br/v1/Primeiros-passos-Instalando-Git) 


## Como executar o projeto 

Existem diversas formas de executar e testar os projetos desenvolvidos, citarei abaixo a forma mais simples e rápida :

**1º Passo:** Clonar o projeto do repositório GitHub através do seguinte comando :
	
	git clone https://github.com/ghdepaula/fullstack-java-teste

**2º Passo:** Acessar a pasta do repositório clonado via terminal/DOS localizar o arquivo **contabilizei.jar** e executá-lo passando como argumento a pasta/diretório da aplicação front-end para inicializar as aplicações de back-end e front-end localmente ou não informar nenhum argumento inicializando apenas a aplicação de back-end, conforme exemplo abaixo :
	
	java -jar contabilizei.jar /path/completo/do/projeto/fullstack-front-end (inicializa as duas aplicações)
	java -jar contabilizei.jar (inicializa apenas a aplicação de back-end)

**3º Passo:** Após isso basta acessar uma das URL's abaixo para navegar e testar a aplicação.
	
	http://127.0.0.1:8081
	http://localhost:8081

**4º Passo (Opcional):** Caso o arquivo **contabilizei.jar** tenha sido executado sem que o argumento path da aplicação front-end tenha sido repassado deve-se inicializar a aplicação de front através de um servidor HTTP como o `` node http-server``.

* [Instalando](https://nodejs.org/en/download/) Node.js.
* [Configurando](http://blog.teamtreehouse.com/install-node-js-npm-windows) o gerenciador de pacotes ``npm`` do Node.js.
* [Instalando e inicializando](https://www.npmjs.com/package/http-server) `` node http-server`` via ``npm``.

**OBS:**

* O arquivo **contabilizei.jar** citado, foi e pode ser gerado executando-se o Maven build via linha de comando ou através da IDE Eclipse.
* A base de dados é gerada no momento em que a aplicação é inicializada, não sendo necessária nenhuma configuração adicional.

**Bonus**

Se voce fosse utilizar esse sistema comercialmente, que alterações vc faria para escalar e/ou facilitar a vida do usuario? OBS: Voce pode descrever isso aqui ou mostrar na implementação.

* Na aplicação front-end melhoraria a validação client-side dos formulários da aplicação, adotando o padrão de validações inline-validation fornecendo assim uma melhor experiência de interação com a aplicação para usuário final.

**Referências e Links de Apoio**

* https://rafaell-lycan.com/2015/angular-services-factories-providers/
* https://felipenipo.wordpress.com/2015/04/10/implementando-cors-em-web-services-com-jax-rs/
* https://www.youtube.com/playlist?list=PLQCmSnNFVYnTD5p2fR4EXmtlR6jQJMbPb
* http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/basic_app_embedded_tomcat/basic_app-tomcat-embedded.html


 

