package eu.vrtime.webappbase.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

import eu.vrtime.webappbase.web.panels.DummyPanel;
import eu.vrtime.webappbase.web.panels.SimpleBootstrapTabMenu;

public class AdminPage extends AbstractBasePage {

	private static final long serialVersionUID = -7375346293013811306L;
	private static final String MAINMENU = "mainMenu";

	private SimpleBootstrapTabMenu<ITab> tabMenu;
	private List<ITab> tabs;

	public AdminPage() {
		super();
		tabs = createTabs();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		tabMenu = new SimpleBootstrapTabMenu<>(MAINMENU, tabs);
		add(tabMenu);
	}

	private List<ITab> createTabs() {
		List<ITab> tabs = new ArrayList<>();

		tabs.add(new AbstractTab(Model.of("Tab 1")) {

			private static final long serialVersionUID = 2512311595181898954L;

			@Override
			public WebMarkupContainer getPanel(String panelId) {
				return new DummyPanel(panelId);
			}
		});

		tabs.add(new AbstractTab(Model.of("Tab 2")) {

			private static final long serialVersionUID = -3460116520500268580L;

			@Override
			public WebMarkupContainer getPanel(String panelId) {
				return new DummyPanel(panelId);
			}
		});

		return tabs;
	}

}
