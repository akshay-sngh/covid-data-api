package project4task0.model;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import project4task0.SurveyResult;

public class QuickStart {
    public static void main( String[] args ) {
    }

    public  void insertCovidInfo(SurveyResult sr) {
        String uri = "mongodb+srv://akshaysi:akshaysi@clusterzipcode.b3xic.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));


        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("covid_db");
            database = database.withCodecRegistry(pojoCodecRegistry);

            MongoCollection<SurveyResult> collection = database.getCollection("locations_would_attend", SurveyResult.class);
            collection.insertOne(sr);
            System.out.println("Complete contents of the database are:");
            for (SurveyResult l : collection.find()) {
                System.out.println(l);
            }
        }
    }
}
