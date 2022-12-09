import java.sql.*;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static Connection connect() {

        String url = "jdbc:sqlite:src/Labb3WassimTajeddin.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void Menu() {
        System.out.println("\nVälj:\n");
        System.out.println("""
                1  - Visa alla länder
                2  - Lägga till ett nytt land
                3  - Uppdatera ett land
                4  - Ta bort ett land
                5  - Räkna länder
                6  - Stäng av""");
    }



    public static void selectAll(){
        String sql = "SELECT * FROM country";

        try {
            Connection connection = connect();
            Statement statement  = connection.createStatement();
            ResultSet resultSet    = statement.executeQuery(sql);

            // loop through the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("countryId") +  "\t" +
                        resultSet.getString("countryName") + "\t" +
                        resultSet.getInt("countryArea") + "\t" +
                        resultSet.getInt("CountryPopulation"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void insert(String name, int area, int population) {
        String sql = "INSERT INTO country(countryName, countryArea, countryPopulation) VALUES(?,?,?)";

        try{
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, area);
            preparedStatement.setInt(3, population);
            preparedStatement.executeUpdate();
            System.out.println("Du har lagt till ett nytt land");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertCountry(){
        System.out.println("Skriv in namnet på landet: ");
        String inputName = scanner.nextLine();
        System.out.println("Skriv in landetsområde: ");
        int inputCountryArea = scanner.nextInt();
        System.out.println("Skriv in befolkningen: ");
        int inputPopulation = scanner.nextInt();
        insert(inputName,inputCountryArea,inputPopulation);
        scanner.nextLine();
    }




    // Update
    public static void update(String name, int area, int population, int id) {
        String sql = "UPDATE country SET countryName = ? , "
                + "countryArea = ? , "
                + "countryPopulation = ? "
                + "WHERE CountryId = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // set the corresponding param
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, area);
            preparedStatement.setInt(3, population);
            preparedStatement.setInt(4, id);
            // update
            preparedStatement.executeUpdate();
            System.out.println("Du har uppdaterat valt land");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateCountry(){
        System.out.println("Skriv in landetsid vilket ska uppdateras : ");
        int inputId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Skriv in namnet på landet: ");
        String inputName = scanner.nextLine();
        System.out.println("Skriv in landetsområde: ");
        int inputCountryArea = scanner.nextInt();

        System.out.println("Skriv in befolkningen: ");
        int inputPopulation = scanner.nextInt();

        update(inputName,inputCountryArea,inputPopulation, inputId);

    }
    public static void count(){
      String sql = "SELECT COUNT (*) FROM country";

        try {
            Connection connection = connect();
            Statement statement  = connection.createStatement();
            ResultSet resultSet    = statement.executeQuery(sql);

            // loop through the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("COUNT (*)"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    // Delete mot bok-tabellen i databasen
    public static void delete(int id) {
        String sql = "DELETE FROM country WHERE countryId = ?";

        try (Connection connect = connect();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)) {

            // set the corresponding param
            preparedStatement.setInt(1, id);
            // execute the delete statement
            preparedStatement.executeUpdate();
            System.out.println("Du har tagit bort landet");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteCountry(){
        System.out.println("Skriv in id:t på landet som ska tas bort: ");
        int inputId = scanner.nextInt();
        delete(inputId);
        scanner.nextLine();
    }

    public static void main(String[] args) {


        boolean quit = false;

        while(!quit) {
            Menu();
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1 -> selectAll();
                case 2 -> insertCountry();
                case 3 -> updateCountry();
                case 4 -> deleteCountry();
                case 5 ->count();
                case 6 -> {
                    System.out.println("\nStänger ner...");
                    quit = true;
                }
            }
        }

    }

}
