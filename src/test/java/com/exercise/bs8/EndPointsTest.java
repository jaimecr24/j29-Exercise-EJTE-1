package com.exercise.bs8;

import com.exercise.bs8.persona.domain.Persona;
import com.exercise.bs8.persona.infrastructure.controller.PersonaInputDTO;
import com.exercise.bs8.persona.infrastructure.repository.PersonaRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EndPointsTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PersonaRepo personaRepo;

	int id1;

	@BeforeAll
	public void starting() throws Exception {
		String dif="x";
        System.out.println("Bs8ApplicationTest: before all");
		Persona p1 = createPersona(dif);
		Persona p2 = createPersona(dif+dif);
		personaRepo.save(p1);
		personaRepo.save(p2);
		id1 = p1.getId();
	}

    @AfterAll
    void cleaning() {
        System.out.println("Bs8ApplicationTest: after all");
        List<Persona> lista = personaRepo.findAll();
        lista.forEach(e -> {
            try {
                personaRepo.delete(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

	@Test
	@DisplayName("Testing GET /")
	void getAllController() throws Exception {
        System.out.println("Bs8ApplicationTest: testing get");
        int numItems = 2;
		MvcResult resultado  = this.mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
		String contenido = resultado.getResponse().getContentAsString();
		List<Persona> personas= new ObjectMapper().readValue(contenido, new TypeReference<>() {	}); // Use TypeReference to map the List.
		Assertions.assertEquals(personas.size(), numItems);
	}

	@Test
	@DisplayName("Testing POST")
	void postController() throws Exception {
        int newId;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		PersonaInputDTO pInputDTO = createPersonaInputDTO("x");
		MvcResult re = this.mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(pInputDTO)))
				.andExpect(status().isOk()).andReturn();
    	String contenido = re.getResponse().getContentAsString();
		Persona p = gson.fromJson(contenido, Persona.class);
		Assertions.assertEquals(p.getUsuario(),pInputDTO.getUsuario());
		Assertions.assertEquals(p.getName(), pInputDTO.getName());
        newId = p.getId();
        p = personaRepo.getById(newId);
        personaRepo.delete(p);
	}

	Persona createPersona(String dif) throws ParseException {
		Persona p = new Persona();
		p.setUsuario("usuario"+dif);
		p.setName("minombre"+dif);
		p.setSurname("miapellido"+dif);
		p.setCompanyEmail("company"+dif+"@email.com");
		p.setPersonalEmail("personal"+dif+"@email.com");
		p.setPassword("123456");
		p.setCity("ciudad"+dif);
		p.setActive(true);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		p.setCreatedDate(formatter.parse("2022-12-01"));
		p.setTerminationDate(formatter.parse("2022-12-22"));
		p.setImagenUrl("url_imagen"+dif);
		return p;
	}

	PersonaInputDTO createPersonaInputDTO(String dif) throws ParseException {
		PersonaInputDTO p = new PersonaInputDTO();
		p.setUsuario("usuario"+dif);
		p.setName("minombre"+dif);
		p.setSurname("miapellido"+dif);
		p.setCompanyEmail("company"+dif+"@email.com");
		p.setPersonalEmail("personal"+dif+"@email.com");
		p.setPassword("123456");
		p.setCity("ciudad"+dif);
		p.setActive(true);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		p.setCreatedDate(formatter.parse("2022-12-01T00:00:00"));
		p.setTerminationDate(formatter.parse("2022-12-22T00:00:00"));
		p.setImagenUrl("url_imagen"+dif);
		return p;
	}

}
