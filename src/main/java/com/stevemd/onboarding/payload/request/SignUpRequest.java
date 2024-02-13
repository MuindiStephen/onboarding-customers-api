package com.stevemd.onboarding.payload.request;


import com.stevemd.onboarding.model.RoleName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 6, max = 100)
    @NotEmpty(message = "Email should not be empty")
    private String password;

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    private Set<RoleName> roles;
}
