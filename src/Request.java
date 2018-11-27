import java.util.*;

public class Request
{
    public int arrivalTime;
    public int job;
    Random rand = new Random();

    
    
    //constructor for random jobs
    public Request(int arrivalTime, int job)
    {
        this.arrivalTime = arrivalTime;
        this.job = job;
    }
    //constructor for set / assignment description jobs
    public Request()
    {
        this.arrivalTime = rand.nextInt(30) + 1; // 1 through 30

        this.job = rand.nextInt(3)+1; // 1 through 3
    }

  
}