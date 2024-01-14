package org.supinfo.gui;

import org.supinfo.process.Install;
import org.supinfo.process.Launch;
import org.supinfo.process.EventTaskListener;
import org.supinfo.process.TaskStatus;

import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Tanguy
 */
public class Frame extends javax.swing.JFrame {

    boolean isInstalled;

    URL logoURL = getClass().getResource("/logo-250.jpg");
    ImageIcon logoIcon = new ImageIcon(logoURL);

    public Frame(boolean isInstalled) {
        initComponents();

        this.isInstalled = isInstalled;

        setLocationRelativeTo(null);
        loadPanel.setVisible(isInstalled ? true : false);
        homePanel.setVisible(false);
        installPanel.setVisible(isInstalled ? false : true);

        URL faviconURL = getClass().getResource("/favicon.png");
        ImageIcon favicon = new ImageIcon(faviconURL);
        setIconImage(favicon.getImage());
    }

    private void initComponents() {
        jLayeredPane1 = new javax.swing.JLayeredPane();
        loadPanel = new javax.swing.JPanel();
        iconLoadMgl = new javax.swing.JLabel();
        loadText = new javax.swing.JLabel();
        loadProgressBar = new javax.swing.JProgressBar();
        homePanel = new javax.swing.JPanel();
        iconHomeMgl = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();
        labelTitleHome = new javax.swing.JLabel();
        labelSubtitleHome = new javax.swing.JLabel();
        launchProgressBar = new javax.swing.JProgressBar();
        installPanel = new javax.swing.JPanel();
        iconInstallMgl = new javax.swing.JLabel();
        scrollTextInstallArea = new javax.swing.JScrollPane();
        textInstallArea = new javax.swing.JTextArea();
        installProgressBar = new javax.swing.JProgressBar();
        installButton = new javax.swing.JButton();
        cancelInstallButton = new javax.swing.JButton();
        labelTitleInstall = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Launcher BYOD - Lyon Métropole");
        setResizable(false);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        loadPanel.setBackground(new java.awt.Color(255, 255, 255));
        loadPanel.setForeground(new java.awt.Color(255, 255, 255));
        loadPanel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                loadPanelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        iconLoadMgl.setIcon(logoIcon);
        iconLoadMgl.setFocusable(false);
        iconLoadMgl.setMaximumSize(new java.awt.Dimension(800, 280));
        iconLoadMgl.setPreferredSize(new java.awt.Dimension(800, 280));
        iconLoadMgl.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        loadText.setBackground(new java.awt.Color(0, 0, 0));
        loadText.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        loadText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadText.setText("<html><center>Chargement de votre environnement de travail profesionnel</center></html>");

        loadProgressBar.setForeground(new java.awt.Color(229, 0, 43));
        loadProgressBar.setFocusable(false);
        loadProgressBar.setIndeterminate(true);

        javax.swing.GroupLayout loadPanelLayout = new javax.swing.GroupLayout(loadPanel);
        loadPanel.setLayout(loadPanelLayout);
        loadPanelLayout.setHorizontalGroup(
                loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loadPanelLayout.createSequentialGroup()
                                .addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loadPanelLayout.createSequentialGroup()
                                                .addGap(176, 176, 176)
                                                .addComponent(loadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(loadPanelLayout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(loadText, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(loadPanelLayout.createSequentialGroup()
                                                .addGap(219, 219, 219)
                                                .addComponent(iconLoadMgl, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(96, Short.MAX_VALUE))
        );
        loadPanelLayout.setVerticalGroup(
                loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loadPanelLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(iconLoadMgl, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(loadText, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(125, Short.MAX_VALUE))
        );

        jLayeredPane1.add(loadPanel, "card2");

        homePanel.setBackground(new java.awt.Color(255, 255, 255));

        iconHomeMgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconHomeMgl.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(150, 50,  java.awt.Image.SCALE_SMOOTH)));

        launchButton.setBackground(new java.awt.Color(229, 0, 43));
        launchButton.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        launchButton.setForeground(new java.awt.Color(255, 255, 255));
        launchButton.setText("Lancer mon environnement");
        launchButton.setBorderPainted(false);
        launchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        launchButton.setDefaultCapable(false);
        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchButtonActionPerformed(evt);
            }
        });

        labelTitleHome.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        labelTitleHome.setForeground(new java.awt.Color(0, 0, 0));
        labelTitleHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleHome.setText("Bienvenue sur votre espace profesionnel");

        labelSubtitleHome.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        labelSubtitleHome.setForeground(new java.awt.Color(0, 0, 0));
        labelSubtitleHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSubtitleHome.setText("Votre environnement est actuellement éteint");

        launchProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        launchProgressBar.setForeground(new java.awt.Color(230, 230, 230));
        launchProgressBar.setFocusable(false);

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
                homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(homePanelLayout.createSequentialGroup()
                                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(homePanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(labelSubtitleHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelTitleHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)))
                                        .addGroup(homePanelLayout.createSequentialGroup()
                                                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(launchProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(iconHomeMgl))
                                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                        .addGap(123, 123, 123)
                                                                        .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        homePanelLayout.setVerticalGroup(
                homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(homePanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(iconHomeMgl)
                                .addGap(98, 98, 98)
                                .addComponent(labelTitleHome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSubtitleHome)
                                .addGap(39, 39, 39)
                                .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(launchProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(137, Short.MAX_VALUE))
        );

        jLayeredPane1.add(homePanel, "card3");

        installPanel.setBackground(new java.awt.Color(255, 255, 255));

        iconInstallMgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconInstallMgl.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(150, 50,  java.awt.Image.SCALE_SMOOTH)));

        textInstallArea.setEditable(false);
        textInstallArea.setColumns(20);
        textInstallArea.setRows(5);
        textInstallArea.setText("Pour commencer l'installation, cliquer sur le bouton \"Installer\" en bas de cette fenêtre.");
        textInstallArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textInstallArea.setFocusable(false);
        scrollTextInstallArea.setViewportView(textInstallArea);

        installProgressBar.setBackground(new java.awt.Color(238, 238, 238));
        installProgressBar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        installProgressBar.setForeground(new java.awt.Color(229, 0, 43));
        installProgressBar.setStringPainted(true);

        installButton.setText("Installer");
        installButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        installButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                installButtonActionPerformed(evt);
            }
        });

        cancelInstallButton.setText("Annuler");
        cancelInstallButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelInstallButton.setFocusable(false);
        cancelInstallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelInstallButtonActionPerformed(evt);
            }
        });

        labelTitleInstall.setFont(new java.awt.Font("Segoe UI Variable", 1, 22)); // NOI18N
        labelTitleInstall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTitleInstall.setText("<html><div style='text-align: right;'>Bienvenue dans l'assistant d'installation de<br>votre environnement professionnel</div></html>");

        javax.swing.GroupLayout installPanelLayout = new javax.swing.GroupLayout(installPanel);
        installPanel.setLayout(installPanelLayout);
        installPanelLayout.setHorizontalGroup(
                installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(installPanelLayout.createSequentialGroup()
                                .addGroup(installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, installPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelInstallButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(installButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(installPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(installProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, installPanelLayout.createSequentialGroup()
                                                        .addGap(17, 17, 17)
                                                        .addGroup(installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(installPanelLayout.createSequentialGroup()
                                                                        .addComponent(iconInstallMgl)
                                                                        .addGap(95, 95, 95)
                                                                        .addComponent(labelTitleInstall, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(scrollTextInstallArea, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(16, 16, 16))
        );
        installPanelLayout.setVerticalGroup(
                installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(installPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(iconInstallMgl)
                                        .addComponent(labelTitleInstall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(installProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollTextInstallArea, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(installPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(installButton)
                                        .addComponent(cancelInstallButton))
                                .addGap(15, 15, 15))
        );

        jLayeredPane1.add(installPanel, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1)
        );

        pack();
    }

    private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Launch launch = new Launch();
        launch.setTaskCompleteListener(new EventTaskListener() {
            @Override
            public void taskStatus(TaskStatus status) {
                launchButton.setEnabled(status == TaskStatus.DONE ? true : false);

                switch (status) {
                    case LOADING:
                        launchButton.setText("Lancement ...");
                        labelSubtitleHome.setText("Un instant, nous préparons votre environnement ...");
                        launchProgressBar.setIndeterminate(true);
                        break;
                    case RUNNING:
                        launchButton.setText("En fonctionnement");
                        labelSubtitleHome.setText("Votre environnement est actuellement en fonctionnement");
                        launchProgressBar.setIndeterminate(true);
                        break;
                    case DONE:
                        launchButton.setText("Lancer mon environnement");
                        labelSubtitleHome.setText("Votre environnement est actuellement éteint");
                        launchProgressBar.setIndeterminate(false);
                        break;
                }
            }
        });
        launch.execute();
    }

    private void loadPanelAncestorAdded(javax.swing.event.AncestorEvent evt) {
        Timer timer = new Timer(5000, e -> {
            loadPanel.setVisible(false);
            homePanel.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void installButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Install install = new Install();
        install.setTaskCompleteListener(new EventTaskListener() {
            @Override
            public void taskStatus(TaskStatus status, String message) {
                switch (status) {
                    case LOADING:
                        installButton.setEnabled(false);
                        textInstallArea.setText(message);
                        break;
                    case RUNNING:
                        textInstallArea.insert(message, 0);
                        installProgressBar.setValue(installProgressBar.getValue() + 5);
                        break;
                    case DONE:
                        textInstallArea.insert(message, 0);
                        installProgressBar.setValue(100);
                        JOptionPane.showMessageDialog(null, "Installation terminée !", "Installation", JOptionPane.INFORMATION_MESSAGE);
                        loadPanel.setVisible(true);
                        installPanel.setVisible(false);
                        break;
                    case FAILED:
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'installation : " + message, "Installation", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
        install.execute();
    }

    private void cancelInstallButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private javax.swing.JButton cancelInstallButton;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel iconHomeMgl;
    private javax.swing.JLabel iconInstallMgl;
    private javax.swing.JLabel iconLoadMgl;
    private javax.swing.JButton installButton;
    private javax.swing.JPanel installPanel;
    private javax.swing.JProgressBar installProgressBar;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel labelSubtitleHome;
    private javax.swing.JLabel labelTitleHome;
    private javax.swing.JLabel labelTitleInstall;
    private javax.swing.JButton launchButton;
    private javax.swing.JProgressBar launchProgressBar;
    private javax.swing.JPanel loadPanel;
    private javax.swing.JProgressBar loadProgressBar;
    private javax.swing.JLabel loadText;
    private javax.swing.JScrollPane scrollTextInstallArea;
    private javax.swing.JTextArea textInstallArea;
}