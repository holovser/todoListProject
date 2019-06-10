package fit.cvut.todoApp.service;


import fit.cvut.todoApp.entity.TodoItem;
import fit.cvut.todoApp.model.TodoData;

public interface TodoItemService {

    public void addItem(TodoItem item);

    public void removeItem(int id);

    public TodoItem getItem(int id);

    public void updateItem(TodoItem item);

    public  Iterable<TodoItem> getData();


}
