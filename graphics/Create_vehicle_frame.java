package graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vehicles.FactoryProvider;
import vehicles.Glider_game;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class Create_vehicle_frame extends JFrame implements ActionListener {
    JButton jeepbtn, frigatebtn, Spygliderbtn, glidergamebtn, bicyclebtn,bicycle2btn,hybridbtn ,cruiseshipbtn, amphibiousbtn,mainmenu;
    JPanel panel;
    JLabel label,photo1, photo2, photo3;
    ArrayList<Vehicle> vehicles;
    public Create_vehicle_frame( ArrayList<Vehicle> vehicles1) {
    	
    	vehicles = new ArrayList<Vehicle>(vehicles1);
 
    
        // create label with text
        label = new JLabel();
        label.setText("Choose which vehicle to create:");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        
        
 
        // create the buttons
        jeepbtn = new JButton();
        frigatebtn = new JButton();
        Spygliderbtn = new JButton();
        glidergamebtn = new JButton();
        bicyclebtn = new JButton();
        bicycle2btn=new JButton();
        cruiseshipbtn = new JButton();
        amphibiousbtn = new JButton();
        hybridbtn=new JButton();
        mainmenu=new JButton();

        // set the bounds of the buttons
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

        // set the text of each button
        jeepbtn.setText("Jeep");
        frigatebtn.setText("Frigate");
        Spygliderbtn.setText("Spy glider");
        glidergamebtn.setText("Glider game");
        bicyclebtn.setText("Regular bicycle");
        bicycle2btn.setText("Electric bicycle");
        cruiseshipbtn.setText("Cruise ship");
        hybridbtn.setText("Hybrid plane");
        amphibiousbtn.setText("Amphibious");
        mainmenu.setText("back to the main menu");

        // set actions
        jeepbtn.addActionListener(this);
        frigatebtn.addActionListener(this);
        Spygliderbtn.addActionListener(this);
        glidergamebtn.addActionListener(this);
        bicyclebtn.addActionListener(this);
        hybridbtn.addActionListener(this);
        cruiseshipbtn.addActionListener(this);
        amphibiousbtn.addActionListener(this);
        mainmenu.addActionListener(this);
        bicycle2btn.addActionListener(this);

        // remove focusable
        jeepbtn.setFocusable(false);
        frigatebtn.setFocusable(false);
        Spygliderbtn.setFocusable(false);
        glidergamebtn.setFocusable(false);
        bicyclebtn.setFocusable(false);
        hybridbtn.setFocusable(false);
        cruiseshipbtn.setFocusable(false);
        amphibiousbtn.setFocusable(false);
        mainmenu.setFocusable(false);
        bicycle2btn.setFocusable(false);

        // add buttons
        panel.add(jeepbtn);
        panel.add(frigatebtn);
        panel.add(Spygliderbtn);
        panel.add(glidergamebtn);
        panel.add(bicyclebtn);
        panel.add(bicycle2btn);
        panel.add(hybridbtn);
        panel.add(amphibiousbtn);
        panel.add(cruiseshipbtn);
        panel.add(mainmenu);
        
        this.setResizable(false);
        this.add(label, "North");
        this.add(panel, "Center");
        this.setTitle("Car menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 370);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jeepbtn) 
        {
        	new JeepFrame(vehicles);
           
        }
        if(e.getSource()==hybridbtn)
        {
        	new HybridPlaneFrame(vehicles);
        }
        if(e.getSource()==frigatebtn)
        {
        	new FrigateFrame(vehicles);
        }
        if(e.getSource()==Spygliderbtn)
        {
        	new SpyGliderFrame(vehicles);
        }
        if(e.getSource()==glidergamebtn)
        {
        	new GliderGameFrame(vehicles);
        }
        if(e.getSource()==bicyclebtn)
        {
        	new BicycleFrame(vehicles);
        }
        if(e.getSource()==bicycle2btn)
        {
        	new ElectricBicycleFrame(vehicles);
        }
        if(e.getSource()==cruiseshipbtn)
        {
        	new CruiseShipFrame(vehicles);
        }
        if(e.getSource()==amphibiousbtn)
        {
        	new AmphibiousVehicleFrame(vehicles);
        }
        if(e.getSource()==mainmenu)
        {
        	this.setVisible(false);
              mainmenuframe x=new mainmenuframe(vehicles);
              x.setVisible(false);
              x.updateFrame(vehicles);
        }
    }

}
