package com.legion.kickstart.DatabaseService;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.legion.kickstart.DatabaseEntities.DatabaseEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DatabaseService {
    private String COLLECTION_NAME;

    public void setCOLLECTION_NAME(String COLLECTION_NAME) {
        this.COLLECTION_NAME = COLLECTION_NAME;
    }

    public String saveEntity(DatabaseEntity entity) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(entity);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }

    public DocumentSnapshot getEntity(String entityId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(entityId);
        ApiFuture<DocumentSnapshot> docSnapShot =  documentReference.get();
        return docSnapShot.get();
    }
    /* --> Pending <--
    public String updateComponent(Component component) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(component);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }
    */

    public String deleteEntity(String entityId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(entityId).delete();
        return "Document with Id " + entityId + " has been deleted at : " + collectionApiFuture.get().getUpdateTime().toString();
    }
}
