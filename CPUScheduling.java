/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cpu.scheduling;

import java.util.Scanner;

/**
 *
 * @author Medo
 */
public class CPUScheduling {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SRTF_Schedule srtfSchedule = new SRTF_Schedule();
        
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter the number of processes: "); 
        int n = input.nextInt(); 
        input.nextLine();
        
        for (int i = 0; i < n; i++) { 
            int arrivalTime,burstTime,priority;
            String name ,color ;
            System.out.println("Enter details for process " + (i + 1)); 
            System.out.print("Process Name: "); 
            name = input.nextLine(); 
            System.out.print("Process Color: "); 
            color = input.nextLine(); 
            System.out.print("Arrival time: "); 
            arrivalTime = input.nextInt(); 
            System.out.print("Burst time: "); 
            burstTime = input.nextInt(); 
            System.out.print("Process Priority Number: "); 
            priority = input.nextInt(); 
            input.nextLine();
            srtfSchedule.addProcess(name, color, arrivalTime,burstTime);
        }
        srtfSchedule.run();
    }         
    
}
    

