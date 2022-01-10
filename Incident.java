
import java.util.*;

public class Incident {
    String IncidentPostcode;
    String IncidentMonth;
    String IncidentYear;
    double IncidentValue;

    final public static ArrayList<Incident> IncidentList = new ArrayList<>();

    public Incident(String postcode, String year, String month, double value) {

        IncidentPostcode = postcode;
        IncidentMonth = month;
        IncidentYear = year;
        IncidentValue = value;
    }

    static void IncidentMenu() {
        //Display the StartMenu UI (User Interface)
        System.out.println(  "    Please choose an option from the following : ");
        ReportingIO.BlankLine(1);
        System.out.println(  "    [1] - Fetch all recorded incidents");
        ReportingIO.BlankLine(1);
        System.out.println(  "    [2] - Add an incident");
        ReportingIO.BlankLine(1);
        System.out.println(  "    [3] - Exit to main menu");
        ReportingIO.BlankLine(3);
        IncidentUserChoice();
    }

    public static void IncidentUserChoice() {
        switch (ReportingIO.ChoiceIntInput(0, 3)) {
            default -> IncidentMenu();
            case (1) -> FetchAllIncidentsUI();
            case (2) -> AddAnIncidentUI();
            case (3) -> ReportingIO.MainMenuRedirect();
        }
    }

    static void SampleData() {
        //Adding the sample data
        System.out.println("\n\nTesting suit activated, sample data for testing has been added\n\n");
        IncidentList.add(new Incident("NE1 6DJ", "2017", "12", 200));
        IncidentList.add(new Incident("NE2 GBD", "2018", "09", 300));
        IncidentList.add(new Incident("NE3 4BU", "2019", "01", 150));
        IncidentList.add(new Incident("NE4 DBE", "2020", "05", 700));
        IncidentList.add(new Incident("NE5 DBE", "2017", "04", 500));
        IncidentList.add(new Incident("NE6 DBE", "2019", "11", 600));
        IncidentList.add(new Incident("NE7 DBD", "2018", "07", 1000));
        IncidentList.add(new Incident("NE1 6DJ", "2020", "04", 250));
        IncidentList.add(new Incident("NE2 GBD", "2018", "09", 350));
        IncidentList.add(new Incident("NE2 4BU", "2021", "02", 100));
        IncidentList.add(new Incident("NE3 DBE", "2019", "08", 750));
        IncidentList.add(new Incident("NE1 6DJ", "2027", "04", 250));
        IncidentList.add(new Incident("NE2 GBD", "2018", "09", 350));
        IncidentList.add(new Incident("NE2 4BU", "2021", "02", 100));
        IncidentList.add(new Incident("NE3 DBE", "2019", "08", 750));
        IncidentList.add(new Incident("NE3 DBE", "2018", "04", 550));
        IncidentList.add(new Incident("NE4 DBE", "2018", "11", 650));
        IncidentList.add(new Incident("NE4 DBD", "2019", "10", 1050));
        IncidentList.add(new Incident("NE1 6DJ", "2019", "06", 300));
        IncidentList.add(new Incident("NE7 GBD", "2018", "09", 400));
        IncidentList.add(new Incident("NE6 4BU", "2021", "01", 250));
        IncidentList.add(new Incident("NE2 DBE", "2019", "03", 800));
        IncidentList.add(new Incident("NE3 DBE", "2018", "04", 600));
        IncidentList.add(new Incident("NE4 DBE", "2017", "02", 700));
        IncidentList.add(new Incident("NE5 DBD", "2020", "08", 900));
        ReportingIO.MainMenuRedirect();
    }

    static void IncidentMenuUI() {
        System.out.println("--------------------------------------------------------------------------------------------------------------INCIDENT----------------------------------------------------------------------------------------------------------");
        ReportingIO.BlankLine(1);
        IncidentMenu();
    }

    static void FetchAllIncidentsUI() {
        //Display the AddAnIncident UI (User Interface)

        ReportingIO.BlankLine(1);
        System.out.println(" We will fetch all recorded incidents.");
        ReportingIO.BlankLine(1);
        System.out.println(" Please, if you need to add an incident, make sure that it is in the following format:");
        System.out.println("\nPostcode: XXX XXX \nYear: YYYY \nMonth : MM \nValue: Must be a number");
        ReportingIO.BlankLine(1);
        ReportingIO.Pause();
        if (!IncidentList.isEmpty()) {
            for (Incident incident : IncidentList) {

                System.out.println(
                        "Postcode is " + incident.IncidentPostcode
                                + "\nDate: " + incident.IncidentMonth + "/"
                                + incident.IncidentYear
                                + "\nCost/Value: " + incident.IncidentValue + " £");
                ReportingIO.BlankLine(1);
            }
            ReportingIO.Pause();
        } else {
            NoDataFound();
        }
        IncidentMenu();

    }

    static void AddAnIncidentUI() {
        //Display the AddAnIncident UI (User Interface)
        ReportingIO.BlankLine(1);
        System.out.println("You will now add an Incident:");
        ReportingIO.BlankLine(1);
        System.out.println("Where did the incident take place ?");
        ReportingIO.BlankLine(1);
        System.out.println("Please, type in the Postcode :");
        String postcode = ReportingIO.StringInfoInput(2);
        System.out.println("When did the incident take place ?");
        System.out.println("Please, type in the Year :");
        String year = ReportingIO.StringInfoInput(2);
        System.out.println("Please, type in the Month :");
        String month = ReportingIO.StringInfoInput(2);
        System.out.println("How much did the incident cost ?");
        System.out.println("Please, type in the cost/value :");
        double value = ReportingIO.DoubleInfoInput(2);
        //System.out.println( postcode + "" + year + "" + month +""+ value ); //only for debugging purposes
        //Create and add the object to the list
        IncidentList.add(new Incident(postcode, year, month, value));
        ReportingIO.BlankLine(1);
        System.out.println("Most recent incident added:\n" +
                "Postcode is " + IncidentList.get(IncidentList.size() - 1).IncidentPostcode + "\nDate: "
                + IncidentList.get(IncidentList.size() - 1).IncidentYear + "/"
                + IncidentList.get(IncidentList.size() - 1).IncidentMonth + "\nCost/Value: " +
                IncidentList.get(IncidentList.size() - 1).IncidentValue + " £");
        IncidentMenu();

    }

    static void NoDataFound() {
        System.out.println("No data found, please add data before continuing");
        ReportingIO.BlankLine(1);
    }
}