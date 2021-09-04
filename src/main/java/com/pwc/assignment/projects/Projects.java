package com.pwc.assignment.projects;

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
public class Projects {

    @Id
    @SequenceGenerator(
            name = "projects_sequence",
            sequenceName = "projects_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "projects_sequence"
    )
    private Long id;

    @Column(nullable = false, length = 45)
    private String projectName;

    @Column(columnDefinition="TEXT")
    private String projectDesc;


    private Integer projectTimeEst;
    private Integer projectTimeTaken;

    public Projects(String projectName, String projectDesc, Integer projectTimeEst, Integer projectTimeTaken) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectTimeEst = projectTimeEst;
        this.projectTimeTaken = projectTimeTaken;
    }
}
