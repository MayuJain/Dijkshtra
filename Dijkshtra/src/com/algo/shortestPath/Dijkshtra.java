package com.algo.shortestPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dijkshtra {
       private int adj[][];
       private boolean directedGraph=false;
       private int size,edge;
       private int source;
       private int[] distance;
       private int[] path;

private int getInt(char c) {
       String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       return alphabet.indexOf(c);
      }

private char getChar(int i) {
       String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       return alphabet.charAt(i);
      }

public void input(String filename) throws IOException {
       FileReader fd=null;
   try {
       fd=new FileReader(new File(filename));
     } catch (FileNotFoundException e) {
     e.printStackTrace();
     }
   if(fd!=null) {
      BufferedReader br=new BufferedReader(fd);
      String line;
      if((line=br.readLine())!=null) {
              String[] arr=line.split(" ");
              size=Integer.parseInt(arr[0]);
              adj=new int[size][size];
              edge=Integer.parseInt(arr[1]);
              
   if(arr[2].equals("D")) {
	   directedGraph=true;
              }
   	//Retrieving all edges from file 
      for(int i=0;i<edge;i++) {   
                   line=br.readLine();
                   arr=line.split(" ");
                   adj[getInt(arr[0].charAt(0))][getInt(arr[1].charAt(0))]=Integer.parseInt(arr[2]);
    
   //For undirected Graph
   if(directedGraph!=true) {  
              adj[getInt(arr[1].charAt(0))][getInt(arr[0].charAt(0))]=Integer.parseInt(arr[2]);
             }
          }

      if((line=br.readLine())!=null) {
                   source=getInt(line.charAt(0)); //Taking the source vertex
            } 
      else { 
    	  source=0;
                }
           }
     }
}

//Displaying the graph
void displayGraph() {  
     for(int i=0;i<size;i++) {
    	 for(int j=0;j<size;j++) {
              System.out.print("\t"+adj[i][j]);
            }
           System.out.println();
         }
}

public void dijkstra() 
{
   distance=new int[size];  /*Store the distance from all vertices*/
   boolean[] visited=new boolean[size];

/* Initialize all distance as max value * Initialize all visited false*/
    for(int i=0;i<size;i++) {
    distance[i]=Integer.MAX_VALUE;
    visited[i]=false;
 }
 path=new int[size]; /* It store the whole path*/

/* Initialize source vertex distance 0 * Initialize source vertex path -1*/
 distance[source]=0;
 path[source]=-1;

/* Find shortest path for all vertices*/
 for(int i=0;i<size;i++){
              int nextVertex=-1;
              int shortestDistance=Integer.MAX_VALUE;
              for(int v=0;v<size;v++) {
                 if(visited[v]==false && distance[v]<shortestDistance) {
                    nextVertex=v;
                    shortestDistance=distance[v];}
                                        }
              if(nextVertex==-1) {
            	  for(int v=0;v<size;v++) {
            		  if(visited[v]=false) {
            			  nextVertex=v;
            			  visited[nextVertex]=true;
            			  continue;
            		  } 
            	  }
              }else {
            	  visited[nextVertex]=true;
            	  /* Update distance value*/
            	  for(int v=0;v<size;v++) {
            	    int edgeDistance=adj[nextVertex][v];
            	    if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < distance[v])) {
            	          path[v] = nextVertex;
            	         distance[v] = shortestDistance + edgeDistance;}
            	                         }
              }
            }
}


public void displayResult() {
           System.out.print("Vertex\t\t Distance\t\tPath");

   for (int i = 0; i < size; i++){
           if (i!= source){
           System.out.print("\n" + getChar(source) + " -> ");
           System.out.print(getChar(i) + " \t\t ");
           System.out.print(distance[i] + "\t\t\t");
        int v=i;
        String str="";
      while(path[v]!=-1) {
                 str=getChar(v)+" "+str;
                 v=path[v];
             }
           System.out.print(str);
        }
    }
}

public static void main(String[] args) throws IOException{
	

	Scanner reader = new Scanner(System.in);  // Reading from System.in
	System.out.println("Which type of Graph do you want to run? 1. Directed Graph 2. Undirected Graph ");
	int input = reader.nextInt(); // Scans the next token of the input as an int.
	//once finished
	String filename=null;
	if(input==1) {
		System.out.println("Which input file you want to use? 1. Input1_Directed.txt 2.Input2_Directed.txt 3.Input3_Directed.txt: ");
		int input1=reader.nextInt();
		switch(input1) {
		case 1:
			filename="Input1_Directed.txt";
			break;
		case 2:
			filename="Input2_Directed.txt";
			break;
		case 3:
			filename="Input3_Directed.txt";
			break;
		default:
			filename="Input1_Directed.txt";
			break;
		}
		
		
	}else if( input==2){
		System.out.println("Which input file you want to use? 1. Input1_Undirected.txt 2.Input2_Undirected.txt 3.Input3_Undirected.txt: ");
		int input2=reader.nextInt();
		switch(input2) {
		case 1:
			filename="Input1_Undirected.txt";
			break;
		case 2:
			filename="Input2_Undirected.txt";
			break;
		case 3:
			filename="Input3_Undirected.txt";
			break;
		default:
			filename="Input1_Undirected.txt";
			break;
		}
	}else {
		String message="Please mention correct file name.";
		try {
			throw new Exception(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	reader.close();
             Dijkshtra ob=new Dijkshtra();
             ob.input(filename);
             ob.displayGraph();
             final long startTime = System.nanoTime();
             ob.dijkstra();
             final long duration = System.nanoTime() - startTime;
               System.out.println("Time taken by Dijkstra's Algorithm - ");
               System.out.println(duration);
               System.out.println("\n");
             ob.displayResult();
     }
}
