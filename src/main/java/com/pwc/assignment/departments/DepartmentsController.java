package com.pwc.assignment.departments;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "departments")
@AllArgsConstructor
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    @GetMapping()
    public List<Departments> getAllDeps() {
        return departmentsService.getAllDeps();
    }

//    @PostMapping("/create")
//    public String createDep(@RequestBody DepartmentsRequest request) {
//        return departmentsService.createDep(request);
//    }

//    @PostMapping("/edit/{id}")
//    public String editDep(@PathVariable Long id, @RequestBody DepartmentsRequest request) {
//        return departmentsService.editDep(id, request);
//    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        return departmentsService.deleteDep(id);
    }


    @PostMapping("/createEdit")
    public String editDep(@RequestBody DepartmentsRequest request) {
        return departmentsService.creatEditDep(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Departments> getDepById(@PathVariable Long id) {
        Optional<Departments> departments = departmentsService.getDepById(id);
        return new ResponseEntity(departments, HttpStatus.OK);
    }
}
