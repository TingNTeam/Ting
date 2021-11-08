package com.ting.ting.web;


import com.ting.ting.model.User;
import com.ting.ting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index() {
        return "index_ting";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/callcenter")
    public String callcenter(){
        return "callcenter";
    }


    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/matchlist")
    public String matchlist(){
        return "matchlist";
    }


    @GetMapping("/notice_id")
    public String notice_id(){
        return "notice_id";
    }
    @GetMapping("/open")
    public String open(){
        return "open";
    }
    @GetMapping("/random")
    public String random(){
        return "random";
    }
    @GetMapping("/select")
    public String select(){
        return "select";
    }
    @GetMapping("/terms")
    public String terms(){
        return "terms";
    }
}
