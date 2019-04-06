package com.pingwinno.infrastructure;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.pingwinno.application.SettingsProperties;
import org.bson.Document;

public class MongoDBHandler {
    private static MongoDatabase database;

    public static void connect() {
        MongoClient mongoClient = new MongoClient(SettingsProperties.getMongoDBAddress());
        database = mongoClient.getDatabase(SettingsProperties.getMongoDBName());
        for (String name : database.listCollectionNames()) {
            getCollection(name).createIndex(Indexes.text("title"));
            getCollection(name).createIndex(Indexes.descending("date"));
        }
    }

    public static MongoCollection<Document> getCollection(String user) {
        if (database == null) {
            connect();
        }
        return database.getCollection(user);
    }
}
