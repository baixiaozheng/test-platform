package com.czn.testplatform.entity;

import com.czn.testplatform.common.enums.RequestMethodType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "interface")
public class Interface implements Serializable {
    private static final long serialVersionUID = -4071406265550408094L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接口名
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 所属项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 提交类型，GET，POST等
     */
    @Column(name = "method_type")
    @Enumerated(EnumType.STRING)
    private RequestMethodType methodType;

    @Column(name = "create_time")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
