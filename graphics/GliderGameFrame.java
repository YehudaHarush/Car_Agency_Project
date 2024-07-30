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

import vehicles.FactoryProvider;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

@SuppressWarnings("serial")
public class GliderGameFrame extends JFrame {
    private JLabel colorLabel;
    private JComboBox<String> colorComboBox;
    private JButton createButton;
    private ArrayList<Vehicle> vehicles;
    private String color;

    public GliderGameFrame(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;

        // create color label and combo box
        colorLabel = new JLabel("Color:");
        String[] colorOptions = { "Red", "Blue", "Green", "Yellow", "Orange", "Cyan", "Pink", "Black", "White" };
        colorComboBox = new JComboBox<>(colorOptions);

        // create create button
        createButton = new JButton("Create Glider Game");
        createButton.addActionListener(e -> createGliderGame());

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(3,1));
        panel.add(colorLabel);
        panel.add(colorComboBox);
        panel.add(createButton);

        // create frame and add panel
        this.setResizable(false);
        this.add(panel);
        this.setTitle("Glider Game information");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createGliderGame() {
        VehicleFactory factory = FactoryProvider.getFactory(1); // 1 represents the air vehicle factory
        color = (String) colorComboBox.getSelectedItem();
        String[] args = { color };
        Vehicle gliderGameVehicle = factory.create(1, args); // 1 represents the glider game type
        vehicles.add(gliderGameVehicle);
        JOptionPane.showMessageDialog(this, "Glider Game vehicle created!");
        setVisible(false); // hide the GliderGameFrame
        List<String> photoNames = Arrays.asList("glidergame1.jpg", "glidergame2.jpg", "glidergame3.jpg");
        @SuppressWarnings("unused")
        Photosframe photosFrame = new Photosframe(photoNames, gliderGameVehicle);
    }
}

