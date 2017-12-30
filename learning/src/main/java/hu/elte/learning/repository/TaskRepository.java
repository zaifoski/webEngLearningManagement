
package hu.elte.learning.repository;
import hu.elte.learning.entity.Task;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sofia
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
    
}
