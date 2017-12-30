
package hu.elte.learning.entity;
/**
 *
 * @author sofia
 */
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution implements Serializable {
    
    @JoinColumn
    @ManyToOne(targetEntity = Task.class, optional = false)
    private Task task;
    
    @JoinColumn
    @ManyToOne(targetEntity = User.class, optional = false)
    private User user;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank
    private String solution_text;
}
