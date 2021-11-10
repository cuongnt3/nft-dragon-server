package com.zitga.config.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.zitga.base.constant.DatabaseConstant;
import com.zitga.base.dao.MorphiaObjectFactory;
import com.zitga.bean.annotation.BeanConfiguration;
import com.zitga.bean.annotation.BeanMethod;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;

@BeanConfiguration("config/db/logic_db.properties")
public class LogicDbConfig {

    private String uri;

    private Datastore datastore;
    private MongoDatabase mongoDatabase;

    public String getUri() {
        return uri;
    }

    @BeanMethod
    public MongoDatabase getMongoDatabase(MorphiaObjectFactory morphiaObjectFactory) {
        if (mongoDatabase == null) {
            init(morphiaObjectFactory);
        }
        return mongoDatabase;
    }

    @BeanMethod
    public Datastore getDatastore(MorphiaObjectFactory morphiaObjectFactory) {
        if (datastore == null) {
            init(morphiaObjectFactory);
        }
        return datastore;
    }

    private void init(MorphiaObjectFactory morphiaObjectFactory) {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.retryWrites(true);

        MongoClientURI connectionUri = new MongoClientURI(uri, builder);
        if (connectionUri.getDatabase() == null) {
            throw new RuntimeException("Database in connectionUri is null");
        }

        MongoClient mongoClient = new MongoClient(connectionUri);
        mongoDatabase = mongoClient.getDatabase(connectionUri.getDatabase());

        Morphia morphia = new Morphia();
        morphia.mapPackage(DatabaseConstant.PACKAGE_NAME);

        MapperOptions mapperOptions = morphia.getMapper().getOptions();
        mapperOptions.setStoreEmpties(true);
        mapperOptions.setStoreNulls(false);
        mapperOptions.setObjectFactory(morphiaObjectFactory);

        createIndexes(morphia);

        datastore = morphia.createDatastore(mongoClient, connectionUri.getDatabase());
        datastore.ensureIndexes();
        datastore.ensureCaps();
        datastore.enableDocumentValidation();
    }

    private void createIndexes(Morphia morphia) {
        // TODO: add class which need to build index (other than primary "_id" field)

//        morphia.map(PlayerAuthentication.class);
    }


    @Override
    public String toString() {
        return "\n\turi='" + uri + '\'';
    }
}