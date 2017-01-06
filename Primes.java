import java.util.ArrayList; 
import java.lang.Math; 
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;

class Primes { 
	static ArrayList<Integer> primeNumbers = new ArrayList<>(); 

	public static void main (String[] args) {
		String primeUp = printPrimes(1000000); 
		writeToFile(primeUp);
	}

	/**
	* Writes a string toWrite to file.
	*/
	public static void writeToFile(String toWrite) {
		try {
			PrintWriter w = new PrintWriter("primelist.rff", "UTF-8"); 
			BufferedWriter writer = new BufferedWriter(w); 
			writer.write(toWrite); 
			writer.close(); 
		} catch (Exception e) {
			System.err.println(e.getMessage()); 
		}
	}

	/**
	* A simple boolean check to see if NUM is prime.
	*/
	public static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false; 
			}
		}
		return true; 
	}

	/**
	* Generates all primes up to integer X in the ArrayList.
	*/
	public static void genPrimes(int x) {
		int num = 1; 
		for (int i = 0; i < x; i++) {
			num += 1; 
			while(!isPrime(num)) {
				num += 1;
			}
			primeNumbers.add(num);
		}
	}

	/**
	* Prints the first n primes in the natural numbers.
	*/
	public static String printPrimes(int n) {
		genPrimes(n); 
		int count = 0; 
		String fin = ""; 
		for (int p: primeNumbers) {
			fin += p + " ";  
			count += 1; 
			if (count % 20 == 0) {
				fin += "\n";
			}
		}
		return fin; 
	}

	/**
	* Prints frequency of primes for buckets
	* [0, b], [b, 2b], [2b, 3b], ...
	* for the first n primes in the natural numbers
	*/

	public static void printFrequencies (int b, int n) {
		System.out.println("We generate the first " + n + " primes." 
			+ " Bucket size is: " + b + "."); 
		genPrimes(n); 
		int mult = 0; 
		int lb = mult * b; 
		int ub = (mult + 1) * b; 
		int bucketCount = 0; 
		int prevCount = 0; 
		int numDecr = 0; 
		int numIncr = 0; 
		for (int i = 0; i < n; i++) {
			int p = primeNumbers.get(i); 
			if (p >= ub) {
				String out = "[" + lb + ", " + ub + "] count: "; 
				out += bucketCount; 
				System.out.print(out); 
				mult += 1; 
				lb = mult * b; 
				ub = (mult + 1) * b; 
				boolean decreased = true; 
				if (mult != 0 && prevCount <= bucketCount) {
					decreased = false;
				}
				if (mult != 0) {
					if (decreased) {
						System.out.print(". DECREASED.\n"); 
						numDecr += 1; 
					}
					if (!decreased) {
						System.out.println(); 
						numIncr += 1;
					}
				}
				prevCount = bucketCount; 
				bucketCount = 0; 
			} else {
				bucketCount += 1; 
			}
		}
		System.out.println("" + numIncr + " increases and " + numDecr + " decreases."); 
	}
	

	public static void clearList() {
		primeNumbers = new ArrayList<>(); 
	}
	/**
	* Generates the distances for first x prime numbers.
	*/ 
	public static void printDistances(int x) {
		genPrimes(x + 1); 
		int count = 0; 
		for (int i = 1; i <= x; i++) {
			int dist = primeNumbers.get(i) - primeNumbers.get(i - 1); 
			count += dist; 
			System.out.print(dist + " ");
			if (i % 15 == 0) {
				double avg = (count/15); 
				count = 0; 
				System.out.println("Average: " + avg); 
			}
		}
	}
}