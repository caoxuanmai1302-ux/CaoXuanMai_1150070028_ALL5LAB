package com.example.lab4ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class FragmentFaceEmoij extends Fragment implements View.OnClickListener {
    private static final int[] IDs = {
            R.id.iv_face1, R.id.iv_face2, R.id.iv_face3,
            R.id.iv_face4, R.id.iv_face5, R.id.iv_face6,
            R.id.iv_face7, R.id.iv_face8, R.id.iv_face9
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.m001_frg_face_emoij, container, false);
        for (int id : IDs) root.findViewById(id).setOnClickListener(this);
        root.findViewById(R.id.btnRandom).setOnClickListener(v -> randomize(root));
        return root;
    }

    @Override
    public void onClick(View v) {
        ImageView iv = (ImageView) v;
        showToast(iv.getDrawable());
    }

    private void showToast(Drawable d) {
        if (getContext() == null) return;
        Toast t = new Toast(getContext());
        ImageView iv = new ImageView(getContext());
        iv.setImageDrawable(d);
        t.setView(iv);
        t.setDuration(Toast.LENGTH_SHORT);
        t.show();
    }

    private void randomize(View root) {
        int[] drawables = {
                R.drawable.ic_angry, R.drawable.ic_baffle, R.drawable.ic_beauty,
                R.drawable.ic_boss, R.drawable.ic_choler, R.drawable.ic_dribble,
                R.drawable.ic_look_down, R.drawable.ic_sure, R.drawable.ic_tire
        };
        ImageView[] targets = {
                root.findViewById(R.id.iv_face1),
                root.findViewById(R.id.iv_face2),
                root.findViewById(R.id.iv_face3)
        };
        Random r = new Random();
        for (ImageView iv : targets) {
            iv.setImageResource(drawables[r.nextInt(drawables.length)]);
        }
    }
}
