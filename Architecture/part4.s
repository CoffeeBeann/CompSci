
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
strHello:
	.string "Hello!"
strAskNumber:
	.string "Which LED? (0-7)"
strHowRed:
	.string "How much red (0-255)"
strBye:
	.string "\nGoodbye."
	
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

	// Initialize the LEDs (code from in blinkt.c)
	bl	start
	
	// Print hello message
	adr	x0, strHello    // put address of strHello string into x0
	bl	puts
	
	// Prompt User Input 
	adr	x0, strAskNumber // put address of strAskNumber into x0
	bl	puts

	// Get user Input
	bl	helperGetLong
	mov	x19, x0 // save LEDNUM result into x19
	
	// Prompt User for amount of Red
	adr	x0, strHowRed // put address of strHowRed into x0
	bl	puts 
	
	// Call helperGetLong to get User Input
	bl	helperGetLong
	mov	x20, x0 // save REDNUM into x20

	// Counter = 30
	mov x23, 30

whileLoop:
	subs xzr, x23, 0
	b.le finalExit

	// Set LED 0 to all blue
	// We use this function:
	//    void set_pixel(uint8_t led, uint8_t r, uint8_t g, uint8_t b);
	// [see blinkt.h]
 	mov	x0, x19  	// LED #
	mov	x1, x20  	// red   value
	mov	x2, 0  		// green value 
	mov	x3, 255		// blue  blue vaue
	bl	set_pixel       // call set_pixel
	
	// Now actually make the LED changes happen
	// using this function (see blinkt.h):
	//   void show(void);
	bl	show
	
	// Pause for 0.5 seconds by using a completely useless for loop
	mov x0, 0 // i = 0
	mov x1, 0 // j = 0
	mov x2, 0 // k = 0
loop:
	subs xzr, x0, 1000
	b.gt exit
	add x0, x0, 1
	mov x1, 0
	bl	loop1
	bl	loop
loop1:
	subs xzr, x1, 1000
	b.gt loop
	add x1, x1, 1
	mov x2, 0
	bl loop2
loop2:
	subs xzr, x2, 300
	b.gt loop1
	add x2, x2, 1
	bl loop2

exit:
	// Turn off LEDs before quitting
	mov	x0, x19  	// LED #
	bl	setLEDoff
	bl	show	 	// Show the updated LED states
	
	// increment LEDNUM
	add x19, x19, 1
	
	// if LEDNUM > 7
	subs xzr, x19, 7
	b.le skip
	mov x19, 0 // LEDNUM = 0

skip:
	sub x23, x23, 1 // counter--
	bl	whileLoop


finalExit:

	// Print farewell message
	adr	x0, strBye
	bl 	puts
	
	// Reload return address and fix-up stack pointer
	ldur    lr,[sp, 0]
	add     sp, sp, 16

	// Actually return from main(), with return value 0
	br      lr

    	// For IC220, every function MUST end with a directive like this (tells the debugger where the function ends)
	// In other places, replace 'main' with the actual name of the function in question
 	.size	main, . - main

	// *****************************************
	// FUNCTION DEFINITIONS
	// *****************************************

	// This simple function takes a LED number (0-7), in x0
	// and sets the RGB values for that LED to zero.
	// NOTE: the caller must still call show() in order to see the effects!!
setLEDoff:	
        // Save return address to stack -- needed to return from this function back to caller 
	sub     sp, sp,  16
	stur    lr, [sp, 0]

	// The LED# is already in x0, but set RGB values to zero (x1/x2/x3)
	mov	x1, 0      	// red   value
	mov	x2, 0  		// green value 
	mov	x3, 0		// blue  blue vaue
	bl 	set_pixel  

	// Reload return address and fix-up stack pointer
	ldur    lr, [sp, 0]
	add     sp, sp, 16

	// Actually return 
	br      lr

    	// For IC220, every function MUST end with a directive like this (tells the debugger where the function ends)
 	.size	setLEFoff, . - setLEDoff
	
	
	

	
	
	
