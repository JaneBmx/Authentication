package com.vlasova.task4.config;

import com.vlasova.task4.entity.User;
import com.vlasova.task4.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    public CustomAuthenticationSuccessHandler(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        theUser.setLastOnline(new Timestamp(System.currentTimeMillis()));
        userService.save(theUser);
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);
        response.sendRedirect(request.getContextPath() + "/");
    }
}