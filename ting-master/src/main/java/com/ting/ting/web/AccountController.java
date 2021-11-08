package com.ting.ting.web;

import com.ting.ting.model.User;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {

        return "/account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return  "redirect:/";
    }

    @GetMapping("/myinfo")
    public String myinfo(Model model, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails =(UserDetails)principal;
        String username = ((UserDetails) principal).getUsername();
       User user = userRepository.findByUsername(username);
            model.addAttribute("user",user);
//        Enumeration enums = session.getAttributeNames();
//        while (enums.hasMoreElements()){
//            String key = (String)enums.nextElement();
//            System.out.println(key+": "+ session.getAttribute(key));
//        }
        return "/account/myinfo";
    }
}
