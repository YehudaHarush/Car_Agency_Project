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

import vehicles.Regular_bicycle;
import vehicles.Enums.RoadType;
import vehicles.FactoryProvider;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class BicycleFrame extends JFrame implements DocumentListener {
    JLabel modelLabel, passengerLabel, speedLabel, roadTypeLabel,colorLabel;
    JTextField modelField, passengerField, speedField;
    JComboBox<RoadType> roadTypeComboBox;
    JComboBox<String> colorComboBox;
    JButton createButton;
    String model,color;
    int passenger, speed;
    RoadType roadType;

    public BicycleFrame(ArrayList<Vehicle> vehicles) {
        // create labels
        modelLabel = new JLabel("Model:");
        passengerLabel = new JLabel("Passenger capacity:");
        speedLabel = new JLabel("Max speed:");
        roadTypeLabel = new JLabel("Road type:");

        // create text fields and combobox
        modelField = new JTextField(10);
        passengerField = new JTextField(10);
        speedField = new JTextField(10);
        roadTypeComboBox = new JComboBox<>(RoadType.values());

        // add listeners to text fields
        modelField.getDocument().addDocumentListener(this);
        passengerField.getDocument().addDocumentListener(this);
        speedField.getDocument().addDocumentListener(this);
        roadTypeComboBox.addActionListener(e -> updateValues());

        // create create button
        createButton = new JButton("Create Bicycle");
        createButton.setEnabled(false);
        createButton.addActionListener(e -> createBicycle(vehicles));
        
        // create color label and combo box
        colorLabel = new JLabel("Color:");
        String[] colorOptions = { "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White" };
        colorComboBox = new JComboBox<>(colorOptions);

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(modelLabel);
        panel.add(modelField);
        panel.add(passengerLabel);
        panel.add(passengerField);
        panel.add(speedLabel);
        panel.add(speedField);
        panel.add(roadTypeLabel);
        panel.add(roadTypeComboBox);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(new JLabel()); // add empty label for spacing
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Bicycle information");
        this.setSize(300, 200);
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

    // method to update values from text fields and enable/disable create button
    private void updateValues() {
        model = modelField.getText();
        String passengerText = passengerField.getText();
        String speedText = speedField.getText();
        roadType = (RoadType) roadTypeComboBox.getSelectedItem();

        if (!model.isEmpty() && !passengerText.isEmpty() && !speedText.isEmpty() && roadTypeComboBox.getSelectedIndex() != -1) {
            try {
                passenger = Integer.parseInt(passengerText);
                speed = Integer.parseInt(speedText);
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

    public void createBicycle(ArrayList<Vehicle> vehicles) {
    	updateValues(); // update the values before creating the Bicycle object
    	if (createButton.isEnabled()) {
    		VehicleFactory factory = FactoryProvider.getFactory(0); // 0 represents the land vehicle factory
    		color = (String) colorComboBox.getSelectedItem();

    	    String[] args = {model, String.valueOf(passenger), String.valueOf(speed), roadType.name(),color};
    	    Vehicle bicycle = factory.create(1, args); // 1 represents the Regular_bicycle type
    	modelField.setText("");
    	passengerField.setText("");
    	speedField.setText("");
    	roadTypeComboBox.setSelectedIndex(0);
    	vehicles.add(bicycle);
    	JOptionPane.showMessageDialog(this, "Bicycle created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	 setVisible(false);
    	 List<String> photoNames = Arrays.asList("bicycle1.jpg", "bicycle2.jpg", "bicycle3.jpg");
         @SuppressWarnings("unused")
		Photosframe photosFrame = new Photosframe(photoNames,bicycle);
    	}
    }
}
