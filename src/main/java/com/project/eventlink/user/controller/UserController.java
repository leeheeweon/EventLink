package com.project.eventlink.user.controller;


import com.project.eventlink.user.dto.JoinForm;
import com.project.eventlink.user.dto.LoginForm;
import com.project.eventlink.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String joinForm(JoinForm joinForm) {
        return "views/join";
    }

    @PostMapping("/join")
    public String join(@Validated JoinForm joinForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "views/join";
        }

        if (userService.loadUserByUsername(joinForm.getUserId()) != null) {
            bindingResult.reject("duplicate", "중복된 Id 입니다. ");
            return "views/join";
        }

        userService.save(joinForm);
        redirectAttributes.addAttribute("userId", joinForm.getUserId());

        return "redirect:/joinSuccess";
    }

    @GetMapping("joinSuccess")
    public String joinSuccessForm(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "views/joinSuccess";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "views/login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm) {
        if (userService.loadUserByUsername(loginForm.getUserId()) != null) {
            return "redirect:/home";
        }

        return "views/login";
    }

    @GetMapping("/home")
    public String home() {
        return "views/home";
    }

    @GetMapping("/hello")
    public String hello() {
        return "views/hello";
    }

}
