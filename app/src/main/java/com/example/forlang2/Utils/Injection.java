package com.example.forlang2.Utils;

import com.example.forlang2.Data.Local.QuestionDataSource;
import com.example.forlang2.Data.Remote.FirebaseDatabaseHelper;
import com.example.forlang2.Data.Repository;

public class Injection {
    public static Repository provideRepository() {

        return Repository.getInstance(
                QuestionDataSource.getInstance(),
                FirebaseDatabaseHelper.getHelperInstance());
    }

    public static FirebaseAuthHelper providesAuthHelper() {

        return FirebaseAuthHelper.getClassInstance();
    }
}
