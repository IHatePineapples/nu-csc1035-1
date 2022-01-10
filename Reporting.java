import java.util.ArrayList;

public class Reporting {
/*
Because of heavy data streaming, it is necessary to use the following system:
Variables names translation/conversion:

Inc(s)   =  Incident(s)
Pco(s)   =  Postcode(s)
Yr       =  Year
Dl       =  "means data/stored data is Double type"
GvnYr    =  Given year
4        =  For
Avr      =  Average
Num     =  Numerator
*/
    //List that will contain all Incidents within a given year
    final public static ArrayList<Incident> Inc4AGivenYr = new ArrayList<>();
    //List that will contain all unique postcodes of Incidents in a given year
    final public static ArrayList<String> Pco4Inc4AGvnYr = new ArrayList<>();
    //List that will contain an average value for incidents in a specific Postcode in a given year
    final public static ArrayList<Double> AvrValue4Incs4Pco4AGvnYr = new ArrayList<>();
    //List that will contain Incidents within a specific postcode within a given year, -does not store any value-
    final public static ArrayList<Incident>  Incs4Pco4PerYrAvr= new ArrayList<>();

    public static void PrintReport() {
        //Clears these list as they are used everytime PrintReport() is called
        Inc4AGivenYr.clear();
        Pco4Inc4AGvnYr.clear();
        AvrValue4Incs4Pco4AGvnYr.clear();
        Incs4Pco4PerYrAvr.clear();
        //Prints the Report
        ReportingIO.BlankLine(1);
        System.out.println("---------------------------------------------------------------------------------------------------------------REPORT-----------------------------------------------------------------------------------------------------------");
        ReportingIO.BlankLine(2);
        //In case there is no data, skips the whole process
        if (!Incident.IncidentList.isEmpty()) {
            //prints the largest value incident ever recorded

            MaximumValue();
            ReportingIO.Pause();

            /*
            print a list of all recorded incidents  with their value greater than a certain amount/threshold
            specified by the user
            */
            ValueOverThreshold();
            ReportingIO.Pause();
            //prints the district with the largest average value incident for a given year
            MaxAvrValue4GvnYr();
            ReportingIO.Pause();
        }
        else {
                Incident.NoDataFound();
            }
            ReportingIO.MainMenuRedirect();
        }

    static void ValueOverThreshold(){
        System.out.println("We will now print a list of all recorded incidents  with their " +
                "value greater than a certain amount:");
        System.out.println("Please, tell us how much you want this amount/threshold to be ?");
        double ValueThreshold = ReportingIO.DoubleInfoInput(3);
        ReportingIO.BlankLine(1);
        //Starts by reminding the user's input
        System.out.println("Threshold : " + ValueThreshold + " £");
        ReportingIO.BlankLine(1);
        //Prints all the Incident detail whenever their value exceeds or equals to the threshold
        for (int i = 0; i < Incident.IncidentList.size(); i++) {
            if (ValueThreshold <= Incident.IncidentList.get(i).IncidentValue) {
                System.out.println("Postcode is : " + Incident.IncidentList.get(i).IncidentPostcode
                        + "\nDate: " + Incident.IncidentList.get(i).IncidentMonth
                        + "/" + Incident.IncidentList.get(i).IncidentYear
                        + "\nValue: " + Incident.IncidentList.get(i).IncidentValue + " £");
                ReportingIO.BlankLine(1);
            }

        }

    }

    static void MaximumValue(){
        System.out.println("We will now print the largest value incident ever recorded");

        ReportingIO.Pause();
        /*
        Initialize MaxValue double type and an integer that we will use as an index to retrieve the data
        */
        double MaxValue = 0;
        int MaxValueIndex = 0;
        /*
        Starts with MaxValue=0 and updates it everytime a larger value has been "discovered"
        MaxValueIndex stores the index of the Incident that was superior to the "old" MaxValue in the IncidentList
        so we can retrieve which incident had the maximum value and print all its data
         */
        for (int i = 0; i < Incident.IncidentList.size(); i++) {

            if (Incident.IncidentList.get(i).IncidentValue > MaxValue) {
                MaxValue = Incident.IncidentList.get(i).IncidentValue;
                MaxValueIndex = i;
            }

        }


        //Prints Incident data with highest value
        ReportingIO.BlankLine(1);
        System.out.println("Postcode is " + Incident.IncidentList.get(MaxValueIndex).IncidentPostcode
                + "\nDate: " + Incident.IncidentList.get(MaxValueIndex).IncidentMonth + "/"
                + Incident.IncidentList.get(MaxValueIndex).IncidentYear + "\nValue: " +
                Incident.IncidentList.get(MaxValueIndex).IncidentValue + " £");
    }

    static void MaxAvrValue4GvnYr(){
        System.out.println("We will now print The district with the largest average value incident for a given year");

        System.out.println("Please, tell us what year are you interested in evaluating ?");

        String AverageYear = ReportingIO.StringInfoInput(3);

        //Filling the IncidentsForAGivenYear with only the involved Incidents within a year
        for (int i = 0; i < Incident.IncidentList.size(); i++) {
            if (Incident.IncidentList.get(i).IncidentYear.equals(AverageYear)) {
                Inc4AGivenYr.add(Incident.IncidentList.get(i));
            }
        }

        //Filling the YearAverageIncidentsPostcodeList with only the postcodes, no duplicates allowed
        for (Incident value : Inc4AGivenYr) {
            if (!Pco4Inc4AGvnYr.contains(value.IncidentPostcode)) {
                Pco4Inc4AGvnYr.add(value.IncidentPostcode);
            }
        }


        // Filling a list with all the averages per district

        for (String s : Pco4Inc4AGvnYr) { //This loop repeats "Number of distinct postcodes" times
            Incs4Pco4PerYrAvr.clear(); //Resets the list to use it again
            for (Incident value : Inc4AGivenYr) {
                //This loop repeats "Number of incidents in a specific district (in a given year)" times
                if (value.IncidentPostcode.startsWith(s)) {
                    //"If the incident happened put it in this list:Incs4Pco4PerYrAvr"
                    Incs4Pco4PerYrAvr.add(value);
                    //This list contains all the incidents in a specific district (in a given year)
                }
            }
            /*
            Initializes the two double type variable that we will
             use to calculate the average for this specific Postcode/District
            */
            double YrAvrValueNum = Incs4Pco4PerYrAvr.stream().mapToDouble(incident -> incident.IncidentValue).sum();
            double YrAvrValue = YrAvrValueNum / Incs4Pco4PerYrAvr.size();
            /*
            AvrValue4Incs4Pco4AGvnYr is the list containing the average for every district's incident values
            The order is the same one as in the list containing the postcodes
            We will use this property to retrieve the postcode and their respective Average Value
            */
            AvrValue4Incs4Pco4AGvnYr.add(YrAvrValue);
        }
        /*
        We find the maximum Average Value in the AvrValue4Incs4Pco4AGvnYr list, and keep track of their index
        by using the integer MaxAverageValueIndex
        */
        double MaxAverageValue = 0.0;
        int MaxAverageValueIndex = 0;

        for (int i = 0; i < AvrValue4Incs4Pco4AGvnYr.size(); i++) {
            if (AvrValue4Incs4Pco4AGvnYr.get(i) > MaxAverageValue) {
                MaxAverageValue = AvrValue4Incs4Pco4AGvnYr.get(i);
                MaxAverageValueIndex = i;
            }
        }
        /*
        We now use the extracted MaxAverageValueIndex with Pco4Inc4AGvnYr to get the postcode
         and print MaxAverageValue that we just retrieved
        */
        System.out.println("Postcode is: " + Pco4Inc4AGvnYr.get(MaxAverageValueIndex)
                + "\nWith an average value of: " + MaxAverageValue + " £");

    }
}




