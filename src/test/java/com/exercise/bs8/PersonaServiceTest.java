package com.exercise.bs8;

import com.exercise.bs8.persona.application.IPersona;
import com.exercise.bs8.persona.infrastructure.controller.PersonaInputDTO;
import com.exercise.bs8.persona.infrastructure.controller.PersonaOutputDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaServiceTest {

    @Autowired
    private IPersona personaService;

    private int id1;

    @BeforeAll
    void starting() throws Exception {
        System.out.println("PersonaService: Before All");
        String dif1 = "x1", dif2 = "x2";
        PersonaOutputDTO p1 = personaService.addPersona(this.createPersonaInputDTO(dif1));
        personaService.addPersona(this.createPersonaInputDTO(dif2));
        id1 = p1.getId();
    }

    @AfterAll
    void cleaning() {
        System.out.println("PersonaService: After all");
        List<PersonaOutputDTO> lista = personaService.findAll();
        lista.forEach(e -> {
            try {
                personaService.delPersona(e.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Test
    @DisplayName("testing findAll")
    void findAllTest() {
        System.out.println("PersonaService: findAll");
        int listSize = 2;
        List<PersonaOutputDTO> lista = personaService.findAll();
        Assertions.assertEquals(listSize,lista.size());
    }

    @Test
    @DisplayName("testing getByUser")
    void getByUserTest() throws Exception {
        int numItems = 1;
        PersonaOutputDTO p = personaService.getById(id1);
        List<PersonaOutputDTO> lista = personaService.getByUser(p.getUsuario());
        Assertions.assertEquals(numItems, lista.size());
    }

    @Test
    @DisplayName("testing getById")
    void getByIdTest() throws Exception {
        PersonaOutputDTO p = personaService.getById(this.id1);
        Assertions.assertNotNull(p);
    }

    @Test
    @DisplayName("testing addPersona")
    void addPersonaTest() throws Exception {
        String dif = "x";
        String myUser = "usuariox";
        int numItems = personaService.findAll().size();
        PersonaOutputDTO p = personaService.addPersona(this.createPersonaInputDTO(dif));

        Assertions.assertEquals(myUser,p.getUsuario());
        List<PersonaOutputDTO> lista = personaService.findAll();
        Assertions.assertEquals(numItems+1, lista.size());
        personaService.delPersona(p.getId());
    }

    @Test
    @DisplayName("testing putPersona")
    void putPersonaTest() throws Exception {
        String dif = "x";
        String newuser = "usuario999";
        String olduser;
        int id;
        PersonaOutputDTO pOut1, pOut2;
        PersonaInputDTO pIn;

        pIn = this.createPersonaInputDTO(dif);
        pIn.setUsuario(newuser);
        List<PersonaOutputDTO> lista = personaService.findAll();
        // Tomamos el primer elemento de la lista, guardamos su id y su usuario.
        pOut1 = lista.get(0);
        id = pOut1.getId();
        olduser = pOut1.getUsuario();
        // Ponemos en su lugar los valores de pIn
        pOut2 = personaService.putPersona(id, pIn);
        // Comprobamos que en el OutputDTO de retorno el valor del usuario es el mismo que hemos pasado.
        Assertions.assertEquals(newuser,pOut2.getUsuario());
        // Volvemos a dejar el usuario como estaba y comprobamos que el valor de retorno es correcto.
        pIn.setUsuario(olduser);
        pOut2 = personaService.putPersona(id,pIn);
        Assertions.assertEquals(pOut2.getUsuario(), olduser);
    }

    @Test
    @DisplayName("testing delPersona")
    void delPesonaTest() throws Exception {
        String dif = "x";
        int newId;

        List<PersonaOutputDTO> lista = personaService.findAll();
        int actualSize = lista.size();

        PersonaInputDTO p = this.createPersonaInputDTO(dif);
        PersonaOutputDTO pOut = personaService.addPersona(p);
        Assertions.assertNotNull(pOut);
        newId = pOut.getId();
        Assertions.assertEquals(pOut.getUsuario(),p.getUsuario());
        lista = personaService.findAll();
        Assertions.assertNotEquals(actualSize, lista.size());
        personaService.delPersona(newId);
        lista = personaService.findAll();
        Assertions.assertEquals(actualSize, lista.size());
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
