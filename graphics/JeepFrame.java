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
import vehicles.Jeep;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class JeepFrame extends JFrame implements DocumentListener {
    JLabel modelLabel, fuelLabel, speedLabel, engineLabel, colorLabel;
    JTextField modelField, fuelField, speedField, engineField;
    JComboBox<String> colorComboBox;
    JButton createButton;
    String model, color;
    int fuel, speed, engine;

    public JeepFrame(ArrayList<Vehicle> vehicles) {
        // create labels
        modelLabel = new JLabel("Model:");
        fuelLabel = new JLabel("Fuel consuming:");
        speedLabel = new JLabel("Max speed:");
        engineLabel = new JLabel("Engine life:");
        colorLabel = new JLabel("Color:");

        // create text fields
        modelField = new JTextField(10);
        fuelField = new JTextField(10);
        speedField = new JTextField(10);
        engineField = new JTextField(10);

        // create color combo box
        String[] colors = { "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White"}; // Modify the colors as per your requirement
        colorComboBox = new JComboBox<>(colors);

        // add listeners to text fields
        modelField.getDocument().addDocumentListener(this);
        fuelField.getDocument().addDocumentListener(this);
        speedField.getDocument().addDocumentListener(this);
        engineField.getDocument().addDocumentListener(this);

        // create create button
        createButton = new JButton("Create Jeep");
        createButton.setEnabled(false);
        createButton.addActionListener(e -> createJeep(vehicles));

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(modelLabel);
        panel.add(modelField);
        panel.add(fuelLabel);
        panel.add(fuelField);
        panel.add(speedLabel);
        panel.add(speedField);
        panel.add(engineLabel);
        panel.add(engineField);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(createButton);

        // create frame and add panel
        this.add(panel);
        this.setTitle("Jeep information");
        this.setSize(300, 300);
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
        String fuelText = fuelField.getText();
        String speedText = speedField.getText();
        String engineText = engineField.getText();

        if (!model.isEmpty() && !fuelText.isEmpty() && !speedText.isEmpty() && !engineText.isEmpty()) {
            try {
                fuel = Integer.parseInt(fuelText);
                speed = Integer.parseInt(speedText);
                engine = Integer.parseInt(engineText);
                createButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                // handle the exception
                createButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            createButton.setEnabled(false);
        }
    }

    // method to create Jeep object
    public void createJeep(ArrayList<Vehicle> vehicles) {
        VehicleFactory factory = FactoryProvider.getFactory(0); // 0 represents the land vehicle factory

        color = (String) colorComboBox.getSelectedItem();

        String[] args = { model, String.valueOf(fuel), String.valueOf(speed), String.valueOf(engine), color };
        Vehicle jeep = factory.create(0, args); // 0 represents the Jeep type

        modelField.setText("");
        fuelField.setText("");
        speedField.setText("");
        engineField.setText("");
        createButton.setEnabled(false);

        vehicles.add(jeep);

        JOptionPane.showMessageDialog(this, "Jeep " + model + " created!");

        setVisible(false); // hide the JeepFrame

        List<String> photoNames = Arrays.asList("jeep1.png", "jeep2.png", "jeep3.jpg");
        Photosframe photosFrame = new Photosframe(photoNames, jeep);
    }
}
