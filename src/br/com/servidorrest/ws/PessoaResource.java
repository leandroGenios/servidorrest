package br.com.servidorrest.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidorrest.pojo.Pessoa;

@Path("/pessoas")
public class PessoaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa getJson() {
		Pessoa p = new Pessoa();
		p.setNome("Rodrigo");
		p.setEmail("rodrigo.pimenta@gmail.com");
		return p;
	}
}
