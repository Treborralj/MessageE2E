package is.hi.messagee2e.dto.response;

import lombok.Getter;
import lombok.Setter;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: DTO containing a token that returns after successful authentication
 *
 *****************************************************************************/
@Getter
@Setter
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse() {
    }
}
