package etnaivebayes;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private final JTextField genderField, hasBusinessParentField, hasJobField, livingAreaField, studiesBusinessField;
    private final ArrayList<String> values;

    public GUI(NaiveBayes naiveBayes, Instances trainingData, Evaluation eval){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel genderLabel = new JLabel("Gender (Male/Female): ");
        genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(50,20));
        add(genderLabel);
        add(genderField);

        JLabel hasBusinessParentLabel = new JLabel("Parent owns business (Yes/No): ");
        hasBusinessParentField = new JTextField();
        hasBusinessParentField.setPreferredSize(new Dimension(50,20));
        add(hasBusinessParentLabel);
        add(hasBusinessParentField);

        JLabel hasJobLabel = new JLabel("Has job (Yes/No): ");
        hasJobField = new JTextField();
        hasJobField.setPreferredSize(new Dimension(50,20));
        add(hasJobLabel);
        add(hasJobField);

        JLabel livingAreaLabel = new JLabel("Lives in urban or rural area (Urban/Rural)");
        livingAreaField = new JTextField();
        livingAreaField.setPreferredSize(new Dimension(50,20));
        add(livingAreaLabel);
        add(livingAreaField);

        JLabel studiesBusinessLabel = new JLabel("Studies business (Yes/No): ");
        studiesBusinessField = new JTextField();
        studiesBusinessField.setPreferredSize(new Dimension(50,20));
        add(studiesBusinessLabel);
        add(studiesBusinessField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterInstancesButtonListener(naiveBayes, trainingData, eval));
        add(enterButton);

        values = new ArrayList<>();

        pack();
        setVisible(true);
    }

    private class EnterInstancesButtonListener implements ActionListener{
        private final NaiveBayes naiveBayes;
        private final Instances trainingData;
        private final Evaluation eval;

        public EnterInstancesButtonListener(NaiveBayes naiveBayes, Instances trainingData, Evaluation eval){
            this.naiveBayes = naiveBayes;
            this.trainingData = trainingData;
            this.eval = eval;
        }
        public void actionPerformed(ActionEvent e){
            String pred;

            values.clear();
            values.add(genderField.getText());
            values.add(hasBusinessParentField.getText());
            values.add(hasJobField.getText());
            values.add(livingAreaField.getText());
            values.add(studiesBusinessField.getText());

            PredictInstance predictInstance = new PredictInstance(trainingData, naiveBayes, values);
            try {
                pred = predictInstance.predict();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(GUI.this, "Class predicted is " + pred + " with an accuracy of " + eval.pctCorrect() + "%");
        }
    }
}
