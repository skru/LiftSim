
//import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LiftSimFrame extends JFrame 
{

   private JPanel controllerPanel = new JPanel();
   private JPanel viewPanel = new JPanel();
   private JPanel consoleViewPanel = new JPanel();
   
   private JTextField liftStartingFloorJTextField; // lift starting position
   private JLabel liftStartingFloorLabel;
   private JTextField gfPeopleInputJTextField; // no people on ground floor
   private JTextField ffPeopleInputJTextField; // no people on first floor
   private JLabel promptgfJLabel;
   private JLabel promptffJLabel;

   private JButton runSimulation; // creates new game

   
   
   //create environment
   private ConsoleView xd = new ConsoleView();
   
   private Floor gFloor = new Floor(0, xd);
   private Floor fFloor = new Floor(1, xd);
   private Floor floors[] = {gFloor, fFloor};
   private Lift lift = new Lift(floors);
   
   // create a CachedThreadPool
   ExecutorService application = Executors.newCachedThreadPool();
   // create SynchronizedBuffer to store ints
   Buffer sharedLocation = new SynchronizedBuffer();
  
 
   // set up GUI and initialize values
   public LiftSimFrame()
   {
      super( "Lift Sim" );
      
      
      controllerPanel.setPreferredSize(new Dimension(150, 600));
      consoleViewPanel.setPreferredSize(new Dimension(850, 200));
      
      add(controllerPanel, BorderLayout.EAST);
	  add(consoleViewPanel, BorderLayout.WEST);

      liftStartingFloorLabel = new JLabel("Lift starting floor");
      promptgfJLabel = new JLabel( "Ground floor people" );
      promptffJLabel = new JLabel( "First floor people" );
  
      ViewHandler handler=new  ViewHandler();
      liftStartingFloorJTextField = new JTextField( 5 );
      gfPeopleInputJTextField = new JTextField( 5 );
      ffPeopleInputJTextField = new JTextField( 5 );

      runSimulation = new JButton( "Run simulation" ); // starts new game
      runSimulation.addActionListener(handler);
  
	   liftStartingFloorJTextField.setEditable( true );
	   gfPeopleInputJTextField.setEditable( true );
	   ffPeopleInputJTextField.setEditable( true );
	   
	   controllerPanel.add(liftStartingFloorLabel);
	   controllerPanel.add( liftStartingFloorJTextField );
	   controllerPanel.add( promptgfJLabel );
	   controllerPanel.add( gfPeopleInputJTextField );
	   controllerPanel.add( promptffJLabel );
	   controllerPanel.add( ffPeopleInputJTextField );
	   controllerPanel.add( runSimulation );

	   
	   JTextArea tx = new JTextArea();
	   consoleViewPanel.add( new JScrollPane( tx ) );
	   MessageConsole mc = new MessageConsole(tx);
	   mc.redirectOut();
	   mc.redirectErr(Color.RED, null);
	   
	   controllerPanel.setLayout( new FlowLayout() ); // set layout
	   viewPanel.setLayout( new GridLayout() ); // set layout
	   consoleViewPanel.setLayout( new GridLayout() ); // set layout
	   
	   this.pack();
	   this.setSize(1000, 600);

	   // add consumer
	   application.execute( new Consumer( sharedLocation ) );
	   
	   // register lift observers
	   lift.registerObserver(xd);

   }

    	   
  private class ViewHandler implements ActionListener
   {
	  int gfPersonCount;
	  int ffPersonCount;
	  int liftStarting;
	  
      public void actionPerformed( ActionEvent e )
      { 
         if (e.getSource()==runSimulation) {
        	 
        	 liftStarting = Integer.parseInt( liftStartingFloorJTextField.getText() );
        	 gfPersonCount = Integer.parseInt( gfPeopleInputJTextField.getText() );
        	 ffPersonCount = Integer.parseInt( ffPeopleInputJTextField.getText() );

        	 //application.shutdown();
        	 application.execute( new Producer( xd, sharedLocation, lift, floors, liftStarting, gfPersonCount, ffPersonCount ) );
         }
      } 
   } 
     
  


} 


