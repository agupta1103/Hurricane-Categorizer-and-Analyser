
/**
 * Write a description of class HurricaneSelector here.
 *
 * @author Anish Gupta
 * @version 7/25/21
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class HurricaneSelector 
{   
    public static void main(String[] args) throws IOException
    {
        File fileName = new File("hurricanedata.txt");
        Scanner inFile = new Scanner(fileName);
        
        double knotsToMiles = 1.15078;
        int[] categoryCounter = {0, 0, 0, 0, 0};

        int[] yearList =  new int[156];
        int[] pressureList = new int[156];
        double[] windSpeedListInMPH = new double[156];
        int[] categoryList = new int[156];
        String[] hurricaneNameList = new String [156];

        int loopCounter = 0;
        while(inFile.hasNextLine())
        {
            int year = inFile.nextInt();
            yearList[loopCounter] = year;

            inFile.next(); // Skip month because it's not necessary

            int pressure = inFile.nextInt();
            pressureList[loopCounter] = pressure;

            double windSpeed = inFile.nextInt() * knotsToMiles;
            windSpeedListInMPH[loopCounter] = windSpeed;

            String hurricaneName = inFile.next();
            hurricaneNameList[loopCounter] = hurricaneName;

            int category;
            if(windSpeed >= 74 && windSpeed <= 95)
            {
                category = 1;
            }
            else if(windSpeed > 95 && windSpeed <= 110)
            {
                category = 2;
            }
            else if(windSpeed > 110 && windSpeed <= 129)
            {
                category = 3;
            }
            else if(windSpeed > 129 && windSpeed <= 156)
            {
                category = 4;
            }
            else
            {
                category = 5;
            }
            categoryList[loopCounter] = category;
            loopCounter++;
        }//end while
        inFile.close();

        Scanner in = new Scanner(System.in);
        int yearStart;
        int yearEnd;
        while (true)
        {
            System.out.print("Please provide a start year between 1995 an 2015: ");
            yearStart = in.nextInt();
            System.out.print("Please provide an end year between 1995 an 2015: ");
            yearEnd = in.nextInt();
            if(yearEnd >= yearStart && yearStart >= 1995 && yearStart <= 2015 && yearEnd >= 1995 && yearEnd <= 2015 )
            {
                break;
            }
            else
            {
                System.out.println("That is out of the range of years");
                continue;
            }
        }

        // Start Index

        int startIndex = 0;
        int endIndex = 0;
        for(int i = 0; i < yearList.length; i++)
        {
            if(yearList[i] == yearStart)
            {
                startIndex = i;
                break;
            }
        }

        // Reverse loop the array (End Index)

        for(int i = yearList.length - 1; i >= 0 ; i--)
        {
            if(yearList[i] == yearEnd)
            {
                endIndex = i;
                break;
            }
        }

        // Category Average

        double categorySum = 0;
        double categoryAverage = 0;
        for(int i = startIndex; i <= endIndex; i++)
        {
            categorySum += categoryList[i];
        }
        categoryAverage = categorySum / (endIndex - startIndex + 1);

        // Wind Speed Average

        double windSpeedSum = 0;
        double windSpeedAverage = 0;
        for(int i = startIndex; i <= endIndex; i++)
        {
            windSpeedSum += windSpeedListInMPH[i];
        }
        windSpeedAverage = windSpeedSum / (endIndex - startIndex + 1);

        // Pressure Average

        double pressureSum = 0;
        double pressureAverage = 0;
        for(int i = startIndex; i <= endIndex; i++)
        {
            pressureSum += pressureList[i];
        }
        pressureAverage = pressureSum / (endIndex - startIndex + 1);

        //Maximum and Minimum Values

        //Maximum and Minimum Values for Categories

        int minCategory = Integer.MAX_VALUE;
        int maxCategory = Integer.MIN_VALUE;

        for(int i = startIndex; i <= endIndex; i++)
        {
            if(categoryList[i] < minCategory)
                minCategory = categoryList[i];
            if(categoryList[i] > maxCategory)
                maxCategory = categoryList[i];
        }

        // Maximum and Minimum Values for Wind Speed

        double minWindSpeed = Integer.MAX_VALUE;
        double maxWindSpeed = Integer.MIN_VALUE;

        for(int i = startIndex; i <= endIndex; i++)
        {
            if(windSpeedListInMPH[i] < minWindSpeed )
                minWindSpeed = windSpeedListInMPH[i];
            if(windSpeedListInMPH[i] > maxWindSpeed)
                maxWindSpeed = windSpeedListInMPH[i];
        }

        // Maximum and Minimum Values for Pressure

        int minPressure = Integer.MAX_VALUE;
        int maxPressure = Integer.MIN_VALUE;

        for(int i = startIndex; i <= endIndex; i++)
        {
            if(pressureList[i] < minPressure)
                minPressure = pressureList[i];
            if(pressureList[i] > maxPressure)
                maxPressure = pressureList[i];
        }

        for(int i = startIndex; i <= endIndex; i++)
        {
            if(windSpeedListInMPH[i] >= 74 && windSpeedListInMPH[i] <= 95)
            {
                categoryCounter[0]++;
            }
            else if(windSpeedListInMPH[i] > 95 && windSpeedListInMPH[i] <= 110)
            {
                categoryCounter[1]++;
            }
            else if(windSpeedListInMPH[i] > 110 && windSpeedListInMPH[i] <= 129)
            {
                categoryCounter[2]++;
            }
            else if(windSpeedListInMPH[i] > 129 && windSpeedListInMPH[i] <= 156)
            {
                categoryCounter[3]++;
            }
            else
            {
                categoryCounter[4]++;
            }
        }

        System.out.println("\n\t\tHurricanes " + yearStart + " - " + yearEnd + "\n");
        System.out.printf("%5s %17s %19s %20s %20s \n","Year","Hurricane", "Category","Pressure (mb)", "Wind Speed (mph)");
        System.out.println("==================================================================================");
        for(int i = startIndex; i <= endIndex; i++)
        {
            System.out.printf("%5d %17s %15d %18d %19.2f \n",yearList[i], hurricaneNameList[i], categoryList[i], pressureList[i], windSpeedListInMPH[i]);
        }
        System.out.println("==================================================================================");
        System.out.printf("%23s %17.1f %18.1f %17.2f\n", "Average:", categoryAverage, pressureAverage, windSpeedAverage);
        System.out.printf("%23s %15d %18d %19.2f\n", "Minimum:", minCategory, minPressure, minWindSpeed);
        System.out.printf("%23s %15d %18d %19.2f\n\n", "Maximum:", maxCategory, maxPressure, maxWindSpeed);
        System.out.println("Summary of Categories:");
        for(int i = 0; i < categoryCounter.length; i++)
        {
            System.out.println("Cat " + (i + 1) + ": " + categoryCounter[i]);
        }
        PrintWriter outfile = new PrintWriter(new File("Year Range and Summary of Category Count Data.txt"));
        outfile.println("Year Range Data: ");
        outfile.println(yearStart + " - " + yearEnd + "\n");
        outfile.println("Summary of Category Count Data: ");
        for(int i = 0; i < categoryCounter.length; i++)
        {
            outfile.println("Cat " + (i + 1) + ": " + categoryCounter[i]);
        }
        outfile.close();
    }
}
