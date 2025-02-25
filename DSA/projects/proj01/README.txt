IC312 Project 1: Text Editor

*** Please fill in this file completely before submitting. ***

Name: Ian Coffey
Alpha: 261194

Describe any help or outside resources used (or write "None"):

Used online resource on part one to determine how to assign a 'null' to
a char. since char is a primitive data type, it cannot handle null,
so the closest option is to assign '\0'.

Furthermore, I attended MGSP to get help on how to implement a linked
list for the insert function on part 1


Which interface did you implement using linked lists, and why?

I implemented MyText as a linked list because it would be impossible to
get insert to be an O(1) function given an array, so Linked list are the
only viable option for part 1


Which interface did you implement using arrays, and why?

I implemented arrays for BoundedStack because it was my only option.

What part or aspect of this project was the most challenging?

Given that you could choose which approach you took for how you
implement each part, I ended up doing an array for part 1 at first. This
ended up being disasterous, and I wasted a lot of time coding something
that would ultimately not work for O(1)


What part or aspect of this project was the most tedious or
uninteresting?

Dealing with nodes is always tedious, because there are about a million
things in this project that could result in a null pointer exception
being thrown. Additionally, the test cases make it very difficult to
determine what is going wrong with regards to the speed test


How did the difficulty and time required for this project
compare to those in your other CS/IT courses?

This is most definitely one of the harder projects I have had. The time
required is immense compared to other courses taken in the past  and it takes a lot of concentration to understand
your program


Any other comments for your instructor?
