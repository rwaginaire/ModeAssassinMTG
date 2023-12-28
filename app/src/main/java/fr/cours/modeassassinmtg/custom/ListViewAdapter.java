package fr.cours.modeassassinmtg.custom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fr.cours.modeassassinmtg.R;
import fr.cours.modeassassinmtg.databinding.PlayersRowBinding;
import fr.cours.modeassassinmtg.ui.WelcomeFragment;

public class ListViewAdapter extends ArrayAdapter<String> {

    private PlayersRowBinding binding;
    ArrayList<String> listItems;
    Context context;

    public ListViewAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.players_row, items);
        this.context = context;
        listItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            binding = PlayersRowBinding.inflate(layoutInflater, parent, false);
            convertView = binding.getRoot();

            TextView playerName = binding.playerName;
            playerName.setText(listItems.get(position));

            ImageView deleteButton = binding.deleteButton;
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WelcomeFragment.removePlayer(position);
                }
            });
        }
        return convertView;
    }
}
