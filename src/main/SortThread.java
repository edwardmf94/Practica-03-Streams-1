package main;

import java.io.*;

public class SortThread extends Thread {
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public SortThread(PrintWriter out, BufferedReader in) {
		this.out = out;
		this.in = in;
	}
	
	public void run() {
		int MAXCOUNT = 1000;
		if (out != null && in != null) {
			try {
				int[] list = new int[MAXCOUNT];
				int count = 0;
				for (String line = in.readLine(); line != null; line = in.readLine()) {
					//System.out.println(line);
					list[count]=Integer.parseInt(line);
					count++;
			    }
				out.println("INICIA PROCESO DE ORDENAMIENTO ASCENDENTE");
				quicksort(list, 0, count-1);
				for (int i = 0; i < count; i++)
					out.println(list[i]);
				out.println("INICIA PROCESO DE ORDENAMIENTO DESCENDENTE");
				sortDesc(list, count-1);
				for (int i = 0; i < count; i++)
					out.println(list[i]);
				out.close();
			} catch (IOException e) {
				System.err.println("SortThread run: " + e);
			}
		}
	}
	
	public static void quicksort(int A[], int left, int right) {

		int piv=A[left];
		int i=left;
		int j=right;
		int aux;
		 
		while(i < j){                                   
			while(A[i] <= piv && i < j) i++;
			while(A[j] > piv) j--;
			if (i < j) {                      
				aux= A[i];
				A[i]=A[j];
				A[j]=aux;
			}
		}
		   
		A[left]=A[j];                                    
		A[j]=piv;
		   
		if(left < j-1)
			quicksort(A,left,j-1);
		if(j+1 < right)
			quicksort(A,j+1,right);
		   
	}
	
	public static void sortDesc(int A[], int _count){
		int[] b = A.clone();
		int count=0;
		for (int i = _count; i >= 0; i--) {
			A[count]=b[i];
			count++;
		}
	}
}
