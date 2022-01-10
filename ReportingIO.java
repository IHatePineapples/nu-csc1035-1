// java.util.Scanner lets us prompt input from user

import java.util.*;

public class ReportingIO {


    static void MainMenuUI() {
        //Displays the StartMenu UI (User Interface)
        //You can press "5" to activate the test suite and generate sample data
        System.out.println("--------------------------------------------------------------------------------------------------------------MAIN_MENU--------------------------------------------------------------------------------------------------------");
        BlankLine(3);
        System.out.println(  "    Please choose an option from the following : ");
        BlankLine(1);
        System.out.println(  "    [1] - Access a specific District's Data");
        BlankLine(1);
        System.out.println(  "    [2] - Open Incident menu");
        BlankLine(1);
        System.out.println(  "    [3] - Print a Report");
        BlankLine(1);
        System.out.println(  "    [4] - Exit");
        BlankLine(1);
        //MainUserChoice handles user's choice
        MainUserChoice();
    }

    public static int ChoiceIntInput(int ForbiddenMinValue, int MaxPossibleValue) {
        //Request integer input from user
        Scanner ChoiceScannerInput = new Scanner(System.in);

        int Choice = 0 ;
        /*Exception handling, in case user puts the wrong value type, the whole code doesn't need to stop
        It asks for new input everytime the user inputs the wrong one*/
        try {
            Choice = ChoiceScannerInput.nextInt();
            //if statement to ensure the value is either 1, 2, 3 or 4
            if (Choice == ForbiddenMinValue ||  Choice>MaxPossibleValue){
                throw new InputMismatchException();
            }
            //ChoiceScannerInput.close(); //ignore
        } catch (java.util.InputMismatchException e) {
            //ChoiceScannerInput.close(); //ignore
            System.out.println("\nPlease check your input and try again:\n");
            MainMenuUI();

        }

        return Choice;
    }

    public static void MainUserChoice() {
        //handles user's choice
        switch (ChoiceIntInput(0, 5)) {
            default -> MainMenuUI();
            case (1) -> District.DistrictUI();
            case (2) -> Incident.IncidentMenuUI();
            case (3) -> Reporting.PrintReport();
            case (4) -> System.out.println("\nGoodbye!");
            case (5) -> Incident.SampleData();
        }
    }

    public static String StringInfoInput(int i) {
        //Request string input from user
        Scanner InfoScannerInput = new Scanner(System.in);

        //initial value
        String Info = "" ;
        /*
        Exception handling, in case user puts the wrong value type, the whole code doesn't need to stop
        It asks for new input everytime the user inputs the wrong one
        */
        try {
            Info = InfoScannerInput.nextLine();
            //if statement to ensure the value is valid
            if (Info.equals(""))
            {
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            //in case user fails to input the right value he gets redirected to the menu he typed in the wrong value
            System.out.println("\nPlease check your input and try again:\n");
            switch (i){
                case (1) -> District.DistrictUI();
                case (2) -> Incident.AddAnIncidentUI();
                case (3) -> Reporting.PrintReport();
            }

        }

        return Info;
    }

    public static void  Pause() {
            //Pauses the system for the user to read what got printed previously
            Scanner Pause = new Scanner(System.in);
            BlankLine(1);
            System.out.println("Hold down enter to continue:");
            BlankLine(1);
            Pause.nextLine();
                }

    public static void  MainMenuRedirect() {
        //Warns the user then redirects him to the main menu
        ReportingIO.BlankLine(1);
        System.out.println("You will now be redirected to the Main Menu");
        ReportingIO.BlankLine(1);
        ReportingIO.Pause();
        ReportingIO.MainMenuUI();
    }

    public static double DoubleInfoInput(int i) {
        //Request double type input from user
        Scanner DoubleInfoScannerInput = new Scanner(System.in);

        double DoubleInfo = 0;
        /*
        Exception handling, in case user puts the wrong value type, the whole code doesn't need to stop
        It asks for new input everytime the user inputs the wrong one
        */
        try {
            DoubleInfo = DoubleInfoScannerInput.nextDouble();
            //if statement to ensure the value is valid
            if (DoubleInfo <= 0)
            {
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("\nPlease check your input and try again:\n");
            //in case user fails to input the right value he gets redirected to the menu he typed in the wrong value
            switch (i){
                case (1) -> District.DistrictUI();
                case (2) -> Incident.AddAnIncidentUI();
                case (3) -> Reporting.PrintReport();
            }
        }
        return DoubleInfo;
    }

    public static void BlankLine(int n){
        //Prints blank lines
        for(int i = 1; i <= n; i++ ) {
            System.out.println();

        }

    }

    public static void main(String[] args) {
        //Greets the user
        Greetings();
        //Moves him to main menu
        MainMenuRedirect();

    }

    static void Greetings(){
        //Greets the user
        BlankLine(10);
        System.out.println("----------------------------------------------------------------------------------------------------------CRIME_REPORTING-------------------------------------------------------------------------------------------------------");
        BlankLine(1);
        System.out.println("    Hello, Welcome to CRIME_REPORTING");
        BlankLine(5);
    }
}
