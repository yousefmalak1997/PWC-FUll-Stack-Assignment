package com.pwc.assignment.projects;

import com.pwc.assignment.CurrentUserCredintial;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private CurrentUserCredintial currentUserCredintial;

    public String createEditProject(ProjectsRequest request) {

        if ( request.getProjectName().isEmpty() ) {
            return "fillReqFields";
        }

        if ( request.getId() == null ) {

            Projects projects = new Projects(
                    request.getProjectName(),
                    request.getProjectDesc(),
                    request.getProjectTimeEst(),
                    request.getProjectTimeTaken()
            );

            projectsRepository.save(projects);
            return "Success";

        } else {

            Projects projects = projectsRepository.getById(request.getId());
            projects.setProjectName(request.getProjectName());
            projects.setProjectDesc(request.getProjectDesc());
            projects.setProjectTimeEst(request.getProjectTimeEst());
            projects.setProjectTimeTaken(request.getProjectTimeTaken());

            projectsRepository.save(projects);
            return "Success";
        }
    }

    public List<Projects> getAllPros() {
        return projectsRepository.findAll();
    }

    public ModelAndView deletePros(Long id) {

        projectsRepository.deleteById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewProjs");
        return modelAndView;

    }

    public Optional<Projects> getProById(Long id) {
        Optional<Projects> projects = projectsRepository.findById(id);
        return projects;
    }
}
