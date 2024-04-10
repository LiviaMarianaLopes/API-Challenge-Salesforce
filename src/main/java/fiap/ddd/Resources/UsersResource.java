package fiap.ddd.Resources;

import fiap.ddd.Repositories.UserRepositoryOrcl;
import fiap.ddd.Services.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fiap.ddd.Entities.User;

import java.util.List;


@Path("users")
public class UsersResource {
    public UserService userService;
    public UserRepositoryOrcl userRepositoryOrcl;
    public UsersResource(){
        userService = new UserService();
        userRepositoryOrcl = new UserRepositoryOrcl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll(){
        return userRepositoryOrcl.readAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        var user = userRepositoryOrcl.getUserById(id);
        return user.isPresent() ?
                Response.ok(user.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user){
        try{
            userService.create(user);
            return Response.status(Response.Status.CREATED).entity("Cadastro realizado com sucesso!").build();
        }
        catch(IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, User user){
        try{
            userService.update(id, user);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch(IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteById(@PathParam("id") int id){
        userRepositoryOrcl.delete(id);
    }


}
