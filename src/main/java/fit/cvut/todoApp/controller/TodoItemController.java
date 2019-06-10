package fit.cvut.todoApp.controller;

import fit.cvut.todoApp.entity.TodoItem;
import fit.cvut.todoApp.service.TodoItemService;
import fit.cvut.todoApp.util.AttributeNames;
import fit.cvut.todoApp.util.Mappings;
import fit.cvut.todoApp.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Slf4j
@Controller
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;

    }



    @GetMapping(Mappings.ITEMS)
    public String items(Model model) {

        model.addAttribute("todoData", todoItemService.getData());

        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id,
                              Model model) {

        TodoItem todoItem = todoItemService.getItem(id);

        if ( todoItem == null ) {
            todoItem = new TodoItem("","", LocalDate.now());
        }

        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute TodoItem todoItem) {
        log.info("todoItem from form = {}", todoItem);

        if ( todoItem.getId() == 0 ) {
            todoItemService.addItem(todoItem);
        } else {
            todoItemService.updateItem(todoItem);
        }

        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        todoItemService.removeItem(id);

        return "redirect:/" + Mappings.ITEMS;
    }


    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {

        model.addAttribute(AttributeNames.TODO_ITEM, todoItemService.getItem(id));

        return ViewNames.VIEW_ITEM;
    }


    @ModelAttribute
    public Iterable<TodoItem> todoData() {
        return todoItemService.getData();
    }

}
