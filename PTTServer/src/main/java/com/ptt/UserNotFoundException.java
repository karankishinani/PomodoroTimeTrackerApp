package com.ptt;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException() {
        super("Contact not found");
    }
}