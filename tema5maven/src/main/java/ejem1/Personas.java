// package ejem1;

// //Consumes lo que el servidor recibe
// //Produces lo que el servidor manda
// import java.util.ArrayList;
// import java.util.Iterator;

// import javax.print.attribute.standard.Media;

// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.DefaultValue;
// import jakarta.ws.rs.FormParam;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.PathParam;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/personas") // IMPORTANTE PARA LA URL
// public class Personas {
//     static ArrayList<Persona> personas = new ArrayList<Persona>();

//     // EJ1
//     @POST
//     @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response Guardar(Persona p) {

//         this.personas.add(p);
//         return Response.ok(p).build();

//     }

//     // EJ2
//     @GET
//     @Produces({ MediaType.APPLICATION_XML })
//     public ArrayList<Persona> listar() {
//         return this.personas;
//     }

//     // EJ3
//     @GET
//      @Path("/{nombre}")
//      public Response ver(@PathParam("nombre") String name) {
//      for (int i = 0; i < personas.size(); i++) {
//      if (personas.get(i).getNombre().equals(name)) {
//      return Response.ok(personas.get(i)).build();
//      }
//      }

//      return Response.status(Response.Status.NOT_FOUND).entity("no hay nadie con este nombre").build();

//      }
//     // EJ4
//     @GET
//     @Path("/buscar")
//     public Response verBuscar(@QueryParam("nombre") String cadena) {
//         for (int i = 0; i < personas.size(); i++) {
//             if (personas.get(i).getNombre().toLowerCase().contains(cadena)) {
//                 return Response.ok(personas.get(i)).build();
//             }
//         }
//         return Response.status(Response.Status.NOT_FOUND).entity("No se encontro un nombre con esa cadena").build();

//     }

//     // EJ6
//     @Path("/form")
//     @POST
//     @Consumes("application/x-www-form-urlencoded")
//     @Produces(MediaType.APPLICATION_JSON)
//     public Persona getPersonaText(@FormParam("nombre") String nombre,
//             @FormParam("casado") Boolean casado, @FormParam("sexo") String sexo, @FormParam("id") int id) {
//         Persona persona = new Persona();
//         persona.setSexo(sexo);
//         persona.setNombre(nombre);
//         persona.setId(0);
//         persona.setCasado(casado);
//         return persona;
//     }

//     // <persona>
//     // <casado>true</casado>
//     // <id>1</id>
//     // <nombre>jose</nombre>
//     // <sexo>MachoAlfa</sexo>
//     // </persona>
//     // EJ7
//     @Path("/add")
//     @POST
//     @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response añadirSimultaneo(ArrayList<Persona> personas) {
//         try {
//             for (Persona p : personas) {
//                 this.personas.add(p);
//             }

//         } catch (Exception e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No ha insertado a ninguna persona").build();
//         }
//         return Response.ok(personas).build();

//     }

//     // EJ8
//     @Path("/{id}")
//     @POST
//     @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response borrarID(@PathParam("id") int id) {
//         try {
//             for (int i = 0; i < personas.size(); i++) {
//                 if (personas.get(i).getId() == id) {
//                     personas.remove(i);
//                 }
//             }
//         } catch (Exception e) {
//             return Response.status(Response.Status.NOT_FOUND).entity("No hay persona con este id").build();
//         }
//         return Response.ok(personas).build();
//     }
//     //EJ9
//     @GET
//     @Path("/buscarr")
//     public Response verBuscarr(@DefaultValue("Ivan") @QueryParam("nombre") String cadena) {
//         for (int i = 0; i < personas.size(); i++) {
//             if (personas.get(i).getNombre().toLowerCase().contains(cadena)) {
//                 return Response.ok(personas.get(i)).build();
//             }
//         }
//         return Response.status(Response.Status.NOT_FOUND).entity("No se encontro un nombre con esa cadena").build();

//     }
//     @Path("/ej1")
//     @POST
//     @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//     public Response guardar(Persona p){
//         this.personas.add(p);
//        return  Response.ok(p).build();
//     }
//     @Path("/ej2")
//     @GET
//     public Response listarr(){
//         return Response.ok(this.personas).build();
//     }

//     @Path("/{nombre}")
//     @GET
//     public Response verr(@PathParam("nombre") String nombre){
//         for (int i = 0; i < personas.size(); i++) {
//             if (personas.get(i).getNombre().equals(nombre)) {
//                 return Response.ok(personas.get(i)).build();
//             }
            
//         }
//         return Response.status(Response.Status.NOT_FOUND).entity("No hay nadie con este ").build();
//     }

//     @Path("/buscar")
//     @GET
//     // public Response verrr(@QueryParam("nombre") String cad){
//     //     for (int i = 0; i < personas.size(); i++) {
//     //         if (personas.get(i).getNombre().toLowerCase().contains(cad)) {
//     //             return Response.ok(personas.get(i)).build();
//     //         }
//     //     }
//     //     return Response.status(Response.Status.NOT_FOUND).entity("No se encontro").build();
//     public Response verrr(@QueryParam("nombre") String cad){
//         for (int i = 0; i < personas.size(); i++) {
//             if (personas.get(i).getNombre().toLowerCase().contains(cad)) {
//                 return Response.ok(personas.get(i)).build();
//             }
//         }
//         return Response.status(Response.Status.NOT_FOUND).entity("No se encontro la cadena").build();
//     }

//     @Path("/{id}")
//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     // public Response borrar(@PathParam("id") int id){
//     //     int contador = 0;
//     //     Iterator<Persona> pe = personas.iterator();
//     //     while (pe.hasNext()) {
//     //         Persona p = pe.next();
//     //         if (p.getId()==id) {
//     //             pe.remove();
//     //             contador++;
//     //         }
//     //     }
//     //     return Response.ok(contador).build();
//     // }
//     public Response borrar(@PathParam("id") int id){
//         int contador = 0;
//         Iterator<Persona> p = personas.iterator();
//         while (p.hasNext()) {
//             Persona pe = p.next();
//             if (pe.getId()==id) {
//                 p.remove();
//                 contador++;
//             }
//         }
//         return Response.ok(contador).build();
//     }
// }
