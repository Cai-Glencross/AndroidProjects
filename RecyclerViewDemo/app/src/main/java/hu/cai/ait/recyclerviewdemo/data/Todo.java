package hu.cai.ait.recyclerviewdemo.data;

/**
 * Created by caiglencross on 3/20/17.
 */

public class Todo {
    private String todoText;
    private boolean done;

    public Todo(String todoText, boolean done) {
        this.todoText = todoText;
        this.done = done;
    }

    public String getTodoText() {
        return todoText;
    }

    public boolean isDone() {
        return done;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
