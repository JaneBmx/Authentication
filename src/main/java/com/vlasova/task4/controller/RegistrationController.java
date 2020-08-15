package com.vlasova.task4.controller;

import com.vlasova.task4.entity.User;
import com.vlasova.task4.service.UserService;
import com.vlasova.task4.user.CrmUser;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("crmUser", new CrmUser());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationFormPost(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
                                              final RedirectAttributes redirectAttributes,
                                              BindingResult theBindingResult) {
        redirectAttributes.addFlashAttribute("crmUser", theCrmUser);
        redirectAttributes.addFlashAttribute("bindingResult", theBindingResult);
        return "redirect:/register/processRegistrationForm";
    }

    @GetMapping("/processRegistrationForm")
    public String processRegistrationFormGet(Model model) {
        Map<String, Object> map = model.asMap();
        CrmUser crmUser = (CrmUser) map.get("crmUser");
        BindingResult bindingResult = (BindingResult) map.get("bindingResult");
        String userName = crmUser.getUserName();
        logger.info("Processing registration form for: " + userName);
        if (bindingResult.hasErrors()) return "registration-form";
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("registrationError", "User name already exists.");
            logger.warning("User name already exists.");
            return "registration-form";
        }
        userService.save(crmUser);
        logger.info("Successfully created user: " + userName);
        return "registration-confirmation";
    }
}