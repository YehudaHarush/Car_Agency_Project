package graphics;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vehicles.Vehicle;

@SuppressWarnings("serial")
public class Photosframe extends JFrame {

    private List<String> photoNames;
    private JLabel[] photoLabels;
    private Vehicle vehicles;

    public Photosframe(List<String> photoNames, Vehicle vehicles) {
        // set the list of photo names
        this.photoNames = photoNames;
        this.vehicles = vehicles;

        // create an array of JLabels for each photo
        photoLabels = new JLabel[photoNames.size()];
        for (int i = 0; i < photoLabels.length; i++) {
            // Load the original image
            BufferedImage originalImage;
            try {
                originalImage = ImageIO.read(getClass().getResource(photoNames.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
                continue; // skip this photo if there was an error loading it
            }
            // Resize the image to 400x400
            BufferedImage resizedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage, 0, 0, 400, 400, null);
            ImageIcon icon = new ImageIcon(resizedImage);
            photoLabels[i] = new JLabel(icon);
            photoLabels[i].addMouseListener(new PhotoMouseListener(i));
        }

        // create panel and add photo labels
        JPanel panel = new JPanel(new GridLayout(1, photoNames.size()));
        for (int i = 0; i < photoLabels.length; i++) {
            panel.add(photoLabels[i]);
        }

        // create frame and add panel
        this.add(panel);
        this.setTitle("Choose a photo");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class PhotoMouseListener extends MouseAdapter {
        private int index;

        public PhotoMouseListener(int index) {
            this.index = index;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // set the selected photo in the vehicle object
            String selectedPhoto = photoNames.get(index);
            vehicles.setPhoto(selectedPhoto);

            // display a dialog box with the selected photo name
            JOptionPane.showMessageDialog(null, "You selected: " + selectedPhoto);

            // close the frame
            dispose();
        }
    }
}
