package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrContacts;

    int lastPosition=-1;
    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts){
        this.context=context;
        this.arrContacts=arrContacts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.imgContact.setImageResource(arrContacts.get(position).img);
       holder.txtName.setText(arrContacts.get(position).name);
       holder.txtNumber.setText(arrContacts.get(position).number);

       setAnimation(holder.itemView,position);

       holder.LinearLayoutRow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.add_update_lay);

               EditText edtName=dialog.findViewById(R.id.edtName);
               EditText edtNumber=dialog.findViewById(R.id.edtNumber);
               Button btnAction=dialog.findViewById(R.id.btnAction);
               TextView txtTitle=dialog.findViewById(R.id.txtTitle);

               txtTitle.setText("Update Contacts");

               btnAction.setText("Update");

               edtName.setText(arrContacts.get(position).name);
               edtNumber.setText(arrContacts.get(position).number);

               btnAction.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String name="",number="";
                       if(!edtName.getText().toString().equals("")) {
                           name = edtName.getText().toString();
                       }else{
                           Toast.makeText(context, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                       }
                       if(!edtNumber.getText().toString().equals("")){
                           number=edtNumber.getText().toString();
                       }else{
                           Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                       }
                       arrContacts.set(position,new ContactModel(R.drawable.a,name,number));
                       notifyItemChanged(position);

                       dialog.dismiss();
                   }
               });
               dialog.show();
           }
       });
        holder.LinearLayoutRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure to want to delete ?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Nothing is Deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName,txtNumber;
        ImageView imgContact;

        LinearLayout LinearLayoutRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            txtNumber=itemView.findViewById(R.id.txtNumber);
            imgContact =itemView.findViewById(R.id.imgContact);
            LinearLayoutRow=itemView.findViewById(R.id.LinearLayoutRow);
        }
    }
    private void setAnimation(View viewToAnimate,int position){
        if(position>lastPosition){
//            Animation slideIn= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            Animation slideIn= AnimationUtils.loadAnimation(context,R.anim.rcy_anim);
            viewToAnimate.startAnimation(slideIn);
            lastPosition=position;
        }
    }
}
