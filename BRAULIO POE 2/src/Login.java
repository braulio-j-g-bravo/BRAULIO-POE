import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;
    private String storedFirstName;
    private String storedLastName;

    public static void main(String[] strings) {
    }

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return hasUpperCase && hasNumber && hasSpecialChar;
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) return false;

        String cleanedNumber = cellPhoneNumber.replaceAll("[\\s\\-\\(\\)]", "");
        String regex = "^\\+27[0-9]{9}$";

        return Pattern.compile(regex).matcher(cleanedNumber).matches();
    }

    public String registerUser(String username, String password, String cellPhone,
                               String firstName, String lastName) {
        boolean usernameValid = checkUserName(username);
        boolean passwordValid = checkPasswordComplexity(password);
        boolean cellPhoneValid = checkCellPhoneNumber(cellPhone);

        if (!usernameValid) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!passwordValid) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!cellPhoneValid) {
            return "Cell phone number incorrectly formatted or does not contain international code, please correct the number and try again.";
        }

        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;
        this.storedFirstName = firstName;
        this.storedLastName = lastName;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername != null && inputPassword != null &&
                inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword);
    }

    public String returnLoginStatus(String inputUsername, String inputPassword) {
        boolean loginSuccess = loginUser(inputUsername, inputPassword);

        if (loginSuccess) {
            return "Welcome " + storedFirstName + "," + storedLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String getStoredUsername() { return storedUsername; }
    public String getStoredFirstName() { return storedFirstName; }
    public String getStoredLastName() { return storedLastName; }
    public String getStoredCellPhone() { return storedCellPhone; }
}