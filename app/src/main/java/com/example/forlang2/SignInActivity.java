package com.example.forlang2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.SelectLanguage.ChooseLang;
import com.example.forlang2.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignInActivity extends AppCompatActivity {

    Button btnSignIned;
    Button btnRegistered;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout rootLayout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FirebaseApp.initializeApp(SignInActivity.this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference(Common.RIDER_INFO_REFERENCE);

        btnSignIned = findViewById(R.id.btnSignIn);

        btnRegistered = findViewById(R.id.btnRegister);

        rootLayout = findViewById(R.id.rootLayout);

        initData();
    }

    private void initData() {

            btnSignIned.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showLoginDialog();
                }
            });

            btnRegistered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showRegisterDialog();
                }
            });
    }

    private void showLoginDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ВХОД");
        dialog.setMessage("Пожалуйста используйте почту для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_login,null);

        final MaterialEditText edtEmail = login_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtPassword = login_layout.findViewById(R.id.edtPassword);

        dialog.setView(login_layout);

        dialog.setPositiveButton("ВХОД", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                btnSignIned.setEnabled(false);

                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Пожалуйста введите почту", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    Snackbar.make(rootLayout, "Пожалуйста введите пароль", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (edtPassword.getText().toString().length() < 8) {
                    Snackbar.make(rootLayout, "Введите пароль от 8 символов", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                SpotsDialog waitingDialog = new SpotsDialog(SignInActivity.this);
                waitingDialog.show();

                auth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                waitingDialog.dismiss();
                                Intent intent = new Intent(SignInActivity.this, ChooseLang.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                waitingDialog.dismiss();
                                Snackbar.make(rootLayout,"Ошибка "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();

                                btnSignIned.setEnabled(true);
                            }
                        });
            }
        });

        dialog.setNegativeButton("ОТМЕНИТЬ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }

    private void showRegisterDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("СОЗДАТЬ АКК");
        dialog.setMessage("Пожалуйста используйте почту для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.layout_register,null);

        MaterialEditText edtEmail = register_layout.findViewById(R.id.edtEmail);
        MaterialEditText edtPassword = register_layout.findViewById(R.id.edtPassword);
        MaterialEditText edtName = register_layout.findViewById(R.id.edtName);
        MaterialEditText edtPhone = register_layout.findViewById(R.id.edtPhone);

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();

        dialog.setView(register_layout);

        dialog.setPositiveButton("СОЗДАТЬ АКК", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if(TextUtils.isEmpty(edtEmail.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Пожалуйста введите почту",Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(edtPassword.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Пожалуйста введите пароль",Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(edtName.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Пожалуйста введите имя",Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(edtPhone.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Пожалуйста введите телефон",Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(edtPassword.getText().toString().length() < 8)
                {
                    Snackbar.make(rootLayout,"Введите пароль от 8 символов",Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                auth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User(email,password,name,phone);
                                user.setEmail(edtEmail.getText().toString());
                                user.setPassword(edtPassword.getText().toString());
                                user.setName(edtName.getText().toString());
                                user.setPhone(edtPhone.getText().toString());
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout,"Регистрация успешна!",Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout,"Ошибка "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout,"Ошибка "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
        });

        dialog.setNegativeButton("ОТМЕНИТЬ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
}
