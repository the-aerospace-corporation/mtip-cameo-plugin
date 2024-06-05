package org.aero.mtip.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Config;

public class ConfigPage extends JFrame {
  private static final long serialVersionUID = -2777677186889149107L;
  
  private static ConfigPage instance;
  
  private static final String FRAME_NAME = "MTIP Configuration Options";
  private static final String EXPORT_ID_MODE = "Export ID Mode";
  
  private static final int frameWidth = 400;
  private static final int titleHeight = 55;
  private static final int bodyHeight = 45;
  
  private JPanel contentPane;
  private JComboBox<String> idModeOptionBox;
      
  private static ConfigPage getInstance() {
    if (instance == null) {
      instance = new ConfigPage();
    }
    
    return instance;
  }
  
  public static void displayFrame() {
    getInstance().setVisible(true);
  }
  
  private ConfigPage() {
    setTitle(FRAME_NAME);
    setContentPane();
    
    buildTitlePanel();
    buildBodyPanel();
  }
  
  private void setContentPane() {
    setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    setBounds(100, 100, frameWidth, titleHeight + bodyHeight);
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(2,1));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    
    setContentPane(contentPane);
  }
  
  private void buildTitlePanel() {
    JPanel titlePanel = new JPanel();       
    titlePanel.setPreferredSize(new Dimension(frameWidth, titleHeight));
    
    JLabel title = new JLabel(FRAME_NAME);
    title.setFont(new Font("Serif", Font.PLAIN, 18));
    titlePanel.add(title);
    
    contentPane.add(titlePanel);
  }
  
  private void buildBodyPanel() {
    JPanel bodyPanel = new JPanel();
    bodyPanel.setLayout(new GridLayout(1, 1));
    bodyPanel.setPreferredSize(new Dimension(frameWidth, bodyHeight));
    
    bodyPanel.add(buildIdModePanel());
    contentPane.add(bodyPanel);
  }
  
  private JPanel buildIdModePanel() {
    JPanel idModePanel = new JPanel();
    idModePanel.setLayout(new GridLayout(1, 2));
    
    JLabel idModeLabel = new JLabel(EXPORT_ID_MODE);
    idModeOptionBox =  new JComboBox<String>(Config.getIdModeOptions());
    
    idModeOptionBox.addActionListener (new ActionListener () {
      public void actionPerformed(ActionEvent e) {
          updateIdModeOption();
      }
    });
    
    idModeOptionBox.setSelectedItem(Config.getInstance().getIdMode());
    
    idModePanel.add(idModeLabel);
    idModePanel.add(idModeOptionBox);
    
    return idModePanel;
  }
  
  private void updateIdModeOption() {
    String idModeSelected = String.valueOf(idModeOptionBox.getSelectedItem());
    
    if (idModeSelected == null) {
      return;
    }
    
    Config.getInstance().setIdMode(idModeSelected);
    Config.save();
  }
}
