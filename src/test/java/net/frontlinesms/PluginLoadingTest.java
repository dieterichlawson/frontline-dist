/**
 * 
 */
package net.frontlinesms;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.frontlinesms.events.EventBus;
import net.frontlinesms.junit.BaseTestCase;
import net.frontlinesms.plugins.PluginController;
import net.frontlinesms.plugins.PluginInitialisationException;
import net.frontlinesms.plugins.forms.FormsPluginController;

import org.springframework.context.ApplicationContext;


/**
 * @author Alex Anderson <alex@frontlinesms.com>
 */
public class PluginLoadingTest extends BaseTestCase {
	/** Test loading of all the plugins 
	 * @throws PluginInitialisationException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException */
	public void testPluginLoading() throws PluginInitialisationException, InstantiationException, IllegalAccessException {
		FrontlineSMS frontlineController = getMockFrontlineController();
		ApplicationContext applicationContext = getMockApplicationContext();

		Class<?>[] controllerClasses = new Class<?>[] {
			FormsPluginController.class,
		};
		
		for(Class<?> c : controllerClasses) {
			PluginController p = (PluginController) c.newInstance();
			p.init(frontlineController, applicationContext);
		}
	}

	private ApplicationContext getMockApplicationContext() {
		ApplicationContext c = mock(ApplicationContext.class);
		
		EventBus eventBus = mock(EventBus.class);
		when(c.getBean("eventBus")).thenReturn(eventBus);
		
		return c;
	}

	private FrontlineSMS getMockFrontlineController() {
		FrontlineSMS f = mock(FrontlineSMS.class);
		return f;
	}
}
