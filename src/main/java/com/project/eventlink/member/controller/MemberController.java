package com.project.eventlink.member.controller;


import com.project.eventlink.entity.Role;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.dto.LoginForm;
import com.project.eventlink.member.service.MemberService;
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
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/")
    public String home() {
        return "views/home";
    }


    @GetMapping("/joinSelect")
    public String joinSelect() {
        return "views/joinSelect";
    }

    @GetMapping("/joinUser")
    public String joinUserForm(JoinForm joinForm) {
        return "views/joinUser";
    }

    @PostMapping("/joinUser")
    public String joinUser(@Validated JoinForm joinForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "views/joinUser";
        }

        if (memberService.loadUserByUsername(joinForm.getUserId()) != null) {
            bindingResult.reject("duplicate", "중복된 Id 입니다. ");
            return "views/joinUser";
        }

        joinForm.setRole(Role.USER);
        memberService.save(joinForm);
        redirectAttributes.addAttribute("userId", joinForm.getUserId());

        return "redirect:/joinSuccess";
    }

    @GetMapping("/joinProvider")
    public String joinProviderForm(JoinForm joinForm) {
        return "views/joinProvider";
    }

    @PostMapping("/joinProvider")
    public String joinProvider(@Validated JoinForm joinForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "views/joinUser";
        }

        if (memberService.loadUserByUsername(joinForm.getUserId()) != null) {
            bindingResult.reject("duplicate", "중복된 Id 입니다. ");
            return "views/joinUser";
        }

        joinForm.setRole(Role.PROVIDER);
        memberService.save(joinForm);
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
        if (memberService.loadUserByUsername(loginForm.getMemberId()) != null) {
            return "redirect:/home";
        }

        return "views/login";
    }
}
