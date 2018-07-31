package p.dudekjunior.reminder.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="user_task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "task_context")
    private String taskContext;
    @Column(name = "task_date")
    private LocalDateTime taskDate;
    @Column(name = "is_done")
    private boolean isDone;
    @ManyToOne
    @JoinColumn(name = "task_user_id")
    private UserEntity user;
}
