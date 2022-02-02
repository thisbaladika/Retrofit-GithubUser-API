package com.example.retrofitgithubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitgithubuser.api.ApiService;
import com.example.retrofitgithubuser.component.UserAdapter;
import com.example.retrofitgithubuser.di.module.UserModule;
import com.example.retrofitgithubuser.response.userSearch.UserSearch;
import com.example.retrofitgithubuser.response.userSearch.UserSearchData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvList)
    RecyclerView recyclerView;

    @BindView(R.id.main_search)
    EditText edt_search;

    List<UserSearchData> userDataEntityList;
    private UserAdapter adapter;
    private ProgressDialog progressDialog;
    private String ApiKey = "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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        edt_search.requestFocus();
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Loading..");
                    progressDialog.show();

                    getData(edt_search.getText().toString());
                }
                return false;
            }
        });


    }



    private void getData(String newText) {
        ApiService service = UserModule.getRetrofit().create(ApiService.class);

        Call<UserSearch> call = service.searchUser(ApiKey, newText);
        call.enqueue(new Callback<UserSearch>() {
            @Override
            public void onResponse(Call<UserSearch> call, Response<UserSearch> response) {
                progressDialog.hide();
                getAdapter(response.body().getUserDataEntityList());

            }

            @Override
            public void onFailure(Call<UserSearch> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(MainActivity.this, "Something went wrong....Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getAdapter(List<UserSearchData> userDataEntityList) {
        RecyclerView recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(userDataEntityList, this);
        recyclerView.setAdapter(adapter);
    }

}