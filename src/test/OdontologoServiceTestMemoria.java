package test;


import dao.impl.DaoEnMemoria;
import model.Odontologo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTestMemoria {
    OdontologoService odontologoService = new OdontologoService(new DaoEnMemoria());

    @Test
    @DisplayName("Testear que un odontólogo fue creado correctamente")
    void caso1(){
        //Dado
        Odontologo odontologo = new Odontologo(658974123, "Maria", "Sanchez");
        //Cuando
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
        //Entonces
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que se listen los odontólogos")
    void caso2(){

        Odontologo odontologo1 = new Odontologo(27187456, "Lorena", "Trujillo");
        Odontologo odontologo2 = new Odontologo(36458789, "Lina", "Gonzalez");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        List<Odontologo> odontologoDesdeDb = odontologoService.listarTodos();

        assertNotNull(odontologoDesdeDb);
    }

}