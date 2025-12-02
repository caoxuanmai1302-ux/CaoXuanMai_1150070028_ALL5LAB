package com.example.mailab5;
import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;

public class M001RegisterFragment extends Fragment implements View.OnClickListener {

 EditText edtEmail,edtPass,edtRe;
 Context mContext;

 @Override
 public View onCreateView(LayoutInflater inf, ViewGroup c, Bundle b){
     View v=inf.inflate(R.layout.m001_frg_register,c,false);
     edtEmail=v.findViewById(R.id.edt_email);
     edtPass=v.findViewById(R.id.edt_pass);
     edtRe=v.findViewById(R.id.edt_re_pass);
     v.findViewById(R.id.tv_register).setOnClickListener(this);
     v.findViewById(R.id.iv_back).setOnClickListener(this);
     return v;
 }

 @Override
 public void onAttach(Context c){super.onAttach(c);mContext=c;}

 @Override
 public void onClick(View v){
     if(v.getId()==R.id.iv_back){
         ((MainActivity)mContext).gotoLoginScreen();
     }else{
         register(edtEmail.getText().toString(),edtPass.getText().toString(),edtRe.getText().toString());
     }
 }

 private void register(String m,String p,String r){
     if(m.isEmpty()||p.isEmpty()||r.isEmpty()){Toast.makeText(mContext,"Empty",Toast.LENGTH_SHORT).show();return;}
     if(!p.equals(r)){Toast.makeText(mContext,"Mismatch",Toast.LENGTH_SHORT).show();}
     SharedPreferences pref=mContext.getSharedPreferences(MainActivity.SAVE_PREF,Context.MODE_PRIVATE);
     if(pref.getString(m,null)!=null){Toast.makeText(mContext,"Exist",Toast.LENGTH_SHORT).show();return;}
     pref.edit().putString(m,p).apply();
     Toast.makeText(mContext,"Register OK",Toast.LENGTH_SHORT).show();
     ((MainActivity)mContext).gotoLoginScreen();
 }
}
