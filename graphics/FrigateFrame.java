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

import vehicles.FactoryProvider;
import vehicles.Frigate;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class FrigateFrame extends JFrame implements DocumentListener {
    JLabel modelLabel, passengerLabel, speedLabel, withWindLabel,colorLabel;
    JTextField modelField, passengerField, speedField;
    JComboBox<String> withWindComboBox;
    JComboBox<String> colorComboBox;
    JButton createButton;
    String model,color;
    int passenger, speed;
    boolean withWind;

    public FrigateFrame(ArrayList<Vehicle> vehicles) {
        // create labels
        modelLabel = new JLabel("Model:");
        passengerLabel = new JLabel("Passenger capacity:");
        speedLabel = new JLabel("Max speed:");
        withWindLabel = new JLabel("Wind dircation:");

        // create text fields and checkbox
        modelField = new JTextField(10);
        passengerField = new JTextField(10);
        speedField = new JTextField(10);
        withWindComboBox = new JComboBox<String>(new String[] { "With wind", "against wind" });

        // add listeners to text fields
        modelField.getDocument().addDocumentListener(this);
        passengerField.getDocument().addDocumentListener(this);
        speedField.getDocument().addDocumentListener(this);
        withWindComboBox.addActionListener(e -> updateValues());

        // create create button
        createButton = new JButton("Create Frigate");
        createButton.setEnabled(false);
        createButton.addActionListener(e -> createFrigate(vehicles));

        // create color label and combo box
        colorLabel = new JLabel("Color:");
        String[] colorOptions = { "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White" };
        colorComboBox = new JComboBox<>(colorOptions);
        
        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(modelLabel);
        panel.add(modelField);
        panel.add(passengerLabel);
        panel.add(passengerField);
        panel.add(speedLabel);
        panel.add(speedField);
        panel.add(withWindLabel);
        panel.add(withWindComboBox);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(new JLabel()); // add empty label for spacing
        panel.add(new JLabel());
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Frigate information");
        this.setSize(300, 250);
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
        withWind = ((String)withWindComboBox.getSelectedItem()).equals("With wind");
        
        if (!model.isEmpty() && !passengerText.isEmpty() && !speedText.isEmpty() && withWindComboBox.getSelectedIndex() != -1) {
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
    public void createFrigate(ArrayList<Vehicle> vehicles) {
        updateValues(); // update the values before creating the Frigate object
        if (createButton.isEnabled()) {
        	VehicleFactory factory = FactoryProvider.getFactory(2); // 2 represents the sea vehicle factory
        	color = (String) colorComboBox.getSelectedItem();
    	    String[] args = {model,String.valueOf(passenger),String.valueOf(speed),String.valueOf(withWind),color};
    	    Vehicle Frigate = factory.create(0, args); // 0 represents the Frigate type
            modelField.setText("");
            passengerField.setText("");
            speedField.setText("");
            createButton.setEnabled(false);
            vehicles.add(Frigate);
            JOptionPane.showMessageDialog(this, "Frigate " + model + " created!");
            setVisible(false); // hide the FrigateFrame
            List<String> photoNames = Arrays.asList("frigate1.jpg", "frigate2.jpg", "frigate3.png");
            @SuppressWarnings("unused")
			Photosframe photosFrame = new Photosframe(photoNames,Frigate);
        }
    }

}