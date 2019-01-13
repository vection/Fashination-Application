package com.fash.aviv.fashinationapp;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.*;
import com.google.firebase.firestore.*;

import org.w3c.dom.Document;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.IntConsumer;


public class DB {
    public static void onUpdate(String table,String id,Map<String,Object> map,final Runnable c) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(table).document(id)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        c.run();
                    }
                });
    }

    public static void onUpdate(String table,String id,Map<String,Object> map) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(table).document(id)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }

    public static void onGet(String from_table, String id, final GetSingleId func )  {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(from_table).document(id);
        try {
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    try {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> map = document.getData();
                            func.run(map);
                        } else {
                            func.onFail();
                        }
                    } catch (Exception ex) {
                        func.onFail();
                    }
                }
            });
        } catch(Exception ex) {
            func.onFail();
        }
    }

    public static void  OnGetTable(String from_table,String where_field,String equal_to, final GetTable func) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query q = db.collection(from_table).whereEqualTo(where_field,equal_to);
        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot q = task.getResult();
                    List<DocumentSnapshot> docs = q.getDocuments();
                    List<Map<String,Object>> list = new ArrayList<>();

                    if(docs == null) {
                        func.run(list);
                    } else {
                        for(DocumentSnapshot doc: docs) {
                            Map<String,Object> map = doc.getData();
                            map.put("key",doc.getId());
                            list.add(map);
                        }
                        func.run(list);
                    }
                }
            }
        });
    }
    public static void  OnGetTable2(String from_table,String where_field,String equal_to, final GetTable func) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query q = db.collection(from_table).whereGreaterThan(where_field,equal_to);
        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot q = task.getResult();
                    List<DocumentSnapshot> docs = q.getDocuments();
                    List<Map<String,Object>> list = new ArrayList<>();

                    if(docs == null) {
                        func.run(list);
                    } else {
                        for(DocumentSnapshot doc: docs) {
                            Map<String,Object> map = doc.getData();
                            map.put("key",doc.getId());
                            list.add(map);
                        }
                        func.run(list);
                    }
                }
            }
        });
    }
}
