package com.pwc.assignment.users;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping(path = "user")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }

    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/editDep")
    public String editUserDep(@RequestBody UsersRequest request) {
        return usersService.editUserDep(request);
    }

    @PostMapping("/editInfo")
    public String editUserInfo(@RequestBody UsersRequest request) { return usersService.editUserInfo(request); }

    @GetMapping("/getInfo")
    public Users getUserInfo() { return usersService.getUserInfo();}

}
