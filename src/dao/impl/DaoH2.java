package dao.impl;

import dao.IDao;
import db.H2Connection;
import org.apache.log4j.Logger;
import model.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoH2 implements IDao<Odontologo>
{
    public static final Logger logger = Logger.getLogger(DaoH2.class);
    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM  ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetonar = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeromatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer idBD = resultSet.getInt(1);
                odontologoARetonar = new Odontologo(idBD, odontologo.getNumeromatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            logger.info("Odontólogo guardado en base de datos " + odontologoARetonar);
            connection.commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return  odontologoARetonar;
    }

    @Override
    public List listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologoBD = null;

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                Integer numeromatricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);

                odontologoBD = new Odontologo(id, numeromatricula, nombre, apellido);

                odontologos.add(odontologoBD);
                logger.info("Odontólogo " + odontologoBD);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologos;
    }
}
