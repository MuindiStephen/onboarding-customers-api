package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.model.User_Role;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.response.MessageResponse;
import com.stevemd.onboarding.repository.RoleRepository;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import com.stevemd.onboarding.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private AuthService authService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     *

     *          * Perform these before steps first attempting to sign up the user
     *          *
     *          * @ControlStatements
     *          * 1.userRepository.existsByName(signUpRequest.getName())
     *          * 2.userRepository.existsByEmail(signUpRequest.getEmail())

     * @param signUpRequest
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//
//        if (userRepository.existsByName(signUpRequest.getName())) {
//            // return new ResponseEntity<>("Username is already taken! Try a new username",HttpStatus.BAD_REQUEST);
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    // .badRequest()
//                    .body("Username is already taken, Try a new username");
//        }
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity<>("Email is taken and is already in use by another account",HttpStatus.BAD_REQUEST);
//        }

        // Now create user's account
//        // UserDTO is the API returns after signup of the new user
//        User signUpUser = authService.signUpUser(signUpRequest);
//
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        for (String role : strRoles) {
//            switch (role) {
//                case "admin":
//                    Role adminRole = roleRepository.findByName(User_Role.ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(adminRole);
//
//                    break;
//                case "moderator":
//                    Role modRole = roleRepository.findByName(User_Role.ROLE_MODERATOR)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(modRole);
//
//                    break;
//                default:
//                    Role userRole = roleRepository.findByName(User_Role.ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(userRole);
//            }
//        }
//
//        signUpUser.setRoles(roles);
        User build = User.builder()
                .name(signUpRequest.getName())
                .build();
        User user= new User();
        user.setName(signUpRequest.getName());
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("User Registered successfully", HttpStatus.ACCEPTED));
        // Alternative statement: return new ResponseEntity<>("User Registered Successfully", HttpStatus.ACCEPTED);
    }
}
