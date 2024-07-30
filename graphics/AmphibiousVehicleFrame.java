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

import vehicles.Amphibious;
import vehicles.FactoryProvider;
import vehicles.Enums.RoadType;
import vehicles.Vehicle;
import vehicles.VehicleFactory;


@SuppressWarnings("serial")
public class AmphibiousVehicleFrame extends JFrame implements DocumentListener {
    JLabel nameLabel, passengerLabel, speedLabel, flagLabel, wheelsNumLabel, fuelLabel, engineLifeLabel,colorLabel;
    JTextField nameField, passengerField, speedField, flagField, wheelsNumField, fuelField, engineLifeField;
    JComboBox<RoadType> roadTypeComboBox;
    JComboBox<String> colorComboBox;
    JButton createButton;
    String name, flag,color;
    int passengers, maxSpeed, wheelsNum, averageFuel, engineLife;
    RoadType roadType;
	private int avgFuel;

    public AmphibiousVehicleFrame(ArrayList<Vehicle> vehicles) {
        // create labels
        nameLabel = new JLabel("Name:");
        passengerLabel = new JLabel("Passenger capacity:");
        speedLabel = new JLabel("Max speed:");
        flagLabel = new JLabel("Flag:");
        wheelsNumLabel = new JLabel("Wheels number:");
        fuelLabel = new JLabel("Average fuel:");
        engineLifeLabel = new JLabel("Engine life:");

        // create text fields and combobox
        nameField = new JTextField(10);
        passengerField = new JTextField(10);
        speedField = new JTextField(10);
        flagField = new JTextField(10);
        wheelsNumField = new JTextField(10);
        fuelField = new JTextField(10);
        engineLifeField = new JTextField(10);

        // add listeners to text fields
        nameField.getDocument().addDocumentListener(this);
        passengerField.getDocument().addDocumentListener(this);
        speedField.getDocument().addDocumentListener(this);
        flagField.getDocument().addDocumentListener(this);
        wheelsNumField.getDocument().addDocumentListener(this);
        fuelField.getDocument().addDocumentListener(this);
        engineLifeField.getDocument().addDocumentListener(this);

        // create create button
        createButton = new JButton("Create Amphibious");
        createButton.setEnabled(false);
        createButton.addActionListener(e -> createAmphibiousVehicle(vehicles));
        
        colorLabel = new JLabel("Color:");
        String[] colorOptions = {  "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White" };
        colorComboBox = new JComboBox<>(colorOptions);
        
        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passengerLabel);
        panel.add(passengerField);
        panel.add(speedLabel);
        panel.add(speedField);
        panel.add(flagLabel);
        panel.add(flagField);
        panel.add(wheelsNumLabel);
        panel.add(wheelsNumField);
        panel.add(fuelLabel);
        panel.add(fuelField);
        panel.add(engineLifeLabel);
        panel.add(engineLifeField);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(new JLabel()); // add empty label for spacing
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Amphibious Vehicle information");
        this.setSize(350, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

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
        String passengersText = passengerField.getText();
        String maxSpeedText = speedField.getText();
        flag = flagField.getText();
        String wheelsNumText = wheelsNumField.getText();
        String avgFuelText = fuelField.getText();
        String engineLifeText = engineLifeField.getText();

        if (!name.isEmpty() && !passengersText.isEmpty() && !maxSpeedText.isEmpty() && !flag.isEmpty()
                && !wheelsNumText.isEmpty() && !avgFuelText.isEmpty() && !engineLifeText.isEmpty()) {
            try {
                passengers = Integer.parseInt(passengersText);
                maxSpeed = Integer.parseInt(maxSpeedText);
                wheelsNum = Integer.parseInt(wheelsNumText);
                avgFuel = Integer.parseInt(avgFuelText);
                engineLife = Integer.parseInt(engineLifeText);
                createButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                createButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            createButton.setEnabled(false);
        }
    }

    public void createAmphibiousVehicle(ArrayList<Vehicle> vehicles) {
        updateValues(); // update the values before creating the AmphibiousVehicle object
        if (createButton.isEnabled()) {
        	VehicleFactory factory = FactoryProvider.getFactory(0); // 0 represents the land vehicle factory
    		color = (String) colorComboBox.getSelectedItem();
    	    String[] args = {name, String.valueOf(passengers), String.valueOf(maxSpeed), flag,String.valueOf(wheelsNum),String.valueOf(averageFuel),String.valueOf(engineLife),color};
    	    Vehicle amphibous = factory.create(2, args); // 2 represents the amphibous 
            nameField.setText("");
            passengerField.setText("");
            speedField.setText("");
            flagField.setText("");
            wheelsNumField.setText("");
            fuelField.setText("");
            engineLifeField.setText("");
            vehicles.add(amphibous);
            JOptionPane.showMessageDialog(this, "Amphibious vehicle created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            List<String> photoNames = Arrays.asList("amph1.jpg", "amph2.png", "amph3.jpg");
            @SuppressWarnings("unused")
			Photosframe photosFrame = new Photosframe(photoNames,amphibous);
        }
    }
}

