package test;
 
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
 
public class CWorkingThread extends Thread {
   
   private final Desktop _desktop;
   private final Label _lbProgressMessage;
   private final Progressmeter _ProgressBar;
 
   public CWorkingThread( Desktop desktop, Label lbProgressMessage, Progressmeter ProgressBar ) {
      
	   _desktop = desktop;
       _lbProgressMessage = lbProgressMessage;
       _ProgressBar = ProgressBar;
	   
   }
 
   public void run() {
      
	   try {

		   if ( _desktop.isServerPushEnabled() ) {

			   for (int i = 1; i < 15; i++) {

				   Executions.activate( _desktop );
				   _lbProgressMessage.setValue( "Loading slow module " + Integer.toString( i ) + " ..."  );
				   Executions.deactivate( _desktop );

				   // Simulate a long opetation
				   Threads.sleep( 2000 );

				   Executions.activate( _desktop );
				   _lbProgressMessage.setValue( "Slow module " + Integer.toString( i ) + " loaded ..."  );
				   _ProgressBar.setValue( i );
				   Executions.deactivate( _desktop );

			   }

		   }

	   } 
	   catch ( Exception ex ) {

		   
		   
	   } 
	   finally {

		   Executions.deactivate( _desktop );
		   _desktop.enableServerPush(false);

	   }
      
   }
 
}