import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JFrame frame;
    JPanel cardPanel;
    CardLayout cardLayout;

    // Login Components
    JPanel loginPanel;
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    // Main Library Components
    JPanel mainPanel;
    JTable table;
    DefaultTableModel tableModel;
    JTextField authorField, titleField, dateField, studentNameField, studentIdField;
    JButton addBtn, deleteBtn, modifyBtn, checkoutBtn, checkinBtn, logoutBtn;

    public GUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        buildLoginPanel();
        buildMainPanel();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(mainPanel, "Main");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private void buildLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        userField = new JTextField(15);
        passField = new JPasswordField(15);
        loginBtn = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(passField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        loginPanel.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (Account.authenticate(user, pass) != null) {
                cardLayout.show(cardPanel, "Main");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
            }
        });
    }

    private void buildMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        // Table setup
        String[] columns = {"Author", "Title", "Date", "Status", "Checked Out By"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        authorField = new JTextField();
        titleField = new JTextField();
        dateField = new JTextField();
        studentNameField = new JTextField();
        studentIdField = new JTextField();

        inputPanel.add(new JLabel("Title:")); inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:")); inputPanel.add(authorField);
        inputPanel.add(new JLabel("Date:")); inputPanel.add(dateField);
        inputPanel.add(new JLabel("Student Name:")); inputPanel.add(studentNameField);
        inputPanel.add(new JLabel("Student ID:")); inputPanel.add(studentIdField);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        addBtn = new JButton("Add Book");
        deleteBtn = new JButton("Delete Book");
        modifyBtn = new JButton("Modify Book");
        checkoutBtn = new JButton("Check Out");
        checkinBtn = new JButton("Check In");
        logoutBtn = new JButton("Logout");

        btnPanel.add(addBtn);
        btnPanel.add(modifyBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(checkoutBtn);
        btnPanel.add(checkinBtn);
        btnPanel.add(logoutBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addBtn.addActionListener(e -> {
            new Library(authorField.getText(), titleField.getText(), dateField.getText());
            refreshTable();
            clearFields();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Library.destroy(row);
                refreshTable();
            }
        });

        modifyBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Library.modify(row, authorField.getText(), titleField.getText(), dateField.getText());
                refreshTable();
                clearFields();
            }
        });

        checkoutBtn.addActionListener(e -> {
            String title = titleField.getText();
            if (Library.checkOutBook(title, studentNameField.getText(), studentIdField.getText())) {
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Book not found or already checked out.");
            }
        });

        checkinBtn.addActionListener(e -> {
            if (Library.checkInBook(titleField.getText())) {
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Book not found or not checked out.");
            }
        });

        logoutBtn.addActionListener(e -> {
            cardLayout.show(cardPanel, "Login");
            userField.setText("");
            passField.setText("");
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if(row != -1) {
                authorField.setText(tableModel.getValueAt(row, 0).toString());
                titleField.setText(tableModel.getValueAt(row, 1).toString());
                dateField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Library lib : Library.libraryList) {
            tableModel.addRow(lib.book);
        }
    }

    private void clearFields() {
        authorField.setText("");
        titleField.setText("");
        dateField.setText("");
        studentNameField.setText("");
        studentIdField.setText("");
    }
}