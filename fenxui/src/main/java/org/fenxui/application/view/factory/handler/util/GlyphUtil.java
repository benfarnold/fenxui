package org.fenxui.application.view.factory.handler.util;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import java.io.IOException;
import javafx.scene.paint.Paint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fenxui.application.exception.FenxuiInitializationException;

public class GlyphUtil {
	private static final Log log = LogFactory.getLog(GlyphUtil.class);
	private static final double DEFAULT_ICON_SIZE = 16;
	private static boolean iconsLoaded = false;
	
	private static void loadIcons() throws IOException {
		if (!iconsLoaded) {
			SVGGlyphLoader.loadGlyphsFont(GlyphUtil.class.getResourceAsStream("icomoon.svg"), "icomoon.svg");
		}
	}

	public static SVGGlyph getGlyph(String name, String iconColor, double iconSize) throws FenxuiInitializationException {
		SVGGlyph glyph = null;
		try {
			loadIcons();
			glyph = SVGGlyphLoader.getIcoMoonGlyph(name);
			glyph.setSize(iconSize);
			glyph.setFill(Paint.valueOf(iconColor));
		} catch (Exception ex) {
			log.error("Error loading fonts", ex);
			throw new FenxuiInitializationException(ex);
		}
		return glyph;
	}

	public static SVGGlyph getGlyph(String name, String iconColor) throws FenxuiInitializationException {
		return getGlyph(name, iconColor, DEFAULT_ICON_SIZE);
	}
}
