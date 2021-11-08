package com.ting.ting.controller;

import com.ting.ting.model.User;
import com.ting.ting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //    아이디 찾기
    @GetMapping("/find_id")
    public String find_id(Model model, HttpSession session,
                          @RequestParam(required = false, defaultValue = "", value = "school") String school,
                          @RequestParam(required = false, defaultValue = "", value = "number") Long number,
                          @RequestParam(required = false, defaultValue = "", value = "name") String name) {
        User user = userRepository.findByNameAndNumberAndSchool(name, number, school);
        model.addAttribute("user", user);
        return "/user/find_id";
    }

    //    비밀번호변경
    @GetMapping("/changepassword")
    public String changepassword(Model model, HttpSession session,
                                 @RequestParam(required = false, defaultValue = "", value = "present_pass") String present_pass,
                                 @RequestParam(required = false, defaultValue = "", value = "new_pass") String new_pass,
                                 @RequestParam(required = false, defaultValue = "", value = "new_pass_ck") String new_pass_ck) {
//        세션에 저장된 값 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
//        현재 비밀번호 확인
        if (passwordEncoder.matches(present_pass, user.getPassword())) {
//            새로운 비밀번호 확인
            if (new_pass.equals(new_pass_ck)) {
                user.setPassword(passwordEncoder.encode(new_pass));
                userRepository.save(user);
                return "redirect:/account/myinfo";
            }
        }
        return "/user/changepassword";
    }

    @PostMapping("/changepassword/{username}")
    public String changepwd(@PathVariable String username,
                            @RequestParam(required = false, defaultValue = "", value = "new_pass") String new_pass,
                            @RequestParam(required = false, defaultValue = "", value = "new_pass_ck") String new_pass_ck) {

        if (!new_pass.isEmpty()) {
            User user = userRepository.findByUsername(username);
            user.setPassword(passwordEncoder.encode(new_pass));
            userRepository.save(user);
            return "redirect:/account/login";
        }
        return "/user/changepassword";

    }

    //비밀번호 찾기
    @GetMapping("/findpassword")
    public String findpassword(Model model, HttpSession session,
                               @RequestParam(required = false, defaultValue = "", value = "username") String username,
                               @RequestParam(required = false, defaultValue = "", value = "number") String number) {
        if (userRepository.findByUsername(username) != null) {
            User user = userRepository.findByUsername(username);
            String u_name = user.getUsername();
            model.addAttribute("user", user);
            return "/user/findpassword";
        }
        return "/user/findpassword";
    }

    //    비밀번호변경
    @GetMapping("/kproject")
    public String kproject(Model model, HttpSession session,
                           @RequestParam(required = false, defaultValue = "", value = "present_pass") String present_pass,
                           @RequestParam(required = false, defaultValue = "", value = "new_pass") String new_pass,
                           @RequestParam(required = false, defaultValue = "", value = "new_pass_ck") String new_pass_ck) {
//        세션에 저장된 값 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
//        현재 비밀번호 확인
        if (passwordEncoder.matches(present_pass, user.getPassword())) {
//            새로운 비밀번호 확인
            if (new_pass.equals(new_pass_ck)) {
                user.setPassword(new_pass_ck);
                userRepository.save(user);
                return "redirect:/account/myinfo";
            }
        }
        return "/user/kproject";
    }
}