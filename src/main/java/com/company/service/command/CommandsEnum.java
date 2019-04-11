package com.company.service.command;

import com.company.service.command.ActionCommand;
import com.company.service.command.commands.*;

public enum CommandsEnum {
    LOGIN{
        {
            this.command = new LoginCommand();
        }
    },
    TO_LOGIN_PAGE{
        {
            this.command = new ToLoginPageCommand();
        }
    },
    TO_MAIN_PAGE{
        {
            this.command = new ToMainPageCommand();
        }
    },
    TO_ADMIN_PAGE{
        {
            this.command = new ToAdminPageCommand();
        }
    },
    TO_REGISTRATION_PAGE{
        {
            this.command = new ToRegistrationPageCommand();
        }
    },
    TO_ADD_PRODUCT_PAGE{
        {
            this.command = new ToAddProductPageCommand();
        }
    },
    TO_ADD_ACTIVITY_PAGE{
        {
            this.command = new ToAddActivityPageCommand();
        }
    },
    TO_PREVIOUS_PAGE{
        {
            this.command = new ToPreviousPageCommand();
        }
    },
    REGISTER_NEW_USER{
        {
            this.command = new RegisterNewUserCommand();
        }
    },
    SET_LOCALE {
        {
            this.command = new SetLocaleCommand();
        }
    },
    ADD_NEW_PRODUCT{
        {
            this.command = new AddNewProductCommand();
        }
    },
    ADD_NEW_ACTIVITY{
        {
            this.command = new AddNewActivityCommand();
        }
    },
    ADD_MEAL{
        {
            this.command = new AddMealCommand();
        }
    },
    ADD_ACTIVITY{
        {
            this.command = new AddToActivityDiaryCommand();
        }
    },
    SELECT_DATE{
        {
            this.command = new SelectDateCommand();
        }
    },
    UPDATE_USER_INFO{
        {
            this.command = new UpdateUserInfoCommand();
        }
    },
    CANCEL{
        {
            this.command = new CancelCommand();
        }
    },
    BLOCK_UNBLOCK_USER{
        {
            this.command = new BlockUnblockUserCommand();
        }
    },
    TO_N_PAGE{
        {
            this.command = new ToNPageCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    DELETE_ENTRY_FROM_FOOD_DIARY{
        {
            this.command = new DeleteEntryFromFoodDiaryCommand();
        }
    },
    DELETE_ENTRY_FROM_ACTIVITY_DIARY{
        {
            this.command = new DeleteEntryFromActivityDiaryCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
