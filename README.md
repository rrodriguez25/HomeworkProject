# Automation framework to compare two CSV files

## Overview
This repository contains a TestNG test suite designed for Compare Two CSV files.
- CompareCSV.xml contains the test suite, see Test Coverage section below.
- configsetting.properties specifies the name of Files to compare
- Files should be in the csvfiles folder
- CSVComparisonReport.html contains the test results and it is generated after a test is executed. 
 
## Features
### Test Coverage: 
Includes tests for
- Compare File Size
- Compare Column Header Count
- Compare Column Header Name
- Compare Row Count
- Compare Row Data

### Running Tests
- **From IDE**: IntelliJ IDEA / Eclipse: Right-click on the CompareCSV.xml file or individual test classes and select Run or Debug As TestNG.

### To-Do
- Create a .bat file containing the necessary commands to run the tests from **Command Line**.
