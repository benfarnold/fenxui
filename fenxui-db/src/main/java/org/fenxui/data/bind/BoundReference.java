package org.fenxui.data.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.fenxui.application.data.bind.handler.ValueManipulatorReference;
import org.fenxui.application.data.bind.handler.ValueManipulatorReferenceMethods;
import org.fenxui.application.data.nitrite.IdAware;

/**
 * Maintain pointers to the id of the entity bound to the page and the page
 * itself. Prevents user from having to be exposed to IDs.
 */
public class BoundReference {

	private Long id;
	private final Object applicationPage;
	private final String table;
	private final List<ValueManipulatorReference> valueManipulatorReferenceMap = new ArrayList<>();

	protected BoundReference(Long id, Object page, String table) {
		this.id = id;
		this.applicationPage = page;
		this.table = table;
	}

	public Long getId() {
		return id;
	}

	public Object getApplicationPage() {
		return applicationPage;
	}

	public String getTable() {
		return table;
	}

	public void putSourceValueManipulatorReference(ValueManipulatorReference valueManipulatorReference) {
		valueManipulatorReferenceMap.add(valueManipulatorReference);
	}

	public void mapToUI(IdAware aware) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		id = aware.getId();
		for (ValueManipulatorReference reference : valueManipulatorReferenceMap) {
			DataBindable node = reference.getNode();

			ValueManipulatorReferenceMethods dbMethods = reference.getDbMethods();

			Method getterMethod = dbMethods.getGetterMethod();
			if (getterMethod == null) {
				getterMethod = boundDbClass.getDeclaredMethod(dbMethods.getGetterName());
				dbMethods.setGetterMethod(getterMethod);
			}
			Object value = getterMethod.invoke(applicationPage);
			node.setValue(value);
		}
	}

	public IdAware mapToDB(Object page) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		for (ValueManipulatorReference reference : valueManipulatorReferenceMap) {
			DataBindable node = reference.getNode();

			ValueManipulatorReferenceMethods dbMethods = reference.getDbMethods();

			Method setterMethod = dbMethods.getSetterMethod();
			if (setterMethod == null) {
				setterMethod = boundDbClass.getDeclaredMethod(dbMethods.getSetterName());
				dbMethods.setSetterMethod(setterMethod);
			}
			Object value = node.getValue();
			setterMethod.invoke(applicationPage, value);
		}
	}

}
