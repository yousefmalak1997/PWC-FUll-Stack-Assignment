package com.pwc.assignment.departments;

import com.pwc.assignment.CurrentUserCredintial;
import com.pwc.assignment.users.Users;
import com.pwc.assignment.users.UsersRepository;
import com.pwc.assignment.users.UsersRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentsService {

    private final DepartmentsRepository departmentsRepository;
    private final CurrentUserCredintial currentUserCredintial;
    private final UsersRepository usersRepository;

    public List<Departments> getAllDeps() {
        return departmentsRepository.findAll();
    }

//    public String createDep(DepartmentsRequest request) {
//        System.out.println(request);
//        if ( request.getDepName().isEmpty() ) {
//            return "fillReqFields";
//        }
//
//        Departments dep = new Departments(request.getDepName(), request.getDepDesc());
//        departmentsRepository.save(dep);
//        return "Success";
//    }

//    public String editDep(Long id, DepartmentsRequest request) {
//
//        Departments departments = departmentsRepository.getById(id);
//
//        if ( request.getDepName().isEmpty() ) {
//            return "fillReqFields";
//        }
//
//        departments.setDepName(request.getDepName());
//        departments.setDepDesc(request.getDepDesc());
//        departmentsRepository.save(departments);
//        return "Success";
//    }

    public ModelAndView deleteDep(Long id) {

        List<String> currentUserRole = currentUserCredintial
                .getCurrentUserCredUserRole()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if ( currentUserRole.contains(UsersRole.MANAGER.getEnumName()) ) {

            List<Users> users = usersRepository.findAllByDepartmentsId(id);
            if ( users.isEmpty() ) {
                departmentsRepository.deleteById(id);
            }

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewDep");
        return modelAndView;
    }

    public Optional<Departments> getDepById(Long id) {
        Optional<Departments> departments = departmentsRepository.findById(id);
        return departments;
    }

    public String creatEditDep(DepartmentsRequest request) {

        if ( request.getDepName().isEmpty() ) {
            return "fillReqFields";
        }

        if ( request.getId() == null ) {

            Departments dep = new Departments(request.getDepName(), request.getDepDesc());
            departmentsRepository.save(dep);
            return "Success";

        } else {

            Departments departments = departmentsRepository.getById(request.getId());
            departments.setDepName(request.getDepName());
            departments.setDepDesc(request.getDepDesc());
            departmentsRepository.save(departments);
            return "Success";

        }
    }
}
