#!/usr/bin/env python3
import sys, getopt, os

def main(argv):
    cwd = os.getcwd()
    fname = "Solution.py"
    print  ("Search for\n dir  {0} \n file {1}".format(cwd, fname))
    # dyn add to path and import module
    sys.path.append(cwd)
    module= __import__("Solution")

    # get the class and instantiate it with ()
    sol = getattr(module, "Solution" )()
    fun = getattr(sol, argv[0] )

    # solve and print
    res = fun(*argv[1:])
    print ("result: ", res)

    # debug
    #for arg in argv[0:]:
    #    print("arg: " + arg)



if __name__ == "__main__":
    main(sys.argv[1:])


def exec():
    f=open("guru99.txt", "r")