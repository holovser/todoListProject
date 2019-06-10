package fit.cvut.todoApp.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class TodoItem {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String details;
    private LocalDate deadline;


    public TodoItem(String title, String details, LocalDate deadline) {
        this.title = title;
        this.details = details;
        this.deadline = deadline;
    }

    public TodoItem() {
    }

    public String message() {
        return Integer.toString(id);
    }

}
