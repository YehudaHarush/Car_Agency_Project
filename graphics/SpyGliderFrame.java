
package graphics;

import vehicles.Enums.PowerSource;


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

import vehicles.FactoryProvider;
import vehicles.Spy_glider;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class SpyGliderFrame extends JFrame {
	private JLabel colorLabel;
    private JComboBox<PowerSource> powerSourceComboBox;
    private JComboBox<String> colorComboBox;
    private JButton createButton;
    private ArrayList<Vehicle> vehicles;
    private String color;

    public SpyGliderFrame(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
        // create labels and combo box
        JLabel powerSourceLabel = new JLabel("<html><div style='text-align: center;'>What is the spy glider power source?</div></html>");
        powerSourceComboBox = new JComboBox<PowerSource>(PowerSource.values());
        powerSourceComboBox.addActionListener(e -> updateValues());

        // create create button
        createButton = new JButton("Create Spy Glider");
        createButton.addActionListener(e -> createSpyGlider());
        
        // create color label and combo box
        colorLabel = new JLabel("Color:");
        String[] colorOptions = { "Red", "Blue", "Green", "Yellow" ,"Orange","Cyan","Pink","Black","White" };
        colorComboBox = new JComboBox<>(colorOptions);

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(powerSourceLabel);
        panel.add(powerSourceComboBox);
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Spy Glider information");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void updateValues() {
        createButton.setEnabled(true);
    }

    private void createSpyGlider() {
        PowerSource powerSource = (PowerSource)powerSourceComboBox.getSelectedItem();
        VehicleFactory factory = FactoryProvider.getFactory(1); // 1 represents the air vehicle factory
		color = (String) colorComboBox.getSelectedItem();
	    String[] args = {powerSource.name(),color};
	    Vehicle spyVehicle = factory.create(0, args); // 0 represents the spy glider type
        vehicles.add(spyVehicle);
        JOptionPane.showMessageDialog(this, "Spy Glider created!");
        setVisible(false); // hide the SpyGliderFrame
        List<String> photoNames = Arrays.asList("spyglider1.jpg", "spyglider2.jpg", "spyglider3.jpg");
        @SuppressWarnings("unused")
		Photosframe photosFrame = new Photosframe(photoNames,spyVehicle);
    }
}
