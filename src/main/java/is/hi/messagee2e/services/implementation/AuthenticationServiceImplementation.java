package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.dto.request.LoginRequest;
import is.hi.messagee2e.dto.request.SignupRequest;
import is.hi.messagee2e.dto.response.AuthenticationResponse;
import is.hi.messagee2e.persistence.entities.User;
import is.hi.messagee2e.persistence.repositories.UserRepository;
import is.hi.messagee2e.security.JwtService;
import is.hi.messagee2e.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
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

    public AuthenticationResponse signup(SignupRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
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
    public AuthenticationResponse login(LoginRequest request){
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
