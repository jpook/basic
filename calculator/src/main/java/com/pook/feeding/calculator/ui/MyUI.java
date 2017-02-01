package com.pook.feeding.calculator.ui;

import javax.inject.Inject;

import com.pook.feeding.calculator.events.TabSheetChangedEvent;
import com.pook.feeding.calculator.ui.view.impl.AllotmentView;
import com.pook.feeding.calculator.ui.view.impl.CustomerProductView;
import com.pook.feeding.calculator.ui.view.impl.NutrientUnitView;
import com.pook.feeding.calculator.ui.view.impl.NutrientView;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@CDIUI("")
@SuppressWarnings("serial")
public class MyUI extends UI {
	// TODO: addSelectedTabListener

	@Inject
	NutrientView nutrientView;

	@Inject
	NutrientUnitView nutrientUnitView;

	@Inject
	AllotmentView allotmentView;

	@Inject
	CustomerProductView customerProductView;

	@Inject
	javax.enterprise.event.Event<TabSheetChangedEvent> event;

	TabSheet tabSheet;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		tabSheet = new TabSheet();
		horizontalLayout.addComponent(tabSheet);
		horizontalLayout.setSizeFull();
		setContent(horizontalLayout);

		createTab(tabSheet, "nutrient", nutrientView, "Nutrient");
		createTab(tabSheet, "nutrientUnit", nutrientUnitView, "Units");
		createTab(tabSheet, "allotment", allotmentView, "Allotment");
		createTab(tabSheet, "customerProduct", customerProductView, "Products");

		tabSheet.setSelectedTab(allotmentView);
		tabSheet.addSelectedTabChangeListener(e -> tabChanged(e));
		System.out.println(allotmentView.isVisible());
		System.out.println(nutrientView.isVisible());

	}

	private void tabChanged(SelectedTabChangeEvent e) {
		event.fire(new TabSheetChangedEvent(e.getTabSheet().getSelectedTab()));
	}

	private void createTab(TabSheet tabSheet, String description, Component component, String title) {
		VerticalLayout v = new VerticalLayout();
		v.setDescription(description);
		v.addComponent(component);
		tabSheet.addTab(v, title);
	}
}
