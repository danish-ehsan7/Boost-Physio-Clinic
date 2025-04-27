
# BoostPhysioClinic

This is a Java-based Physiotherapy Clinic Management System. It helps manage appointments, treatments, physiotherapist schedules, and more.

## Project Structure


BoostPhysioClinic/
├── .vscode/                  # Visual Studio Code settings
├── lib/                      # External libraries (JUnit for testing)
├── out/                      # Compiled output (source files and test classes)
├── src/                      # Source code
│   ├── src/                  # Java source code files
│   └── public/               # Public assets (e.g., images)
├── test/                     # Test files for unit testing
└── readme.txt                # Project documentation

Navigate to project directory

## To compile java files 
javac -d out/src src/src/*.java

## To run the GUI Main file 
java -cp out/src Main



## For testing 

## To compile 
javac -cp "lib/junit-platform-console-standalone-1.13.0-M2.jar;out/src" -d out/test test/BookingSystemTest.java


## To run test
java -jar lib/junit-platform-console-standalone-1.13.0-M2.jar --class-path "out/src;out/test" --scan-classpath
