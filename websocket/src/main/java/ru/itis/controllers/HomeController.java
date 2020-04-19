package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getHome(@CookieValue(name = "Authorization", required = false) String cookieValue,
                          ModelMap modelMap) {
        User user = userService.findUserByCookie(cookieValue);
        UserDto userDto = user == null ? null : UserDto.from(user);
        modelMap.addAttribute("user", userDto);
        return "home";
    }
}
