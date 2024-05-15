package fiap.ddd.Services;

import fiap.ddd.Entities.Login;
import fiap.ddd.Repositories.UserRepositoryOrcl;
import jakarta.ws.rs.core.Response;

public class LoginService {
    private UserRepositoryOrcl userRepository;
    public LoginService(){
        userRepository = new UserRepositoryOrcl();
    }
    public int validarLogin(Login login){

           if( userRepository.verificarLogin(login)){
               return userRepository.saberLogin(login).getId();
           }
          else{
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

    }
}
