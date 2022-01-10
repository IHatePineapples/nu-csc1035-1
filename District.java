import java.util.ArrayList;

public class District {


    String DistrictPostcode;
    String DistrictYear;

    //List containing Incidents that took place in a specific District/Postcode
    final public static ArrayList<Incident> DistrictList = new ArrayList<>();
    //List containing Incidents that took place in a specific District/Postcode within a year
    final public static ArrayList<Incident> YearDistrictList = new ArrayList<>();


    public District(String postcode, String year){

        DistrictPostcode = postcode;
        DistrictYear = year;

    }

    static void DistrictUI() {
        //Clear YearDistrictList
        YearDistrictList.clear();
        //Display the District UI (User Interface)
        ReportingIO.BlankLine(2);
        System.out.println("--------------------------------------------------------------------------------------------------------------DISTRICT----------------------------------------------------------------------------------------------------------");
        ReportingIO.BlankLine(5);
        //In case there is no data, skips the whole process
        if (!Incident.IncidentList.isEmpty()) {
            //Fetches all incidents within a district, selected by the user
            FetchDistrictIncidents();
            //Prints Incident with highest value
            MaximumValue();
            //Prints year average value
            YearAverage();

            //Prints the Incidents and their attributes only if they are exceeding the threshold set by the user
            ValueOverThreshold();
        }
        else
            {
            Incident.NoDataFound();
            }
        ReportingIO.MainMenuRedirect();
    }

    static void FetchDistrictIncidents(){
        System.out.println(" You will access all the incidents that occurred in your chosen postcode:");
        ReportingIO.BlankLine(2);
        System.out.println("Please tell us which District are you interested in by inputting its postcode :");
        String DistrictPostcode = ReportingIO.StringInfoInput(1);


        //Filling the the DistrictList with only the involved Incidents
        for (int i = 0; i < Incident.IncidentList.size(); i++) {
            if (Incident.IncidentList.get(i).IncidentPostcode.startsWith(DistrictPostcode)) {
                DistrictList.add(Incident.IncidentList.get(i));
            }
        }
        //Prints the postcode user wanted
        ReportingIO.BlankLine(1);
        System.out.println("Chosen Postcode is " + DistrictPostcode);
        ReportingIO.BlankLine(1);

        //Prints the Incidents and their attribute
        for (Incident incident : DistrictList) {
            ReportingIO.BlankLine(1);
            System.out.println("Date: " + incident.IncidentMonth + "/"
                    + incident.IncidentYear + "\nValue: " +
                    incident.IncidentValue + " £");
            ReportingIO.BlankLine(1);

        }
    }

    static void MaximumValue (){
        //Prints Incident with highest value

        System.out.println("Incident with highest value was the following:");
        double MaxValue = 0;


        for (Incident incident : DistrictList) {

            if (incident.IncidentValue > MaxValue) {
                MaxValue = incident.IncidentValue;
            }
        }
        for (Incident incident : DistrictList) {

            if (incident.IncidentValue == MaxValue) {
                ReportingIO.BlankLine(1);
                System.out.println("Date: " + incident.IncidentMonth + "/"
                        + incident.IncidentYear + "\nValue: " +
                        incident.IncidentValue + " £");
                ReportingIO.BlankLine(1);

            }
        }
    }

    static void YearAverage(){
        System.out.println("We will now print the average value incident for a specific year in this district.");
        ReportingIO.Pause();
        System.out.println("Please, tell us what year are you interested in evaluating ?");

        String DistrictYear = ReportingIO.StringInfoInput(1);

        //Filling the YearDistrictList with only the involved Incidents within a year
        for (int i = 0; i < Incident.IncidentList.size(); i++) {
            if (Incident.IncidentList.get(i).IncidentYear.equals(DistrictYear)) {
                YearDistrictList.add(Incident.IncidentList.get(i));
            }
        }

        //Calculates and prints Average Incident Value for a specified year

        double YearAverageValueNumerator = YearDistrictList.stream().mapToDouble(incident -> incident.IncidentValue).sum();
        double YearAverageValue = YearAverageValueNumerator / YearDistrictList.size();

        ReportingIO.BlankLine(1);
        System.out.println("Average incident value in this district is: " + YearAverageValue + " £");
        ReportingIO.BlankLine(1);
    }

    static void ValueOverThreshold(){
        //Prints the Incidents and their attributes only if they are exceeding the threshold set by the user
        System.out.println("We will now print Incidents over the specified Threshold value:");
        ReportingIO.Pause();
        ReportingIO.BlankLine(1);
        System.out.println("Please tell us how high you want the threshold to be,\n" +
                "only incidents with their cost/value over the threshold will be shown. ");
        ReportingIO.BlankLine(1);
        double ValueThreshold = ReportingIO.DoubleInfoInput(1);
        System.out.println("The following Incidents are the only ones over the Threshold value of this postcode: ");
        ReportingIO.BlankLine(1);
        System.out.println("Threshold : " + ValueThreshold);
        ReportingIO.BlankLine(1);
        for (Incident incident : DistrictList) {
            if (ValueThreshold <= incident.IncidentValue) {
                System.out.println("Date: " + incident.IncidentMonth + "/"
                        + incident.IncidentYear + "\nValue: " +
                        incident.IncidentValue + " £");
                ReportingIO.BlankLine(1);
            }
        }
    }
}

