package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(this.password) && newPassword.length() >= 8)
        {
            int countUpper = 0;
            int countLower = 0;
            int countDigit = 0;
            int countSpecial = 0;
            for(int i=0; i<newPassword.length(); i++)
            {
                char ch = newPassword.charAt(i);
                if(ch >= 'A' && ch <= 'Z') countUpper++;
                else if(ch >= 'a' && ch <= 'z') countLower++;
                else if(ch >= '0' && ch <= '9') countDigit++;
                else countSpecial++;
            }
            if(countDigit > 0 && countLower > 0 && countSpecial > 0 && countUpper > 0) {
                this.password = newPassword;
                System.out.println("Successfully  changed");
            }
            else
            {
                System.out.println("Not possible");
            }
        }
        else
        {
            System.out.println("Not possible");
        }

    }
}
