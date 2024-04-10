package com.example.churchapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.User;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class activity_SignUp extends AppCompatActivity {
    private String image;

    EditText name_in, surname_in, phone_in_c, passw_in, passw_in_c, church_in;
    String name, surname, email, password, phone, church, role, gender, title;
    RadioGroup titleGroup, genderGroup, roleGroup;
    TextView addImg, roleTxt, genderTxt, titleTxt,errorSU;
    LinearLayout signUp_btn;
    ProgressBar loadingProgressBar;
    ImageView imageProf;
    Database_Methods databaseMethods;
    ImageView pic2add;

    Boolean addImage = false;
    Uri imageUri2;
    User user;
    ManagePreferences managePreferences;
    int mr, mrs, miss, male, female, none,none1, pastor, assistance, member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //-0-----------------------
        databaseMethods = new Database_Methods(getApplicationContext());
        Log.d("l4 1111111", "_______________");
        user = new User();
        Log.d("l4 1111111", "_______________2");
        managePreferences = new ManagePreferences(getApplicationContext());
        callViews();
        Log.d("l4 1111111", "_______________4");
        radioButtons();
        Log.d("l4 1111111", "_______________6");
    }
    private void callViews(){
        name_in = findViewById(R.id.nameIn);
        surname_in = findViewById(R.id.surnameIn);
        phone_in_c = findViewById(R.id.phoneIn);
        passw_in = findViewById(R.id.passwordIn);
        passw_in_c = findViewById(R.id.passwordCIn);
        church_in = findViewById(R.id.churchIn);
        signUp_btn = findViewById(R.id.signUpBtn);
        titleGroup = findViewById(R.id.titleGroup);
        genderGroup = findViewById(R.id.genderGroup);
        roleGroup = findViewById(R.id.roleGroup);
        roleTxt = findViewById(R.id.roleIn);
        genderTxt = findViewById(R.id.genderIn);
        titleTxt = findViewById(R.id.titleIn);
        pic2add = findViewById(R.id.myPP);
        addImg = findViewById(R.id.addPPtxt);
        errorSU = findViewById(R.id.errorSU);

        member = R.id.memberRB;
        assistance = R.id.assistanceRB;
        pastor = R.id.pastorRB;
        none = R.id.noneRB;
        none1 = R.id.noneRB1;
        female = R.id.femaleRB;
        male = R.id.maleRB;
        miss = R.id.missRB;
        mrs = R.id.mrsRB;
        mr = R.id.mrRB;
    }

    public void ShowTitles(View view) {

    }

    public void ShowRoles(View view) {
    }

    public void OpenHelp(View view) {
    }

    public void OpenSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_SignIn.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
        //call add to db method
        name = name_in.getText().toString();
        surname = surname_in.getText().toString();
        phone = phone_in_c.getText().toString();
        password = passw_in.getText().toString();
        church = church_in.getText().toString();
        if (validate()){
            user.name = name;
            user.email = "email";
            user.phone = phone;
            user.password = password;
            user.church = church;
            user.surname = surname;
            user.role = role;
            user.gender = gender;
            user.title = title;
            user.bio = "----";
            user.bckGndP = "------";
            databaseMethods.getImage(imageUri2, new Database_Methods.FirestoreListenerGetImage() {
                @Override
                public void onSuccess(String image) {
                    user.image = image;
                    databaseMethods.addUser(user);
                    Intent intent = new Intent(getApplicationContext(), activity_SignIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String errorMessage) {
                    errorSU.setVisibility(View.VISIBLE);
                    errorSU.setText(errorMessage);

                }
            });

        }






    }
    private boolean available(){

        return false;

    }
    private void radioButtons(){

        titleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    // Get the text of the selected RadioButton
                    String selectedText = selectedRadioButton.getText().toString();
                    // Perform actions based on the selected RadioButton
                    if (checkedId == mr) {
                        // Do something for Mr
                        title= Constants.Key_Mr;
                        titleTxt.setText(Constants.Key_Mr);
                        titleGroup.setVisibility(View.GONE);
                        titleTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == miss) {
                        // Do something for Miss
                        title= Constants.Key_Miss;
                        titleTxt.setText(Constants.Key_Miss);
                        titleGroup.setVisibility(View.GONE);
                        titleTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == mrs) {
                        // Do something for Mrs
                        title= Constants.Key_Mrs;
                        titleTxt.setText(Constants.Key_Mrs);
                        titleGroup.setVisibility(View.GONE);
                        titleTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == none1) {
                        // Do something for None
                        title= Constants.Key_None;
                        titleTxt.setText(Constants.Key_None);
                        titleGroup.setVisibility(View.GONE);
                        titleTxt.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    // Get the text of the selected RadioButton
                    String selectedText = selectedRadioButton.getText().toString();
                    // Perform actions based on the selected RadioButton
                    if (checkedId == none) {
                        // Do something for Mr
                        gender= Constants.Key_None;
                        genderTxt.setText(Constants.Key_None);
                        genderGroup.setVisibility(View.GONE);
                        genderTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == male) {
                        // Do something for Miss
                        gender= Constants.Key_Male;
                        genderTxt.setText(Constants.Key_Male);
                        genderGroup.setVisibility(View.GONE);
                        genderTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == female) {
                        // Do something for Mrs
                        gender= Constants.Key_Female;
                        genderTxt.setText(Constants.Key_Female);
                        genderGroup.setVisibility(View.GONE);
                        genderTxt.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        roleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    // Get the text of the selected RadioButton
                    String selectedText = selectedRadioButton.getText().toString();
                    // Perform actions based on the selected RadioButton
                    if (checkedId == pastor) {
                        // Do something for Mr
                        role= Constants.Key_Pastor;
                        roleTxt.setText(Constants.Key_Pastor);
                        roleGroup.setVisibility(View.GONE);
                        roleTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == assistance) {
                        // Do something for Miss
                        role= Constants.Key_Assistance;
                        roleTxt.setText(Constants.Key_Assistance);
                        roleGroup.setVisibility(View.GONE);
                        roleTxt.setVisibility(View.VISIBLE);
                    } else if (checkedId == member) {
                        // Do something for Mrs
                        role= Constants.Key_Member;
                        roleTxt.setText(Constants.Key_Member);
                        roleGroup.setVisibility(View.GONE);
                        roleTxt.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    public void addTitle(View view) {
        titleGroup.setVisibility(View.VISIBLE);
        titleTxt.setVisibility(View.INVISIBLE);
    }
    public void addGender(View view) {
        genderGroup.setVisibility(View.VISIBLE);
        genderTxt.setVisibility(View.INVISIBLE);

    }
    public void addRole(View view) {
        roleGroup.setVisibility(View.VISIBLE);
        roleTxt.setVisibility(View.INVISIBLE);

    }
    private Boolean validate(){
        errorSU.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(name)) {
            errorSU.setText("Please provide a name");
            return false;
        }

        if (TextUtils.isEmpty(surname)) {
            errorSU.setText("Please provide a surname");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            errorSU.setText("Please provide a password");
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            errorSU.setText("Please provide a phone number");
            return false;
        }

        if (TextUtils.isEmpty(title)) {
            errorSU.setText("Please provide a title");
            return false;
        }

        if (TextUtils.isEmpty(gender)) {
            errorSU.setText("Please provide a gender");
            return false;
        }

        if (imageUri2 == null) {
            errorSU.setText("Please add a picture");
            return false;
        }
        return true;
    }

    public void addApp(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.launch(intent);
    }
    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData() != null){
                        Uri imgUri = result.getData().getData();
                        Log.d("11111111111111111", "-----"+imgUri.toString());
                        imageUri2 =imgUri;
                        addImage = true;
                        databaseMethods.uploadAnImage(imgUri, Constants.Key_Type_Image_PP);

                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imgUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                            pic2add.setVisibility(View.VISIBLE);
                            addImg.setVisibility(View.GONE);
                            pic2add.setImageBitmap(bitmap);
                        }catch (FileNotFoundException e){
                            errorSU.setVisibility(View.VISIBLE);
                            errorSU.setText("please try that again");
                        }
//
                    }
                }
            });

}