package com.pwc.assignment.UsersProjectsMapping;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsersProjectsMappingRequest {
    private Long projectId;
    private Long userId;
    private String projectName;
    private String userName;
}
