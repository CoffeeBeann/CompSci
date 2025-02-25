Author:
MIDN 2/C Ian Coffey (m261194)

External resources used:
https://www.geeksforgeeks.org/c-program-to-read-contents-of-whole-file/
https://www.tutorialspoint.com/c_standard_library/c_function_fscanf.htm
https://www.tutorialspoint.com/c_standard_library/c_function_realloc.htm

How the program works:
The sort that I decided to go with I call 'targetSort'. it is a variation of selection sort 
with a few differences. Lets say I have an array as follows

[2, 4, 5, 2, 3, 4, 1, 1, 3, 5]

Target sort will work very similar to selection sort, but a key difference is that wherever 'i' 
points to initially will become the 'target'. So in this scenario, instead of sorting by the minimum
value, we just choose 2 as the value we are going to search for and sort. So just like selection sort,
an inner for loop 'j' will iteration through the loop from i+1 -> totalMids, and if a 2 is found, will 
immeditatley perform a swap to put it next to other 2's by swapping array[placeHolder] and array[j]. 
placeHolder refers to the next spot open spot where we want to put our next number when we find it.
This is why placeholder is incremented on every swap call. Offset is also incremented in total, so that 
when we finall sort all of our 2's and our inner for loop breaks out, we can put our placeholder right 
back where is was before. 

    placeHolder
        |
        |
        \/
  [2, 2, 5, 4, 3, 4, 1, 1, 3, 5]
  |----|
 |offset|

From here, 5 is selected as our next target, and the loop will start sorting the 5's next. However
Since we do not want to swap with something that is already a 5, I use a while loop to make sure the 
placeholder value is not already pointing at a target to avoid unecessary swaps. so after that while loop
the state of the array looks like this

       placeHolder
           |
           |
           \/
  [2, 2, 5, 4, 3, 4, 1, 1, 3, 5]
  |----|
 |offset|

Now we are ready to start sorting 5's. This process is repeated until the array is fully sorted. 
The offset keeps incrementing to put the placeholder where it needs to be, and there are checks
to make sure there are not any index out of bounds errors (j < totalMids &&...)

How I came up with this:
When starting this problem set, I originally set out to optimize the selection sort algorithm in an
attempt to minimize swaps. Long story short, that did not work. My algorithm ran faster, but did the 
same number of swaps as selection sort, so it was no use. So I decided that a good way to cut down
on swaps would be to set the next 'target' value to whatever the placeholder happens to be at. 
When the sort begins, that value is whatever the fisrt company is found in stdin. As opposed to 
selection sort, where it would start by sorting 1's as it is the smallest value. This cuts down on swaps
because I do not consider the strict order of the companies. I just start ordering the next company that 
just so happens to be at my placeholder value. This works extremely well for the 'close' files where companies
are generally in the same location. For instance... the normal selection sort in part 1 took
3839 swaps to sort the 30x4000close.txt file, whereas my rooms.c only needed 3059 swaps.
However, when companies are not grouped close together, the improvements are very marginal.
For instance... part 1 required 2623 swaps to sort the 3x4000.txt file, and mine took 2614 swaps.
An incredible 9 swaps saved.

Runtime analysis.
In terms of run time, target sort can only perform as bad as selection sort, since
target sort is a variant of selection sort. However, since target sort does not strictly keep companies in order
from least to greatests, and since this algorithm is particularly good with groupings,
the worst possible scenario for this algorithm would be an array where there exists no grouping
and elements with scattered to ensure the need for a swap for every index in an array. So for example, the array,

[1, 2, 3, 1, 2, 3, 1, 2, 3]

is part of the family of worst possible scenarios, since there is no grouping that the algorithm gets to capitalize
on and a swap is needed for all n-1 indexes to sort the array. So with this in mind, we will define the size of the
array as n and k as the number of companies to determine the runtime of this algorithm. The outer for loop 'i' 
will always iterate from 0 to the length of the array, so the outer for loop does n work, and the inner loop doe
s n - 1 work, just like a selection sort. And for each iteration, the work done by the inner loop decreases by 1, 
so the work performed as the loop progresses is (n - 1) + (n - 2) + (n - 3) + ... This is can be represented by the 
arithmetic series (n - 1)/2. Since the outer loop runs n times, we multiply these two terms together to get n(n - 1)/2, 
which after supressing constants and lower order terms results in big-O of n^2. However, this only gives an upper bound. 
To find the lower bound, we can substitute n/2 for n, as a target sort will asbolutely do less work with an size n/2,
so any runtime derivation can be used for a lower bound. So substituting n/2 into the n(n -1)/2 equation results in
(n / 2)((n / 2) - 1 / 2). Cancelling out lower order terms and constant still yields n^2, so our big-omega is n^2,
and our big-O is n^2, so the overall big-theta runtime is also n^2. The number of companies k does not impact our 
overall runtime, because the algorithm will still do this amount of work regardless of how many companies are
in the array. The number of companies would have an impact on the number of swaps we would need to perform, but 
since a swap is done in constant time, it has no bearing over our overall runtime.

Swap analysis:
We can use the worst case scenario array from the runtime analysis to determine the relationship between
the number of companies we have and the number of swaps we have to perform in order to sort the array.
Since there are no groupings at all in our worst case and since they are out of order we can start by 
saying that we need to do n swaps in order to sort the array because every index is out of place. And if 
every index is out of place, a swap is needed for every index to sort the array. However, target sort actually 
does less swaps than this due to the placeholder value determining the next value to sort. When the inner 
for loop is done sorting a company, the next company that just so happens to be at the placeholder 
value is what we decide to sort next, and if that is the case, the company at the placeholder value is already sorted.

already sorted
    |
    |
    \/
|----------|
[1, 1, 1, 3, 4, 2, 3, 4, 2]
         /\
         |
         |
    placeholder

This means that for every new company we start to sort, we will do one less swap at the start of the inner for loop.
So we perform (n - k) swaps in the worse case scenario. This is why in some of the test cases, the improvements were 
incredibly minor. Selection sort does n swaps, so if we came across a worse case scenario array like we did in
3x4000.txt, the improvements were marginal because we only did k less swaps.

