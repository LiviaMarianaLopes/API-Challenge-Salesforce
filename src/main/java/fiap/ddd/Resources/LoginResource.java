package fiap.ddd.Resources;
import fiap.ddd.Entities.Login;
import fiap.ddd.Services.LoginService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
public class LoginResource {
    public LoginService loginService;
    public LoginResource(){
        loginService = new LoginService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarLogin(Login login){
        try{
           var idUser = loginService.validarLogin(login);
           return Response.ok(idUser).build();
        }
        catch(IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
