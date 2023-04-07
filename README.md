# NaiveBayes
OOP project in java containing a naive bayes classifier. Naive bayes classifier
is a simple technique for constructing class labels to problem instances.   </br></br>
In this classifier, the labels include: </br>
Student Gender, Parent had own business, Has a part-time job, Urban or 
rural address, Studies business subjects.  </br></br>
The classification is: </br>
Becomes and entrepreneur </br> </br>
Many classes are used to create this classifier.

## Classes
These classes contain various constructors, methods, encapsulated attributes and
various other core functionality.

#### CSVtoARFF
This class contains code that converts the provided CSV file to ARFF format
(Attribute-Relation File Format) which is the preferred format for weka.
The last attribute is also set as the classifications in this class.

#### SplitData
This class randomizes the dataset and splits the data into 70% training
and 30% testing.

#### TrainAndTest
This class trains the naive bayes classifier on the data returned in the
split data class. It then tests the classifier on the testing dataset
and prints out a summary of the comparison. This summary contains
correctly and incorrectly classified instances, a confusion matrix and
other bits of information.

#### PredictInstance
This class contains a method that takes in a list of features and returns
them in the instances format provided by weka. The other method then takes
in this list and uses the trained classifier to predict its classification.

#### GUI
This class contains the GUI.

#### Main
The main class declares variables and initializes them with instances of
the other classes.

## Weka
This classifier was built using the weka dependency. Weka is a collection
of machine learning algorithms for data mining tasks. Here are the steps
to use it:

1. Download the [weka jar file](http://www.java2s.com/Code/Jar/w/Downloadwekajar.htm).
2. Use Ctrl+Alt+Shift+S and click Modules | Dependencies.
3. Select add and enter file path to jar file.
4. Using maven, [add the dependency](https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable/3.8.0). 

## Usage + Optional features
- After running, enter the features.
- Select the enter button.
- This will print out the classification predicted.
- It will also print out the accuracy of the prediction which can be increased with
extra instances.
- A confusion matrix with other extra information will also be printed stdout.
- You can add the extra instances through the csv as a new arff file replaces the
old one when ran.