package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.CurrencyList;
import model.Money;
import model.MoneyExchanger;

public class SwingMoneyExchangeWindow   extends JFrame 
                                        implements MoneyExchangeWindow{

    private CurrencyList currencyList;
    private JPanel mainPane;
    private JTextField from;
    private JLabel to;
    private JComboBox comboFrom, comboTo;
    
    
    public SwingMoneyExchangeWindow(CurrencyList currencyList) {
        this.currencyList = currencyList;
        init();
        mainPane.setLayout(new BorderLayout());        
        mainPane.add(BorderLayout.NORTH,new JPanel());
        mainPane.add(BorderLayout.CENTER,panel());
        this.add(mainPane);
    }

    public void execute(){
        this.setVisible(true);
    }
    
    private void init() {
        mainPane = new JPanel();
        
        from = new JTextField(12);
        to = new JLabel("");
        
        String codeSortedList[] = currencyList.getCodeSortedList(); 
        comboFrom = new JComboBox(codeSortedList);
        comboTo = new JComboBox(codeSortedList);
        frameInitialization();
    }
    
    private void frameInitialization() {
        this.setTitle("MoneyCalculator");
        this.setSize(260,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    private JPanel panel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.EAST,ComboPanel());
        
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());
        westPanel.add(BorderLayout.NORTH,TextPanel(from));
        westPanel.add(BorderLayout.SOUTH,TextPanel(to));        
        panel.add(BorderLayout.WEST,westPanel);
        
        panel.add(BorderLayout.SOUTH,convertButtonPanel());
        return panel;
    }
    
    private JPanel TextPanel(JComponent text) {
        JPanel panel = new JPanel();
        panel.add(text);
        return panel;
    }
    private JPanel convertButtonPanel(){
        JPanel panel = new JPanel();
        JButton convert = new JButton("Convert");
        panel.add(convert);
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ConvertButtonPush();
                }
                catch( NumberFormatException ex){}
            }
            
        });
        return panel;
    }
    
    private void ConvertButtonPush() {
        double fromAmount = Double.parseDouble(from.getText());
        String fromCode = (String) comboFrom.getSelectedItem();
        Money fromMoney = new Money(fromAmount,currencyList.get(fromCode));
        String toCode = (String) comboTo.getSelectedItem();        
        Money toMoney = MoneyExchanger.exchange(fromMoney, currencyList.get(toCode));
        to.setText(toMoney.getAmount()+"");
    }

    private Component ComboPanel() {
        JPanel panel = new JPanel();        
        panel.setLayout(new BorderLayout());              
        panel.add(BorderLayout.NORTH,comboFrom);
        panel.add(BorderLayout.SOUTH,comboTo);

        return panel;
    }

    
}
