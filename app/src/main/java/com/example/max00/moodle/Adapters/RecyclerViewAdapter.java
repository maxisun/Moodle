package com.example.max00.moodle.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.max00.moodle.Entity_Class.Student;
import com.example.max00.moodle.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.StudentsViewHolder> {
    private List<Student> students;
    private Context context;
    private LayoutInflater layoutInflater;

    public class StudentsViewHolder extends RecyclerView.ViewHolder{
        TextView textView,nota;

        public StudentsViewHolder(View itemview){
            super(itemview);
            textView = itemview.findViewById(R.id.muestra_viewholder);
            nota = itemview.findViewById(R.id.nota_viewholder);
        }
    }

    public RecyclerViewAdapter(Context context,List<Student> students){
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_viewholder,parent,false);
        StudentsViewHolder studentsViewHolder = new StudentsViewHolder(v);
        return studentsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.textView.setText(student.getCarnet());
        holder.nota.setText(student.getNota());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
