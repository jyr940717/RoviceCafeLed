package com.example.myapplication23;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;




import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;

    private String TAG = "MainActivity";
    private RecyclerView.Adapter adapter;
    private ArrayList<RovinText> arrayList;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    public Handler handler = new Handler();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void updateText(String str) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                DocumentReference docRef = db.collection("user").document("rIDv4Jy1sNg8Hr0OL3gS").collection("user").document("user");
                docRef.update("rovin_text", str)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: ");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "onFailure: ", e);
                    }
                });
                handler.postDelayed(this, 25000);
            }
        };
        if (str.equals("dance"))
            handler.postDelayed(runnable, 5000);
        if (str.equals("cook"))
            handler.postDelayed(runnable, 13000);
        if (str.equals("load"))
            handler.postDelayed(runnable, 20000);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize((true));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        db.collection("user").document("rIDv4Jy1sNg8Hr0OL3gS").collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                arrayList.clear();
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }
                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case MODIFIED:
                            List<DocumentSnapshot> list = snapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                if (d.get("rovin_text") != null) {
                                    RovinText obj = d.toObject(RovinText.class);
                                    arrayList.add(obj);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                            break;
                    }
                }
            }
        });
        db.collection("user").document("rIDv4Jy1sNg8Hr0OL3gS").collection("user").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                arrayList.clear();
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    RovinText obj = d.toObject(RovinText.class);
                    arrayList.add(obj);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("MainActivity", "onFailure: ", e);
            }
        });
        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
        updateText("dance");
        updateText("cook");
        updateText("load");
    }
}
