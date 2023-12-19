/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cpu.scheduling;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Medo
 */
public class CPUScheduling {
    private static Vector<Process> processes1 = new Vector<Process>() ;
    private static Vector<Process> processes2 = new Vector<Process>() ;
    private static Vector<Process> processes3 = new Vector<Process>() ;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); 
        System.out.print("Enter the Number of Processes: "); 
        int n = input.nextInt(); 
        System.out.print("Enter the Context Switching: "); 
        int context = input.nextInt(); 
        input.nextLine();
        
        for (int i = 0; i < n; i++) { 
            System.out.println("Enter details for process " + (i + 1)); 
            System.out.print("Process Name: "); 
            String name = input.nextLine(); 
            System.out.print("Arrival time: "); 
            int arrivalTime = input.nextInt(); 
            System.out.print("Burst time: "); 
            int burstTime = input.nextInt(); 
            System.out.print("Process Priority Number: "); 
            int priority = input.nextInt(); 
            input.nextLine();
            Process process1 = new Process(name,arrivalTime,burstTime,priority);
            Process process2 = new Process(name,arrivalTime,burstTime,priority);
            Process process3 = new Process(name,arrivalTime,burstTime,priority);
            processes1.add(process1);
            processes2.add(process2);
            processes3.add(process3);
        }

        System.out.print("1.SJF\n2.SRTF\n3.Priority\n4.AG\n5.all\nEnter your choice: ");
        int answer = input.nextInt() ;
        
        if(answer == 1 || answer == 5){
            System.out.println("\n              SJF Schedule");
            SJF_Schedule sjfSchedule = new SJF_Schedule(processes1);
            sjfSchedule.setContextSwiching(context);
            sjfSchedule.run();
        }
        
        if(answer == 2 || answer == 5){
            System.out.println("\n              SRTF Schedule");
            SRTF_Schedule srtfSchedule = new SRTF_Schedule(processes2);
            srtfSchedule.run();
        }
        
        if(answer == 3 || answer == 5){
            System.out.println("\n              priority Schedule");
            Priority_Schedule prioritySchedule = new Priority_Schedule(processes3) ;
            prioritySchedule.run();
        }
    }
}
