/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Tarea;

/**
 *
 * @author Oswaldo
 */
public class TareaDTO {

    private final DataBase db;
    private final String tableName = "tbl_tarea";

    public TareaDTO() {
        db = new DataBase();
    }

    public String addTarea(Tarea t) throws ClassNotFoundException, SQLException {
        String respuesta = "";
        Connection connect = db.getConexion();
        if (connect == null) {
            System.out.println("No hay conexion");
            respuesta = TareaDTO.simpleClaveValorToJSON("message", "Error: No hay conexion");
        } else {
            try {
                PreparedStatement st = connect.prepareStatement("insert into " + tableName + " (name, description) values (?,?)");
                st.setString(1, t.getName());
                st.setString(2, t.getDescription());
                st.execute();
                connect.close();
                // Para devolver el resultado en un objeto, que tenga un elemento "records" se completa
                respuesta = TareaDTO.simpleClaveValorToJSON("message", "Tarea creada");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                respuesta = TareaDTO.simpleClaveValorToJSON("message", "Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public String listTasks() throws ClassNotFoundException, SQLException {
        String respuesta = "";

        ResultSet result = null;
        PreparedStatement st;
        Connection connect = db.getConexion();
        if (connect == null) {
            System.out.println("No hay conexion");
            respuesta = TareaDTO.simpleClaveValorToJSON("message", "Error: No hay conexion");
        } else {
            try {
                st = connect.prepareStatement("select * from " + tableName);
                result = st.executeQuery();

                ArrayList lista = new ArrayList();

                while (result.next()) {
                    lista.add(new Tarea(result.getInt("id"), result.getString("name"), result.getString("description")));
                }
                connect.close();
                Gson gson = new Gson();
                String representacionJSON = gson.toJson(lista); // En JSON sería un array []

                // Para devolver el resultado en un objeto, que tenga un elemento "records" se completa
                respuesta = "{\"records\":" + representacionJSON + "}";
                System.out.println("Listado: " + respuesta);

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                respuesta = TareaDTO.simpleClaveValorToJSON("message", "Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public static String simpleClaveValorToJSON(String clave, String valor) {
        return "{\"" + clave + "\":\"" + valor + "\"}";
    }
}
