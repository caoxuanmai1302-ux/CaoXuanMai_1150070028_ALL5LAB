package com.example.mailab2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class M000SplashFrg extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle s) {
        return inflater.inflate(R.layout.m000_frg_splash, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        new Handler(Looper.getMainLooper()).postDelayed(this::go, 1200);
    }
    private void go() {
        Activity act = getActivity();
        if (act instanceof MainActivity) ((MainActivity) act).gotoM001Screen();
    }
}
