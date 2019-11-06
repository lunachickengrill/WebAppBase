package eu.vrtime.webappbase.web.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class DummyPanel extends Panel {

	private static final long serialVersionUID = -1305563314468196250L;

	public DummyPanel(final String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Label("dummyPanel", "Dummy Panel"));
	}

}
