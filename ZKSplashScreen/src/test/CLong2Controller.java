package test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.impl.PollingServerPush;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zul.Button;
//import org.zkoss.zk.ui.sys.ServerPush;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;

@SuppressWarnings("serial")
public class CLong2Controller extends SelectorComposer<Component> {

    CWorkingThread thread = null;   

	@WireVariable
	private Desktop desktop;

	@Wire
	private Label lbProgressMessage;
	
	@Wire
	private Progressmeter ProgressBar;
	
	@Wire
	Button btnLanuchThread;
   
    public void doAfterCompose( Component component ) throws Exception {

	   super.doAfterCompose( component );

    }

	@Listen("onClick = #btnLanuchThread")
	public void doLong(){
		
        //enable server push
		if ( thread == null || thread.isAlive() == false ) {
		
			//boolean b = desktop.isServerPushEnabled();

			//ServerPush OldServerPushImpl = ((DesktopCtrl)desktop).getServerPush();

			//if ( OldServerPushImpl instanceof PollingServerPush == false ) { 

				//((DesktopCtrl)desktop).enableServerPush( new org.zkoss.zkmax.ui.comet.CometServerPush() );

			//}

			//b = desktop.isServerPushEnabled();
			//desktop.enableServerPush(true);       

			//invoke working thread and passing required component as parameter (in this case we are passing the desktop)           
			//if ( thread == null || thread.isInterrupted() )   

			((DesktopCtrl)desktop).enableServerPush( new PollingServerPush( 1000, 2000, -1 ) );

			thread = new CWorkingThread( desktop, lbProgressMessage, ProgressBar );           
			
			thread.start();  
			
			btnLanuchThread.setLabel( "Stop Server Push" );

		}
		else {
			
			thread.interrupt();
			thread = null;

			btnLanuchThread.setLabel( "Start Server Push" );
			
		}
       
	}
}
