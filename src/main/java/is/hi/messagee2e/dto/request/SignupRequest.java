package is.hi.messagee2e.dto.request;

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
public class SignupRequest {
    private String username;
    private String password;
    private String publicKey;

    public SignupRequest() {
    }

}
