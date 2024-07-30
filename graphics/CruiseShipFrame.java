package graphics;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vehicles.Cruise_ship;
import vehicles.FactoryProvider;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class CruiseShipFrame extends JFrame implements DocumentListener {
    JLabel nameLabel, passengerLabel, speedLabel, flagLabel, fuelLabel, engineLabel,colorLabel;
    JTextField nameField, passengerField, speedField, flagField, fuelField, engineField, engineLifeField;
    JButton createButton;
    JComboBox<String> colorComboBox;
    String name, flag,color;
    int passengers, maxSpeed, averageFuel, engineLife;

    public CruiseShipFrame(ArrayList<Vehicle> cruiseShips) {
        // create labels
        nameLabel = new JLabel("Name:");
        passengerLabel = new JLabel("Passenger capacity:");
        speedLabel = new JLabel("Max speed:");
        flagLabel = new JLabel("Flag:");
        fuelLabel = new JLabel("Average fuel consumption:");
        engineLabel = new JLabel("Engine life:");

        // create text fields
        nameField = new JTextField(10);
        passengerField = new JTextField(10);
        speedField = new JTextField(10);
        flagField = new JTextField(10);
        fuelField = new JTextField(10);
        engineField = new JTextField(10);
        engineLifeField = new JTextField(10);


        // add listeners to text fields
        nameField.getDocument().addDocumentListener(this);
        passengerField.getDocument().addDocumentListener(this);
        speedField.getDocument().addDocumentListener(this);
        flagField.getDocument().addDocumentListener(this);
        fuelField.getDocument().addDocumentListener(this);
        engineField.getDocument().addDocumentListener(this);

     // create color label and combo box
        colorLabel = new JLabel("Color:");
        String[] colorOptions = { "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White" };
        colorComboBox = new JComboBox<>(colorOptions);

        
        // create create button
        createButton = new JButton("Create Cruise Ship");
        createButton.setEnabled(false);
        createButton.addActionListener(e -> createCruiseShip(cruiseShips));

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passengerLabel);
        panel.add(passengerField);
        panel.add(speedLabel);
        panel.add(speedField);
        panel.add(flagLabel);
        panel.add(flagField);
        panel.add(fuelLabel);
        panel.add(fuelField);
        panel.add(engineLabel);
        panel.add(engineField);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(new JLabel()); // add empty label for spacing
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Cruise Ship information");
        this.setSize(350, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // DocumentListener methods
    public void changedUpdate(DocumentEvent e) {
        updateValues();
    }

    public void removeUpdate(DocumentEvent e) {
        updateValues();
    }

    public void insertUpdate(DocumentEvent e) {
        updateValues();
    }

    private void updateValues() {
        name = nameField.getText();
        String passengerText = passengerField.getText();
        String speedText = speedField.getText();
        flag = flagField.getText();
        String fuelText = fuelField.getText();
        String engineText = engineField.getText();
        
        if (!name.isEmpty() && !passengerText.isEmpty() && !speedText.isEmpty() && !flag.isEmpty() && !fuelText.isEmpty() && !engineText.isEmpty()) {
            try {
                passengers = Integer.parseInt(passengerText);
                maxSpeed = Integer.parseInt(speedText);
                averageFuel = Integer.parseInt(fuelText);
                engineLife = Integer.parseInt(engineText);
                createButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                // handle the exception
                createButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            createButton.setEnabled(false);
        }
    }

    public void createCruiseShip(ArrayList<Vehicle> vehicles) {
        updateValues(); // update the values before creating the CruiseShip object
        if (createButton.isEnabled()) {
        	VehicleFactory factory = FactoryProvider.getFactory(2); // 2 represents the sea vehicle factory
    		color = (String) colorComboBox.getSelectedItem();
    	    String[] args = {name,String.valueOf(passengers), String.valueOf(maxSpeed),flag,String.valueOf(averageFuel),String.valueOf(engineLife),color};
    	    Vehicle Cruise = factory.create(1, args); // 1 represents the Cruise ship type          
            nameField.setText("");
            passengerField.setText("");
            speedField.setText("");
            flagField.setText("");
            fuelField.setText("");
            engineLifeField.setText("");
            vehicles.add(Cruise);
            JOptionPane.showMessageDialog(this, "Cruise ship created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            List<String> photoNames = Arrays.asList("cruiseship1.jpg", "cruiseship2.jpg", "cruiseship3.jpg");
            @SuppressWarnings("unused")
			Photosframe photosFrame = new Photosframe(photoNames,Cruise);
        }
    }

}