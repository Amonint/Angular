/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionHilos;

/**
 *
 * @author Abraham
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class EncontrarPrimos {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bimestral";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection conn = ConexionDB.getConnection();
            System.out.println("Conexión exitosa");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM valores";
            ResultSet rs = stmt.executeQuery(sql);

            List<Integer> totalPrimes = new ArrayList<>();

            while (rs.next()) {
                List<Integer> rowPrimes = new ArrayList<>();
                int numberOfThreads = 4;

                ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
                List<Future<List<Integer>>> futures = new ArrayList<>();

                for (int i = 1; i <= numberOfThreads; i++) {
                    int columnIndex = i;
                    Callable<List<Integer>> worker = () -> {
                        List<Integer> primes = new ArrayList<>();
                        int value = rs.getInt("col" + columnIndex);
                        if (esPrimo(value)) {
                            primes.add(value);
                        }
                        return primes;
                    };
                    futures.add(executorService.submit(worker));
                }

                for (Future<List<Integer>> future : futures) {
                    try {
                        rowPrimes.addAll(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                executorService.shutdown();
                totalPrimes.addAll(rowPrimes);
            }

            rs.close();
            stmt.close();
            conn.close();

            System.out.println("Números primos encontrados: " + totalPrimes);
            System.out.println("Total de números primos: " + totalPrimes.size());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private static boolean esPrimo(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
