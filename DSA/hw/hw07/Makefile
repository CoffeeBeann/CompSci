JUNIT_JAR = /usr/share/java/junit4.jar

myCompile:
	javac DoubleTree.java AddMax.java

myTest: compile
	java DoubleTree

compile:
	javac -Xlint:all -cp .:$(JUNIT_JAR) *.java

test: compile
	java -ea -cp .:$(JUNIT_JAR) org.junit.runner.JUnitCore AddMaxTest

clean:
	rm -f *.class

.PHONY: compile test clean
