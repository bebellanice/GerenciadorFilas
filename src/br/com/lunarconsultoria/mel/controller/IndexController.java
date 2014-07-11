package br.com.lunarconsultoria.mel.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.lunarconsultoria.mel.annotation.Restrito;

@Resource
public class IndexController {

	private final Result result;

	public IndexController(Result result) {
		this.result = result;
	}

	@Path("/")
	@Restrito
	public void index() {
		result.include("variable", "VRaptor!");
	}

}
