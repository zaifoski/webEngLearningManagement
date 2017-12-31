
package hu.elte.learning.entity;

/**
 *
 * @author sofia
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @OneToMany(targetEntity = Solution.class, mappedBy = "task")
    private List<Solution> solutions;
    
    @JoinColumn
    @ManyToMany(targetEntity = User.class)
    private List<User> users;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank
    private String task_name;
    
    @Column(nullable=true)
    private String task_description;
    /*
    @Column(nullable=false, length=7, unique = true)
    private String neptun;
*/
}
