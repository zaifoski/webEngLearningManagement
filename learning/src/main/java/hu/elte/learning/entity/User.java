package hu.elte.learning.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @JoinColumn
    @ManyToMany(targetEntity = Task.class)
    private List<Task> tasks;
    
    @JoinColumn
    @OneToMany(targetEntity = Solution.class)
    private List<Solution> solutions;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean enabled;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }
}
