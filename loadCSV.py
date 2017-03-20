# This file 
#   1. traverse to find all csv files in "data" folders
#   2. generate "mongoimport" commands using "find()" function
#   3. run all the commands through "subprocess.run()" 

import os, fnmatch, sys
import subprocess
from os.path import join, getsize

# This function 
#   1. finds the file with the pattern in the path
#   2. return a list of shell commands to load the file into mongodb through mongoimport
def genCmds(pattern, path):
	cmds = []
	for root, dirs, files in os.walk(path):
		if fnmatch.fnmatch(root, '*data'):
			# print('root=', root)
			for file in files:
				if fnmatch.fnmatch(file, pattern):
					location = root.split("/")[1]
					cmds.append('mongoimport --db zika --collection ' + location + ' --type=csv --headerline --file=' + str(os.path.join(root,file)))
	return cmds

# find all .csv files in current folder
cmds = genCmds('*.csv', '.')

# execute all the "mongoimport" commands
for cmd in cmds:
	subprocess.run(cmd,shell=True)
