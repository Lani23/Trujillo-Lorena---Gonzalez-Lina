package test;

import dao.impl.DaoH2;
import service.OdontologoService;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new DaoH2());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./odontologos;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que un odontólogo fue cargado correctamente")
    void caso1(){
        //Dado
        Odontologo odontologo = new Odontologo(65412369, "Andres", "Castro");
        //Cuando
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
        //Entonces
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que liste los odontologos")
    void caso2(){
        //Dado
        List<Odontologo> odontologos;
        //Cuando
        odontologos = odontologoService.listarTodos();
        //Entonces
        assertNotNull(odontologos);
    }
}