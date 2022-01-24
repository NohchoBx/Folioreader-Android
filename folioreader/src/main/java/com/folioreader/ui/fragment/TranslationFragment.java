package com.folioreader.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.folioreader.Constants;
import com.folioreader.R;
import com.folioreader.model.sqlite.DoshamDbAdapter;
import com.folioreader.ui.adapter.HighlightAdapter;

public class TranslationFragment extends DialogFragment {


    private static final String TAG = "TranslationFragment";

    private String word;
    private TextView dosh, meaning;
    private ProgressBar progressBar;
    //private DoshamDbAdapter adapter;



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
        return inflater.inflate(R.layout.translation, container);
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
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
        dosh = (TextView) view.findViewById(R.id.dosh);
        meaning = (TextView) view.findViewById(R.id.meaning);

        progressBar = (ProgressBar) view.findViewById(R.id.progress);

        //will create a view of our custom dialog layout
        //View alertCustomdialog = getLayoutInflater().inflate(R.layout.translation, item, false);

        //getDialog().setContentView(alertCustomdialog);


        //this line removed app bar from dialog and make it transperent and you see the image is like floating outside dialog box.
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Cursor cursor = DoshamDbAdapter.getDosh(word);

        if (((cursor != null) && (cursor.getCount() > 0))) {
            cursor.moveToFirst();
            dosh.setText(cursor.getString(cursor.getColumnIndex("word")).replaceAll("\\<.*?\\>", ""));
            meaning.setText(cursor.getString(cursor.getColumnIndex("translate")).replaceAll("\\<.*?\\>", ""));
            Log.v(TAG, "-> TranslationFragment -> cursor -> " + cursor.getString(cursor.getColumnIndex("word")));

        }



    }




}
