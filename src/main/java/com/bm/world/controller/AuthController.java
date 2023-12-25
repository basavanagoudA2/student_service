package com.bm.world.controller;

import com.bm.world.ApplicationConstants;
import com.bm.world.ObjectMapper;
import com.bm.world.model.ERole;
import com.bm.world.model.Role;
import com.bm.world.model.User;
import com.bm.world.payload.LoginRequest;
import com.bm.world.payload.MessageResponse;
import com.bm.world.payload.SignUpRequest;
import com.bm.world.repository.RoleRepository;
import com.bm.world.repository.UserRepository;
import com.bm.world.utilts.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = ApplicationConstants.API_AUTH)
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping(ApplicationConstants.LOGIN)
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().headers(
                httpHeaders -> {
                    try {
                        httpHeaders.add("jwt_token",jwtUtils.generateJwtToken(authentication));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).build();
    }
    @PostMapping(ApplicationConstants.REGISTER)
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        //userName exist or not checking
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("already user is found"));
        }
        //checking emailId availability
        if (userRepository.existsByEmailId(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("EmailId already found! please use different"));
        }
        //create user Account
        //added new comments
        User user = new User();
        ObjectMapper.dtoToModel(user, signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Set<Role> userRolesSet = user.getRoles();
        String[] roles = signUpRequest.getRoles();
        if (roles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
            userRolesSet.add(userRole);
        } else {
            Stream.of(roles).forEach(
                    role -> {
                        switch (role) {
                            case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: user role not found"));
                                userRolesSet.add(adminRole);
                                break;
                            case "mod":
                                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                        .orElseThrow(() -> new RuntimeException("Error:mode role not found"));
                                userRolesSet.add(modRole);
                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
                                userRolesSet.add(userRole);
                        }
                    }
            );
        }
        user.setRoles(userRolesSet);
        userRepository.save(user);
        userRolesSet.clear();
        return ResponseEntity.ok("Registration is successfully completed");
    }
}
