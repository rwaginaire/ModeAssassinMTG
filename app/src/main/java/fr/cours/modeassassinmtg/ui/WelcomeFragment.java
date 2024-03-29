package fr.cours.modeassassinmtg.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import fr.cours.modeassassinmtg.R;
import fr.cours.modeassassinmtg.custom.ListViewAdapter;
import fr.cours.modeassassinmtg.databinding.FragmentWelcomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    private ListView playerLV;
    private Button addBtn;
    private Button tirageBtn;
    private EditText itemEdt;
    private static ArrayList<String> playerList;
    private static ListViewAdapter adapter;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // on below line we are initializing our variables.
        playerLV = binding.idLVPlayers;
        addBtn = binding.idBtnAdd;
        itemEdt = binding.idEdtItemName;
        tirageBtn = binding.idBtnTirage;
        playerList = new ArrayList<>();

        // on the below line we are initializing the adapter for our list view.
        adapter = new ListViewAdapter(getContext(), playerList);

        // on below line we are setting adapter for our list view.
        playerLV.setAdapter(adapter);

        // on below line we are adding click listener for our button.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String item = itemEdt.getText().toString();

                // on below line we are checking if item is not empty
                if (!item.isEmpty()) {

                    // on below line we are adding item to our list.
                    playerList.add(item);

                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    adapter.notifyDataSetChanged();
                }

                // clear edit text content
                itemEdt.getText().clear();
            }
        });

        tirageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playersCount = adapter.getCount();
                if (playersCount < 3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Nombre de joueurs insuffisant !");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    // TODO lancer tirage
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setReorderingAllowed(true);
                    DrawFragment drawFragment = DrawFragment.newInstance();
                    fragmentTransaction.replace(R.id.fragment_container_view, drawFragment, null);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    public static void removePlayer(int index) {
        playerList.remove(index);
        adapter.notifyDataSetChanged();
    }
}