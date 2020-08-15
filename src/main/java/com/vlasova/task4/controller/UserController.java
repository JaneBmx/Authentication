package com.vlasova.task4.controller;

import com.vlasova.task4.entity.User;
import com.vlasova.task4.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public String doJob(HttpServletRequest request) {
        switch (request.getParameter("job")) {
            case "delete": return deleteUser(request);
            case "block":  return blockUser(request);
            case "unblock": return unblockUser(request);
        }
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public String listUsers(Model model, HttpServletRequest request) {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User trueUser = userService.findByUserName(user.getUsername());
        if (trueUser == null || !trueUser.isEnabled()) {
            request.getSession(true).invalidate();
            return "access-denied";
        }
        model.addAttribute("users", userService.getUsers());
        return "list-users";
    }

    @GetMapping("/delete")
    public String deleteUser(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids == null || ids.length == 0) return "redirect:/users/list";
        for (String id : ids) userService.delete(Integer.parseInt(id));
        return "redirect:/users/list";
    }

    @GetMapping("/block")
    public String blockUser(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids == null || ids.length == 0) return "redirect:/users/list";
        for (String id : ids) {
            User user = userService.getUser(Integer.parseInt(id));
            user.setEnabled(false);
            userService.save(user);
        }
        return "redirect:/users/list";
    }

    @GetMapping("/unblock")
    public String unblockUser(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids == null || ids.length == 0) return "redirect:/users/list";
        for (String id : ids) {
            User user = userService.getUser(Integer.parseInt(id));
            user.setEnabled(true);
            userService.save(user);
        }
        return "redirect:/users/list";
    }
}