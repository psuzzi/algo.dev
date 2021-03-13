#!/bin/bash
JAVA_CP=../target/classes/
JAVA_CLASS=ksp190.prep.Solution1

echo '=== run testing tool ==='
if [ $1 = "java" ]; then
    # with this option runs java only
    echo '= run java only ='
    java -cp $JAVA_CP $JAVA_CLASS
else 
    # by default runs python script with java
    echo '= run python test java ='
    python3 testing_tool.py java -cp $JAVA_CP $JAVA_CLASS
fi