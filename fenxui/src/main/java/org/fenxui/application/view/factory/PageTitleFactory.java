package org.fenxui.application.view.factory;

import javafx.scene.control.Label;

/**
 * Makes title region of page
 */
public interface PageTitleFactory {

	Label makeTitle(String text, String cssId);
}
