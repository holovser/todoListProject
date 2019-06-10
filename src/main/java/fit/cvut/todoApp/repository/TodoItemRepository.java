package fit.cvut.todoApp.repository;

import fit.cvut.todoApp.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {

//    public void removeById(int id);
}


