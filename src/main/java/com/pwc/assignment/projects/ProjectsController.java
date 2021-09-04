package com.pwc.assignment.projects;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "projects")
@AllArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping()
    public ResponseEntity<List<Projects>> getAllPros() {
        List<Projects> projects = projectsService.getAllPros();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        return projectsService.deletePros(id);
    }

    @PostMapping("/createEdit")
    public String createDep(@RequestBody ProjectsRequest request) { return projectsService.createEditProject(request); }

    @GetMapping("/get/{id}")
    public ResponseEntity<Projects> getDepById(@PathVariable Long id) {
        Optional<Projects> projects = projectsService.getProById(id);
        return new ResponseEntity(projects, HttpStatus.OK);
    }
}
