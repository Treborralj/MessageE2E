package is.hi.messagee2e.dto.response;

import lombok.Getter;
import lombok.Setter;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
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
