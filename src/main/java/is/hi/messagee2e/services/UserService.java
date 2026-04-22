package is.hi.messagee2e.services;

import is.hi.messagee2e.dto.response.PublicKeyResponse;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Defines user-related business functions.
 *
 *****************************************************************************/
public interface UserService {
    PublicKeyResponse getPublicKeyByUsername(String username);

}
