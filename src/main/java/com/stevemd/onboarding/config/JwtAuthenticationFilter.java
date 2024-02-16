//package com.stevemd.onboarding.config;
//
//import com.stevemd.onboarding.service.AuthService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtService;
//    private final AuthService authService;
//    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
//        String message = "";
//        try {
//            LoginDto creds = new ObjectMapper().readValue(req.getInputStream(), LoginDto.class);
//            String username = creds.getUsername();
//            String password = creds.getPassword();
//
//            var userOp = userService.findByEmail(username);
//            if(userOp.isEmpty())message = "Incorrect email please check and retry";
//            else{
//                message = "Password incorrect";
//            }
//
//            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            log.warn("Authentication failed for hostname {} Ip address: {}", req.getLocalName(), req.getLocalAddr());
//            res.setStatus(401);
//            try {
//                PrintWriter out = res.getWriter();
//                res.setContentType("application/json");
//                res.setCharacterEncoding("UTF-8");
//                out.print(new ObjectMapper().writeValueAsString(new GenericResponse(res.getStatus(), message)));
//                out.flush();
//            } catch (Exception ignored) {}
//
//
//        }
//        return null;
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
//
//        log.warn("Authentication Successful User {} Ip address: {}", ((User) auth.getPrincipal()).getUsername(), req.getRemoteAddr());
//        String username = ((User) auth.getPrincipal()).getUsername();
//
//        Users userOp = userService.findByEmail(username).get();
//        String token = jwtService.generateToken(username);
//
//        var loginResponse = new LoginResponse(token, userOp.getRole(), res.getStatus(), "Login Successful", userOp);
//
//        PrintWriter out = res.getWriter();
//        res.setContentType("application/json");
//        res.setCharacterEncoding("UTF-8");
//        out.print(new ObjectMapper().writeValueAsString(loginResponse));
//        out.flush();
//    }
//    }
