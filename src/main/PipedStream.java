package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PipedStream {
	public static void main(String[] args) {
		try {
			FileReader numbers = new FileReader("numeros.txt");
			Reader sortedNumbers = sort(numbers);
			BufferedReader br = new BufferedReader(sortedNumbers);
			String input;
			PrintWriter stdout = new PrintWriter(System.out, true);
			while ((input = br.readLine()) != null) {
				stdout.println(input);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("PipedStream: " + e);
		}
	}
	public static Reader sort(Reader source) {
		PipedWriter pw = null;
		PipedReader pr = null;
		try {
			BufferedReader br = new BufferedReader(source);
			pw = new PipedWriter();
			pr = new PipedReader(pw);
			PrintWriter output = new PrintWriter(pw);
			new SortThread(output, br).start();
		} catch (Exception e) {
			System.err.println("PipedStream sort: " + e);
		}
		return pr;
	}
}
