import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SafeSwitchUI extends JFrame {

    // =========================
    // COLORS
    // =========================

    private final Color BG = new Color(10, 13, 17);
    private final Color SIDEBAR = new Color(14, 18, 23);
    private final Color CARD = new Color(20, 26, 33);
    private final Color INPUT = new Color(27, 34, 42);
    private final Color BORDER = new Color(48, 58, 68);

    private final Color TEXT = new Color(235, 240, 242);
    private final Color MUTED = new Color(145, 156, 166);

    private final Color TEAL = new Color(74, 190, 170);
    private final Color GREEN = new Color(70, 190, 120);
    private final Color AMBER = new Color(230, 170, 70);
    private final Color RED = new Color(225, 75, 82);

    // =========================
    // MAIN UI
    // =========================

    private CardLayout cardLayout;
    private JPanel pages;

    private final List<JButton> navigationButtons = new ArrayList<>();

    // =========================
    // INPUTS
    // =========================

    private JComboBox<String> applianceBox;
    private JComboBox<String> applianceOnBox;
    private JComboBox<String> personPresentBox;

    private JTextField durationField;
    private JTextField temperatureField;
    private JTextField powerField;

    // =========================
    // OVERVIEW
    // =========================

    private JLabel powerValue;
    private JLabel temperatureValue;
    private JLabel durationValue;

    private JLabel overviewRisk;
    private JLabel overviewReason;
    private JLabel overviewIndicator;

    // =========================
    // RISK PAGE
    // =========================

    private JLabel riskPageStatus;
    private JLabel riskPageDetails;

    private JLabel evidenceAppliance;
    private JLabel evidencePerson;
    private JLabel evidenceDuration;
    private JLabel evidenceTemperature;
    private JLabel evidencePower;

    // =========================
    // HISTORY
    // =========================

    private DefaultTableModel historyModel;

    // =========================
    // CONSTRUCTOR
    // =========================

    public SafeSwitchUI() {

        setTitle("SafeSwitch - Intelligent Appliance Safety");

        setSize(1250, 760);
        setMinimumSize(new Dimension(1050, 680));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildApplication();
    }

    // =========================
    // BUILD APPLICATION
    // =========================

    private void buildApplication() {

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        root.add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();

        pages = new JPanel(cardLayout);
        pages.setBackground(BG);

        pages.add(createOverviewPage(), "OVERVIEW");
        pages.add(createMonitorPage(), "MONITOR");
        pages.add(createRiskPage(), "RISK");
        pages.add(createSystemPage(), "SYSTEM");

        root.add(pages, BorderLayout.CENTER);

        setContentPane(root);

        showPage("OVERVIEW");
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setBackground(SIDEBAR);
        sidebar.setPreferredSize(new Dimension(225, 0));

        // ---------- TOP ----------

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(SIDEBAR);
        top.setBorder(new EmptyBorder(30, 18, 20, 18));

        JLabel logo = new JLabel("SafeSwitch");
        logo.setForeground(TEXT);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 25));

        JLabel subtitle = new JLabel("INTELLIGENT SAFETY");
        subtitle.setForeground(TEAL);
        subtitle.setFont(new Font("Segoe UI", Font.BOLD, 10));

        top.add(logo);
        top.add(Box.createVerticalStrut(4));
        top.add(subtitle);
        top.add(Box.createVerticalStrut(35));

        JButton overview = createNavButton("Overview", "OVERVIEW");
        JButton monitor = createNavButton("Appliance Monitor", "MONITOR");
        JButton risk = createNavButton("Risk Detection", "RISK");
        JButton system = createNavButton("System Status", "SYSTEM");

        top.add(overview);
        top.add(Box.createVerticalStrut(8));

        top.add(monitor);
        top.add(Box.createVerticalStrut(8));

        top.add(risk);
        top.add(Box.createVerticalStrut(8));

        top.add(system);

        sidebar.add(top, BorderLayout.NORTH);

        // ---------- BOTTOM ----------

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(SIDEBAR);
        bottom.setBorder(new EmptyBorder(15, 18, 25, 18));

        JLabel online = new JLabel("●  SIMULATION MODE");
        online.setForeground(GREEN);
        online.setFont(new Font("Segoe UI", Font.BOLD, 11));

        JLabel version = new JLabel("SafeSwitch Prototype v1.0");
        version.setForeground(MUTED);
        version.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        bottom.add(online);
        bottom.add(Box.createVerticalStrut(7));
        bottom.add(version);

        sidebar.add(bottom, BorderLayout.SOUTH);

        return sidebar;
    }

    // ============================================================
    // NAVIGATION BUTTON
    // ============================================================

    private JButton createNavButton(String text, String page) {

        JButton button = new JButton(text);

        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setFont(new Font("Segoe UI", Font.BOLD, 12));

        button.setForeground(MUTED);
        button.setBackground(SIDEBAR);

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        button.setBorder(
                new EmptyBorder(12, 15, 12, 10)
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // IMPORTANT:
        // Prevent Windows Look & Feel from changing button appearance.
        button.setUI(new BasicButtonUI());

        button.addActionListener(e -> showPage(page));

        navigationButtons.add(button);

        return button;
    }

    // ============================================================
    // SHOW PAGE
    // ============================================================

    private void showPage(String page) {

        cardLayout.show(pages, page);

        for (JButton button : navigationButtons) {

            boolean active = false;

            if (page.equals("OVERVIEW")
                    && button.getText().equals("Overview")) {

                active = true;
            }

            if (page.equals("MONITOR")
                    && button.getText().equals("Appliance Monitor")) {

                active = true;
            }

            if (page.equals("RISK")
                    && button.getText().equals("Risk Detection")) {

                active = true;
            }

            if (page.equals("SYSTEM")
                    && button.getText().equals("System Status")) {

                active = true;
            }

            if (active) {

                button.setBackground(
                        new Color(25, 48, 47)
                );

                button.setForeground(TEAL);

            } else {

                button.setBackground(SIDEBAR);
                button.setForeground(MUTED);
            }
        }
    }

    // ============================================================
    // HEADER
    // ============================================================

    private JPanel createHeader(
            String title,
            String description) {

        JPanel header = new JPanel(new BorderLayout());

        header.setOpaque(false);

        JPanel left = new JPanel();

        left.setLayout(
                new BoxLayout(left, BoxLayout.Y_AXIS)
        );

        left.setOpaque(false);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setForeground(TEXT);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setForeground(MUTED);

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        left.add(titleLabel);

        left.add(
                Box.createVerticalStrut(5)
        );

        left.add(descriptionLabel);

        JLabel online =
                new JLabel("● SYSTEM ONLINE");

        online.setForeground(GREEN);

        online.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        header.add(left, BorderLayout.WEST);

        header.add(online, BorderLayout.EAST);

        return header;
    }

    // ============================================================
    // OVERVIEW PAGE
    // ============================================================

    private JPanel createOverviewPage() {

        JPanel page = createPageBase();

        page.add(
                createHeader(
                        "Safety Overview",
                        "Real-time appliance monitoring and risk analysis"
                ),
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(
                        new BorderLayout(0, 18)
                );

        center.setOpaque(false);

        // ---------- METRICS ----------

        JPanel metrics =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        metrics.setOpaque(false);

        powerValue = new JLabel("—");

        temperatureValue = new JLabel("—");

        durationValue = new JLabel("—");

        metrics.add(
                createMetricCard(
                        "POWER",
                        powerValue,
                        "Current consumption"
                )
        );

        metrics.add(
                createMetricCard(
                        "TEMPERATURE",
                        temperatureValue,
                        "Current reading"
                )
        );

        metrics.add(
                createMetricCard(
                        "RUN TIME",
                        durationValue,
                        "Appliance activity"
                )
        );

        center.add(
                metrics,
                BorderLayout.NORTH
        );

        // ---------- SAFETY STATUS ----------

        JPanel safetyCard = createCard();

        safetyCard.setLayout(
                new BorderLayout()
        );

        JPanel safetyContent = new JPanel();

        safetyContent.setLayout(
                new BoxLayout(
                        safetyContent,
                        BoxLayout.Y_AXIS
                )
        );

        safetyContent.setOpaque(false);

        JLabel small =
                new JLabel("SAFETY STATUS");

        small.setForeground(MUTED);

        small.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        overviewRisk =
                new JLabel("NO ANALYSIS");

        overviewRisk.setForeground(TEXT);

        overviewRisk.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        31
                )
        );

        overviewReason =
                new JLabel(
                        "<html>Run a safety check from Appliance Monitor.</html>"
                );

        overviewReason.setForeground(MUTED);

        overviewReason.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        safetyContent.add(small);

        safetyContent.add(
                Box.createVerticalStrut(8)
        );

        safetyContent.add(overviewRisk);

        safetyContent.add(
                Box.createVerticalStrut(8)
        );

        safetyContent.add(overviewReason);

        overviewIndicator =
                new JLabel("●");

        overviewIndicator.setForeground(MUTED);

        overviewIndicator.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        35
                )
        );

        safetyCard.add(
                safetyContent,
                BorderLayout.CENTER
        );

        safetyCard.add(
                overviewIndicator,
                BorderLayout.WEST
        );

        center.add(
                safetyCard,
                BorderLayout.CENTER
        );

        center.add(
                createHistoryCard(),
                BorderLayout.SOUTH
        );

        page.add(
                center,
                BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // METRIC CARD
    // ============================================================

    private JPanel createMetricCard(
            String title,
            JLabel value,
            String description) {

        JPanel card = createCard();

        card.setLayout(
                new BorderLayout()
        );

        JPanel content = new JPanel();

        content.setOpaque(false);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(MUTED);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        value.setForeground(TEXT);

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        JLabel desc =
                new JLabel(description);

        desc.setForeground(MUTED);

        desc.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        content.add(titleLabel);

        content.add(
                Box.createVerticalStrut(7)
        );

        content.add(value);

        content.add(
                Box.createVerticalStrut(4)
        );

        content.add(desc);

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // ============================================================
    // APPLIANCE MONITOR PAGE
    // ============================================================

    private JPanel createMonitorPage() {

        JPanel page = createPageBase();

        page.add(
                createHeader(
                        "Appliance Monitor",
                        "Enter simulated sensor readings and analyse appliance safety"
                ),
                BorderLayout.NORTH
        );

        JPanel main =
                new JPanel(
                        new BorderLayout(18, 0)
                );

        main.setOpaque(false);

        // ---------- INPUT CARD ----------

        JPanel inputCard = createCard();

        inputCard.setLayout(
                new BorderLayout()
        );

        JLabel title =
                new JLabel("SENSOR INPUT");

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Simulated sensor values"
                );

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        JPanel header = new JPanel();

        header.setOpaque(false);

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.add(title);

        header.add(
                Box.createVerticalStrut(4)
        );

        header.add(subtitle);

        inputCard.add(
                header,
                BorderLayout.NORTH
        );

        // ---------- FORM ----------

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setOpaque(false);

        form.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        10,
                        0
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 6, 8, 6);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        applianceBox =
                new JComboBox<>(
                        new String[]{
                                "Iron",
                                "Heater",
                                "Electric Stove",
                                "Fan",
                                "Other Appliance"
                        }
                );

        applianceOnBox =
                new JComboBox<>(
                        new String[]{
                                "Yes",
                                "No"
                        }
                );

        personPresentBox =
                new JComboBox<>(
                        new String[]{
                                "Yes",
                                "No"
                        }
                );

        durationField =
                createInput("5");

        temperatureField =
                createInput("40");

        powerField =
                createInput("400");

        addFormField(
                form,
                gbc,
                0,
                0,
                "Appliance",
                applianceBox
        );

        addFormField(
                form,
                gbc,
                1,
                0,
                "Appliance ON",
                applianceOnBox
        );

        addFormField(
                form,
                gbc,
                0,
                1,
                "Person Present",
                personPresentBox
        );

        addFormField(
                form,
                gbc,
                1,
                1,
                "Running Time (min)",
                durationField
        );

        addFormField(
                form,
                gbc,
                0,
                2,
                "Temperature (°C)",
                temperatureField
        );

        addFormField(
                form,
                gbc,
                1,
                2,
                "Power Consumption (W)",
                powerField
        );

        inputCard.add(
                form,
                BorderLayout.CENTER
        );

        // ---------- BUTTONS ----------

        JPanel actions =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        actions.setOpaque(false);

        JButton check =
                createPrimaryButton(
                        "CHECK SAFETY"
                );

        check.addActionListener(
                e -> checkSafety()
        );

        JButton reset =
                createSecondaryButton(
                        "RESET"
                );

        reset.addActionListener(
                e -> resetForm()
        );

        actions.add(check);
        actions.add(reset);

        inputCard.add(
                actions,
                BorderLayout.SOUTH
        );

        main.add(
                inputCard,
                BorderLayout.CENTER
        );

        main.add(
                createDemoCard(),
                BorderLayout.EAST
        );

        page.add(
                main,
                BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // FORM FIELD
    // ============================================================

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int column,
            int row,
            String labelText,
            JComponent component) {

        gbc.gridx = column;
        gbc.gridy = row * 2;

        JLabel label =
                new JLabel(labelText);

        label.setForeground(MUTED);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        panel.add(label, gbc);

        gbc.gridy =
                row * 2 + 1;

        panel.add(component, gbc);
    }

    // ============================================================
    // INPUT FIELD
    // ============================================================

    private JTextField createInput(
            String value) {

        JTextField field =
                new JTextField(value);

        field.setForeground(TEXT);

        field.setBackground(INPUT);

        field.setCaretColor(TEAL);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setOpaque(true);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER),
                        new EmptyBorder(
                                9,
                                10,
                                9,
                                10
                        )
                )
        );

        return field;
    }

    // ============================================================
    // DEMO CARD
    // ============================================================

    private JPanel createDemoCard() {

        JPanel card = createCard();

        card.setPreferredSize(
                new Dimension(275, 0)
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("DEMO SCENARIOS");

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JLabel sub =
                new JLabel(
                        "<html>One-click scenarios<br>" +
                        "for your project demonstration.</html>"
                );

        sub.setForeground(MUTED);

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        card.add(title);

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(sub);

        card.add(
                Box.createVerticalStrut(20)
        );

        // ---------- NORMAL ----------

        JButton normal =
                createScenarioButton(
                        "Normal Operation"
                );

        normal.addActionListener(
                e -> applyScenario(
                        "Iron",
                        "Yes",
                        "Yes",
                        5,
                        40,
                        400
                )
        );

        // ---------- MONITOR ----------

        JButton monitor =
                createScenarioButton(
                        "Person Left Room"
                );

        monitor.addActionListener(
                e -> applyScenario(
                        "Iron",
                        "Yes",
                        "No",
                        10,
                        50,
                        400
                )
        );

        // ---------- WARNING ----------

        JButton warning =
                createScenarioButton(
                        "Forgotten Appliance"
                );

        warning.addActionListener(
                e -> applyScenario(
                        "Iron",
                        "Yes",
                        "No",
                        25,
                        58,
                        420
                )
        );

        // ---------- HIGH RISK ----------

        JButton highRisk =
                createScenarioButton(
                        "Abnormal Temperature"
                );

        highRisk.addActionListener(
                e -> applyScenario(
                        "Iron",
                        "Yes",
                        "No",
                        30,
                        75,
                        420
                )
        );

        card.add(normal);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(monitor);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(warning);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(highRisk);

        card.add(
                Box.createVerticalGlue()
        );

        JLabel tip =
                new JLabel(
                        "<html><b>Demo tip:</b><br>" +
                        "Click a scenario to automatically<br>" +
                        "fill the sensor values and analyse them.</html>"
                );

        tip.setForeground(MUTED);

        tip.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        10
                )
        );

        card.add(tip);

        return card;
    }

    // ============================================================
    // SCENARIO BUTTON
    // ============================================================

    private JButton createScenarioButton(
            String text) {

        JButton button =
                new JButton(text);

        // THIS FIXES THE WHITE BUTTON PROBLEM
        button.setUI(new BasicButtonUI());

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(true);

        button.setForeground(TEXT);

        button.setBackground(INPUT);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER),
                        new EmptyBorder(
                                10,
                                13,
                                10,
                                13
                        )
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return button;
    }

    // ============================================================
    // CHECK SAFETY
    // ============================================================

    private void checkSafety() {

        try {

            String appliance =
                    applianceBox
                            .getSelectedItem()
                            .toString();

            String status =
                    applianceOnBox
                            .getSelectedItem()
                            .toString();

            String person =
                    personPresentBox
                            .getSelectedItem()
                            .toString();

            int duration =
                    Integer.parseInt(
                            durationField
                                    .getText()
                                    .trim()
                    );

            double temperature =
                    Double.parseDouble(
                            temperatureField
                                    .getText()
                                    .trim()
                    );

            double power =
                    Double.parseDouble(
                            powerField
                                    .getText()
                                    .trim()
                    );

            if (duration < 0
                    || temperature < 0
                    || power < 0) {

                showError(
                        "Values cannot be negative."
                );

                return;
            }

            String risk =
                    RiskCalculator.calculateRisk(
                            status,
                            duration,
                            temperature,
                            person,
                            power
                    );

            // UPDATE EVERYTHING
            updateDashboard(
                    risk,
                    appliance,
                    status,
                    person,
                    duration,
                    temperature,
                    power
            );

            addHistory(
                    appliance,
                    risk,
                    temperature,
                    power
            );

            // IMPORTANT:
            // Automatically open Risk Detection page.
            showPage("RISK");

        }

        catch (NumberFormatException ex) {

            showError(
                    "Please enter valid numbers for " +
                    "time, temperature and power."
            );
        }
    }

    // ============================================================
    // APPLY DEMO SCENARIO
    // ============================================================

    private void applyScenario(
            String appliance,
            String status,
            String person,
            int duration,
            double temperature,
            double power) {

        applianceBox.setSelectedItem(
                appliance
        );

        applianceOnBox.setSelectedItem(
                status
        );

        personPresentBox.setSelectedItem(
                person
        );

        durationField.setText(
                String.valueOf(duration)
        );

        temperatureField.setText(
                String.valueOf(temperature)
        );

        powerField.setText(
                String.valueOf(power)
        );

        checkSafety();
    }

    // ============================================================
    // UPDATE DASHBOARD
    // ============================================================

    private void updateDashboard(
            String risk,
            String appliance,
            String status,
            String person,
            int duration,
            double temperature,
            double power) {

        powerValue.setText(
                String.format(
                        "%.0f W",
                        power
                )
        );

        temperatureValue.setText(
                String.format(
                        "%.0f °C",
                        temperature
                )
        );

        durationValue.setText(
                duration + " min"
        );

        overviewRisk.setText(risk);

        riskPageStatus.setText(risk);

        String reason =
                getRiskReason(
                        risk,
                        appliance,
                        status,
                        person,
                        duration,
                        temperature,
                        power
                );

        overviewReason.setText(
                "<html>" + reason + "</html>"
        );

        riskPageDetails.setText(
                "<html>" + reason + "</html>"
        );

        Color statusColor =
                getRiskColor(risk);

        overviewRisk.setForeground(
                statusColor
        );

        riskPageStatus.setForeground(
                statusColor
        );

        overviewIndicator.setForeground(
                statusColor
        );

        updateEvidence(
                appliance,
                status,
                person,
                duration,
                temperature,
                power,
                risk
        );
    }

    // ============================================================
    // RISK REASON
    // ============================================================

    private String getRiskReason(
            String risk,
            String appliance,
            String status,
            String person,
            int duration,
            double temperature,
            double power) {

        if (risk.equals("SAFE")) {

            return "Appliance is OFF. " +
                    "No active appliance risk detected.";
        }

        if (risk.equals("HIGH RISK")) {

            if (temperature >= 70
                    && power >= 1000) {

                return "Abnormal temperature and " +
                        "high power consumption detected.";
            }

            if (temperature >= 70) {

                return "Abnormal temperature detected. " +
                        "Immediate attention is required.";
            }

            return "Abnormally high power consumption " +
                    "detected. Immediate attention is required.";
        }

        if (risk.equals("WARNING")) {

            return "The " + appliance +
                    " is ON, no person is present, " +
                    "and it has been running for " +
                    duration +
                    " minutes. It may have been forgotten.";
        }

        if (risk.equals("MONITOR")) {

            return "The " + appliance +
                    " is ON but no person is currently " +
                    "present. Continue monitoring.";
        }

        return "The " + appliance +
                " is ON and a person is present. " +
                "Operation appears normal.";
    }

    // ============================================================
    // RISK COLOR
    // ============================================================

    private Color getRiskColor(
            String risk) {

        switch (risk) {

            case "SAFE":
            case "NORMAL":
                return GREEN;

            case "MONITOR":
                return TEAL;

            case "WARNING":
                return AMBER;

            case "HIGH RISK":
                return RED;

            default:
                return MUTED;
        }
    }

    // ============================================================
    // EVIDENCE
    // ============================================================

    private void updateEvidence(
            String appliance,
            String status,
            String person,
            int duration,
            double temperature,
            double power,
            String risk) {

        evidenceAppliance.setText(
                "✓  Appliance: " +
                        appliance +
                        " — " +
                        status
        );

        evidencePerson.setText(
                "✓  Person present: " +
                        person
        );

        evidenceDuration.setText(
                "✓  Running time: " +
                        duration +
                        " min"
        );

        evidenceTemperature.setText(
                "✓  Temperature: " +
                        String.format(
                                "%.0f °C",
                                temperature
                        )
        );

        evidencePower.setText(
                "✓  Power: " +
                        String.format(
                                "%.0f W",
                                power
                        )
        );

        evidenceAppliance.setForeground(
                TEXT
        );

        evidenceDuration.setForeground(
                TEXT
        );

        evidencePerson.setForeground(
                person.equalsIgnoreCase("no")
                        ? AMBER
                        : TEXT
        );

        evidenceTemperature.setForeground(
                temperature >= 70
                        ? RED
                        : TEXT
        );

        evidencePower.setForeground(
                power >= 1000
                        ? RED
                        : TEXT
        );
    }

    // ============================================================
    // RISK PAGE
    // ============================================================

    private JPanel createRiskPage() {

        JPanel page = createPageBase();

        page.add(
                createHeader(
                        "Risk Detection",
                        "Detection result and evidence from the safety engine"
                ),
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(0, 16)
                );

        content.setOpaque(false);

        // ---------- CURRENT RESULT ----------

        JPanel resultCard =
                createCard();

        resultCard.setLayout(
                new BorderLayout()
        );

        JPanel resultContent =
                new JPanel();

        resultContent.setOpaque(false);

        resultContent.setLayout(
                new BoxLayout(
                        resultContent,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel current =
                new JLabel(
                        "CURRENT ANALYSIS"
                );

        current.setForeground(MUTED);

        current.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        riskPageStatus =
                new JLabel(
                        "NO ANALYSIS"
                );

        riskPageStatus.setForeground(TEXT);

        riskPageStatus.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        riskPageDetails =
                new JLabel(
                        "<html>Run a safety check to see the detection result.</html>"
                );

        riskPageDetails.setForeground(MUTED);

        riskPageDetails.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        resultContent.add(current);

        resultContent.add(
                Box.createVerticalStrut(8)
        );

        resultContent.add(
                riskPageStatus
        );

        resultContent.add(
                Box.createVerticalStrut(8)
        );

        resultContent.add(
                riskPageDetails
        );

        resultCard.add(
                resultContent,
                BorderLayout.CENTER
        );

        content.add(
                resultCard,
                BorderLayout.NORTH
        );

        // ---------- EVIDENCE ----------

        JPanel evidenceCard =
                createCard();

        evidenceCard.setLayout(
                new BorderLayout()
        );

        JLabel evidenceTitle =
                new JLabel(
                        "DETECTION EVIDENCE"
                );

        evidenceTitle.setForeground(TEXT);

        evidenceTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        evidenceCard.add(
                evidenceTitle,
                BorderLayout.NORTH
        );

        JPanel evidence =
                new JPanel();

        evidence.setOpaque(false);

        evidence.setLayout(
                new BoxLayout(
                        evidence,
                        BoxLayout.Y_AXIS
                )
        );

        evidenceAppliance =
                createEvidenceLabel(
                        "○  Appliance: —"
                );

        evidencePerson =
                createEvidenceLabel(
                        "○  Person present: —"
                );

        evidenceDuration =
                createEvidenceLabel(
                        "○  Running time: —"
                );

        evidenceTemperature =
                createEvidenceLabel(
                        "○  Temperature: —"
                );

        evidencePower =
                createEvidenceLabel(
                        "○  Power: —"
                );

        evidence.add(evidenceAppliance);

        evidence.add(
                Box.createVerticalStrut(12)
        );

        evidence.add(evidencePerson);

        evidence.add(
                Box.createVerticalStrut(12)
        );

        evidence.add(evidenceDuration);

        evidence.add(
                Box.createVerticalStrut(12)
        );

        evidence.add(evidenceTemperature);

        evidence.add(
                Box.createVerticalStrut(12)
        );

        evidence.add(evidencePower);

        evidenceCard.add(
                evidence,
                BorderLayout.CENTER
        );

        content.add(
                evidenceCard,
                BorderLayout.CENTER
        );

        // ---------- RULES ----------

        JPanel rulesCard =
                createCard();

        rulesCard.setLayout(
                new BoxLayout(
                        rulesCard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel rulesTitle =
                new JLabel(
                        "DETECTION RULES"
                );

        rulesTitle.setForeground(TEXT);

        rulesTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        rulesCard.add(rulesTitle);

        rulesCard.add(
                Box.createVerticalStrut(10)
        );

        rulesCard.add(
                createRule(
                        "SAFE",
                        "Appliance is OFF"
                )
        );

        rulesCard.add(
                createRule(
                        "NORMAL",
                        "Appliance ON + person present"
                )
        );

        rulesCard.add(
                createRule(
                        "MONITOR",
                        "Appliance ON + person absent"
                )
        );

        rulesCard.add(
                createRule(
                        "WARNING",
                        "Person absent + 20 min or more"
                )
        );

        rulesCard.add(
                createRule(
                        "HIGH RISK",
                        "Temperature ≥ 70°C OR power ≥ 1000W"
                )
        );

        content.add(
                rulesCard,
                BorderLayout.SOUTH
        );

        page.add(
                content,
                BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // EVIDENCE LABEL
    // ============================================================

    private JLabel createEvidenceLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(TEXT);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        return label;
    }

    // ============================================================
    // RULE
    // ============================================================

    private JPanel createRule(
            String name,
            String description) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        row.setOpaque(false);

        row.setBorder(
                new EmptyBorder(
                        4,
                        0,
                        4,
                        0
                )
        );

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setForeground(
                getRiskColor(name)
        );

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setForeground(MUTED);

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        row.add(
                nameLabel,
                BorderLayout.WEST
        );

        row.add(
                descriptionLabel,
                BorderLayout.CENTER
        );

        return row;
    }

    // ============================================================
    // HISTORY
    // ============================================================

    private JPanel createHistoryCard() {

        JPanel card = createCard();

        card.setPreferredSize(
                new Dimension(0, 175)
        );

        card.setLayout(
                new BorderLayout(0, 8)
        );

        JLabel title =
                new JLabel(
                        "RECENT DETECTIONS"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        historyModel =
                new DefaultTableModel(
                        new Object[]{
                                "TIME",
                                "APPLIANCE",
                                "STATUS",
                                "TEMP",
                                "POWER"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable table =
                new JTable(historyModel);

        table.setBackground(CARD);

        table.setForeground(TEXT);

        table.setGridColor(BORDER);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        table.setRowHeight(25);

        table.getTableHeader()
                .setBackground(INPUT);

        table.getTableHeader()
                .setForeground(MUTED);

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                10
                        )
                );

        JScrollPane scroll =
                new JScrollPane(table);

        scroll.setBorder(
                new LineBorder(BORDER)
        );

        card.add(
                scroll,
                BorderLayout.CENTER
        );

        return card;
    }

    // ============================================================
    // ADD HISTORY
    // ============================================================

    private void addHistory(
            String appliance,
            String risk,
            double temperature,
            double power) {

        String time =
                new java.text.SimpleDateFormat(
                        "HH:mm:ss"
                ).format(
                        new java.util.Date()
                );

        historyModel.insertRow(
                0,
                new Object[]{
                        time,
                        appliance,
                        risk,
                        String.format(
                                "%.0f °C",
                                temperature
                        ),
                        String.format(
                                "%.0f W",
                                power
                        )
                }
        );

        while (
                historyModel.getRowCount() > 5
        ) {

            historyModel.removeRow(
                    historyModel.getRowCount() - 1
            );
        }
    }

    // ============================================================
    // SYSTEM PAGE
    // ============================================================

    private JPanel createSystemPage() {

        JPanel page = createPageBase();

        page.add(
                createHeader(
                        "System Status",
                        "SafeSwitch prototype configuration"
                ),
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                0,
                                12
                        )
                );

        content.setOpaque(false);

        content.add(
                createSystemItem(
                        "JAVA APPLICATION",
                        "ONLINE",
                        GREEN
                )
        );

        content.add(
                createSystemItem(
                        "RISK ENGINE",
                        "ONLINE",
                        GREEN
                )
        );

        content.add(
                createSystemItem(
                        "SENSOR INPUT",
                        "SIMULATED",
                        TEAL
                )
        );

        content.add(
                createSystemItem(
                        "ALERT ENGINE",
                        "READY",
                        GREEN
                )
        );

        page.add(
                content,
                BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // SYSTEM ITEM
    // ============================================================

    private JPanel createSystemItem(
            String name,
            String status,
            Color color) {

        JPanel card = createCard();

        card.setLayout(
                new BorderLayout()
        );

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setForeground(TEXT);

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        JLabel statusLabel =
                new JLabel(
                        "● " + status
                );

        statusLabel.setForeground(color);

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        card.add(
                nameLabel,
                BorderLayout.WEST
        );

        card.add(
                statusLabel,
                BorderLayout.EAST
        );

        return card;
    }

    // ============================================================
    // RESET
    // ============================================================

    private void resetForm() {

        applianceBox.setSelectedItem(
                "Iron"
        );

        applianceOnBox.setSelectedItem(
                "Yes"
        );

        personPresentBox.setSelectedItem(
                "Yes"
        );

        durationField.setText("5");

        temperatureField.setText("40");

        powerField.setText("400");

        powerValue.setText("—");

        temperatureValue.setText("—");

        durationValue.setText("—");

        overviewRisk.setText(
                "NO ANALYSIS"
        );

        overviewRisk.setForeground(
                TEXT
        );

        overviewReason.setText(
                "<html>Run a safety check from Appliance Monitor.</html>"
        );

        overviewIndicator.setForeground(
                MUTED
        );

        riskPageStatus.setText(
                "NO ANALYSIS"
        );

        riskPageStatus.setForeground(
                TEXT
        );

        riskPageDetails.setText(
                "<html>Run a safety check to see the detection result.</html>"
        );

        evidenceAppliance.setText(
                "○  Appliance: —"
        );

        evidencePerson.setText(
                "○  Person present: —"
        );

        evidenceDuration.setText(
                "○  Running time: —"
        );

        evidenceTemperature.setText(
                "○  Temperature: —"
        );

        evidencePower.setText(
                "○  Power: —"
        );

        showPage("MONITOR");
    }

    // ============================================================
    // PAGE BASE
    // ============================================================

    private JPanel createPageBase() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        panel.setBackground(BG);

        panel.setBorder(
                new EmptyBorder(
                        25,
                        28,
                        25,
                        28
                )
        );

        return panel;
    }

    // ============================================================
    // CARD
    // ============================================================

    private JPanel createCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        return panel;
    }

    // ============================================================
    // PRIMARY BUTTON
    // ============================================================

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setUI(
                new BasicButtonUI()
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setForeground(
                new Color(
                        8,
                        25,
                        22
                )
        );

        button.setBackground(TEAL);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        button.setBorder(
                new EmptyBorder(
                        11,
                        20,
                        11,
                        20
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ============================================================
    // SECONDARY BUTTON
    // ============================================================

    private JButton createSecondaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setUI(
                new BasicButtonUI()
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setForeground(TEXT);

        button.setBackground(INPUT);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER),
                        new EmptyBorder(
                                10,
                                19,
                                10,
                                19
                        )
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ============================================================
    // ERROR
    // ============================================================

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    /*
                     * Do NOT use Windows Look & Feel here.
                     * BasicButtonUI is used for our custom buttons.
                     */

                    SafeSwitchUI ui =
                            new SafeSwitchUI();

                    ui.setVisible(true);
                }
        );
    }
}