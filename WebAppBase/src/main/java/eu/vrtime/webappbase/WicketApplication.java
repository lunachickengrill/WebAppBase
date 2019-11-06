package eu.vrtime.webappbase;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import eu.vrtime.webappbase.web.AdminPage;

public class WicketApplication extends WebApplication{
	
	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));		
		mountPage("/admin", AdminPage.class);

	}

	@Override
	public Class<? extends Page> getHomePage() {
		return AdminPage.class;
	}

	public static WicketApplication get() {
		return (WicketApplication) Application.get();
	}

}
