package cn.zhou.demo.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "test_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String nickname;

    private String password;

    private String sex;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "create_date")
    private Date createDate;

}
