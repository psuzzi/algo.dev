#!/bin/bash

echo '=== run testing tool ==='
if [ $1 = "java" ]; then
    # with this option runs java only
    echo '= run java only ='
    java -cp ../bin/ gcj190.qual.Solution
else 
    # by default runs python script with java
    echo '= run python test java ='
    python3 testing_tool.py java -cp ../bin/ gcj190.qual.Solution
fi
