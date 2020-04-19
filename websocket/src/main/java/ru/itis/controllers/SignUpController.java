package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignUpDto;
import ru.itis.services.SignUpService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping
    public String signUp(SignUpDto signUpDto,
                         HttpServletResponse response,
                         ModelMap modelMap) {
        try {
            String cookieValue = signUpService.signUp(signUpDto);
            Cookie cookie = new Cookie("Authorization", cookieValue);
            response.addCookie(cookie);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "sign_up_page";
        }
    }
}
