package is.hi.messagee2e.dto.response;

import lombok.Getter;
import lombok.Setter;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: DTO containing a user's public key.
 *
 *****************************************************************************/
@Getter
@Setter
public class PublicKeyResponse {
    private String username;
    private String publicKey;

    public PublicKeyResponse() {
    }

    public PublicKeyResponse(String username, String publicKey) {
        this.username = username;
        this.publicKey = publicKey;
    }
}
