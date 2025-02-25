IC312 Project 2: Pills

*** Please fill in this file completely before submitting. ***

Name: Ian Coffey
Alpha: m261194

Describe any help or outside resources used (or write "None"):

Used the oracle java documentation for ArrayList, List, Comparable,
List Iterator, and iterator. Futher resources used regarding syntax and conceptual
approaches are linked below...

https://www.geeksforgeeks.org/formatted-output-in-java/
https://www.geeksforgeeks.org/how-to-sort-an-arraylist-in-descending-order-in-java/
https://www.geeksforgeeks.org/quick-sort-algorithm/

Estimate how well you did on each part, on a scale of
0 (nothing) to 5 (perfectly correct and efficient):
  Part 1 (TreeMap):    5 / 5
  Part 2 (Pharmacies): 5 / 5
  Part 3 (TopK):       4 / 5
  Part 4 (Zips):       4 / 5
  Code style:          5 / 5
  Bonus (faster):      0 / 5

Please explain your self-assessment scores above:
The programs work as specified and perform the functions that they need
to. I feel like the bubble down function was complicated, and i tried to
simplify the logic as much as i could, but i feel the approach could be
better. The Zips works well using three maps and a single heap, and it
ran between 10-11 seconds.

What did you do to thoroughly test your own code?
Given that all of these methods are based on generics, i used multiple
data types for the TreeMap and the TopK to ensure that I did not
accidentlly use binary logic for generics. This proved effective, as my
TopK worked well with numbers, but something went wrong when i used
strings with it. 

What part or aspect of this project was the most challenging?
Most certainly the topK was very challenging conceptually. I tried to
use a binary sort to keep the heap in order to make things more simple
at first, but it only proved to make things much more challenging for
myself in the long run.

Explain what you did for the bonus (if anything):
none

What part or aspect of this project was the most tedious or
uninteresting?

I think it was all interesting, but topK was very very tedious to say
the least

How did the difficulty and time required for this project
compare to those in your other CS courses?

Out of all the projects and assignments so far, this took the longest.
Compared to all other classes, this takes the longest out of any.

Any other comments for your instructor?
