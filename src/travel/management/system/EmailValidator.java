package travel.management.system;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        int atPos = email.indexOf('@');
        if (atPos == -1) return false;
        
        int dotPos = email.indexOf('.', atPos);
        if (dotPos == -1) return false;
        
        if (atPos == 0 || dotPos == email.length() - 1) return false;

        return true;
    }
}