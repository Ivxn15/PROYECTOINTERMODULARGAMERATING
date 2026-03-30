package ejem1;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/usuarios")
public class GestionaUsuarios {

    private static final String URL = "jdbc:mariadb://localhost:3306/usuarios";
    private static final String USER = "root";
    private static final String PASS = "";

    
    // CLASE RESEÑA
    
    public static class Resena {

        private int id;

        @JsonProperty("usuario_id") 
        private int usuario_id;

        private String juego;
        private int puntuacion;
        private String comentario;
        private String fecha;
        private String nombre;

        public Resena() {} 

        public Resena(String nombre,int id, int usuario_id, String juego, int puntuacion, String comentario, String fecha) {
            this.id = id;
            this.usuario_id = usuario_id;
            this.juego = juego;
            this.puntuacion = puntuacion;
            this.comentario = comentario;
            this.fecha = fecha;
            this.nombre = nombre;
        }

        // Getters y setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getUsuario_id() { return usuario_id; }
        public void setUsuario_id(int usuario_id) { this.usuario_id = usuario_id; }

        public String getJuego() { return juego; }
        public void setJuego(String juego) { this.juego = juego; }

        public int getPuntuacion() { return puntuacion; }
        public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

        public String getComentario() { return comentario; }
        public void setComentario(String comentario) { this.comentario = comentario; }

        public String getFecha() { return fecha; }
        public void setFecha(String fecha) { this.fecha = fecha; }
        public String getNombre() { return nombre; }
public void setNombre(String nombre) { this.nombre = nombre; }
    }

    
    // REGISTRO USUARIO
    
   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirUsuario(Usuarios u) {
    String sql = "INSERT INTO usuario(nombre, correo, contrasena) VALUES (?, ?, ?)";
    try {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getContrasena());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int idGenerado = -1;
        if (rs.next()) idGenerado = rs.getInt(1);

        rs.close();
        ps.close();
        con.close();

        return Response.ok("{\"id\":" + idGenerado + "}").build();

    } catch (Exception e) {
        e.printStackTrace();
        return Response.serverError().entity(e.getMessage()).build();
    }
}

    
    // LOGIN USUARIO
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuarios u) {
        String sql = "SELECT id, nombre FROM usuario WHERE correo=? AND contrasena=?";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getContrasena());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                rs.close();
                ps.close();
                con.close();
                return Response.ok("{\"id\":" + id + "}").build();
            } else {
                rs.close();
                ps.close();
                con.close();
                return Response.status(401).entity("Usuario incorrecto").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    
    // CREAR RESEÑA
    
    @POST
    @Path("/resenas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crearResena(Resena r) {
        String sql = "INSERT INTO valoraciones(usuario_id, juego, puntuacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, r.getUsuario_id());
            ps.setString(2, r.getJuego());
            ps.setInt(3, r.getPuntuacion());
            ps.setString(4, r.getComentario());
            ps.setString(5, r.getFecha());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int idGenerado = -1;
            if (rs.next()) idGenerado = rs.getInt(1);
            rs.close();
            ps.close();
            con.close();
            return Response.ok(String.valueOf(idGenerado)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    
    // ELIMINAR RESEÑA
    
    @DELETE
    @Path("/resenas/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarResena(@PathParam("id") int id, @QueryParam("usuarioId") int usuarioId) {
    String sql = "DELETE FROM valoraciones WHERE id=? AND usuario_id=?";
    try {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, usuarioId);

        int rowsAffected = ps.executeUpdate();
        ps.close();
        con.close();

        if (rowsAffected == 0) {
            return Response.status(403).entity("No puedes eliminar esta reseña").build();
        }

        return Response.ok("Reseña eliminada").build();
    } catch (Exception e) {
        e.printStackTrace();
        return Response.serverError().entity(e.getMessage()).build();
    }
}

    
    // LISTAR RESEÑAS POR USUARIO
    
    @GET
@Path("/usuarios/{id}/resenas")
@Produces(MediaType.APPLICATION_JSON)
public Response listarResenasUsuario(@PathParam("id") int usuarioId) {

    String sql = "SELECT v.*, u.nombre FROM valoraciones v " + "JOIN usuario u ON v.usuario_id = u.id " + "where v.usuario_id=?";

    try {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USER, PASS);

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, usuarioId);

        ResultSet rs = ps.executeQuery();

        List<Resena> resenas = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String juego = rs.getString("juego");
            int puntuacion = rs.getInt("puntuacion");
            String comentario = rs.getString("comentario");
            String fecha = rs.getString("fecha");
            String nombre = rs.getString("nombre"); 

            resenas.add(new Resena(nombre,id, usuarioId, juego, puntuacion, comentario, fecha));
        }

        rs.close();
        ps.close();
        con.close();

        return Response.ok(resenas).build();

    } catch (Exception e) {
        e.printStackTrace();
        return Response.serverError().entity(e.getMessage()).build();
    }
}
//Este metodo me sirve para mostrar todas las reseñas para que la vean todos
@GET
@Path("/resenas/todas")
@Produces(MediaType.APPLICATION_JSON)
public Response listarTodasResenas() {

    String sql = "SELECT v.*, u.nombre FROM valoraciones v " + "JOIN usuario u ON v.usuario_id = u.id";

    try {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USER, PASS);

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Resena> resenas = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int usuarioId = rs.getInt("usuario_id");
            String juego = rs.getString("juego");
            int puntuacion = rs.getInt("puntuacion");
            String comentario = rs.getString("comentario");
            String fecha = rs.getString("fecha");
            String nombre = rs.getString("nombre");

            resenas.add(new Resena(nombre,id, usuarioId, juego, puntuacion, comentario, fecha));
        }

        rs.close();
        ps.close();
        con.close();

        return Response.ok(resenas).build();

    } catch (Exception e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
}
}