// package ejem1;

// import java.sql.Statement;
// import java.sql.Connection;
// import java.sql.Driver;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import javax.print.attribute.standard.Media;

// import org.checkerframework.common.reflection.qual.GetClass;

// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.FormParam;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.PUT;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.PathParam;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
// import jakarta.ws.rs.core.GenericEntity;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// //Ejercicio2
// @Path("/deportistas")
// public class GestionaDeportistas {
//     private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
//     private static final String USER = "root";
//     private static final String PASS = "";

//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response obtenerTodos(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//                 Statement st = conexion.createStatement();
//                 ResultSet rs = st.executeQuery("Select * from deportistas")

//         ) {
//                 while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
//                 GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};

//                 Response response = Response.ok(entity).build();
//                 return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el diriver").build();
//         }
//         return null;

        

//     }
//     @Path("/deport")
//     @POST
//     @Consumes({MediaType.APPLICATION_JSON})
//     public Response subirDeportistaAndroid(Deportista d) throws ClassNotFoundException{
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//                 Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//                 Statement st = conexion.createStatement();
//                 st.executeUpdate("Insert into deportistas(nombre,deporte) values ('"+d.getNombre()+"','"+d.getDeporte()+")");

         
                
//                 return Response.ok("Subido correctamente").build();
//             } catch (SQLException e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
        
//         return null;

        

//     }
//     //Buscar jugador (/{id}): devuelve la información relativa al deportista id.

//     @Path("/{id}")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response buscarjugador(@PathParam("id") int id){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where id = " + id)
//         ) {
//              while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //4. Por deporte (/deporte/{nombreDeporte}): Lista los deportistas de un deporte.
//     @Path("/deporte/{nombreDeporte}")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response PorDeporte(@PathParam("nombreDeporte") String nombreDeporte){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where deporte = '" + nombreDeporte+"'")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Activos (/activos): Lista los deportistas activos.
//     @Path("/activos")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Activos(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where activo = 1")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Retirados (/retirados): Lista los deportistas retirados.
//     @Path("/retirados")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Retirados(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where activo = 0")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Masculinos (/masculinos): Lista los deportistas masculinos.
//     @Path("/masculinos")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Masculinos(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where genero = 'Masculino'")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Femeninos (/femeninos): Lista los deportistas femeninos.
//     @Path("/femeninos")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Femeninos(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where genero = 'Femenino'")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Deportes por genero (/xg): Lista un array con dos elementos: uno con todos los deportistas masculinos y otro con todos los deportistas femeninos.
//     @Path("/xg")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response ListaGeneros(){
//         ArrayList<ArrayList<Deportista>> deportistas = new ArrayList<>();
//         ArrayList<Deportista> deportistasFemeninos = new ArrayList<>();
//         ArrayList<Deportista> deportistasMasculinos = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where genero = 'Femenino'");
//             ResultSet rs2 = st.executeQuery("Select * from deportistas where genero = 'Masculino'")

//         ) {
//             while (rs.next()) {
//                     deportistasFemeninos.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
                    
//                 }
//             while (rs2.next()) {
//                 deportistasMasculinos.add(new Deportista(rs2.getInt("id"),
//                         rs2.getString("nombre"),
//                         rs2.getBoolean("activo"),
//                         rs2.getString("deporte"),
//                         rs2.getString("genero")));
//             }
//             deportistas.add(deportistasMasculinos);
//             deportistas.add(deportistasFemeninos);
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistasFemeninos) {};
//             GenericEntity<List<Deportista>>entity2 =new GenericEntity<List<Deportista>>(deportistasMasculinos) {};
                
//             Response response = Response.ok(entity).build();
//             Response response2 = Response.ok(entity2).build();
//             return Response.ok(deportistas).build();
            
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Activos por deporte (/deporte/{nombreDeporte}/activos): Lista los deportistas activos de un deporte.
//     @Path("/deporte/{nombreDeporte}/activos")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Activos_Por_Deporte(@PathParam("nombreDeporte")String nombreDeporte){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select * from deportistas where deporte = '" + nombreDeporte+"'"+" and activo = 1")
//         ) {
//             while (rs.next()) {
//                     deportistas.add(new Deportista(rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getBoolean("activo"),
//                         rs.getString("deporte"),
//                         rs.getString("genero")));
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deportistas).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Contar deportistas (/sdepor): Cuenta el número de deportistas distintos.
//      @Path("/sdepor")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Contar_Deportistas(){
//         ArrayList<Deportista> deportistas = new ArrayList<>();
//         int numero = 0;
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select Count(*) from deportistas");
            
//         ) {
//             while (rs.next()) {
//                     numero = rs.getInt(1);
                    
//                 }
            
//             GenericEntity<List<Deportista>>entity =new GenericEntity<List<Deportista>>(deportistas) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(numero).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Ejercicio 12
//     @Path("/deportes")
//     @GET
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response Lista_deportes(){
//         ArrayList<String> deporte = new ArrayList<>();
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("Select Distinct deporte from deportistas order by deporte Asc ");
            
//         ) {
//             while (rs.next()) {
//                     deporte.add(rs.getString("deporte"));
                    
//                 }
            
//             GenericEntity<List<String>>entity =new GenericEntity<List<String>>(deporte) {};
                
//             Response response = Response.ok(entity).build();
//             return Response.ok(deporte).build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Crear deportista (/): Añade un deportista en el sistema.
//     @POST
// @Path("/anhadir")
// @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
// public Response CrearDeportista1(Deportista deportista) {
//     try {
//         Class.forName("org.mariadb.jdbc.Driver");
//         try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//              Statement st = conexion.createStatement()) {
            
            
//             String consulta = "INSERT INTO deportistas (nombre, activo, genero, deporte) VALUES ('" 
//                               + deportista.getNombre() + "', '" 
//                               + deportista.isActivo() + "', '"
//                               + deportista.getGenero() + "', '"
//                               + deportista.getDeporte() + "')"; 
            
//             st.executeUpdate(consulta);

//             return Response.ok("Deportista creado correctamente").build();
            
//         } catch (Exception e) {
//             System.out.println(e.getLocalizedMessage());
//             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar").build();
//         }
//     } catch (ClassNotFoundException e) {
//         return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el driver JDBC").build();
//     }
// }
//     //Ejercicio14
//     @Path("/form") //Con preparada
//     @POST
//     @Consumes("application/x-www-form-urlencoded")
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response CrearDeportistaFormulario(@FormParam("nombre") String nombre,
//             @FormParam("estado") Boolean estado,@FormParam("genero") String genero,@FormParam("id") int id,@FormParam("deporte") String deporte){
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement()
            
//         ) {
//             String consulta = "Insert into deportistas(id,nombre,activo,genero,deporte) VALUES (" + id +",'" + nombre+"',"+ estado + ",'"+ genero +"','"+ deporte +"')";
//             st.executeUpdate(consulta);
            
            
//             return Response.ok("Bien ingresado").build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Ejercicio15
//     @POST
//     @Path("/adds")
//     @Consumes({MediaType.APPLICATION_JSON})
//     @Produces({MediaType.APPLICATION_JSON})
//     public Response CrearVariosDeportistas(List<Deportista> listaDeportistas) {
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//                  Statement st = conexion.createStatement()) {
                
//                 // Recorremos la lista que nos llega en el JSON
//                 for (Deportista d : listaDeportistas) {
//                     String consulta = "INSERT INTO deportistas (nombre, activo, genero, deporte) VALUES ('" 
//                                       + d.getNombre() + "', '" 
//                                       + d.isActivo() + "', '"
//                                       + d.getGenero() + "', '"
//                                       + d.getDeporte() + "')";
//                     st.executeUpdate(consulta);
//                 }

//                 return Response.ok("Todos los deportistas han sido creados").build();
                
//             } catch (Exception e) {
//                 return Response.status(500).entity("Error en la inserción masiva").build();
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(404).entity("Driver no encontrado").build();
//         }
//     }
//     //16. Actualizar deportista (/): actualiza la información relativa a un deportista.
//     @Path("/actualizar")
//     @POST
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response ActualizarDeportista(){
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement()
            
//         ) {
//             String consulta = "Update deportistas set nombre = 'Jose Barros' where nombre = 'Alex Criville'";
//             st.executeUpdate(consulta);
            
            
            
//             return Response.ok("Bien Actualizado").build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     //Borrar deportista (/del/{id}): elimina la información relativa a un deportista id.
//     @Path("/del/{id}")
//     @POST
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response ActualizarDeportista(@PathParam("id") int id){
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement()
            
//         ) {
//             String consulta = "delete from deportistas where id = " + id;
//             st.executeUpdate(consulta);
            
            
            
//             return Response.ok("Bien Borrado").build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }
//     @Path("/img/{id}/{num}")
//     @POST
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response MuestraImagen(@PathParam("id") int id){
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//             try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement()
            
//         ) {
//             String consulta = "delete from deportistas where id = " + id;
//             st.executeUpdate(consulta);
            
            
            
//             return Response.ok("Bien Borrado").build();
//             } catch (Exception e) {
//                 System.out.println(e.getLocalizedMessage());
//             }
//         } catch (ClassNotFoundException e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la clase").build();
//         }
        
//         return null;
//     }

//     //EjercicioExamen 9
//     @PUT
// @Path("/actualizar")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
// public Response actualizarJuego(@QueryParam("id") int id, Juego juegoNuevo){

//     try {
//         Class.forName("org.mariadb.jdbc.Driver");

//         try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement()
//         ){

//             ResultSet rs = st.executeQuery("SELECT * FROM juegos WHERE id=" + id);

//             if(!rs.next()){
//                 return Response.status(Response.Status.NOT_FOUND)
//                         .entity("El juego no existe")
//                         .build();
//             }

//             String sql = "UPDATE juegos SET " +
//                     "nombre='" + juegoNuevo.getNombre() + "', " +
//                     "stockventa=" + juegoNuevo.getStockventa() + ", " +
//                     "precioventa=" + juegoNuevo.getPrecioventa() +
//                     " WHERE id=" + id;

//             st.executeUpdate(sql);

//             return Response.ok("Juego actualizado correctamente").build();

//         } catch (Exception e) {
//             return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity(e.getLocalizedMessage())
//                     .build();
//         }

//     } catch (ClassNotFoundException e) {
//         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                 .entity("No encuentra el driver")
//                 .build();
//     }
// }
// //Ejercicio8 Examen
// @GET
// @Path("/sinprecio")
// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
// public Response obtenerJuegosSinPrecio(){

//     ArrayList<Juego> juegos = new ArrayList<>();

//     try {
//         Class.forName("org.mariadb.jdbc.Driver");

//         try(Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//             Statement st = conexion.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM juegos WHERE precioalquiler IS NULL")
//         ){

//             while(rs.next()){
//                 juegos.add(new Juego(
//                         rs.getInt("id"),
//                         rs.getString("nombre"),
//                         rs.getString("plataforma"),
//                         rs.getInt("stockalquiler"),
//                         rs.getDouble("precioalquiler")
//                 ));
//             }

//             GenericEntity<List<Juego>> entity =
//                     new GenericEntity<List<Juego>>(juegos){};

//             return Response.ok(entity).build();

//         } catch (Exception e) {
//             return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity(e.getLocalizedMessage())
//                     .build();
//         }

//     } catch (ClassNotFoundException e) {
//         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                 .entity("No encuentra el driver")
//                 .build();
//     }
// }
// @POST
// @Path("/actualizar")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
// public Response actualizarJuego(@QueryParam("id") int id,Juego juegoNuevo){
//     try {
//         Class.forName("org.mariadb.jdbc.Driver");
//         try(Connection conexionn = DriverManager.getConnection(URL, USER, PASS);
//         Statement st = conexionn.createStatement()
//     ){
//         ResultSet rs = st.executeQuery("Select * from juegos where id=" + id);
//         if (!rs.next()) {
//             return Response.status(Response.Status.NOT_FOUND).entity("El juego no existe").build();
//         }
//         String sql = "Update juegos SET nombre ='" + juegoNuevo.getNombre() + "',"
            
            
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//     } catch (Exception e) {
//         // TODO: handle exception
//     }
// }

// @GET
// @Path("/devuelve")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON) 
// public Response mostrar(){
//     ArrayList<Juego> juegos = new ArrayList<>();
//     try {
//         Class.forName("org.mariadb.jdbc.Driver");
//         try(Connection conexion = DriverManager.getConnection(URL, USER, PASS)
//         Statement st = conexion.createStatement()
//         ResultSet rs = st.executeQuery("SELECT * FROM juegos WHERE precioalquiler IS NULL")) {
//             while (rs.next()) {
//                 juegos.add(new Juego(rs.getInt("id")))
//             }
//             GenericEntity<List<Juego>>entity = new GenericEntity<List>(<Juego>>(juegos)){};

//             return Response.ok(entity).build();
            
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//     } catch (Exception e) {
//         // TODO: handle exception
//     }
// }


    
    

