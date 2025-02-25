
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
	.string "Welcome to Simon!"
strWhatRounds:
	.string "Enter a number (max rounds): "
strWhatSeed:
	.string "Enter a number (seed): "
	
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
	adr	x0, strHello
	bl	puts
	
	// Set a default for 'maxRounds' and 'seed' (may be over-written below)
	// Note that we store these variables into a safe location (a "saved"/"preseved" register), so they don't get clobbered,
	// but if a function needs these values, we must PASS these values as arguments (in x0, x1, etc.)
	// E.g., functions may NOT access 'maxRounds' by directly looking at x20!!!!!
	mov	x20, 5   // maxRounds = 5
	mov	x19, 1   // seed      = 1

	// ********************************************************************
	//      LEAVE THIS SECTION HERE FOR LATER USE (start of section)
	// ********************************************************************
	// Get game settings from user:  maxRounds and seed.
	// HOWEVER, leave this commented out for now, until the project instructions tell you to change it.
	// For now, just use the value of maxRounds that is already set above (in x20)
	//    and the 'seed' value set above (in x19)
	//
	// Get max rounds from user
	//adr 	x0, strWhatRounds
	//bl	puts
	//bl 	helperGetLong
	//mov	x20, x0       	// x20 = maxRounds	
	// Get random seed from user, use for srand()
	//adr 	x0, strWhatSeed
	//bl	puts
	//bl 	helperGetLong
	//mov	x19, x0         // x19 = seed
	// ********************************************************************
	//      LEAVE THIS SECTION HERE FOR LATER USE (end of section)
	// ********************************************************************

	// Initialize random number generator
	mov 	x0, x19   // set argument = 'seed' (seed value set above)
	bl	srand
	
	// Calling nobufin() changes the terminal so that getchar() will immediately give us
	// the typed character, without waiting for <Enter>
	// We want to do this AFTER possible calls to helperGetLong() above
	bl 	nobufin 

	
	
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


	// MaxRounds = 5
	mov x0, 5

	bl	createAndFillArray

	// Save arrayPointer
	stur x0,[sp, 8]
	

	ldur x0,[x0, 0] // x0 = arrayPointer[0]
	bl	helperPrintLong
	

	ldur x0,[sp, 8]
	ldur x0,[x0, 8] // x0 = arrayPointer[1]
	bl	helperPrintLong

	ldur x0,[sp, 8]
	ldur x0,[x0, 16] // x0 = arrayPointer[2]
	bl	helperPrintLong

	ldur x0,[sp, 8]
	ldur x0,[x0, 24] // x0 = arrayPointer[3]
	bl	helperPrintLong	

	ldur x0,[sp, 8]
	ldur x0,[x0, 32] // x0 = arrayPointer[4]
	bl	helperPrintLong

	adr x0, strNewline
	bl	puts	

	
	// move arguments for Show call
	ldur x0,[sp, 8]
	mov x1, 5

	bl	showLEDsFromArray
	

	


	
	// *************************************
	// Exit from main()
	// *************************************
Exit:	
	// Reload return address and fix-up stack pointer
	ldur    lr,[sp, 0]
	add     sp, sp, 16

	// Actually return from main(), with return value 0
	br      lr
	
	.size	main,  . - main     // tell gdb where this function ends


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
	.size	setupIC220,  . - setupIC220     // tell gdb where this function ends
	
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
	mov	x3, 0		// blue  blue vaue
	bl 	set_pixel  

	// Reload return address and fix-up stack pointer
	ldur    lr, [sp, 0]
	add     sp, sp, 16

	// Actually return 
	br      lr
	.size	setLEDoff,  . - setLEDoff     // tell gdb where this function ends



createAndFillArray:

	// Allocate Memory
	sub sp, sp, 32

	// x1 = ii = 0
	mov x1, 0

	// Save Important Registers onto Stack
	stur lr,[sp, 0] // Save LR
	stur x0,[sp, 8] // Save Array Length
	stur x1,[sp, 16] // Save ii
	
	// x0 = arrayLength * 8
	lsl x0, x0, 3

	// Call Malloc
	bl	malloc

	// Save Array Pointer on Stack
	stur x0,[sp, 24]

forLoop:

	// Reload Registers From Stack
	ldur x0,[sp, 8] // load arrayLength
	ldur x1,[sp, 16] // load ii
	
	// For Loop Check
	subs x8, x1, x0
	cbz x8, Leave

	// Rand & 3
	bl	rand
	and  x0, x0, 3 // rand & 3
	
	// Reload registers
	ldur x1,[sp, 16] // x1 = ii
	ldur x2,[sp, 24] // x2 = ap

	lsl x1, x1, 3 // ii * 8
	add x1, x1, x2 // x1 = ii * 8 + AP

	// Store final Value
	stur x0,[x1, 0]

	// ii++
	ldur x1,[sp, 16]
	add x1, x1, 1
	stur x1,[sp, 16]
	
	// For Loop
	bl	forLoop	

Leave:

	// Save final result in x0
	ldur x0,[sp, 24]

	// Load LR
	ldur lr,[sp, 0]

	// Deallocate Memory
	add sp, sp, 32

	// Return
	br	lr	


showLEDsFromArray:
	
	// Allocate Memory
	sub sp, sp, 48
	
	// Save Arguements & LR
	stur lr,[sp, 0]
	stur x0,[sp, 8] // save Array Pointer
	stur x1,[sp, 16] // save arrayLength

	// Make & Save Counter
	mov x2, 0
	stur x2,[sp, 24]

showForLoop:
	
	// Load II & Array Length
	ldur x0,[sp, 16] // x0 = arrayLength
	ldur x1,[sp, 24] // x1 = ii	

	subs x8, x1, x0
	cbz x8, showLeave

	// Load Pointer in x2
	ldur x2,[sp, 8]

	// LED = arrayPointer[ii]
	lsl x1, x1, 3 // ii * 8
	add x1, x1, x2 // ii * 8 + ArrayPointer
	ldur x0,[x1, 0] // LED = arrayPointer[ii]

	// Save LED into stack
	stur x0,[sp, 32]
	
	// Move Arguments Into registers
	mov x1, 0
	mov x2, 0
	mov x3, 255

	bl	set_pixel
	bl	show

	// Pause for 0.5 seconds
	mov x0, 976
	lsl x0, x0, 9
	bl	usleep
	
	// load and move arguments for next call
	ldur x0,[sp, 32]
	mov x1, 0
	mov x2, 0
	mov x3, 0

	bl	set_pixel
	bl	show

	// Pause for 0.25 seconds
	mov x0, 976
	lsl x0, x0, 8
	bl	usleep

	// increment ii
	ldur x0,[sp, 24]
	add x0, x0, 1
	stur x0,[sp, 24]

	bl	showForLoop

showLeave:
	
	// Load Lr
	ldur lr,[sp, 0]

	// Deallocate mem
	add sp, sp, 48

	br	lr



	

	
	

	
























