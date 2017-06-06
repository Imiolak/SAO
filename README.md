# Reversi (aka Othello)

## 1. State of the art
* https://www.lri.fr/~hansen/proceedings/2011/GECCO/companion/p739.pdf
* http://www.xiaotu.com/pub/ChonS05a.pdf
* http://gamelearninglab.nctu.edu.tw/ctsun/GA%20learning%20in%20game%20playing.pdf
* http://nn.cs.utexas.edu/downloads/papers/moriarty.discovering.pdf
* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.114.1746&rep=rep1&type=pdf
* https://skatgame.net/mburo/ps/compoth.pdf

## 2. Problem definition

### 2.1. Reversi rules
Reversi is a two-player strategy board game played with pieces (or disks), which are light on one side and dark on the other. Each player has one color assigned to him. The goal of the game is to have the majority of disks turned to display your color when the game ends. Reversi rules are as follows:
* game is played on a 8x8 board
* game starts with two light and two dark pieces on board, positioned in the center, see image below:
 <img src="docs/img/plan.png">

* dark player is the first to move
* during player's turn, the player places a piece on the board, his colour up. A piece must be placed on a square adjacent to opposing player's piece in such a way, that at least one opposing piece is captured
* capture occurs when there is a straight line (horizontal, vertical or diagonal) between a piece that is being placed on the board and any other piece owned by the acting player, with at least one opposing player's piece in between. The pieces in beetween are turned over
* if a player to act has no available moves to make he passes
* game ends when both players have no available moves to make, whoever has the majority of pieces in his colour wins

### 2.2. Problem
The aim of this project is to autmate the process of finding the optimal strategies for Reversi game. A torunament platform for Reversi strategies will be created.

### 2.3. Strategies
#### 2.3.1. Non-parametrized
* Random moves - sefl-explanatory
* Greedy algorithm - makes moves that yield the most points at a given time

#### 2.3.2. Parametrized
1. Stable discs - aims to capture squares that can't be recaptured
    Parameters:
    * stable-disk-weight - capture stable disk or capture something that yields more points
    
    Value range - [0, 1] - 1 always capture stable, 0 always capture for points
    
    Value step - 0.1

1. Corners - aims to capture board corners
    Parameters:
    * number-of-corners-to-capture
    
    Value set - {1, 2, 3, 4}

1. Positional - based on risk map, aims to capture low-risk squares and aviod high-risk squares
    Parameters:
    * risk-map - array of 5 integers - risk value for each region
        
    Value set for each region - {0, 1, .. , 99 }
    
    <img src="docs/img/risk_zones.gif">

1. Mobility - aims to make such moves that maximize number of your moves and minimize number of opponent's moves
    Parameters:
    * maximize - decide whether to maximize self moves or minimize opponent's moves
    
    Value range - [0, 1] - 1 always maximize self moves, 0 always minimize opponent's moves
    
    Value step - 0.1

1. Mixed strategy - used one strategy at first, changes to another strategy when condition is met
    Parameters:
    * strategy1 - strategy to use at the beginning of the game
    * changeCondition - number of moves to make before changing strategy
    
    Value set - { 0, 1, 2, .. , 19 } - 0 indicates that only strategt 1 should be used throughout the game
    * strategy2 - strategy to use after changeCondigion in met
    
### 2.4 Problem resolution proposition
#### 2.4.1 Model
Each strategy will be represented as an agent containing to following properties:
* gamesPlayed [int] - number of games played by the stratgy agent
* gamesWon [int] - numbers of games won
* earlyGameStrategy [strategy] - strategy that should be used from the start of the game
* strategySwitchPoint [int] - number indicating after how many moves should the agent change strategy to lateGameStrategy - if the value is 0, only earlyGameStrategy should be used throughout the game
* lateGameStrategy [strategy] - strategy that should be used when {strategySwitchPoints} moves are made

#### 2.4.2 Initial population

#### 2.4.3 Agent disturbance
If at any point disturbance of agent's strategies is to be performed, a decision tree determines the details of the disturbance. The decistion tree requires the following parameters:
* disturbEarlyGameStrategyChance
* replaceEarlyGameStrategyChance
* disturbStrategySwitchPointChance
* useOnlyEarlyGameStrategyChance
* disturbLateGameStrategyChance
* replaceLateGameStrategyChance
The decision tree has the following structure:
```
DisturbAgentsStrategies(disturbEarlyGameStrategyChance, replaceEarlyGameStrategyChance,
        disturbStrategySwitchPointChance, useOnlyEarlyGameStrategyChance,
        disturbLateGameStrategyChance, replaceLateGameStrategyChance)
{
    # check to see if distirbance should be performed
    if random(0,1) > win%
        # check to see whether to disturb earlyGameStrategy
        if random(0,1) < disturbEarlyGameStrategyChance
            # check to see whether to disturb parameters or change strategy
            if random(0.1) < replaceEarlyGameStrategyChance
                ChangeStrategy(earlyGameStrategy)
            else
                DisturbParameters(earlyGameStrategy)

        # check to see whether to disturb strategySwitchPoint
        if random(0,1) < disturbStrategySwitchPointChance
            if random(0,1) < useOnlyEarlyGameStrategyChance
                strategySwitchPoint = 0
            else
                strategySwitchPoint = random(1,20)
        # check to see whether to disturb lateGameStrategy
        if random(0,1) < disturbLateGameStrategyChance
            # check to see whether to disturb parameters or change strategy
            if random(0.1) < replaceLateGameStrategyChance
                ChangeStrategy(lateGameStrategy)
            else
                DisturbParameters(lateGameStrategy)
}            
```

## 3. Software documentation

## 4. Testing plan
### 4.1 Initial population
* 6 strategies - 1 non-parametrized, 5 parametrized
* each parametrized - 5 different parameter sets
* 26 singular strategies + 34 randomly mixed multiple strategies
* 60 strategies in total

### 4.2 Tournament selection
#### 4.2.1 Child and parents succession
In each iteration:
    Do 20 times:
* randomly chose two 10-strategies sets
* round-robin games in each set
* two winning strategies populate and together with their child advance to next iteration

#### 4.2.2 Only child succession
In each iteration:
    Do 60 times:
* randomly chose two 10-strategies sets
* round-robin games in each set
* two winning strategies populate, but only their child advances to next iteration

### 4.3 Disturbance
After each iteration:
* decide whether to disturb agents based on its winning precentage

### 4.4 Determining winners
After a set number of iterations a list of all surviving specimen is sorted by win percentage in a descending manner. Should there be any specimen within (subject to change) 5% win rate margin, a second tournament in performed.

## 5. Results
