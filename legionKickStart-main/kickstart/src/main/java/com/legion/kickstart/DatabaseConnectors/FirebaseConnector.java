package com.legion.kickstart.DatabaseConnectors;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseConnector {

    @PostConstruct
    public void init() throws IOException {
            FileInputStream serviceAccount = null;
            serviceAccount = new FileInputStream("./serviceAccountKey.json");
            FirebaseOptions options = null;
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://legion-one-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();
            FirebaseApp.initializeApp(options);
    }
}
