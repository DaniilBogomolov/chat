package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignInDto;
import ru.itis.services.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping
    public String getSignInPage() {
        return "sign_in_page";
    }


    @PostMapping
    public String signIn(SignInDto signInDto,
                         HttpServletResponse response,
                         ModelMap modelMap) {
        try {
            String cookieValue = signInService.signIn(signInDto);
            response.addCookie(new Cookie("Authorization", cookieValue));
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "sign_in_page";
        }
    }
}
