package edu.elon.NearU.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private Long hash_pass;

    private Integer isStudent;

    private Integer isOrganization;


    // Getters and setters

}
