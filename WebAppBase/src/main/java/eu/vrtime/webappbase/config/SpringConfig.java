package eu.vrtime.webappbase.config;

import javax.servlet.DispatcherType;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.vrtime.webappbase.WicketApplication;
import eu.vrtime.webappbase.web.servlet.ApplicationServlet;

@Configuration
public class SpringConfig {

	@Bean
	public FilterRegistrationBean<WicketFilter> wicketFilterRegistration() {
		WicketApplication webApplication = new WicketApplication();
		webApplication.setConfigurationType(RuntimeConfigurationType.DEVELOPMENT);

		WicketFilter filter = new WicketFilter(webApplication);
		filter.setFilterPath("/");

		FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(filter);
		registration.addInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
		registration.addUrlPatterns("/*");

		return registration;
	}

	@Bean
	public ApplicationServlet xmlServlet() {
		return new ApplicationServlet();
	}

	@Bean
	public ServletRegistrationBean<ApplicationServlet> servletRegistration() {
		ServletRegistrationBean<ApplicationServlet> registration = new ServletRegistrationBean<ApplicationServlet>(
				xmlServlet(), "/service");
		registration.setLoadOnStartup(12);
		return registration;
	}

}
