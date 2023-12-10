/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.scheduling;

import java.util.Vector;

/**
 *
 * @author Medo
 */
public class SRTF_Schedule {
    private Vector<Process> processes ;
    private Vector<Boolean> isCompleted ; 
    int Time; 
    int completed ;
 
    public SRTF_Schedule(){
        this.processes = new Vector<Process>();
        this.isCompleted = new Vector<Boolean>();
        this.Time = 0 ;
        this.completed = 0 ;
    }
    
    public void addProcess(String name ,String color, int arrivalTime, int burstTime){
        Process process = new Process(name,color,arrivalTime,burstTime,1);
        this.processes.add(process);
        this.isCompleted.add(false);
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
       
            processes.get(shortest).decreaseRemainingTime(); 
  
            if (processes.get(shortest).getRemainingTime() == 0) { 
                processes.get(shortest).setCompletionTime( Time + 1); 
                processes.get(shortest).setTurnaroundTime( processes.get(shortest).getCompletionTime() - processes.get(shortest).getArrivalTime()); 
                processes.get(shortest).setWaitingTime( processes.get(shortest).getTurnaroundTime() - processes.get(shortest).getBurstTime()); 
                isCompleted.set(shortest, true); 
                completed++; 
            } 
  
            Time++; 
            
            if(completed == processes.size()){
                System.out.println("Time: "+Time+" Finished");
            }
        } 
  
        double totalTurnaroundTime = 0; 
        double totalWaitingTime = 0; 
        
        System.out.println("\nProcess  Arrival Time  Burst Time  Completion Time  Turnaround Time  Waiting Time"); 
        for (int i = 0; i < processes.size(); i++) { 
            System.out.println(processes.get(i).getName() + "\t\t" + processes.get(i).getArrivalTime() + "\t    " + processes.get(i).getBurstTime() + "\t\t" + processes.get(i).getCompletionTime() + "\t\t" + processes.get(i).getTurnaroundTime() + "\t\t" + processes.get(i).getWaitingTime());
            totalTurnaroundTime += processes.get(i).getTurnaroundTime(); 
            totalWaitingTime += processes.get(i).getWaitingTime(); 
        } 
  
        System.out.println("\nAverage Turnaround Time: " + (totalTurnaroundTime / processes.size())); 
        System.out.println("Average Waiting Time: " + (totalWaitingTime / processes.size())); 
    
    }
}
