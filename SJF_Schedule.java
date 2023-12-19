/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.scheduling;

import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Medo
 */
public class SJF_Schedule {
    private Vector<Process> processes ;
    private Vector<Boolean> isCompleted ; 
    private int Time; 
    private int completed ;
    private int contextSwiching;
    private double totalTurnaroundTime ; 
    private double totalWaitingTime ; 
    
    public SJF_Schedule(){
        this.processes = new Vector<Process>();
        this.isCompleted = new Vector<Boolean>();
        this.Time = 0 ;
        this.completed = 0 ;
        this.contextSwiching = 0;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
    }

    public SJF_Schedule(Vector<Process> processes) {
        this.processes = processes;
        this.isCompleted = new Vector<Boolean>(Collections.nCopies(processes.size(), false));
        this.Time = 0 ;
        this.completed = 0 ;
        this.contextSwiching = 0 ;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
    }

    public void setContextSwiching(int contextSwiching) {
        this.contextSwiching = contextSwiching;
    }
    
    public void run(){
        int workingProcessIndex = -1 ;
       
        while (completed != processes.size()) { 
            int shortest= -1 ; 
            for (int i=0; i<processes.size();i++) { 
                if (!isCompleted.get(i) && processes.get(i).getArrivalTime() <= Time) { 
                    if (shortest == -1 || processes.get(i).getRemainingTime() < processes.get(shortest).getRemainingTime()) { 
                        shortest=i; 
                    } 
                } 
            } 
  
            if (shortest == -1) { 
                Time++; 
                continue; 
            }
            
            if(workingProcessIndex != shortest){
                workingProcessIndex = shortest ;
                System.out.println("Time: "+Time+ " Process: "+processes.get(workingProcessIndex).getName()+" is Working");
            }
           
            processes.get(shortest).setRemainingTime(0); 
            Time += processes.get(shortest).getBurstTime() ;
       
            if (processes.get(shortest).getRemainingTime() == 0) { 
                processes.get(shortest).setCompletionTime( Time ); 
                processes.get(shortest).setTurnAroundTime( processes.get(shortest).getCompletionTime() - processes.get(shortest).getArrivalTime()); 
                processes.get(shortest).setWaitingTime( processes.get(shortest).getTurnAroundTime() - processes.get(shortest).getBurstTime()); 
                isCompleted.set(shortest, true); 
                completed++; 
            } 

            Time+= contextSwiching ;
            
            if(completed == processes.size()){
                System.out.println("Time: "+(Time-contextSwiching)+" Finished");
            }
        } 
        
        System.out.println("\nProcess  Arrival Time  Burst Time  Completion Time  Turnaround Time  Waiting Time"); 
        for (int i = 0; i < processes.size(); i++) { 
            System.out.println(processes.get(i).getName() + "\t\t" + processes.get(i).getArrivalTime() + "\t    " + processes.get(i).getBurstTime() + "\t\t" + processes.get(i).getCompletionTime() + "\t\t" + processes.get(i).getTurnAroundTime() + "\t\t" + processes.get(i).getWaitingTime());
            totalTurnaroundTime += processes.get(i).getTurnAroundTime(); 
            totalWaitingTime += processes.get(i).getWaitingTime(); 
        } 
  
        System.out.println("\nAverage Turnaround Time: " + (totalTurnaroundTime / processes.size())); 
        System.out.println("Average Waiting Time: " + (totalWaitingTime / processes.size())); 
    
    }
    
}
