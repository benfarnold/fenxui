package org.fenxui.data.annotation.handler;

import java.io.File;
import java.lang.annotation.Annotation;
import org.dizitart.no2.Nitrite;
import org.fenxui.application.data.event.SaveEvent;
import org.fenxui.application.data.nitrite.NitriteDataService;
import org.fenxui.application.event.EventDispatcher;
import org.fenxui.application.service.EventHandlingService;
import org.fenxui.application.service.ServiceLookup;
import org.fenxui.application.view.factory.handler.app.AppAnnotationHandler;
import org.fenxui.application.view.factory.ootb.AppConstruction;
import org.fenxui.annotation.db.EnableMongo;

public class EnableNitriteAnnotationHandler implements AppAnnotationHandler {

	@Override
	public void handle(AppConstruction appContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		EnableMongo enableNitrite = (EnableMongo) annotation;
		String location = new PropertyParser().parse(enableNitrite.location());
		File dbLoc = new File(location);
		if (!dbLoc.exists()) {
			dbLoc.mkdir();
		}
		File dbFile = new File(dbLoc.getAbsolutePath() + File.separator + enableNitrite.name());
		final Nitrite db = Nitrite.builder()
				.compressed()
				.filePath(dbFile.getAbsolutePath())
				.openOrCreate(enableNitrite.username(), enableNitrite.password());
		EventDispatcher.INSTANCE.subscribe(new NitriteDataService(db), SaveEvent.TOPIC);
	}

}
