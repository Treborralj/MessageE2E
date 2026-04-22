package is.hi.messagee2e.controllers;

import is.hi.messagee2e.dto.request.LoginRequest;
import is.hi.messagee2e.dto.request.SignupRequest;
import is.hi.messagee2e.dto.response.AuthenticationResponse;
import is.hi.messagee2e.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description : Controller that handles authentication related API requests
 *               like signup and login.
 *
 *****************************************************************************/
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Register a new user.
     * @param request the signup data received from the client
     * @return a response containing the generated JWT token
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@Valid @RequestBody SignupRequest request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    /**
     * Authenticates an existing user.
     * @param request the login credentials received from the client
     * @return a response containing the generated JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
