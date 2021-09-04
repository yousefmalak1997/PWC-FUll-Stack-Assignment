package com.pwc.assignment.departments;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DepartmentsRequest {
    private Long id;
    private String depName;
    private String depDesc;
}
