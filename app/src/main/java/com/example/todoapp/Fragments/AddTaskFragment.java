package com.example.todoapp.Fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.AlarmActivity;
import com.example.todoapp.Model.DialogCloseListener;
import com.example.todoapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Utils.DatabaseHandler;

/**
 * Classe qui s'occupe de l'ajout d'une nouvelle tâche dans la liste, elle ouvre une DialogFragment
 * pour que l'utilisateur ajoute le nom de la liste
 * Il faut ajouter un TextWatcher pour récupérer l'input de l'utilisateur et changer dans la bdd en même temps
 */
public class AddTaskFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";

    private EditText my_et_task;    //
    private Button btn_add_task;
    private ImageView my_clock_fab;

    private DatabaseHandler my_db;

    /**
     * Crée une instance d'une nouvelle tâche (ajout d'un BottomSheetDialogFragment),
     * @return un nouvel objet AddTaskFragment
     * @see BottomSheetDialogFragment
     */
    public static AddTaskFragment newInstance(){
        return new AddTaskFragment();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

              View v = inflater.inflate( R.layout.add_task_layout, container, false );
        getDialog().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE ); // deprecated, mais voir apres

        // sinon : android:windowSoftInputMode="adjustResize"
        //mettre ça dans la balise concernée dans xml (https://stackoverflow.com/questions/16411056/how-to-adjust-layout-when-soft-keyboard-appears)
        //return super.onCreateView( inflater, container, savedInstanceState );

        return v;
    }

    /**
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_et_task = requireView().findViewById(R.id.et_task_desc1);
        btn_add_task = getView().findViewById(R.id.btn_add_task);

        my_db = new DatabaseHandler(getActivity());
        my_db.openDatabase();

        boolean isUpdate = false;

        final Bundle bundle = getArguments();

        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            my_et_task.setText(task);
            assert task != null;

            if(task.length()>0 ){   //condition "unique" fait qu'il n'y a pas besoin de tester la bdd
                btn_add_task.setTextColor(Color.DKGRAY);

            }
        }



        my_et_task.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    btn_add_task.setEnabled(false);
                    btn_add_task.setTextColor(Color.GRAY);
                }
                else{
                    btn_add_task.setEnabled(true);
                    btn_add_task.setTextColor(Color.DKGRAY);    //NK
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = my_et_task.getText().toString();
                if(finalIsUpdate){
                    my_db.updateTask(bundle.getInt("id"), text);
                }
                else {
                    ToDoModel task = new ToDoModel();
                    task.setTask(text);
                    task.setStatus(0);
                    my_db.insertTask(task);
                }
                dismiss();
            }
        });


    }



    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }

    /**
     * Redirection vers les rappels
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v == my_clock_fab){
            createAlarmActivity();
        }
    }

    private void createAlarmActivity(){
        Intent i = new Intent(getContext(), AlarmActivity.class);
        startActivity(i);
    }
}
