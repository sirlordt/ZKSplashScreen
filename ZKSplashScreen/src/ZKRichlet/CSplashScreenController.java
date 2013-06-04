package ZKRichlet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Timer;

public class CSplashScreenController extends SelectorComposer<Component> {

	private static final long serialVersionUID = -4362626162184372513L;

	/*public void doAfterCompose( Component component ) throws Exception {

		super.doAfterCompose( component );
		
	}*/
	Image OldImageModule = null;
	
	
	int intCountModule = 1;
	
	@Wire
	Timer LoadTimer; 
	
	@Wire 
    Label lbMessageProgress;
	
	@Wire 
	Progressmeter ProgressBar;
	
	@Wire
	Hbox ModulesLoaded;
	
	@Listen( "onClientInfo = #SplashScreen")
	public void onClientInfo( ClientInfoEvent event ) {
		
		Session session = Sessions.getCurrent();
		
		session.setAttribute( "browserwidth", Integer.toString( event.getDesktopWidth() ) );
		session.setAttribute( "browserheight", Integer.toString( event.getDesktopHeight() ) );
		
	}
	
	@Listen( "onTimer = #LoadTimer" )
    public void onTimerLoad( Event event ) {
		
		//Session session = Sessions.getCurrent();

		//session.setAttribute( "app_initiated", "1" );

		//Executions.sendRedirect( null );
		int intProgress = ProgressBar.getValue();
		
		intProgress += 1;
		
		if ( intProgress == 100 )
		   LoadTimer.stop();

		ProgressBar.setValue( intProgress );
		
		lbMessageProgress.setValue( "Loading module " + Integer.toString( intProgress ) + "..." );
		
		Image ImageModule = new Image();
		
		ImageModule.setSrc( "/" + Integer.toString( intCountModule ) + ".png" );

		/*if ( intCountModule == 0 ) {
			
			ImageModule.setSrc( "/puzzle_green.png" );
			
		}
		else if ( intCountModule == 1 ) {
			
			ImageModule.setSrc( "/puzzle_red.png" );
			
		}
		else if ( intCountModule == 2 ) {
			
			ImageModule.setSrc( "/puzzle_yellow.png" );
			
		}*/
		
		ModulesLoaded.insertBefore( ImageModule, OldImageModule );
		//Div D = new Div();
		//D.appendChild( ImageModule );
		
		//ModulesLoaded.appendChild( D );
		/*ModulesLoaded.appendChild( ImageModule );
		
		int intModlesLoadedWidth = 0;
		
		if ( ModulesLoaded.getWidth() != null )
			intModlesLoadedWidth = Integer.parseInt( ModulesLoaded.getWidth() );
		
		if ( intModlesLoadedWidth > 370 ) {
		
			ModulesLoaded.setLeft( Integer.toString( 370 - intModlesLoadedWidth ) );
			
		}*/
		//Clients.scrollIntoView( ImageModule );
		
		OldImageModule = ImageModule;
		
		intCountModule += 1;
		
		if ( intCountModule > 15 )
			intCountModule = 1; 
		
	}
	
	
}