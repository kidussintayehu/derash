package com.webproject.derash.security;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class RegistrationController {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   @GetMapping("/register")
   public String registerForm(Model model) {
     model.addAttribute("user", new User());
     return "registeration";
   }

   @PostMapping("/register")
   public String processRegistration( @Valid User user,Errors errors, RegistrationForm form) {
      if(errors.hasErrors()){
        return "registeration";
      }
       userRepository.save(form.toUser(passwordEncoder));
       return "redirect:/login";
   }


}
