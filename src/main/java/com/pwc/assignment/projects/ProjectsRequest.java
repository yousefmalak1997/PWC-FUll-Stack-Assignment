package com.pwc.assignment.projects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProjectsRequest {
    private Long id;
    private String projectName;
    private String projectDesc;
    private Integer projectTimeEst;
    private Integer projectTimeTaken;
}
