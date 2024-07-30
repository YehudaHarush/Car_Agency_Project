package graphics;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

import vehicles.Amphibious;
import vehicles.Color_StatusD;
import vehicles.HybridPlane;
import vehicles.Vehicle;
import vehicles.air_vehicles;
import vehicles.land_vehicles;
import vehicles.sea_vehicles;

@SuppressWarnings("serial")
public class mainmenuframe extends JFrame {

    private ArrayList<Vehicle> vehicles;
    private List<JLabel> photoLabels;
    private JPanel photoPanel;
    private JLabel distanceLabel;
    private ThreadPoolExecutor testDriveExecutor;
    private int count=0;
    private static int totalDistance;
    private volatile boolean processesInProgress = false;
    private static ReentrantLock landLock = new ReentrantLock();
    private static ReentrantLock seaLock = new ReentrantLock();
    private static ReentrantLock airLock = new ReentrantLock();
    public mainmenuframe(ArrayList<Vehicle> vehicles1) {
        vehicles = new ArrayList<Vehicle>(vehicles1);
        this.photoLabels = new ArrayList<>();
        // create panel for photo labels
         photoPanel = new JPanel(new GridLayout(0, 4, 5, 5)); // create a grid layout with 4 columns and 5 pixels gap
         for (Vehicle vehicle : vehicles) {
        	    String color = ((Color_StatusD) vehicle).GetColor().toUpperCase();
        	    Color x = getColorFromString(color);
        	    
        	    ImageIcon icon = new ImageIcon(getClass().getResource(vehicle.getPhoto()));
        	    Image image = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        	    icon = new ImageIcon(image);
        	    
        	    JLabel photoLabel = new JLabel(icon);
        	    photoLabel.setToolTipText(vehicle.toString());
        	    photoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        	    photoLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        	    photoLabel.setBorder(BorderFactory.createLineBorder(x, 3));
        	    photoLabel.setPreferredSize(new Dimension(200, 200));
        	    photoLabels.add(photoLabel);
        	    photoPanel.add(photoLabel);
        	}
         distanceLabel = new JLabel("total distance: "+totalDistance);
         photoPanel.add(distanceLabel);
         // add photo panel to main menu frame
         getContentPane().add(photoPanel, BorderLayout.CENTER);
     


        JScrollPane scrollPane = new JScrollPane(photoPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton createButton = new JButton("Create New Vehicle");
        JButton resetKmButton = new JButton("Reset KM");
        JButton changeFlagButton = new JButton("Change Flag");
        JButton exitButton = new JButton("Exit");
        JButton reportButton=new JButton("Current inventory report");
        buttonPanel.add(createButton);
        buttonPanel.add(resetKmButton);
        buttonPanel.add(changeFlagButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(exitButton);

        // add action listeners to buttons
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Create_vehicle_frame(vehicles);
            }
        });
        reportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VehiclesReport(vehicles);
			}
		});
        resetKmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i=0;i<vehicles.size();i++)
        		{
        			vehicles.get(i).reset_km();
        		}
            	updateFrame(vehicles);
            	JOptionPane.showMessageDialog(null, "All vehicles km has been restarted.", "KM Reset", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        changeFlagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            
                // create an array of image icons to use as options
                ImageIcon[] options = {
                        new ImageIcon(getClass().getResource("israel.jpg")),
                        new ImageIcon(getClass().getResource("italy.jpg")),
                        new ImageIcon(getClass().getResource("usa.png")),
                        new ImageIcon(getClass().getResource("somalia.png")),
                        new ImageIcon(getClass().getResource("pirat.png")),
                        new ImageIcon(getClass().getResource("germany.png")),
                        new ImageIcon(getClass().getResource("greece.png"))
                };
                for (ImageIcon icon : options) {
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    icon.setImage(newImg);
                }
                int selectedOption = JOptionPane.showOptionDialog(
                        null,
                        "Please choose a flag",
                        "Choose Flag",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        null);
                // if the user selects an option, update the flag for all sea vehicles
                if (selectedOption == 0) {
                    for (Vehicle vehicle : vehicles) {
                        if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("israel");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 1) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("italy");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 2) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("usa");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 3) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("somalia");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 4) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("pirat");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 5) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("germany");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", "Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selectedOption == 6) {
                    for (Vehicle vehicle : vehicles) {
                    	if (((Color_StatusD)vehicle).get_vehicle() instanceof sea_vehicles) {
                            ((sea_vehicles) ((Color_StatusD)vehicle).get_vehicle()).change_flag("greece");
                        }
                    }
                    count=0;
                    updateFrame(vehicles);
                	JOptionPane.showMessageDialog(null, "All sea vehicles flag has been changed.", ")Flag change", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (processesInProgress) {
                    JOptionPane.showMessageDialog(null, "Please wait for all processes to complete before exiting.", "Processes in Progress",
                            JOptionPane.WARNING_MESSAGE);
                 
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(confirm);
                }
            }
        });
        
       
        
        // set up main menu frame
        setTitle("Vehicle Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // center the frame on the screen
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
    public void updateFrame(ArrayList<Vehicle> vehicles) {
        photoPanel.removeAll();
        photoPanel.revalidate();
        photoPanel.repaint();
        
        photoLabels.clear();
        for (Vehicle vehicle : vehicles) {
            ImageIcon icon = new ImageIcon(getClass().getResource(vehicle.getPhoto()));
            Image image = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            JLabel photoLabel = new JLabel(icon);
            String color = ((Color_StatusD) vehicle).GetColor().toUpperCase();
    	    Color x = getColorFromString(color);
            photoLabel.setToolTipText(vehicle.toString());
            photoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            photoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            
            photoLabel.setBorder(BorderFactory.createLineBorder(x, 3));
            photoLabels.add(photoLabel);
            final int index = photoLabels.size() - 1;
            photoLabel.addMouseListener(new PhotoMouseListener(index, vehicles));
            photoPanel.add(photoLabel);
        }
        photoPanel.revalidate();
        photoPanel.repaint();
        distanceLabel = new JLabel("total distance: "+totalDistance);
        photoPanel.add(distanceLabel);

        int sleepDuration = (int) (Math.random() * (8000 - 3000 + 1) + 3000);

        JOptionPane optionPane = new JOptionPane("Updating database... Please wait", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog("Updating");
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);

        Timer timer = new Timer(5, e -> {
            dialog.setVisible(false);
            dialog.dispose();
            setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }
    public class PhotoMouseListener extends MouseAdapter {
        private final int index;
        private ArrayList<Vehicle> vehicles;
        private Thread optionThread,buyThread=null,testThread;
        private Random random = new Random();


        public PhotoMouseListener(int index, ArrayList<Vehicle> vehicles) {
            this.index = index;
            this.vehicles = new ArrayList<>(vehicles);
     

        }
   
        @Override
        public void mouseClicked(MouseEvent e) {
            // Create a new window with three options when the user clicks on a photo label
        	
        	 optionThread=new Thread(new Runnable() 
        	 {
			
				@Override
				public void run() {
					
					JFrame optionsFrame = new JFrame("Vehicle Options");
		            optionsFrame.setSize(400, 200);
		            optionsFrame.setLocationRelativeTo(null);

		            JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		            optionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		            optionsFrame.getContentPane().add(optionsPanel, BorderLayout.CENTER);

		            JButton buyButton = new JButton("Buy Vehicle");
		            JButton testDriveButton = new JButton("Test Drive");
		            JButton exitButton = new JButton("Exit to Menu");
		            

		          

		            buyButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    // Check if a purchase is already in progress
		                	if ((buyThread != null && buyThread.isAlive()) || (testThread != null && testThread.isAlive())) {
		                        JOptionPane.showMessageDialog(null, "A purchase or test drive is already in progress. Please wait.", "Action in Progress",
		                                JOptionPane.WARNING_MESSAGE);
		                        return;
		                    }
		                    // Handle buy vehicle action in a separate thread
		                    buyThread = new Thread(new Runnable() {
		                        @Override
		                        public void run() {
		                        	processesInProgress = true;
		                            buyButton.setText("Buying in progress , please wait...");
		                            Vehicle vehicle = vehicles.get(index);
		                            ((Color_StatusD)vehicle).SetStatus("in the buying process");
	                                updateFrame(mainmenuframe.this.vehicles);
		                            int sleepTime = random.nextInt(10000 - 5000 + 1) + 5000;
		                            try {
		                                Thread.sleep(sleepTime);
		                            } catch (InterruptedException ex) {
		                                ex.printStackTrace();
		                            }

		                            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy " + vehicle.toString() + "?", "Confirmation",
		                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		                            if (confirmation == JOptionPane.YES_OPTION) {
		                                mainmenuframe.this.vehicles.remove(vehicle);
		                                optionsFrame.setVisible(false);
		                                JOptionPane.showMessageDialog(null, "You bought " + vehicle.toString(), "Vehicle Bought",
		                                        JOptionPane.INFORMATION_MESSAGE);
		                                processesInProgress = false;
		                                updateFrame(mainmenuframe.this.vehicles);
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Vehicle purchase canceled.", "Purchase Canceled",
		                                        JOptionPane.WARNING_MESSAGE);
		                                buyButton.setText("Buy");
			                            ((Color_StatusD)vehicle).SetStatus("in stock");
			                            updateFrame(mainmenuframe.this.vehicles);
		                                processesInProgress = false;
		                                
		                            }
		                        }
		                    });

		                    // Start the buy thread
		                    buyThread.start();
		                }
		            });
		           

		            testDriveButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    // Handle test drive action
		                	if ((buyThread != null && buyThread.isAlive()) || (testThread != null && testThread.isAlive())) {
		                        JOptionPane.showMessageDialog(null, "A purchase or test drive is already in progress. Please wait.", "Action in Progress",
		                                JOptionPane.WARNING_MESSAGE);
		                        return;
		                    }

		                    Vehicle vehicle = vehicles.get(index);
		                    
		                     if (vehicle instanceof land_vehicles && landLock.isLocked()) {
		                        JOptionPane.showMessageDialog(null, "Only one land vehicle can be tested at the same time");
		                        return;
		                    }  if (vehicle instanceof sea_vehicles && seaLock.isLocked()) {
		                        JOptionPane.showMessageDialog(null, "Only one sea vehicle can be tested at the same time");
		                        return;
		                    }  if (vehicle instanceof air_vehicles && airLock.isLocked()) {
		                        JOptionPane.showMessageDialog(null, "Only one air vehicle can be tested at the same time");
		                        return;
		                    }
		                    
		                    

		                    String input = JOptionPane.showInputDialog(null, "Enter the number of kilometers driven:");

		                    try {
		                        if (input == null) {
		                            // User clicked cancel, do nothing
		                            return;
		                        }

		                        float distance = Float.parseFloat(input);
		                        testThread = new Thread(new Runnable() {
		                            @Override
		                            public void run() {
		                                try {
				                            testDriveButton.setText("Testing in progress, please wait...");
		                                	processesInProgress = true;
		                                	 ((Color_StatusD)vehicle).SetStatus("in the testing process");
		 	                                updateFrame(mainmenuframe.this.vehicles);
		                                	 
				                                 if (vehicle instanceof land_vehicles) {
				                                	landLock.lock();
				                                } if (vehicle instanceof sea_vehicles) {
				                                	seaLock.lock();
				                                } if (vehicle instanceof air_vehicles) {
				                                	airLock.lock();
				                                }
		                                    
		                                 
		                                    Thread.sleep((long) (distance * 100));
		                                } catch (InterruptedException ex) {
		                                    ex.printStackTrace();
		                                }
		                                vehicle.Distance(distance);
		                                totalDistance += distance;
		                                optionsFrame.setVisible(false);
		                                updateFrame(vehicles);
		                                JOptionPane.showMessageDialog(null,
		                                        "You test drove " + vehicle.toString() + " for " + distance + " kilometers.", "Test Drive",
		                                        JOptionPane.INFORMATION_MESSAGE);
		                                 if (vehicle instanceof land_vehicles) {
		                                	landLock.unlock();
		                                }   if (vehicle instanceof sea_vehicles) {
		                                	seaLock.unlock();
		                                }   if (vehicle instanceof air_vehicles) {
		                                	airLock.unlock();
		                                }
		                                processesInProgress = false;
		                                testDriveButton.setText("Test");
		                                ((Color_StatusD)vehicle).SetStatus("in stock");
		                                updateFrame(mainmenuframe.this.vehicles);
		                            }
		                        });
		                        testThread.start();
		                    } catch (NumberFormatException ex) {
		                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error",
		                                JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });







		            exitButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                	
		                    optionsFrame.dispose();
		                }
		            });

		            optionsPanel.add(buyButton);
		            optionsPanel.add(testDriveButton);
		            optionsPanel.add(exitButton);
		            optionsFrame.setVisible(true);
		        }
				
			});
        	 optionThread.start();
        }
    }
}


