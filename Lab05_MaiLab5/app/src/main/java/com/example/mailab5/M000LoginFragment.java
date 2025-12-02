package com.example.mailab5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class M000LoginFragment extends Fragment implements View.OnClickListener {

    EditText edtEmail, edtPass;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.m000_frg_login, container,false);
        edtEmail = v.findViewById(R.id.edt_email);
        edtPass = v.findViewById(R.id.edt_pass);
        v.findViewById(R.id.tv_login).setOnClickListener(this);
        v.findViewById(R.id.tv_register).setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Context c){
        super.onAttach(c);
        mContext = c;
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.tv_login){
            login(edtEmail.getText().toString(), edtPass.getText().toString());
        }else{
            ((MainActivity)mContext).gotoRegisterScreen();
        }
    }

    private void login(String m, String p){
        if(m.isEmpty() || p.isEmpty()){
            Toast.makeText(mContext, "Empty value", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.SAVE_PREF, Context.MODE_PRIVATE);
        String saved = pref.getString(m, null);
        if(saved == null){
            Toast.makeText(mContext, "Email not exist", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!saved.equals(p)){
            Toast.makeText(mContext, "Wrong pass", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(mContext, "Login OK", Toast.LENGTH_SHORT).show();
    }
}
