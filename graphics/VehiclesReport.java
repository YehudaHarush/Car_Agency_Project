package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import vehicles.Color_StatusD;
import vehicles.Vehicle;

@SuppressWarnings("serial")
public class VehiclesReport extends JFrame {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<JLabel> photoLabels;

    public VehiclesReport(ArrayList<Vehicle> vehicles) {
        this.vehicles = new ArrayList<Vehicle>(vehicles);
        this.photoLabels = new ArrayList<JLabel>();

        // Create panel for photo labels
        JPanel photoPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        for (Vehicle vehicle : vehicles) {
            ImageIcon icon = new ImageIcon(getClass().getResource(vehicle.getPhoto()));
            Image image = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            String color = ((Color_StatusD) vehicle).GetColor().toUpperCase();
    	    Color x = getColorFromString(color);
            JLabel photoLabel = new JLabel(icon);
            photoLabel.setToolTipText(vehicle.toString());
            photoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            photoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            photoLabel.setBorder(BorderFactory.createLineBorder(x, 3));
    	    photoLabel.setPreferredSize(new Dimension(200, 200));
            photoLabels.add(photoLabel);
            photoPanel.add(photoLabel);
        }

        // Set up main menu frame
        setTitle("Vehicle Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JScrollPane(photoPanel), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private Color getColorFromString(String color) {
        switch (color) {
            case "BLACK":
                return Color.BLACK;
            case "RED":
                return Color.RED;
            case "PINK":
                return Color.PINK;
            case "ORANGE":
                return Color.ORANGE;
            case "YELLOW":
                return Color.YELLOW;
            case "BLUE":
                return Color.BLUE;
            case "GREEN":
                return Color.GREEN;
            case "CYAN":
                return Color.CYAN;
            default:
                return Color.WHITE;
        }
    }
}
