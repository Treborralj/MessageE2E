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
 * e-mail: ral9@hi.is
 * Description: Controller for handling user related API requests.
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

    /**
     * Returns the public key of the specified user.
     * @param username the username whose public key is requested
     * @return a response containing the user's public key
     */
    @GetMapping("/public-key/{username}")
    public ResponseEntity<PublicKeyResponse> getPublicKey(@PathVariable String username){
        return ResponseEntity.ok(userService.getPublicKeyByUsername(username));
    }
}
