# coding: utf-8

import os, re
from collections import Counter
import matplotlib.pyplot as plt
import numpy as np

def makePlot(file, pathToSave):
    with open(file) as f:
        strategies = []
        for line in f.readlines():
            strategy = [i for i in re.findall(r'[a-zA-Z]+', line) if 'NaN' not in i]
            strategies.append(strategy[0] if len(strategy) == 1 else strategy[0]+'|'+strategy[1])

    STRATEGIES = Counter(strategies)
    SUM = sum(STRATEGIES.values())
    # for i in STRATEGIES.most_common():
    #     print i[0], float(i[1])/SUM

    labels, values = zip(*STRATEGIES.items())

    indexes = np.arange(len(labels))
    plt.bar(indexes, values)
    plt.xticks(indexes, labels, rotation='vertical')
    plt.tight_layout()
    plt.savefig(pathToSave)
    plt.clf()

PATH = re.findall(r'(.*)/', os.getcwd())[0]
FOLDERS = [i for i in os.listdir(PATH) if '2017' in i]
pickFolder = 3
FILES = [ i for i in os.listdir(PATH+'/'+FOLDERS[pickFolder]) if '.' not in i]
pickFile = 8
FILE = PATH+'/'+FOLDERS[pickFolder]+'/'+FILES[pickFile]

# print FOLDERS[pickFolder]+'/'+FILES[pickFile]
# print 
for folder in FOLDERS:
    files = [ i for i in os.listdir(PATH+'/'+folder) if '.' not in i]
    directory = os.getcwd()+'/'+folder+'/'
    if not os.path.exists(directory):
            os.makedirs(directory)
    for file in files:
        directoryToSave = directory+file+'.png'
        print directoryToSave
        makePlot(PATH+'/'+folder+'/'+file, directoryToSave)
# makePlot(FILE)