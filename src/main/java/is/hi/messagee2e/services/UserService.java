package is.hi.messagee2e.services;

import is.hi.messagee2e.dto.response.PublicKeyResponse;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
public interface UserService {
    PublicKeyResponse getPublicKeyByUsername(String username);
    String getCurrentUsername(String username);
}
