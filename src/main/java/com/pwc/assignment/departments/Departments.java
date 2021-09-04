package com.pwc.assignment.departments;

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
public class Departments {

    @Id
    @SequenceGenerator(
            name = "departments_sequence",
            sequenceName = "departments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "departments_sequence"
    )
    private Long id;

    @Column(nullable = false, length = 45)
    private String depName;

    @Column(columnDefinition="TEXT")
    private String depDesc;

//    @OneToMany(mappedBy = "departments",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Users> uses;

    Departments(String depName, String depDesc) {
        this.depName = depName;
        this.depDesc = depDesc;
    }


}
