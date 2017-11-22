package org.fenxui.application.data.nitrite;

import java.util.HashMap;
import java.util.Map;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fenxui.application.data.DataService;
import org.fenxui.application.data.event.SaveEvent;
import org.fenxui.application.event.BroadcastEvent;

public class NitriteDataService implements DataService {

	private final Map<Class, ObjectRepository> repositoryMap = new HashMap<>();
	private final Nitrite db;

	public NitriteDataService(Nitrite db) {
		this.db = db;
	}

	@Override
	public void registerType(Class klass) {
		repositoryMap.put(klass, db.getRepository(klass));
	}

	public void shutdown() {
		db.close();
	}

	public boolean hasUnsavedChanges() {
		return db.hasUnsavedChanges();
	}

	public <T extends IdAware> Iterable<T> getAll(Class<T> type) {
		return repositoryMap.get(type).find();
	}

	public <T extends IdAware> T save(T toSave) {
		ObjectRepository repository = repositoryMap.get(toSave.getClass());
		if (toSave.getId() == null) {
			toSave.setId(System.currentTimeMillis());
			repository.insert(toSave);
		} else {
			repository.update(toSave);
		}
		return toSave;
	}

	@Override
	public void handleEvent(BroadcastEvent event) {
		if (event instanceof SaveEvent) {
			Object o = ((SaveEvent) event).getSubject();
			if (o instanceof IdAware) {
				save((IdAware) o);
			}
		}
	}

}
