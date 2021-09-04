package com.pwc.assignment.UsersProjectsMapping;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersProjectsMapping {

    @Id
    @SequenceGenerator(
            name = "upm_sequence",
            sequenceName = "upm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "upm_sequence"
    )
    private Long id;
    private Long projectId;
    private Long userId;
    private String projectName;
    private String userName;

    public UsersProjectsMapping(Long projectId, Long userId, String projectName, String userName) {
        this.projectId = projectId;
        this.userId = userId;
        this.projectName = projectName;
        this.userName = userName;
    }
}
