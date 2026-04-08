package is.hi.messagee2e.dto.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Passowrd is required")
    private String password;
    @NotBlank(message = "Public key is required")
    private String publicKey;

    public SignupRequest() {
    }

}
