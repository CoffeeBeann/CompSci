
	// Note -- this file relies upon other files, including:
	//   blinkt.c (for set_pixel(), show() )
	//   helper.s (for helperGetLong(), helperPrintLong() )
	
	// ********************
	// Specify the instruction set to generate for
	.arch armv8-a

	// ******************
	// Define all necessary strings
	.text	
	.section	.rodata
	.align	3
strNewline:	
	.string "\n"
strHello:
	.string "Hello!"
strWhatNumber:
	.string "Enter a number: "
	
	// *****************************
	// *****************************
	// *****************************
	// *****************************
	// main() really starts here
	// *****************************
	// *****************************
	// *****************************
	// *****************************
	.text
	.align	2
	.global	main
	.type	main, %function
main:
        // Save return address to stack -- needed to return from main()
	// NOTE -- we only need 8 bytes of space on stack for this
	// BUT --  must move $sp in increments of 16, or get 'bus error' on use
	sub     sp, sp,  16
	stur    lr,[sp, 0]

	// Do some necessary setup for IC220 (includes initializing the LEDs)
	// Note: if running with gdb, you probably want to skip over this function call (use "next")
	bl	setupIC220	

	// Print welcome message
	adr 	x0, strHello
	bl	puts
	
	// Get a number from the user
	adr 	x0, strWhatNumber
	bl	puts
	bl 	helperGetLong
	mov	x20, x0       	// x20 = theNumber

	// *************************************
	// *************************************
	// *************************************
	// *************************************
	// *************************************
	// *************************************
	// *************************************
	// *************************************
	// *************************************
	//  START YOUR CODE HERE
	
	// Sample code for you to step through
	// [delete this code when you don't need it anymore....]
	
	mov x0, x20
	bl	fibonacci

	bl	helperPrintLong

	// New Line
	adr 	x0, strNewline
	bl	puts


	
	// *************************************
	// Exit from main()
	// *************************************
Exit:	
	// Reload return address and fix-up stack pointer
	ldur    lr,[sp, 0]
	add     sp, sp, 16

	// Actually return from main(), with return value 0
	br      lr
	
 	.size	main, . - main     // tell gdb where this function ends


	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// FUNCTION DEFINITIONS
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************
	// *****************************************



	// function:  void setupIC220()
	// *************************************
	// *************************************
	// Some necessary startup steps for IC220 -- don't change these!
setupIC220:
        // Save return address to stack -- needed to return from this function back to caller 
	sub     sp, sp,  16
	stur    lr, [sp, 0]
	
	// 1. Initialize the LEDs (code from in blinkt.c)
	bl	start 
	// 2. Set terminal output (stdout) to *not* buffer, so we can
	// see results right away (important for debugging)
	adrp    x0, :got:stdout
	ldr     x0, [x0, #:got_lo12:stdout]
	ldr     x0, [x0]    // arg0 = stdout
	mov  	x1, 0       // arg1 = NULL
	bl      setbuf      // call setbuf(stdout, NULL)

	// Reload return address and fix-up stack pointer
	ldur    lr, [sp, 0]
	add     sp, sp, 16

	// Actually return 
	br      lr

	.size	setupIC220, . - setupIC220         // tell gdb where this function ends

	
	// End IC220 setup steps
	// *************************************
	// *************************************

	


	
	// function: void setLEDoff(long ledNumber)
	// This simple function takes a LED number (0-7), in x0
	// and sets the RGB values for that LED to zero.
	// The caller must still call show() in order to see the effects
setLEDoff:	
        // Save return address to stack -- needed to return from this function back to caller 
	sub     sp, sp,  16
	stur    lr, [sp, 0]

	// The LED# is already in x0, but set RGB values to zero (x1/x2/x3)
	mov	x1, 0      	// red   value
	mov	x2, 0  		// green value 
	mov	x3, 0		// blue  blue value
	bl 	set_pixel  

	// Reload return address and fix-up stack pointer
	ldur    lr, [sp, 0]
	add     sp, sp, 16

	// Actually return 
	br      lr

	.size	setLEDoff, . - setLEDoff         // tell gdb where this function ends

fibonacci:
		
	// Check N < 1
	subs xzr, x0, 1
	b.gt step

	// Return to Main
	br	lr
step:	
	// Allocate Memory
	sub sp, sp, 48

	// Find N-1 & N-2
	sub x1, x0, 1 // x1 = N-1
	sub x2, x0, 2 // x2 = N-2

	// Save Values Onto Stack
	stur lr,[sp, 0] // Save LR
	stur x0,[sp, 8] // save N
	stur x1,[sp, 16] // save N-1
	stur x2,[sp, 24] // save N-2
	
	// Move N-1 into x0 for Function Call
	mov x0, x1

	// Call Fib(n-1)
	bl	fibonacci

	// Grab & Store Result 
	stur x0,[sp,32] // save fib(n-1)

	// Move N-2 Into x0
	ldur x0,[sp, 24]
	
	// Call Fib(n-2)
	bl	fibonacci
	
	// calculate fib(n-1) + fib(n-2)
	ldur x1,[sp, 32] // Load fib(n-1)
	add x0, x0, x1 // x0 = fib(n-1) + fib(n-2)

	// Load LR
	ldur lr,[sp, 0]
	
	// Deallocate Memory
	add sp, sp, 48
	
	// Return to Caller
	br	lr
	
