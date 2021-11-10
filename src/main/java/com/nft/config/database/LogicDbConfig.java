package com.nft.config.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.zitga.bean.annotation.BeanConfiguration;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

@BeanConfiguration("config/db/logic_db.properties")
public class LogicDbConfig {

    private String uri;

    private Datastore datastore;
    private MongoDatabase mongoDatabase;

    public String getUri() {
        return uri;
    }

    public MongoDatabase getMongoDatabase() {
        if (mongoDatabase == null) {
            init();
        }
        return mongoDatabase;
    }

    public Datastore getDatastore() {
        if (datastore == null) {
            init();
        }
        return datastore;
    }

    private void init() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.retryWrites(true);

        MongoClientURI connectionUri = new MongoClientURI(uri, builder);
        if (connectionUri.getDatabase() == null) {
            throw new RuntimeException("Database in connectionUri is null");
        }

        MongoClient mongoClient = new MongoClient(connectionUri);
        mongoDatabase = mongoClient.getDatabase(connectionUri.getDatabase());

        Morphia morphia = new Morphia();
        datastore = morphia.createDatastore(mongoClient, connectionUri.getDatabase());
    }

    @Override
    public String toString() {
        return "\n\turi='" + uri + '\'';
    }
}