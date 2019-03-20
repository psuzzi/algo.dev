#!/bin/bash

echo '=== run testing tool ==='
if [ $1 = "java" ]; then
    # with this option runs java only
    echo '= run java only ='
    java -cp ../target/classes/ ksp190.prep.Solution
else 
    # by default runs python script with java
    echo '= run python test java ='
    python3 testing_tool.py java -cp ../target/classes/ ksp190.prep.Solution
fi
