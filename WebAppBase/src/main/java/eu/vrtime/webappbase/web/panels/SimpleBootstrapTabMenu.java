package eu.vrtime.webappbase.web.panels;

import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;

public class SimpleBootstrapTabMenu<T extends ITab> extends AjaxTabbedPanel<T> {

	private static final long serialVersionUID = -3315492258820137042L;

	public SimpleBootstrapTabMenu(final String id, List<T> tabs) {
		super(id, tabs);
	}

	@Override
	protected String getSelectedTabCssClass() {
		return "active";
	}

	@Override
	protected String getTabContainerCssClass() {
		return "nav nav-tabs nav-justified";
	}

}
