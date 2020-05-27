# cases

Essa API é responsável por fornecer o gerenciamento de casos.


#### Tecnologias utilizadas

 * Java 1.8
 * Apache maven 3.6.2
 * Springboot 2.1.6.RELEASE
 * Springfox Swagger 2 2.9.2
 * Junit 4.12
 * Mockito 2.23
 * Node v12.16.3

#### Pré-requisitos antes de executar a aplicação backend

 * Ter previamente instalado e configurado o Git, Java e Maven nas versões citas acima.
  
 * Fazer clone deste repositório em seu workspace.
 
 * Após fazer o clone do projeto navege até o diretório principal da aplicação:
 
	$ cd ~/case-backend 

 * Execute o seguinte commando para o maven baixar as dependências do projeto e compilar o jar:

	$ mvn install
  
 * A chave de acesso a base está dentro do projeto src/main/resources/caes2-02d1e7cd7cfe.json
 
 * Configure a propriedade "spring.cloud.gcp.datastore.credentials.location=..." com o caminho absoluto do arquivo em sua maquina.
 
#### Executando a aplicação backend com maven

 *  Para iniciar a aplicação com o maven execute esse comando:
 
	$ mvn spring-boot:run
	
 * A aplicação estará rodando na porta 3000 e poderá acessá-la facilmente pela interface do swagger2 no endereço: 
 
[http://localhost:3000/swagger-ui.html](http://localhost:3000/swagger-ui.html)

#### Executando a aplicação frontend

 * Deve ter o Node instalado na versão citada.
	
 * Faça o install das dependências do node:
 
  $ cd ~/case-frontend
	$ npm install .
	
 * Para iniciar a aplicação com o docker execute o seguinte comando:
 
	$  npm run start
  
 * A aplicação irá abrir no navegador com o endereço http://localhost:8080
	
	

#### Referências

 * [DDD - https://martinfowler.com/bliki/BoundedContext.html](https://martinfowler.com/bliki/BoundedContext.html)
 * [Spring data - https://spring.io/guides/gs/accessing-data-jpa](https://spring.io/guides/gs/accessing-data-jpa)
 * [Spring with docker - https://spring.io/guides/gs/spring-boot-docker](https://spring.io/guides/gs/spring-boot-docker/)
	 
