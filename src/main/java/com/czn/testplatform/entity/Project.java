package com.czn.testplatform.entity;

import com.czn.testplatform.common.enums.InterfaceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "registry_address")
    private String registryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "interface_type")
    private InterfaceType interfaceType;

    @Column(name = "create_time")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
