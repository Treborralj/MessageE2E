package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.dto.response.PublicKeyResponse;
import is.hi.messagee2e.persistence.entities.User;
import is.hi.messagee2e.persistence.repositories.UserRepository;
import is.hi.messagee2e.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Implements the getPublicKeyByUssername funciton.
 *
 *****************************************************************************/
@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns the public key of the specified user.
     * @param username the username of the requested user
     * @return a DTO containing the user's public key
     */
    @Override
    public PublicKeyResponse getPublicKeyByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new PublicKeyResponse(user.getUsername(), user.getPublicKey());
    }
}

