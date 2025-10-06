package Product_TestCases;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

class LoginCheckoutUser {

    public String email;
    public String password;
}

class RegisterCheckoutUser {

    public String name;
    public String email;
}

class RegisterBeforeCheckoutUser {

    public String name;
    public String email;
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

class LoginUser {

    public String email;
    public String password;
}
class InvoiceUser {

    public String name;
    public String email;
}
public class ProductHelperClass {

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

    public static Product[] ReadProductData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        Product[] Products = new Gson().fromJson(reader, Product[].class);
        return Products;
    }

    public static LoginUser[] ReadLoginData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        LoginUser[] ListOfCredentials = new Gson().fromJson(reader,
                LoginUser[].class);
        return ListOfCredentials;
    }

    public static LoginCheckoutUser[] ReadLoginCheckoutData(
            String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        LoginCheckoutUser[] CheckoutUser = new Gson().fromJson(
                reader,
                LoginCheckoutUser[].class);
        return CheckoutUser;
    }

    public static RegisterCheckoutUser[] ReadRegisterWhileCheckoutData(
            String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        RegisterCheckoutUser[] register_checkout_user = new Gson().
                fromJson(
                        reader, RegisterCheckoutUser[].class);
        return register_checkout_user;
    }

    public static RegisterBeforeCheckoutUser[] ReadRegisterBeforeCheckoutData(
            String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        RegisterBeforeCheckoutUser[] RegisterBeforeCheckoutUsers = new Gson().
                fromJson(reader,
                        RegisterBeforeCheckoutUser[].class);
        return RegisterBeforeCheckoutUsers;
    }
    public static InvoiceUser[] ReadInvoiceUserData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(
                TestPrjRoot + TestDataFolder + fileName);
        InvoiceUser[] invoiceUsers = new Gson().
                fromJson(reader, InvoiceUser[].class);
        return invoiceUsers;
    }    
}
