package is.hi.messagee2e.services;

import is.hi.messagee2e.dto.request.LoginRequest;
import is.hi.messagee2e.dto.request.SignupRequest;
import is.hi.messagee2e.dto.response.AuthenticationResponse;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Defines authentication related business functions.
 *
 *****************************************************************************/
public interface AuthenticationService {

    public AuthenticationResponse signup(SignupRequest request);

    public AuthenticationResponse login(LoginRequest request);
}
