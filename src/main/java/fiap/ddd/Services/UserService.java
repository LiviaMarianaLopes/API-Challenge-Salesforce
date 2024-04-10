package fiap.ddd.Services;

import fiap.ddd.Entities.User;
import fiap.ddd.Repositories.UserRepositoryOrcl;

import java.util.Objects;

public class UserService {
    private UserRepositoryOrcl userRepository;
    public UserService(){
        userRepository = new UserRepositoryOrcl();
    }

    public void create(User user) {

        var users = userRepository.readAll();
        for (User u : users) {
            if (Objects.equals(u.getEmail(), user.getEmail())) {
                throw new IllegalArgumentException("Este email já foi cadastrado");

            }

        }
        var validation = user.validate();
        if (validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            userRepository.create(user);
    }


    public void update(int id, User user){
        var validation = user.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            userRepository.update(id, user);
    }
}
