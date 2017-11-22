package org.fenxui.data.mongo.annotation.handler;

import com.mongodb.MongoClient;
import java.io.File;
import java.lang.annotation.Annotation;
import org.fenxui.application.data.event.SaveEvent;
import org.fenxui.application.data.nitrite.NitriteDataService;
import org.fenxui.application.event.EventDispatcher;
import org.fenxui.application.view.factory.handler.app.AppAnnotationHandler;
import org.fenxui.application.view.factory.ootb.AppConstruction;
import org.fenxui.data.mongo.annotation.EnableMongo;
import org.fenxui.data.mongo.service.MongoDataService;
import org.fenxui.util.PropertyParser;

public class EnableMongoAnnotationHandler implements AppAnnotationHandler {

	@Override
	public void handle(AppConstruction appContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		EnableMongo enableMongo = (EnableMongo) annotation;

		MongoClient mongoClient = new MongoClient(enableMongo.host(), enableMongo.port());
		EventDispatcher.INSTANCE.subscribe(new MongoDataService(mongoClient, enableMongo.value()), SaveEvent.TOPIC);
	}

}
