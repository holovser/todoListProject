package fit.cvut.todoApp.service;

import fit.cvut.todoApp.entity.TodoItem;
import fit.cvut.todoApp.repository.TodoItemRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


import java.util.Optional;

@Slf4j
@Service
public class TodoItemServiceImpl implements TodoItemService {


    private TodoItemRepository itemRepository;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        itemRepository.save(new TodoItem("first title", "first details", LocalDate.now()));
        itemRepository.save(new TodoItem("second title", "second details", LocalDate.now()));
        itemRepository.save(new TodoItem("third title", "third details", LocalDate.now()));

    }

    @Override
    public void addItem(TodoItem toAdd) {
        TodoItem newItem = new TodoItem(toAdd.getTitle(), toAdd.getDetails(), toAdd.getDeadline());

        itemRepository.save(newItem);

        log.info("newItem title: {}", newItem.getTitle());
        log.info("itemRepository size: {}", itemRepository.count());
    }

    @Override
    public void removeItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public TodoItem getItem(int id) {
        Optional<TodoItem> item = itemRepository.findById(id);

        if ( item.isPresent() ) {
            return item.get();
        }

        return null;
    }

    @Override
    public void updateItem(@NonNull TodoItem toUpdate) {
        itemRepository.save(toUpdate);
    }


    @Override
    public Iterable<TodoItem> getData() {
        return itemRepository.findAll();
    }
}
