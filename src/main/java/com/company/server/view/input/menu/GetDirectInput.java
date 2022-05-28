package com.company.server.view.input.menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static com.company.server.view.input.menu.DirectInputHashing.getSha256;
import static com.company.server.view.input.menu.DirectInputValidator.*;
import static com.company.server.view.messages.Errors.*;
import static com.company.server.view.messages.GeneralInfo.*;
import static com.company.server.view.messages.InputFields.*;

public class GetDirectInput {

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public GetDirectInput(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public String getFirstName() throws IOException {

        dataOutputStream.writeUTF(FIRST_NAME_INPUT);
        return dataInputStream.readUTF();
    }

    public String getLastName() throws IOException {

        dataOutputStream.writeUTF(LAST_NAME_INPUT);
        return dataInputStream.readUTF();
    }

    public String getUsername() throws IOException {

        dataOutputStream.writeUTF(USERNAME_INPUT);
        return dataInputStream.readUTF();
    }

    public String getEmailAddress() throws IOException {

        dataOutputStream.writeUTF(EMAIL_ADDRESS_INPUT);
        return dataInputStream.readUTF();
    }

    public String getPasswordHash() throws IOException {

        dataOutputStream.writeUTF(PASSWORD_INPUT);
        return getSha256(dataInputStream.readUTF().trim());
    }

    private String getPassword() throws IOException {

        dataOutputStream.writeUTF(PASSWORD_INPUT);
        return dataInputStream.readUTF().trim();
    }

    public String getPossibleUsername() throws IOException {

        dataOutputStream.writeUTF(USERNAME_VALIDATION);
        dataInputStream.readUTF();

        String username = getUsername();

        while (!checkUsername(username)) {
            dataOutputStream.writeUTF(USERNAME_ERROR);
            dataInputStream.readUTF();

            username = getUsername();
        }

        return username;
    }

    public String getPossibleEmailAddress() throws IOException {

        String emailAddress = getEmailAddress();

        while (!checkEmailAddress(emailAddress)) {
            dataOutputStream.writeUTF(EMAIL_ADDRESS_ERROR);
            dataInputStream.readUTF();

            emailAddress = getEmailAddress();
        }

        return emailAddress;
    }

    public String getPossiblePasswordHash() throws IOException {

        dataOutputStream.writeUTF(PASSWORD_VALIDATION);
        dataInputStream.readUTF();

        String password = getPassword();

        while (!checkPassword(password)) {
            dataOutputStream.writeUTF(PASSWORD_ERROR);
            dataInputStream.readUTF();

            password = getPassword();
        }

        return getSha256(password);
    }
}
