#!/bin/bash
>&2 echo "Running in $(pwd)"
>&2 echo "Moving to $(cd ../../../../target/classes/ && pwd)"
python3 interactive_runner.py python3 local_testing_tool.py 1 -- java -cp ../../../../target/classes/ ks19.r0p.p1.Solution