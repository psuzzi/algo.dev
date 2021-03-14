#!/bin/bash
>&2 echo "Running in $(pwd)"
>&2 echo "Moving to $(cd ../../../../bin && pwd)"
python testing_tool.py java -cp ../../../../bin/ cj18.r0p.p1.Solution