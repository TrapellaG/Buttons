package com.example.buttons;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class ButtonAccess
{

    public static void updateButton(Button button)
    {
        FirebaseFirestore.getInstance().collection("buttons").document(button.getId())
                .set(button.toMap())
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Log.d("hola", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w("hola", "Error writing document", e);
                    }
                });
    }

    /*public static void getButtons(final ButtonsCallback callback)
    {
        FirebaseFirestore.getInstance().collection("buttons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful()) {
                            List<Button> buttons = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d("hola", document.getId() + " => " + document.getData());

                                buttons.add(new Button(document.getId(), document.getData()));
                            }
                            callback.onCallback(buttons,Constants.STATUS_OK);
                        } else
                        {
                            Log.d("hola", "Error getting documents: ", task.getException());
                            callback.onCallback(null,Constants.STATUS_KO);
                        }
                    }
                });
    }*/


    public static void ButtonsSnap(final ButtonsCallback callback)
    {
        FirebaseFirestore.getInstance().collection("buttons")
                .addSnapshotListener(new EventListener<QuerySnapshot>()
                {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e)
                    {
                        if (e != null) {
                            Log.w("hola", "Listen failed.", e);
                            return;
                        }

                        List<Button> buttons = new ArrayList<>();

                        for (QueryDocumentSnapshot document : value)
                        {
                            buttons.add(new Button(document.getId(), document.getData()));
                        }
                        callback.onCallback(buttons,Constants.STATUS_OK);
                    }
                });
    }

    public interface ButtonsCallback
    {
        public void onCallback(List<Button> buttons, int status);
    }


    public interface Constants
    {
    public static final int STATUS_OK = 0;
    public  static final int STATUS_KO = 1;
    }

}
