package com.folioreader.ui.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.folioreader.Constants;
import com.folioreader.R;
import com.folioreader.model.Dosh;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.sqlite.DoshamDbAdapter;
import com.folioreader.ui.adapter.DoshamAdapter;

import java.util.ArrayList;
import java.util.List;

public class TranslationFragment extends DialogFragment {


    private static final String TAG = "TranslationFragment";

    private String word;
    private TextView dosh, meaning;
    private ProgressBar progressBar;
    //private DoshamDbAdapter adapter;
    private ImageView imageViewClose;
    private TextView no_results;

    RecyclerView recyclerView;

    List<Dosh> doshList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
        word = getArguments().getString(Constants.SELECTED_WORD);
        Log.v(TAG, "-> TranslationFragment -> onCreate -> " + word);
        //adapter = new DoshamDbAdapter(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dosh_list_view, container);
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = getActivity().getResources().getDisplayMetrics().widthPixels;
        int height = getActivity().getResources().getDisplayMetrics().heightPixels;
        getDialog().getWindow().setLayout(width - 200, height / 2);
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v(TAG, "-> TranslationFragment");

        //LinearLayout item = (LinearLayout) view.findViewById(R.id.translation);
        //dosh = (TextView) view.findViewById(R.id.dosh);
        //meaning = (TextView) view.findViewById(R.id.meaning);

        no_results = view.findViewById(R.id.no_results);
        no_results.setVisibility(View.GONE);


        //will create a view of our custom dialog layout
        //View alertCustomdialog = getLayoutInflater().inflate(R.layout.translation, item, false);

        //getDialog().setContentView(alertCustomdialog);


        //this line removed app bar from dialog and make it transperent and you see the image is like floating outside dialog box.
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        imageViewClose = view.findViewById(R.id.btn_close);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Cursor cursor = DoshamDbAdapter.getDosh(word);

        if(cursor.moveToFirst()){
            do{
                Log.v(TAG, "-> TranslationFragment -> cursor -> " + cursor.getString(cursor.getColumnIndex("word")) + "  " +  cursor.getString(cursor.getColumnIndex("translate1")));

                doshList.add(new Dosh(cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("word")) , cursor.getString(cursor.getColumnIndex("translate1")), cursor.getString(cursor.getColumnIndex("translate1"))));
            }while(cursor.moveToNext());
        } else {
            //do something else
        }

        //if (((cursor != null) && (cursor.getCount() > 0))) {

            /*while (cursor.moveToNext()) {
                doshList.add(new Dosh(cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("word")) , cursor.getString(cursor.getColumnIndex("translate1")), cursor.getString(cursor.getColumnIndex("translate1"))));
            }*/
            /*dosh.setText(cursor.getString(cursor.getColumnIndex("word")).replaceAll("\\<.*?\\>", ""));
            meaning.setText(cursor.getString(cursor.getColumnIndex("translate")).replaceAll("\\<.*?\\>", ""));*/

        //}

        if(doshList.isEmpty()){
            no_results.setVisibility(View.VISIBLE);
        }

        DoshamAdapter movieAdapter = new DoshamAdapter(getActivity(), doshList);
        recyclerView = (RecyclerView) view.findViewById(R.id.doshList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setVisibility(View.VISIBLE);




    }




}
