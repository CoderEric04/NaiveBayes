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
        JFrame frame1 = new JFrame("Naive Bayes Classifier");

        JPanel panel1 = new JPanel();
        frame1.add(panel1);

        setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        panel1.setLayout(new FlowLayout());

        JLabel rules = new JLabel("Only enter choices given in brackets, each entry must have capital letter");
        rules.setHorizontalAlignment(JLabel.CENTER);
        rules.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        frame1.getContentPane().add(rules, BorderLayout.NORTH);

        JLabel genderLabel = new JLabel("Gender (Male/Female): ");
        genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(50,20));
        panel1.add(genderLabel);
        panel1.add(genderField);

        JLabel hasBusinessParentLabel = new JLabel("Parent owns business (Yes/No): ");
        hasBusinessParentField = new JTextField();
        hasBusinessParentField.setPreferredSize(new Dimension(50,20));
        panel1.add(hasBusinessParentLabel);
        panel1.add(hasBusinessParentField);

        JLabel hasJobLabel = new JLabel("Has job (Yes/No): ");
        hasJobField = new JTextField();
        hasJobField.setPreferredSize(new Dimension(50,20));
        panel1.add(hasJobLabel);
        panel1.add(hasJobField);

        JLabel livingAreaLabel = new JLabel("Lives in urban or rural area (Urban/Rural)");
        livingAreaField = new JTextField();
        livingAreaField.setPreferredSize(new Dimension(50,20));
        panel1.add(livingAreaLabel);
        panel1.add(livingAreaField);

        JLabel studiesBusinessLabel = new JLabel("Studies business (Yes/No): ");
        studiesBusinessField = new JTextField();
        studiesBusinessField.setPreferredSize(new Dimension(50,20));
        panel1.add(studiesBusinessLabel);
        panel1.add(studiesBusinessField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterInstancesButtonListener(naiveBayes, trainingData, eval));
        frame1.getContentPane().add(enterButton, BorderLayout.SOUTH);

        values = new ArrayList<>();

        //frame1.pack();
        frame1.setSize(1000, 200);
        frame1.setVisible(true);
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
        public void actionPerformed(ActionEvent e) {
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

            double[] prob;
            try {
                prob = naiveBayes.distributionForInstance(predictInstance.enterInstance(predictInstance.getValues()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            for (int i = 0; i < prob.length; i++) {
                prob[i] = prob[i] * 100;
            }

            JOptionPane.showMessageDialog(GUI.this, "There is a " + Math.round(prob[0]) + "% chance of becoming an entrepreneur and a "
                    + Math.round(prob[1]) + "% chance of not becoming an entrepreneur. " +
                    "Class predicted is " + pred + " with an accuracy of " + Math.round(eval.pctCorrect()) + "%");

        }
    }
}
