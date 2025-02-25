#include <stdio.h>
#include <string.h>

extern int add(int x, int y);
extern int isPowerOfTwo(int x);
extern int isOdd(int x);
extern int bitToggle(int x, int y);
extern int oppositeSigns(int x, int y);
extern int countingBits(int x);

int main() {
    
    printf("%d\n", add(5,5));
    printf("%d\n", isPowerOfTwo(2048));
    printf("%d\n", isOdd(15));
    printf("%d\n", bitToggle(15,1));
    printf("%d\n", countingBits(15));
    return 0;
}
