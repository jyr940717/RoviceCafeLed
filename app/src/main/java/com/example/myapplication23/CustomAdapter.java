package com.example.myapplication23;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.particles.ParticleSystem;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<RovinText> arrayList;
    private Context context;

    public CustomAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    //    public boolean isChanged(String str,int position){
//        switch (str) {
//            case "cook":
//                str = arrayList.get(position).getCook();
//                break;
//            case "Dance":
//                str = arrayList.get(position).getDance();
//                break;
//            case "load":
//                str = arrayList.get(position).getLoad();
//                break;
//        }
//        return !str.equals(str);
//    }
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String url = "https://firebasestorage.googleapis.com/v0/b/my-application23-5229d.appspot.com/o/ro-font-designs-logo-icons-260nw-1586099149.webp?alt=media&token=47e786fd-4699-4dda-9e46-b230a2a4a96d";
        String url2 = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif";
        String url3 = "https://mymodernmet.com/wp/wp-content/uploads/2014/02/3d-gifs-3.gif";
        String url4 = "http://storage.enuri.info/pic_upload/knowbox2/201912/091752569201912267223846e-747d-4790-915f-4ddf6be55f4e.gif";
        String url5 = "https://cdn.dribbble.com/users/202586/screenshots/3800253/media/a137e368dac3ed5dfadf79010acc6b94.gif";
        String url6 = "https://c.tenor.com/kUEzB3p6wH4AAAAC/teletubbies.gif";
        String str = "|";

//        Glide.with(holder.itemView)
//                .load(url4)
//                .apply(new RequestOptions().override(1920, 158))
//                .into(holder.rovin_img2);
//        Glide.with(holder.itemView)
//                .load(url5)
//                .apply(new RequestOptions().override(1920, 158))
//                .into(holder.rovin_img3);

        Handler handler = new Handler();
        String rovinAct = arrayList.get(position).getRovin_text();
        String rovinDance = arrayList.get(position).getDance();
        String rovinCook = arrayList.get(position).getCook();
        String rovinLoad = arrayList.get(position).getLoad();
        String imoji = new String(Character.toChars(0x1F349));

        switch (rovinAct) {
            case "cook":
                Glide.with(holder.itemView)
                        .load(url3)
                        .apply(new RequestOptions().override(1920, 158))
                        .into(holder.rovin_img);
                break;

            case "load":
                Glide.with(holder.itemView)
                        .load(url6)
                        .apply(new RequestOptions().override(1920, 158))
                        .into(holder.rovin_img);
                break;
        }
//        if (!rovinAct.equals("cook")){
//            Glide.with(holder.itemView).clear(holder.rovin_img);}
//        if (!rovinAct.equals("load")){
//            Glide.with(holder.itemView).clear(holder.rovin_img);}
        switch (rovinAct) {
            case "cook":
                for (int i = 0; i < rovinCook.length(); i++) {
                    final int finalI = i + 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalI % 2 == 0) {
                                holder.rovin_txt.setText(rovinCook.substring(0, finalI));
                            }
                            if (finalI % 2 == 1) {
                                holder.rovin_txt.setText(rovinCook.substring(0, finalI) + str);
                            }
                            if (finalI == rovinCook.length()) {
                                int i = 0;
                                if (i == 0) {
                                    holder.rovin_txt.setText(rovinCook+str);
                                    i = 1;

                                }
                                if (i == 0) {
                                    holder.rovin_txt.setText(rovinCook);
                                    i = 0;
                                }


                            }
                        }
                    }, i * 250);
                }
                break;
            case "dance":
                for (int i = 0; i < rovinDance.length(); i++) {
                    final int finalI = i + 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalI % 2 == 0) {
                                holder.rovin_txt.setText(rovinDance.substring(0, finalI));
                            }
                            if (finalI % 2 == 1) {
                                holder.rovin_txt.setText(rovinDance.substring(0, finalI) + str);
                            }
                        }
                    }, i * 250);
                }
                break;
            case "load":
                for (int i = 0; i < rovinLoad.length(); i++) {
                    final int finalI = i + 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalI % 2 == 1) {
                                holder.rovin_txt.setText(rovinLoad.substring(0, finalI));
                            }
                            if (finalI % 2 == 0) {
                                holder.rovin_txt.setText(rovinLoad.substring(0, finalI) + str);
                            }
                            if (finalI == rovinLoad.length()) {
                                holder.rovin_txt.setText(rovinLoad);
                            }
                        }
                    }, i * 250);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView rovin_txt;
        ImageView rovin_img;
        ImageView rovin_img2;
        ImageView rovin_img3;

        public CustomViewHolder(View itemView) {
            super(itemView);
            rovin_img = itemView.findViewById(R.id.rovin_img);
            rovin_img2 = itemView.findViewById(R.id.rovin_img2);
            rovin_img3 = itemView.findViewById(R.id.rovin_img3);
            rovin_txt = itemView.findViewById(R.id.rovin_txt);


        }
    }
}