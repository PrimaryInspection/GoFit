package com.company.service.command;
import com.company.service.command.commands.*;


public enum CommandsEnum {


    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    TO_REGISTRATION_PAGE {
        {
            this.command = new ToRegistrationPageCommand();
        }
    };


        ActionCommand command;

        public ActionCommand getCurrentCommand() {
            return command;
        }
    }
