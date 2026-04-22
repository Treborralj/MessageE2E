package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.dto.request.LoginRequest;
import is.hi.messagee2e.dto.request.SignupRequest;
import is.hi.messagee2e.dto.response.AuthenticationResponse;
import is.hi.messagee2e.persistence.entities.User;
import is.hi.messagee2e.persistence.repositories.UserRepository;
import is.hi.messagee2e.security.JwtService;
import is.hi.messagee2e.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Implements the signup and login functions.
 *
 *****************************************************************************/
@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationServiceImplementation(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder,
                                               JwtService jwtService,
                                               AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user, stores their public key and returns a JWT token.
     * @param request the signup request data
     * @return an authentication response containing the JWT token.
     */
    public AuthenticationResponse signup(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getPublicKey()
        );

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthenticationResponse(token);
    }

    /**
     * Authenticates a user's credentials and returns a JWT token.
     * @param request the login request data
     * @return an authentication response containing the JWT token
     */
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getUsername());
        return new AuthenticationResponse(token);
    }
}
