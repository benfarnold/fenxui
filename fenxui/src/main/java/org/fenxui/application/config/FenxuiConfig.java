package org.fenxui.application.config;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FenxuiConfig {

	private Pos alignent = Pos.CENTER;
	private int hgap = 10;
	private int vgap = 10;
	private int titleColSpan = 3;
	private int titleRowSpan = 1;
	private Insets padding = new Insets(25, 25, 25, 25);
	private List<String> stylesheets = new ArrayList<>();

	public Pos getAlignent() {
		return alignent;
	}

	public void setAlignent(Pos alignent) {
		this.alignent = alignent;
	}

	public int getHgap() {
		return hgap;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}

	public int getVgap() {
		return vgap;
	}

	public void setVgap(int vgap) {
		this.vgap = vgap;
	}

	public Insets getPadding() {
		return padding;
	}

	public void setPadding(Insets padding) {
		this.padding = padding;
	}

	public List<String> getStylesheets() {
		return stylesheets;
	}

	public void setStylesheets(List<String> stylesheets) {
		this.stylesheets = stylesheets;
	}

	public int getTitleColSpan() {
		return titleColSpan;
	}

	public void setTitleColSpan(int titleColSpan) {
		this.titleColSpan = titleColSpan;
	}

	public int getTitleRowSpan() {
		return titleRowSpan;
	}

	public void setTitleRowSpan(int titleRowSpan) {
		this.titleRowSpan = titleRowSpan;
	}

	public static class Builder {

		FenxuiConfig config = new FenxuiConfig();

		public FenxuiConfig build() {
			return config;
		}

		public Builder set(Setter setter) {
			setter.execute();
			return this;
		}

		public Builder alignment(Pos pos) {
			return set(() -> config.alignent = pos);
		}

		public Builder hGap(int hgap) {
			return set(() -> config.hgap = hgap);
		}

		public Builder vGap(int vgap) {
			return set(() -> config.vgap = vgap);
		}

		public Builder titleColSpan(int titleColSpan) {
			return set(() -> config.titleColSpan = titleColSpan);
		}

		public Builder titleRowSpan(int titleRowSpan) {
			return set(() -> config.titleRowSpan = titleRowSpan);
		}

		public Builder padding(double inset) {
			return set(() -> config.padding = new Insets(inset));
		}

		public Builder padding(double top, double right, double bottom, double left) {
			return set(() -> config.padding = new Insets(top, right, bottom, left));
		}

		public Builder css(URL url) {
			return set(() -> config.stylesheets.add(url.toExternalForm()));
		}
	}

	@FunctionalInterface
	private interface Setter {

		void execute();
	}
}
