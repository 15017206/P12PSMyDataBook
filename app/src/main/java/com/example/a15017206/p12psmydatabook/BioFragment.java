package com.example.a15017206.p12psmydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {

    Button btnEdit;
    ListView listView;
    EditText etBio;

    ArrayList<String> mobileArray = new ArrayList<String>();

    @Override
    public void onResume() {
        super.onResume();
retrieve();

    }

    public BioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        mobileArray.add("Some eg. Bio 1");

        final View view = inflater.inflate(R.layout.fragment_bio, container, false);
        btnEdit = (Button) view.findViewById(R.id.btnEdit);

        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mobileArray);
        listView = (ListView) view.findViewById(R.id.lvBio);

        listView.setAdapter(adapter);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout passphrase = (LinearLayout) inflater.inflate(R.layout.dialoglayout1, null);

                etBio = (EditText) passphrase.findViewById(R.id.etBio);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Bio")
                        .setView(passphrase)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String bio = etBio.getText().toString();
                                mobileArray.add(bio);
                                adapter.notifyDataSetChanged();
                                addToDB();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bio, container, false);
        return view;
    }

    public void addToDB(){
        String data = etBio.getText().toString();
        DBHelper dbh = new DBHelper(getContext());
        long row_affected = dbh.insertNote(data);
        dbh.close();

        if (row_affected != -1) {
            Toast.makeText(getContext(), "Insert successful", Toast.LENGTH_SHORT).show();
        }
    }

    public void retrieve(){
        DBHelper dbh = new DBHelper(getContext());
        mobileArray.clear();
        mobileArray.addAll(dbh.getAllNotes());
        dbh.close();

        String txt = "";
        for (int i = 0; i < mobileArray.size(); i++) {
            String tmp = mobileArray.get(i);
            txt += tmp + "\n";
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mobileArray);
        adapter.notifyDataSetChanged();
    }
}
