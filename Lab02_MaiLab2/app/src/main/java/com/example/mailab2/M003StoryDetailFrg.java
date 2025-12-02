package com.example.mailab2;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class M003StoryDetailFrg extends Fragment {
    private static final String ARG_LIST="arg_list", ARG_CURR="arg_curr";
    private ArrayList<StoryEntity> list; private StoryEntity current; private int currentIndex;

    public static M003StoryDetailFrg newInstance(ArrayList<StoryEntity> list, StoryEntity current){
        Bundle b = new Bundle(); b.putParcelableArrayList(ARG_LIST, list); b.putParcelable(ARG_CURR, current);
        M003StoryDetailFrg f = new M003StoryDetailFrg(); f.setArguments(b); return f;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle s) {
        return inflater.inflate(R.layout.m003_frg_detail, container, false);
    }

    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        list = getArguments().getParcelableArrayList(ARG_LIST);
        current = getArguments().getParcelable(ARG_CURR);
        currentIndex = 0; for (int i=0;i<list.size();i++) if (list.get(i).id==current.id){ currentIndex=i; break; }
        ((TextView) v.findViewById(R.id.tv_story_title)).setText(current.title);
        ((TextView) v.findViewById(R.id.tv_story_content)).setText(current.content);
        v.findViewById(R.id.btn_prev).setOnClickListener(view -> goPrev());
        v.findViewById(R.id.btn_next).setOnClickListener(view -> goNext());

        GestureDetector detector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
            private static final int TH=120, VT=120;
            @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY){
                float dx = e2.getX()-e1.getX();
                if (Math.abs(dx) > Math.abs(e2.getY()-e1.getY()))
                    if (Math.abs(dx)>TH && Math.abs(vX)>VT){ if (dx<0) goNext(); else goPrev(); return true; }
                return false;
            }
        });
        v.setOnTouchListener((vv, ev) -> detector.onTouchEvent(ev));
    }

    private void goPrev(){ if (currentIndex<=0) return; currentIndex--; ((MainActivity) requireActivity()).openStoryAt(currentIndex); }
    private void goNext(){ if (list==null || currentIndex>=list.size()-1) return; currentIndex++; ((MainActivity) requireActivity()).openStoryAt(currentIndex); }
}
