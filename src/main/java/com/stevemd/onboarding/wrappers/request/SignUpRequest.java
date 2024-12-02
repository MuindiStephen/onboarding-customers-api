    package com.stevemd.onboarding.wrappers.request;


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

        @Size(min = 3, max = 100)
        @NotEmpty(message = "Username is too short or invalid")
        private String username;

        @NotEmpty(message = "Email should not be empty or invalid")
        @Size(max = 100)
        @Email
        private String email;

        @Size(min = 6, max = 100)
        @NotEmpty(message = "Password is empty or too short")
        private String password;

        @Size(min = 6, max = 100)
        @NotEmpty(message = "Confirm Password is empty or too short or does not match")
        private String confirmPassword;

    //    private Set<RoleName> roles;
    //
    //    public Set<RoleName> getRoles() {
    //        return roles;
    //    }
    //
    //    public void setRoles(Set<RoleName> roles) {
    //        this.roles = roles;
    //    }


    }
