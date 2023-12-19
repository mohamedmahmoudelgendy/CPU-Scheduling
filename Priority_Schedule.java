/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.scheduling;

import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Ayman
 */
// class to find Gantt chart
class Priority_Schedule {
    private Vector<Process> processes;
    private Vector<Boolean> isCompleted ; 
    int Time; 
    int completed ;
    int certenTime ;
    private double totalTurnaroundTime ; 
    private double totalWaitingTime ; 
 
    public Priority_Schedule(){
        this.processes = new Vector<Process>();
        this.isCompleted = new Vector<Boolean>();
        this.Time = 0;
        this.completed = 0;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
        this.certenTime = 10 ;
    }
    
    public Priority_Schedule(Vector<Process> processes) {
        this.processes = processes;
        this.isCompleted = new Vector<Boolean>(Collections.nCopies(processes.size(), false));
        this.Time = 0 ;
        this.completed = 0 ;
        this.totalTurnaroundTime = 0; 
        this.totalWaitingTime = 0;
        this.certenTime = 10 ;
    }
    
    public void addProcess(String name , int arrivalTime, int burstTime, int priorityNumber){
        Process process = new Process(name, arrivalTime, burstTime, priorityNumber);
        this.processes.add(process);
        this.isCompleted.add(false);
    }
     
    public void run(){        
        while (completed != processes.size()) { 
            int highestPriority = -1; 
            for (int i = 0; i < processes.size();i++){
                if (!isCompleted.get(i) && processes.get(i).getArrivalTime() <= Time) {
                    if (highestPriority == -1 || processes.get(i).getPriorityNumber() > processes.get(highestPriority).getPriorityNumber()){
                        highestPriority = i; 
                    }
                }
            }
            
            if (highestPriority == -1) { 
                Time++; 
                continue; 
            }
            
            if(Time > certenTime){
                int lowestPriority = -1; 
                for (int i = 0; i < processes.size();i++){
                    if (!isCompleted.get(i) && processes.get(i).getArrivalTime() <= Time) {
                        if (highestPriority == -1 || processes.get(i).getPriorityNumber() < processes.get(highestPriority).getPriorityNumber()){
                            lowestPriority = i; 
                        }
                    }
                }
                if(lowestPriority != highestPriority && lowestPriority != -1){
                    processes.get(lowestPriority).increasePriority();
                    certenTime = certenTime * 2 ;
                }
            }
            
            System.out.println("Time: "+ Time + " Process: " + processes.get(highestPriority).getName()+" is Working");
            Time += processes.get(highestPriority).getBurstTime();
            
            processes.get(highestPriority).setRemainingTime(0);
            processes.get(highestPriority).setCompletionTime(Time); 
            processes.get(highestPriority).setTurnAroundTime(processes.get(highestPriority).getCompletionTime() - processes.get(highestPriority).getArrivalTime()); 
            processes.get(highestPriority).setWaitingTime(processes.get(highestPriority).getTurnAroundTime() - processes.get(highestPriority).getBurstTime());
            isCompleted.set(highestPriority, true); 
            completed++;
            
            if(completed == processes.size())
                System.out.println("Time: "+Time+" Finished");
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
