package com.pwc.assignment.UsersProjectsMapping;

import com.pwc.assignment.CurrentUserCredintial;
import com.pwc.assignment.departments.Departments;
import com.pwc.assignment.users.UsersRole;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersProjectsMappingService {

    private final UsersProjectsMappingRepository usersProjectsMappingRepository;
    private final CurrentUserCredintial currentUserCredintial;

    public List<UsersProjectsMapping> getAllupm() {

        List<String> currentUserRole = currentUserCredintial
                .getCurrentUserCredUserRole()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if ( currentUserRole.contains(UsersRole.MANAGER.getEnumName()) ) {
            return usersProjectsMappingRepository.findAll(Sort.by(Sort.Direction.ASC, "projectId"));
        } else {
            Long currentUserId = currentUserCredintial.getCurrentUserCredUserId();
            return usersProjectsMappingRepository.findAllByUserId(currentUserId);
        }

    }

    public String addUPM(UsersProjectsMappingRequest request) {

        if ( request.getUserId() == null || request.getProjectId() == null ) {
            return "fillReqFields";
        }

        Optional<UsersProjectsMapping> alreadyExists = usersProjectsMappingRepository
                                                        .findAllByUserIdAndProjectId(
                                                            request.getUserId(),
                                                            request.getProjectId());

        if ( alreadyExists.isEmpty() ) {

            UsersProjectsMapping usersProjectsMapping = new UsersProjectsMapping(
                                                            request.getProjectId(),
                                                            request.getUserId(),
                                                            request.getProjectName(),
                                                            request.getUserName());

            usersProjectsMappingRepository.save(usersProjectsMapping);

            return "Success";
        } else {
            return "alreadyExists";
        }


    }

    public ModelAndView deleteDep(Long id) {

        List<String> currentUserRole = currentUserCredintial
                .getCurrentUserCredUserRole()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if ( currentUserRole.contains(UsersRole.MANAGER.getEnumName()) ) {
            usersProjectsMappingRepository.deleteById(id);
        } else {
            Long currentUserId = currentUserCredintial.getCurrentUserCredUserId();
            Long assignmentUserId = usersProjectsMappingRepository.getById(id).getUserId();

            if ( currentUserId == assignmentUserId ) {
                usersProjectsMappingRepository.deleteById(id);
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewAssignments");
        return modelAndView;
    }
}
