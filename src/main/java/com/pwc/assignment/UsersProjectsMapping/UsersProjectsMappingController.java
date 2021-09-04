package com.pwc.assignment.UsersProjectsMapping;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "upm")
@AllArgsConstructor
public class UsersProjectsMappingController {

    private final UsersProjectsMappingService usersProjectsMappingService;

    @GetMapping()
    public List<UsersProjectsMapping> getAllupm() {
        return usersProjectsMappingService.getAllupm();
    }

    @PostMapping("/add")
    public String addUPM(@RequestBody UsersProjectsMappingRequest request) {
        return usersProjectsMappingService.addUPM(request);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        return usersProjectsMappingService.deleteDep(id);
    }
}
