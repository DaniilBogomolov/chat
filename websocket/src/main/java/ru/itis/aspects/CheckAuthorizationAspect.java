package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.itis.handlers.AccessDeniedException;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class CheckAuthorizationAspect {

    @Autowired
    private UserService userService;

    @Before(value = "execution(* ru.itis.controllers.RoomController.*(..))")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Optional<Cookie> cookieAuth = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("Authorization")).findFirst();
        if (!(cookieAuth.isPresent() && userService.findUserByCookie(cookieAuth.get().getValue()) != null)) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
