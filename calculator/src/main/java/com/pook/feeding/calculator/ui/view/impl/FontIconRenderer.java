package com.pook.feeding.calculator.ui.view.impl;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.renderers.ButtonRenderer;

import elemental.json.JsonValue;

@SuppressWarnings("serial")
public class FontIconRenderer extends ButtonRenderer {

	@Override
	public JsonValue encode(String value) {
		System.out.println("rendering... " + super.encode(FontAwesome.ANDROID.getHtml()));
		return super.encode(FontAwesome.ANDROID.getHtml());
	}
}
