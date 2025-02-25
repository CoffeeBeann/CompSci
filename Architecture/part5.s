
	// Note -- this file relies upon other files, including:
	//   blinkt.c (for set_pixel(), show() )
	//   helper.s (for helperGetLong(), helperPrintLong() )
	
	// ********************
	// Specify the instruction set to generate for
	.arch armv8-a

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
	// Allocate Memory
	sub     sp, sp,  16
	stur    lr,[sp, 0]

	// Initialize the LEDs
	bl	start
	
	// Initialize LED Num
	mov x19, 0

	// Initialize Starting Colors
	mov x20, 255 // Red
	mov x21, 0 // Green
	mov x22, 0 // Blue	
	
	// Set Color Tracker to x4
	mov x4, 1

whileLoop:

	// Set LED Colors
 	mov	x0, x19  	// LED #
	mov	x1, x20	  	// red   value
	mov	x2, x21  	// green value 
	mov	x3, x22		// blue  blue vaue
	bl	set_pixel       // call set_pixel
	
	// Update LEDS
	bl	show
	
	// Pause using nested for loops
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
	subs xzr, x2, 20
	b.gt loop1
	add x2, x2, 1
	bl loop2

exit:

	// Increment LED Num
	add x19, x19, 1

	// Ensuring LED <= 7
	subs xzr, x19, 7
	b.le whileLoop
	mov x19, 0
	
	// change color Tracker
	add x4, x4, 1
	subs xzr, x4, 7
	b.le skipTrackReset
	mov x4, 1	
	
skipTrackReset:

	// Check if color is 1 (red)
	subs xzr, x4, 1
	b.eq setRed
	
	
	// Check if color is 2 (green)
	subs xzr, x4, 2
	b.eq setGreen

	// Check if color is 3 (blue)
	subs xzr, x4, 3
	b.eq setBlue

	// Check if color is 4 (purple)
	subs xzr, x4, 4
	b.eq setPurp

	// Check if color is 5 (yellow)
	subs xzr, x4, 5
	b.eq setYellow

	// Check if color is 6 (orange)
	subs xzr, x4, 6
	b.eq setOrange
	
	// Set LED off
	mov x20, 0
	mov x21, 0
	mov x22, 0	
	bl	whileLoop

setRed:
	// Set Color to Red
	mov x20, 255
	mov x21, 0
	mov x22, 0
	bl	whileLoop

setGreen:

	// Set Color to Green
	mov x20, 0
	mov x21, 255
	mov x22, 0
	bl	whileLoop

setBlue:
	// Set Color to Blue
	mov x20, 0
	mov x21, 0
	mov x22, 255
	bl	whileLoop

setPurp:
	// Set Color to Purple
	mov x20, 255
	mov x21, 0
	mov x22, 255
	bl	whileLoop

setYellow:
	// Set Color to Yellow
	mov x20, 255
	mov x21, 255
	mov x22, 0
	bl	whileLoop

setOrange:
	// Set Color to Orange
	mov x20, 255
	mov x21, 165 
	mov x22, 0
	bl	whileLoop
	
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
	
	
	

	
	
	
