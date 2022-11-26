package com.upc.ReservaRecursos;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Negocio.IRecursoNegocio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReservaRecursosApplicationTests {

	@Autowired
	private IRecursoNegocio iRecursoNegocio;


}
