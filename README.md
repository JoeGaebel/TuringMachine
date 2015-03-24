# TuringMachine
For the course "Theory of Software Engineering" it was our job to build a turing machine interpreter, which would recieve a series of states and transitions, and some input to test against within a text file. 

#Mechanisms
By abstracting the problem into seperate classes, and defining a unique data structure, complexity was managed.
A State machine has a collection of states, a current state, and a tape. The tape is a seemingly infinite "ticker tape" which can be seen as a dynamic array. By producing a seperate "Tape" data structure, using an underlying array and providing specific read and write operations, the turing machine's ticker tape was implemented. Then, by creating a State class, and a transition class in order to define the different logical paths, a text file could be read, a turing machine could be created on the fly, and input could be tested.
