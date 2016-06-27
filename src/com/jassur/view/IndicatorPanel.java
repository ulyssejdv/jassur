package com.jassur.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jassur.controller.IndicatorController;
import com.jassur.model.Indicator;
/**
 *
 * @author christophegougam
 */
public class IndicatorPanel extends JPanel{

    /**
     * Creates new form IndicatorPanel
     */
    public IndicatorPanel(Indicator indicator) {
    	JLabel lblTitle = new JLabel("Indicators");
		System.out.println("Indicators");

		JLabel lblNbPretCon = new JLabel("Nombre de pr\u00EAt(s) contract\u00E9(s): "+Integer.toString(indicator.getNbEnCours()));
		add(lblNbPretCon);
		System.out.println("Nombre de pr\u00EAt(s) contract\u00E9(s): "+indicator.getNbEnCours());

		JLabel lblNbPretSim = new JLabel("Nombre de pr\u00EAt(s) simul\u00E9(s): "+Integer.toString(indicator.getNbSimulation()));
		add(lblNbPretSim);
		System.out.println("Nombre de pr\u00EAt(s) simul\u00E9(s): "+indicator.getNbSimulation());

		JLabel lblDureeMoyPret = new JLabel("Dur\u00E9e moyenne des pr\u00EAts: "+Integer.toString(indicator.getAvgDurationLoan()));
		add(lblDureeMoyPret);
		System.out.println("Dur\u00E9e moyenne des pr\u00EAts: "+indicator.getAvgDurationLoan());

		JLabel lblSumInter = new JLabel("Somme totale des int\u00E9r\u00EAts: "+Integer.toString(indicator.getSumInterest()));
		add(lblSumInter);
		System.out.println("Somme totale des int\u00E9r\u00EAts: "+indicator.getSumInterest());

		JLabel lblAvgAmntLoan = new JLabel("Montant moyen des pr\u00EAts: "+Integer.toString(indicator.getAvgLoansAmount()));
		add(lblAvgAmntLoan);
		System.out.println("Montant moyen des pr\u00EAts: "+indicator.getAvgLoansAmount());

		JLabel lblNbTotalLoan = new JLabel("Nombre total de pr\u00EAts: "+Integer.toString(indicator.getNbLoans()));
		add(lblNbTotalLoan);
		System.out.println("Nombre total de pr\u00EAts: "+indicator.getNbLoans());    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    public IndicatorPanel() {

        lblNbLoansCoS = new javax.swing.JLabel();
        lblAvgLoanDuration = new javax.swing.JLabel();
        lblSumInterests = new javax.swing.JLabel();
        lblAvgLoanAmount = new javax.swing.JLabel();
        jbtnLauch = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblNbTotalLoan = new javax.swing.JLabel();
        lblLoanType = new javax.swing.JLabel();
        jRBImmo = new javax.swing.JRadioButton();
        jRBAuto = new javax.swing.JRadioButton();
        lblPeriod = new javax.swing.JLabel();
        jCBMonth = new javax.swing.JComboBox();
        jCBYear = new javax.swing.JComboBox();

        lblNbLoansCoS.setText("Nombre de prêts contractés ou simulés");

        lblAvgLoanDuration.setText("Durée moyenne des prêts");

        lblSumInterests.setText("Somme totale des intérêts perçus");

        lblAvgLoanAmount.setText("Montant moyen des prêts");

        jbtnLauch.setText("LANCER");
        jbtnLauch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLauchActionPerformed(evt);
            }
        });

        lblTitle.setText("Indicateurs");

        lblNbTotalLoan.setText("Nombre total de prêts");

        lblLoanType.setText("Type de prêt");

        jRBImmo.setText("Immobilier");
        jRBImmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBImmoActionPerformed(evt);
            }
        });

        jRBAuto.setText("Automobile");
        jRBAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAutoActionPerformed(evt);
            }
        });

        lblPeriod.setText("Periode");

        jCBMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));
        jCBMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBMonthActionPerformed(evt);
            }
        });

        jCBYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016", "2015", "2014", "2013", "2012" }));
        jCBYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnLauch))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblAvgLoanAmount)
                                .addComponent(lblSumInterests)
                                .addComponent(lblAvgLoanDuration)
                                .addComponent(lblNbLoansCoS)
                                .addComponent(lblNbTotalLoan))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTitle)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblPeriod)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblLoanType)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRBImmo)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRBAuto)
                                .addComponent(jCBYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoanType)
                    .addComponent(jRBImmo)
                    .addComponent(jRBAuto))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriod)
                    .addComponent(jCBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblNbLoansCoS)
                .addGap(18, 18, 18)
                .addComponent(lblAvgLoanDuration)
                .addGap(18, 18, 18)
                .addComponent(lblSumInterests)
                .addGap(18, 18, 18)
                .addComponent(lblAvgLoanAmount)
                .addGap(18, 18, 18)
                .addComponent(lblNbTotalLoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnLauch)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void jRBImmoActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void jRBAutoActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void jCBMonthActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jCBYearActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void jbtnLauchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	
    	if(jRBImmo.isSelected()){
    		IndicatorController ic = new IndicatorController();
    		ic.indicatorImmobilier();
    	}
    	
    	if(jRBAuto.isSelected()){
    		IndicatorController ic = new IndicatorController();
    		ic.indicatorAutomobile();
    	}
    	
    }                                         


    // Variables declaration - do not modify                     
    private javax.swing.JComboBox jCBMonth;
    private javax.swing.JComboBox jCBYear;
    private javax.swing.JRadioButton jRBAuto;
    private javax.swing.JRadioButton jRBImmo;
    private javax.swing.JButton jbtnLauch;
    private javax.swing.JLabel lblAvgLoanAmount;
    private javax.swing.JLabel lblAvgLoanDuration;
    private javax.swing.JLabel lblLoanType;
    private javax.swing.JLabel lblNbLoansCoS;
    private javax.swing.JLabel lblNbTotalLoan;
    private javax.swing.JLabel lblPeriod;
    private javax.swing.JLabel lblSumInterests;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration                   
}
