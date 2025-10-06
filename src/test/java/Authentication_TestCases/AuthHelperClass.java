/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentication_TestCases;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

class LoginUser {

    public String email;
    public String password;
}

class SignUpUser {

    public String name;
    public String email;
}

class FormUser {

    public String name;
    public String password;
    public String day;
    public String month;
    public String year;
    public String first_name;
    public String last_name;
    public String company;
    public String Address;
    public String country;
    public String State;
    public String city;
    public String zipcode;
    public String mobile_number;
    public String cardName;
    public String cardNumber;
    public String expiryMonth;
    public String expiryYear;
    public String cvv;
}

class Product {

    String productName;
}

public class AuthHelperClass {

    private static final String TestPrjRoot = "src/test/java/";
    private static final String TestDataFolder = "TestingData/";

    public static String ReadFromFile(String fileName,
            String Key) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        JsonElement e1 = JsonParser.parseReader(reader);
        return e1.getAsJsonObject().get(Key).getAsString();
    }

    /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static SignUpUser[] ReadUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        SignUpUser[] ListOfCredentials = new Gson().fromJson(reader,
                SignUpUser[].class);
        return ListOfCredentials;
    }

    public static FormUser[] ReadFormData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        FormUser[] ListOfCredentials = new Gson().fromJson(reader,
                FormUser[].class);
        return ListOfCredentials;
    }

    public static LoginUser[] ReadLoginData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        LoginUser[] ListOfCredentials = new Gson().fromJson(reader,
                LoginUser[].class);
        return ListOfCredentials;
    }
}
