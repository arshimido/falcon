import models.RealTimebroadcaster;
import play.Application;
import play.GlobalSettings;
import play.Logger;


public class Global extends GlobalSettings{
	@Override
	  public void onStart(Application app) {
	    RealTimebroadcaster.init();
	  }  

	  @Override
	  public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	  }  
}
