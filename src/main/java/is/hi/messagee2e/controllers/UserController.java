package is.hi.messagee2e.controllers;

import is.hi.messagee2e.dto.response.PublicKeyResponse;
import is.hi.messagee2e.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : Controller for handling user details.
 *
 *****************************************************************************/
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication){
        return ResponseEntity.ok("Hello " + authentication.getName());
    }

    @GetMapping("/public-key/{username}")
    public ResponseEntity<PublicKeyResponse> getPublicKey(@PathVariable String username){
        return ResponseEntity.ok(userService.getPublicKeyByUsername(username));
    }
}
