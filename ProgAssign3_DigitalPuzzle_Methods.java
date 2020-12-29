/* Description: This program reads a 10 digit number entered by the user, validates that each number 0 - 9 appears once in the number entered
 * 				and that the number is a 10 digits long. If valid, the program prompts the user to re-enter a valid number. Once valid, the 
 * 				program determines the number of divisions possible for where the divisor n equals the left most n digits. The number of divisions
 * 				is displayed on the console. 
 * Author: Jeremy Reinert
 * Date: 11/6/2018
 * Version: 1.0
 * 
 * STEP 1: Prompt user and read a 10 digit number input
 * STEP 2: Validate input - 
 * 		2a: Must be 10 digit #
 * 		2b: Must contain numbers 0 - 9 exactly once
 * 		2c: If invalid, prompt user to reenter a valid number
 * STEP 3: Determine number of divisions possible and display to console
 * 		3a: firstDigit % 1 == 0, firstTwoDigits % 2 == 0, firstThreeDigits % 3 == 0, .... allTenDigits % 10 == 0
 * STEP 4: Prompt user to input an integer 1 to test a new number or an integer 0 to exit the program
 * 		4a: Check if user entered an integer 1 and if so, prompt user and read a 10 digit number input
 * 		4b: if user entered an integer 0, display "Thank you, exit the loop, and end the program
 */

//Import the Java Scanner
import java.util.Scanner;
public class ProgAssign3_DigitalPuzzle_Methods {
	
	//Declare/initialize global scanner variable to read input throughout all methods in program
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// Declare Variables
		long inputUserNumber;
		boolean isTenDigits, isUniqueDigits;
		int numOfDivisions, enterNewNum;
		
		// Initialize enterNewNumber to 1 for first loop through while loop below. User will be prompted at end of the loop to enter a number to enter a new number to be tested.
		enterNewNum = 1;
		
		// STEP 1:
		// Call readInputNumber method to read user input
		inputUserNumber = readInputNumber();
		
		while(enterNewNum != 0) {
			
			//STEP 2:
			// Initialize isTenDigits to isTenDigit method
			isTenDigits = isTenDigit(inputUserNumber);
			
			// If isTenDigit method returns false, prompt user to enter a new number
			while(!isTenDigits) {
				// Prompt user to enter a new 10 digit number
				System.out.println("ERROR: The number you entered was not a 10 digit number.");
				inputUserNumber = readInputNumber();
				// Call isTenDigit again to check if new number entered is 10 digits and if true is returned from the method
				isTenDigits = isTenDigit(inputUserNumber);
			}
			
			// Initialize isUniqueDigits to isUnique method
			isUniqueDigits = isUnique(inputUserNumber);
			
			// If isUnique returns false, prompt user to enter a new number
			while(!isUniqueDigits) {
				System.out.println("ERROR: The number you entered contained two or more instances of the same number 0 - 9.");
				inputUserNumber = readInputNumber();
				
				// Check if new number entered is 10 digits -- If isTenDigit method returns false, prompt user to enter a 10 digit number
				isTenDigits = isTenDigit(inputUserNumber);
				while(!isTenDigits) {
					System.out.println("ERROR: The number you entered was not a 10 digit number.");
					inputUserNumber = readInputNumber();
					isTenDigits = isTenDigit(inputUserNumber);
				}
				
				// Call isUnique to check if 10 digit number entered above one instance of numbers 0 - 9
				isUniqueDigits = isUnique(inputUserNumber);
			}
			
			// STEP 3:
			// Call numDivisions to determine number of divisions that can be performed on number entered
			numOfDivisions = numDivisions(inputUserNumber);
			
			// Print out the number of divisions that can be performed on the number entered
			System.out.println("There were " + numOfDivisions + " divisions that were performed on the number you entered where the leftmost n digits is divisible by n.");
			
			// STEP 4:
			// Call enterNewNumber to read user input regarding whether they want to enter a new number or not
			enterNewNum = enterNewNumber();
			
			// Check if int returned from enterNewNumber method is a 1 to prompt user to enter a new 10 digit number -- if not, print "Thank You."
			if(enterNewNum == 1) {
				// Read new 10 digit number to be tested
				inputUserNumber = readInputNumber();
			}
			else if(enterNewNum == 0) {
				System.out.println("Thank you.");
			}
			else {
				System.out.println("ERROR: You did not enter a 1 or 0.");
			}
		}
		
	}
	
	// Method to read input number and return the number to the main method
	public static long readInputNumber() {
		// Declare Variables
		long inputUserNumber;
		
		// Prompt user input
		System.out.println("Please enter a 10 digit number that contains the numbers 0 - 9. Each number 0 - 9 should only be used once in the 10 digit number.");
		inputUserNumber = input.nextLong();
				
		// Return inputUserNumber to main method
		return inputUserNumber;
	}
	
	// Method to validate if input number is 10 digits and return boolean value to main method
	public static boolean isTenDigit(long inputNumber) {
		// Declare Variables
		int digitCount;
		long inputNumber2;
		boolean isTenDigits;
		
		// Initialize Variables
		digitCount = 0;
		
		
		// Validate that inputNumber is equal to 10 digits by removing digits from the number entered and incrementing digitCount for each digit separated
		while(inputNumber != 0) {
			inputNumber2 = inputNumber % 10;
			inputNumber /= 10;
										
			switch((int)inputNumber2) {
				case 0: digitCount++;
						break;
				case 1: digitCount++;
						break;
				case 2: digitCount++;
						break;
				case 3: digitCount++;
						break;
				case 4: digitCount++;
						break;
				case 5: digitCount++;
						break;
				case 6: digitCount++;
						break;
				case 7: digitCount++;
						break;
				case 8: digitCount++;
						break;
				case 9: digitCount++;
						break;
			}
		}
		
		// Assign boolean value to isTenDigits
		isTenDigits = digitCount == 10 ? true : false;
		
		// Return isTenDigits boolean to main method
		return isTenDigits;
		
	}
	
	// Method to validate if input number contains the numbers 0 - 9 once each and return boolean value to main method
	public static boolean isUnique(long inputNumber) {
		// Declare Variables
		boolean isUniqueDigits;
		int countZeroes, countOnes, countTwos, countThrees, countFours, countFives, countSixes, countSevens, countEights, countNines;
		long inputNumber2;
		
		// Initialize Variables
		countZeroes = countOnes = countTwos = countThrees = countFours = countFives = countSixes = countSevens = countEights = countNines = 0;
		
		// Increment corresponding number count variable for each instance of the number in the 10 digit number entered
		while(inputNumber != 0) {
			inputNumber2 = inputNumber % 10;
			inputNumber /= 10;
										
			switch((int)inputNumber2) {
				case 0: countZeroes++;
						break;
				case 1: countOnes++;
						break;
				case 2: countTwos++;
						break;
				case 3: countThrees++;
						break;
				case 4: countFours++;
						break;
				case 5: countFives++;
						break;
				case 6: countSixes++;
						break;
				case 7: countSevens++;
						break;
				case 8: countEights++;
						break;
				case 9: countNines++;
						break;
			}
		}
		
		// Assign boolean value to isUniqueDigits
		isUniqueDigits = countZeroes == 1 && countOnes == 1 && countTwos == 1 && 
				countThrees == 1 && countFours == 1 && countFives == 1 && countSixes == 1 && 
				countSevens == 1 && countEights == 1 && countNines == 1 ? true : false;
		
		// Return isUniqueDigits to main method. 
		return isUniqueDigits;
	}
	
	// Method to count the number of digits entered and return the digit count to numDivisions method for calculations
	public static int numDigits(long inputNumber) {
		// Declare Variables
		int digitCount;
		long inputNumber2;
		
		// Initialize Variables
		digitCount = 0;
				
		// Validate that inputNumber is equal to 10 digits by removing digits from the number entered and incrementing digitCount for each digit separated
		while(inputNumber != 0) {
			inputNumber2 = inputNumber % 10;
			inputNumber /= 10;
										
			switch((int)inputNumber2) {
				case 0: digitCount++;
						break;
				case 1: digitCount++;
						break;
				case 2: digitCount++;
						break;
				case 3: digitCount++;
						break;
				case 4: digitCount++;
						break;
				case 5: digitCount++;
						break;
				case 6: digitCount++;
						break;
				case 7: digitCount++;
						break;
				case 8: digitCount++;
						break;
				case 9: digitCount++;
						break;
			}
		}
		
		// Return isTenDigits boolean to main method
		return digitCount;
		
	}
	
	// Method to calculate the number of divisions possible where the leftmost n digits is divisible by n and return the division count to the main method
	public static int numDivisions(long inputNumber) {
		// Declare Variables
		int digitCount, divisibleCount;
		long isDivisible;
		
		//Initialize Variables
		divisibleCount = 0;
		digitCount = numDigits(inputNumber);
		
		// Determine number of divisions where the left most n digits is divisible by n
		while (inputNumber != 0) {
			isDivisible = inputNumber % digitCount;
			inputNumber /= 10;
			
			switch ((int)isDivisible) {
				case 0: divisibleCount++;
						break;
			}
			digitCount--;
		}
		
		// Return divisibleCount (number of divisions) to the main method
		return divisibleCount;
	}

	// Method to read input number to determine if user wants to enter a new number or end the program and return the number to the main method
	public static int enterNewNumber() {
		// Declare Variable
		int enterNewNum;
		
		// Prompt user input
		System.out.println("Do you want to enter a new number to be tested?");
		System.out.println("Please enter a 1 to test a new number or 0 to exit the program.");
		enterNewNum = input.nextInt();
		
		// Return inputUserNumber to main method
		return enterNewNum;
	}
}
