package com.square.health.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {
            return "redirect:/home";


            /*String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = this.authenticationManager.authenticate(token);
            // vvv THIS vvv
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
*/

        }
        return "/login";
    }

}
