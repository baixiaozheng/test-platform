package com.czn.testplatform.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bxz
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = -5576565082619620383L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;
}
