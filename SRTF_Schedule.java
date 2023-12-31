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
public class SRTF_Schedule {
    private Vector<Process> processes ;
    private Vector<Boolean> isCompleted ; 
    private int Time; 
    private int completed ;
    private double totalTurnaroundTime ; 
    private double totalWaitingTime ; 
 
    public SRTF_Schedule(){
        this.processes = new Vector<Process>();
        this.isCompleted = new Vector<Boolean>();
        this.Time = 0 ;
        this.completed = 0 ;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
    }

    public SRTF_Schedule(Vector<Process> processes) {
        this.processes = processes;
        this.isCompleted = new Vector<Boolean>(Collections.nCopies(processes.size(), false));
        this.Time = 0 ;
        this.completed = 0 ;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
    }
    
    public void addProcess(String name ,int arrivalTime, int burstTime,int priority){
        Process process = new Process(name,arrivalTime,burstTime,priority);
        this.processes.add(process);
        this.isCompleted.add(false);
    }
    
    
    public void run(){
        
        int workingProcessIndex = -1 , shortestBurstTime = processes.get(0).getBurstTime() ;
        
        for(int i = 0 ; i < processes.size() ;i++){
            if(shortestBurstTime > processes.get(i).getBurstTime()){
                shortestBurstTime = processes.get(i).getBurstTime();
            }
        }
        
        mainWhile:
        while (completed != processes.size()) { 
            
            if(Time % shortestBurstTime == 0 && Time != 0){
                for(int i = 0 ; i < shortestBurstTime && completed != processes.size();i++){
                    int longest = -1 ; 
                    for (int j = 0 ; j <processes.size();j++) { 
                        if (!isCompleted.get(j) && processes.get(j).getArrivalTime() <= Time) { 
                            if (longest == -1 || processes.get(j).getRemainingTime() > processes.get(longest).getRemainingTime()) { 
                                longest = j ; 
                            } 
                        } 
                    } 

                    if (longest == -1) { 
                        Time++; 
                        continue; 
                    } 

                    if(workingProcessIndex != longest){
                        workingProcessIndex = longest ;
                        System.out.println("Time: "+Time+ " Process: "+processes.get(workingProcessIndex).getName()+" is Working");
                    }

                    processes.get(longest).decreaseRemainingTime();
                    if (processes.get(longest).getRemainingTime() == 0) { 
                        processes.get(longest).setCompletionTime( Time + 1); 
                        processes.get(longest).setTurnAroundTime( processes.get(longest).getCompletionTime() - processes.get(longest).getArrivalTime()); 
                        processes.get(longest).setWaitingTime( processes.get(longest).getTurnAroundTime() - processes.get(longest).getBurstTime()); 
                        isCompleted.set(longest, true); 
                        completed++; 
                    } 
                    Time++ ;

                }
                
                if(completed == processes.size()){
                    System.out.println("Time: "+Time+" Finished");
                    break mainWhile ;
                }
                
            }
            
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
       
            processes.get(shortest).decreaseRemainingTime(); 
  
            if (processes.get(shortest).getRemainingTime() == 0) { 
                processes.get(shortest).setCompletionTime( Time + 1); 
                processes.get(shortest).setTurnAroundTime( processes.get(shortest).getCompletionTime() - processes.get(shortest).getArrivalTime()); 
                processes.get(shortest).setWaitingTime( processes.get(shortest).getTurnAroundTime() - processes.get(shortest).getBurstTime()); 
                isCompleted.set(shortest, true); 
                completed++; 
            } 
  
            Time++; 
            
            if(completed == processes.size()){
                System.out.println("Time: "+Time+" Finished");
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
