package hu.cai.ait.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.cai.ait.recyclerviewdemo.R;
import hu.cai.ait.recyclerviewdemo.data.Todo;


public class TodoRecyclerAdapter
        extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private List<Todo> todoList;

    private Context context;

    public TodoRecyclerAdapter(Context context) {
        this.context = context;

        todoList = new ArrayList<Todo>();

        for (int i = 0; i < 20; i++) {
            todoList.add(new Todo("Todo "+i, false));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTodo.setText(todoList.get(position).getTodoText());
        holder.cbDone.setChecked(todoList.get(position).isDone());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbDone;
        private TextView tvTodo;

        public ViewHolder(View itemView) {
            super(itemView);

            cbDone = (CheckBox) itemView.findViewById(R.id.cbDone);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
        }
    }
}