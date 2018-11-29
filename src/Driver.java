import java.util.*;

public class Driver 
{    
	/*
	 * If the program's output shows a job's name but not the contents of the shared buffer it is because the job after it 
	 * preempts said job and said job had no time to complete (arrival time of 2nd is < current time of preempted job). 
	 * 
	 * Joel Lechman
	 * CSCI 460 Fall 2018
	 */

	
	//random job requests
    private static Request[] randRequests = new Request[10];
	//static requests are the 7 set jobs from the assignment description
    private static Request[] nonRandRequests = new Request[7];
    //buffer or shared resource for 1 and 3
    private static int[] sharedBuffer = new int[3];
    
    
    
    //initalize time
    private static int nonRandJobTime = 0;
    private static int randomJobsTime = 0;

    //init one through three with a priority and name
    private static Job t1 = new Job(3, "T1");
    private static Job t2 = new Job(2, "T2");
    private static Job t3 = new Job(1, "T3");

    //helper method to print the queue of jobs
    public static void printJobs(Request[] jobs)
    {
        for(int i = 0; i < jobs.length; i++)
        {
            System.out.print(jobs[i].arrivalTime);
            System.out.print("(" + jobs[i].job + ")");
            if(i != jobs.length - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
    
    public static void main(String[] args)
    {
        //initialize the shared resource or buffer and the job requests (both static and random)
        setBuffer(0);
        initSetJobs();
        populateRandJobs();
        
        //start system time at the time that the first job arrives
        nonRandJobTime = nonRandRequests[0].arrivalTime;
        randomJobsTime = randRequests[0].arrivalTime;

        
        
        
        
        //Complete set jobs
        System.out.println("Set Jobs:");
        for(int i = 0; i < nonRandRequests.length; i++)
        {
            //sometimes a job will complete before the next request
            if(nonRandJobTime < nonRandRequests[i].arrivalTime)
            {
                nonRandJobTime = nonRandRequests[i].arrivalTime;
            }

            if(nonRandRequests[i].job == 1)	//if job 1
            {
                setBuffer(1);
                System.out.print("Time = " + nonRandJobTime + ", " + "(" + t1.name + ")");
                for(int j = 0; j < sharedBuffer.length; j++)
                {
                    System.out.print(sharedBuffer[j]);
                }
                System.out.print("(" + t1.name + ")");
                System.out.println("");
                nonRandJobTime += 3; //t1 is always +3 time increments
            }
            else if(nonRandRequests[i].job == 2) //if job 2 'N'
            {
                //preemption handling
                if(i + 1 < nonRandRequests.length)
                {
                    if(nonRandRequests[i+1].job == 1 && nonRandRequests[i+1].arrivalTime < nonRandJobTime + 10)
                    {
                        int preempt = nonRandRequests[i+1].arrivalTime - nonRandJobTime;
                        System.out.print("Time = " + nonRandJobTime + ", " + "(" + t2.name + ")");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");


                        if(preempt > 0)
                        {
                            nonRandJobTime += preempt; //only add time before preemption
                        }
                    }
                    else
                    {
                        System.out.print("Time = " + nonRandJobTime + ", " + "(" + t2.name + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");

                        nonRandJobTime += 10;
                    }
                }
                else
                {
                    System.out.print("Time = " + nonRandJobTime + ", " + "(" + t2.name + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");

                        nonRandJobTime += 10;
                }
            }
            else if(nonRandRequests[i].job == 3) //if job 3 
            {
                setBuffer(3);
                if(i + 1 < nonRandRequests.length)
                {
                    if(nonRandRequests[i+1].job == 2 && nonRandRequests[i+1].arrivalTime < nonRandJobTime + 3)
                    {
                        int preempt = nonRandRequests[i+1].arrivalTime - nonRandJobTime;
                        System.out.print("Time = " + nonRandJobTime + ", " + "(" + t3.name + ")");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");

                        
                 
                        if(preempt > 0)
                        {
                            nonRandJobTime += preempt; //only add time completed before preemption
                        }
                    }
                    else
                    {
                        System.out.print("Time = " + nonRandJobTime + ", " + "(" + t3.name + ")");
                        for(int j = 0; j < sharedBuffer.length; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");

                        nonRandJobTime += 3; //t3 is always +3 time increments
                    }
                }
                else
                {
                    System.out.print("Time = " + nonRandJobTime + ", " + "(" + t3.name + ")");
                        for(int j = 0; j < sharedBuffer.length; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");

                        nonRandJobTime += 3; //t3 is always +3 time increments
                }
            }
        }
        System.out.println();

        
        
        
        
        
        
        
        
        //Complete random jobs
        System.out.println("Random Jobs");
        for(int i = 0; i < randRequests.length; i++)
        {
            //sometimes a job will complete before the next request
            if(randomJobsTime < randRequests[i].arrivalTime)
            {
                randomJobsTime = randRequests[i].arrivalTime;
            }

            if(randRequests[i].job == 1) //if job 1
            {
                setBuffer(1);
                System.out.print("Time: " + randomJobsTime + ", " + "(" + t1.name + ")");
                for(int j = 0; j < sharedBuffer.length; j++)
                {
                    System.out.print(sharedBuffer[j]);
                }
                System.out.print("(" + t1.name + ")");
                System.out.println("");
                randomJobsTime += 3;
            }
            else if(randRequests[i].job == 2) //if job 2 "N"
            {
                //preemption handling
                if(i + 1 < randRequests.length)
                {
                    if(randRequests[i+1].job == 1 && randRequests[i+1].arrivalTime < randomJobsTime + 10)
                    {
                        int preemptTime = randRequests[i+1].arrivalTime - randomJobsTime;
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.name + ")");

                        for(int j = 0; j < preemptTime; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");


                      
                        if(preemptTime > 0)
                        {
                            randomJobsTime += preemptTime;//only add time completed before preemption
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.name + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");

                        randomJobsTime += 10;
                    }
                } 
                else
                {
                    System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.name + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.name + ")");
                        System.out.println("");

                        randomJobsTime += 10;
                }
            }
            else if(randRequests[i].job == 3) //if job 3
            {
                setBuffer(3);
                //preemption handling
                if(i + 1 < randRequests.length)
                {
                    if(randRequests[i+1].job == 2 && randRequests[i+1].arrivalTime < randomJobsTime + 3)
                    {
                        int preemptTime = randRequests[i+1].arrivalTime - randomJobsTime;
                        
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.name + ")");
                        
                        for(int j = 0; j < preemptTime; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");


                        if(preemptTime > 0)
                        {
                            randomJobsTime += preemptTime; //only add time completed before preemption
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.name + ")");
                        for(int j = 0; j < sharedBuffer.length; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");
                        randomJobsTime += 3; //t3 is always +3 increments of time
                    }
                }
                else
                {
                    System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.name + ")");
                        for(int j = 0; j < sharedBuffer.length; j++)
                        {
                            System.out.print(sharedBuffer[j]);
                        }
                        System.out.print("(" + t3.name + ")");
                        System.out.println("");
                        randomJobsTime += 3;//t3 is always +3 increments of time
                }
            }
        }
    }



    //helper method to add job requests to the job array and sort the array
    public static void populateRandJobs()
    {
        //requests will be populated with random values
        for(int i = 0; i < randRequests.length; i++)
        {
            randRequests[i] = new Request();
        }

        
        //requests will then be sorted by time
        //implemented with bubble sort
        for(int a = 0; a < randRequests.length; a++)
        {
            for(int b =  a + 1; b < randRequests.length; b++)
            {
                if(randRequests[a].arrivalTime > randRequests[b].arrivalTime)
                {
                    Request temp = new Request();
                    temp = randRequests[a];
                    randRequests[a] = randRequests[b];
                    randRequests[b] = temp;
                }
            }
        }     
        System.out.println("Random Jobs AFTER sorting:");
        printJobs(randRequests);
        System.out.println();
    }

    
    
    //(2) For the following sequence of input print out your output: < 1, 3 >,< 3, 2 >,<6, 3 >,< 8, 1 >,< 10, 2 >,< 12, 3 >,< 26, 1 >.
    public static void initSetJobs()
    {
    	//below are the set non-random jobs from the assignment description.
        nonRandRequests[0] = new Request(1, 3);
        nonRandRequests[1] = new Request(3, 2);
        nonRandRequests[2] = new Request(6, 3);
        nonRandRequests[3] = new Request(8, 1);
        nonRandRequests[4] = new Request(10, 2);
        nonRandRequests[5] = new Request(12, 3);
        nonRandRequests[6] = new Request(26, 1);
        System.out.println("Set Jobs (from assignment description):");
        printJobs(nonRandRequests);
        System.out.println();
    }
    
    //helper method to set values in the buffer (the shared resource for 1 and 3) to the number currently using it
    public static void setBuffer(int value)
    {
        for(int i = 0; i < sharedBuffer.length; i++)
        {
            sharedBuffer[i] = value;
        }
    }
}






