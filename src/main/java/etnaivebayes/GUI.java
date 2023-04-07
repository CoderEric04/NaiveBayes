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
        //create frame
        JFrame frame1 = new JFrame("Naive Bayes Classifier");

        //create panel and add it to frame
        JPanel panel1 = new JPanel();
        frame1.add(panel1);

        //ets the operation that will happen by default to exit when the user initiates a close on this frame
        setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        //sets layout to a flow layout
        panel1.setLayout(new FlowLayout());

        //label that contains criteria for entries
        JLabel rules = new JLabel("Only enter choices given in brackets, each entry must have capital letter");
        //center the label
        rules.setHorizontalAlignment(JLabel.CENTER);
        //sets border to have a larger gap below and on top of label
        rules.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        //places the label north of the frame
        frame1.getContentPane().add(rules, BorderLayout.NORTH);

        //label and field for entry
        JLabel genderLabel = new JLabel("Gender (Male/Female): ");
        genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(50,20));
        panel1.add(genderLabel);
        panel1.add(genderField);

        //label and field for entry
        JLabel hasBusinessParentLabel = new JLabel("Parent owns business (Yes/No): ");
        hasBusinessParentField = new JTextField();
        hasBusinessParentField.setPreferredSize(new Dimension(50,20));
        panel1.add(hasBusinessParentLabel);
        panel1.add(hasBusinessParentField);

        //label and field for entry
        JLabel hasJobLabel = new JLabel("Has job (Yes/No): ");
        hasJobField = new JTextField();
        hasJobField.setPreferredSize(new Dimension(50,20));
        panel1.add(hasJobLabel);
        panel1.add(hasJobField);

        //label and field for entry
        JLabel livingAreaLabel = new JLabel("Lives in urban or rural area (Urban/Rural)");
        livingAreaField = new JTextField();
        livingAreaField.setPreferredSize(new Dimension(50,20));
        panel1.add(livingAreaLabel);
        panel1.add(livingAreaField);

        //label and field for entry
        JLabel studiesBusinessLabel = new JLabel("Studies business (Yes/No): ");
        studiesBusinessField = new JTextField();
        studiesBusinessField.setPreferredSize(new Dimension(50,20));
        panel1.add(studiesBusinessLabel);
        panel1.add(studiesBusinessField);

        //button that calls action listener when pressed
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterInstancesButtonListener(naiveBayes, trainingData, eval));
        //place button south of frame
        frame1.getContentPane().add(enterButton, BorderLayout.SOUTH);

        //create array list
        values = new ArrayList<>();

        //set size of frame
        frame1.setSize(1000, 200);
        //set visibility of frame
        frame1.setVisible(true);
    }

    //class for the action listener when button is pressed
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
            double[] prob;

            //add values entered into text fields into arraylist
            values.clear();
            values.add(genderField.getText());
            values.add(hasBusinessParentField.getText());
            values.add(hasJobField.getText());
            values.add(livingAreaField.getText());
            values.add(studiesBusinessField.getText());

            //create new instance of PredictInstance class passing training data,
            //the classifier and the arraylist containing entries
            PredictInstance predictInstance = new PredictInstance(trainingData, naiveBayes, values);
            try {
                //get prediction (yes/no)
                pred = predictInstance.predict();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            try {
                //in an array of size 2, store the decimal chance of each class of the entered instance
                //which was predicted by the trained classifier
                prob = naiveBayes.distributionForInstance(predictInstance.enterInstance(predictInstance.getValues()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            //change values from percent to decimal
            for (int i = 0; i < prob.length; i++) {
                prob[i] = prob[i] * 100;
            }

            //Show message containing information on the values entered. Rounds percentages.
            JOptionPane.showMessageDialog(GUI.this, "There is a " + Math.round(prob[0]) + "% chance of becoming an entrepreneur and a "
                    + Math.round(prob[1]) + "% chance of not becoming an entrepreneur. " +
                    "Class predicted is " + pred + " with an accuracy of " + Math.round(eval.pctCorrect()) + "%");
        }
    }
}
