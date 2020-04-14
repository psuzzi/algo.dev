#!/usr/bin/env python3
import os, sys, subprocess

def main():
    with open("test.txt", "r") as f:
        t = int(f.readline())
        for i in range(0, t):
            testin = intlist(f.readline().strip())
            expout = intlist(f.readline().strip())
            testPython3('productExceptSelf', testin, expout)

# boolean from a string
def boolean(strval):
    return str(strval).lower() in ['true', 'True', '1']

# list of integers from a space separated string with integers
def intlist(strline):
    return [int(s) for s in strline.split(' ')]
            
def testPython3(funcname, testin, expout):
    res = runPython3('./', 'Solution', 'Solution', funcname, testin )
    correct = 'OK' if expout==res else 'WRONG'
    print("{:10} : {}( \"{}\" ) -> {}".format(correct, funcname, testin, res))

def runPython3(dir, file, clazz, funcname, params):
    # dyn add to path and import module
    sys.path.append(dir)
    module= __import__(file)
    # get the class and instantiate it with ()
    instance = getattr(module, "Solution" )()
    fun = getattr(instance, funcname )
    # execute and return
    return fun(params)


if __name__ == "__main__":
    main()