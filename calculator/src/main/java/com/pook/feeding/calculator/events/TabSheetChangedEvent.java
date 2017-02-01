package com.pook.feeding.calculator.events;

import java.io.Serializable;

import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class TabSheetChangedEvent implements Serializable {
	private Component tab;

	public TabSheetChangedEvent(Component tab) {
		this.tab = tab;;
	}

	public Component getTab() {
		return tab;
	}

	public void setTab(Component tab) {
		this.tab = tab;
	}

}
