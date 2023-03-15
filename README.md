# Connect-4
CMSC150 final project:

Proposal:
This is a very rough draft of a connect-4 game. I'm attempting to code a 2-player game, in which player 1 is the user and player 2 is the computer. Each player takes a turn alternatively; player 1 chooses a column which they input in the terminal, and player 2 (computer) chooses a column at random. The end goal for this project is to implement a bit of strategy on player 2's side of things, similar to how a human would strategize in the a game. In the end, I'd want the computer try to place their chip as close to their other chips to simulate how a person would try to build a connect-4. Additionally, I'd want the computer to also detect whether player 1 is close to achieving a connect-4 in order to make this program challenging for the user.

As of right now, I'm having troubles with designing methods in the C4Board class. I don't know what methods would be relavent for the class file, and which methods would be better off in the Connect-4 file where the actual gameplay occurs.

Final project outcome:
I ended up not being able to add strategy to the CPU gameplay since it involves advanced algorithms that I do not know. Instead, I made two gamemodes (human vs. human (hard) and human vs. CPU (extremely easy)). Both of these implement the same logic, except the CPU takes random inputs for its dropChip() method. 
