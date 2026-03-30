// package ejem1;

// import java.util.ArrayList;

// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.DefaultValue;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/persona") //IMPORTANTE PARA LA URL
// public class GestionaPersona {
//     static ArrayList<Persona> personas = new ArrayList<Persona>();

//     @DefaultValue("valor por defecto")
//     @QueryParam("valor")
//     String text;

//      @POST
//      @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//      @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//      public Response  Guardar(Persona p) {
        
//          this.personas.add(p);
//          return Response.ok(p).build();
         
//      }

//     @GET
//     @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//     public Persona  leer(){
//        Persona p = new Persona();
//      p.setCasado(true);
//      p.setId(1);
//      p.setNombre("Borja");
//      p.setSexo("Hombre");
//      this.personas.add(p);
//      return p;
     
//     }

// }
