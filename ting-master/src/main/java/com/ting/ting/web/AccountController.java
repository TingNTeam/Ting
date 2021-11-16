package com.ting.ting.web;

import com.ting.ting.entity.User;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping("/user/login")
    public String login() {

        return "/user/login";
    }

    @PostMapping("/user/register")
    public String register() {
        return "/user/register";
    }

//    @PostMapping("/register")
//    public String register(User user) {
//        userService.save(user);
//        return  "redirect:/";
//    }

    @GetMapping("/myinfo")
    public String myinfo(Model model, HttpSession session){

        return "/account/myinfo";
    }
}
