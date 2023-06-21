import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Train {
    private int trainNo;
    private String trainName;

    public Train(int trainNo, String trainName) {
        this.trainNo = trainNo;
        this.trainName = trainName;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }
}

class User {
    private String loginId;
    private String password;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}

class ReservationSystem {
    private static final Map<Integer, Train> trainMap = new HashMap<>();
    private static final Map<String, User> userMap = new HashMap<>();
    private static final Map<Integer, String> reservationMap = new HashMap<>();
    private static int pnrCounter = 1000;

    static {
        trainMap.put(17301, new Train(17301, "Mysore-Dharwad Express"));
        trainMap.put(12726, new Train(12726, "Siddhaganga Intercity Express"));

        userMap.put("Abdallah", new User("Abdallah", "ExpPwd"));
        userMap.put("Mohammed Ali", new User("Mohammed Ali", "PWexp"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Online Train Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel labelLoginId = new JLabel("Login ID:");
        JTextField textFieldLoginId = new JTextField();
        JLabel labelPassword = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton buttonLogin = new JButton("Login");
        JButton buttonExit = new JButton("Exit");

        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginId = textFieldLoginId.getText();
                String password = new String(passwordField.getPassword());

                // Call the loginModule with the provided loginId and password
                loginModule(loginId, password);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(labelLoginId);
        frame.add(textFieldLoginId);
        frame.add(labelPassword);
        frame.add(passwordField);
        frame.add(buttonLogin);
        frame.add(buttonExit);

        frame.setVisible(true);
    }

    private static void loginModule(String loginId, String password) {
        User user = userMap.get(loginId);
        if (user != null && user.getPassword().equals(password)) {
            JFrame optionsFrame = new JFrame("Online Train Reservation System");
            optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionsFrame.setSize(400, 200);
            optionsFrame.setLayout(new GridLayout(3, 1));

            JButton buttonReservation = new JButton("Make a reservation");
            JButton buttonCancellation = new JButton("Cancel a reservation");
            JButton buttonExit = new JButton("Exit");

            buttonReservation.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reservationModule();
                }
            });

            buttonCancellation.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cancellationModule();
                }
            });

            buttonExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    optionsFrame.dispose();
                }
            });

            optionsFrame.add(buttonReservation);
            optionsFrame.add(buttonCancellation);
            optionsFrame.add(buttonExit);

            optionsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid login ID or password.");
        }
    }

    private static void reservationModule() {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reservationFrame.setSize(400, 300);
        reservationFrame.setLayout(new GridLayout(8, 2));

        JLabel labelTrainNo = new JLabel("Train Number:");
        JTextField textFieldTrainNo = new JTextField();
        JLabel labelTrainName = new JLabel("Train Name:");
        JTextField textFieldTrainName = new JTextField();
        JLabel labelClassType = new JLabel("Class Type:");
        JTextField textFieldClassType = new JTextField();
        JLabel labelJourneyDate = new JLabel("Journey Date:");
        JTextField textFieldJourneyDate = new JTextField();
        JLabel labelSource = new JLabel("Source:");
        JTextField textFieldSource = new JTextField();
        JLabel labelDestination = new JLabel("Destination:");
        JTextField textFieldDestination = new JTextField();
        JButton buttonMakeReservation = new JButton("Make Reservation");
        JButton buttonCancel = new JButton("Cancel");

        reservationFrame.add(labelTrainNo);
        reservationFrame.add(textFieldTrainNo);
        reservationFrame.add(labelTrainName);
        reservationFrame.add(textFieldTrainName);
        reservationFrame.add(labelClassType);
        reservationFrame.add(textFieldClassType);
        reservationFrame.add(labelJourneyDate);
        reservationFrame.add(textFieldJourneyDate);
        reservationFrame.add(labelSource);
        reservationFrame.add(textFieldSource);
        reservationFrame.add(labelDestination);
        reservationFrame.add(textFieldDestination);
        reservationFrame.add(buttonMakeReservation);
        reservationFrame.add(buttonCancel);

        textFieldTrainNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int trainNo;
                try {
                    trainNo = Integer.parseInt(textFieldTrainNo.getText());
                } catch (NumberFormatException ex) {
                    textFieldTrainName.setText("");
                    return;
                }

                Train train = trainMap.get(trainNo);
                if (train != null) {
                    textFieldTrainName.setText(train.getTrainName());
                } else {
                    textFieldTrainName.setText("N/A");
                }
            }
        });

        buttonMakeReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int trainNo;
                try {
                    trainNo = Integer.parseInt(textFieldTrainNo.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid train number.");
                    return;
                }

                Train train = trainMap.get(trainNo);
                if (train == null) {
                    JOptionPane.showMessageDialog(null, "Invalid train number.");
                } else {
                    String classType = textFieldClassType.getText();
                    String journeyDate = textFieldJourneyDate.getText();
                    String source = textFieldSource.getText();
                    String destination = textFieldDestination.getText();
                    int pnr = generatePNR();
                    reservationMap.put(pnr, "Train: " + train.getTrainName() +
                            ", Class: " + classType +
                            ", Date: " + journeyDate +
                            ", Source: " + source +
                            ", Destination: " + destination);
                    JOptionPane.showMessageDialog(null, "Reservation successful!\nPNR: " + pnr);
                    reservationFrame.dispose();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservationFrame.dispose();
            }
        });

        reservationFrame.setVisible(true);
    }

    private static void cancellationModule() {
        JFrame cancellationFrame = new JFrame("Cancel a Reservation");
        cancellationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancellationFrame.setSize(400, 150);
        cancellationFrame.setLayout(new GridLayout(3, 2));

        JLabel labelPNR = new JLabel("PNR number:");
        JTextField textFieldPNR = new JTextField();
        JButton buttonCancelReservation = new JButton("Cancel Reservation");
        JButton buttonCancel = new JButton("Cancel");

        buttonCancelReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pnr;
                try {
                    pnr = Integer.parseInt(textFieldPNR.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid PNR number.");
                    return;
                }

                String reservationInfo = reservationMap.get(pnr);
                if (reservationInfo != null) {
                    JOptionPane.showMessageDialog(null, "Reservation Details: " + reservationInfo);
                    reservationMap.remove(pnr);
                    JOptionPane.showMessageDialog(null, "Cancellation successful!");
                    cancellationFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PNR number.");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancellationFrame.dispose();
            }
        });

        cancellationFrame.add(labelPNR);
        cancellationFrame.add(textFieldPNR);
        cancellationFrame.add(buttonCancelReservation);
        cancellationFrame.add(buttonCancel);

        cancellationFrame.setVisible(true);
    }

    private static int generatePNR() {
        return ++pnrCounter;
    }
}

