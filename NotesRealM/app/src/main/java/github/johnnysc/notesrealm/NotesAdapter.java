package github.johnnysc.notesrealm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.realm.RealmResults;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class NotesAdapter extends BaseAdapter {
    RealmResults<Note> notesList = null;

    public NotesAdapter(RealmResults<Note> notesList) {
        this.notesList = notesList;
    }

    @Override
    public int getCount() {
        return notesList.size();
    }

    @Override
    public Object getItem(int position) {
        return notesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.note_list_content,parent,false);
        }

        Note note = notesList.get(position);

        TextView dateTextView = (TextView)convertView.findViewById(R.id.dateTextView);
        dateTextView.setText(note.getDate());

        TextView headTextView = (TextView)convertView.findViewById(R.id.headTextView);
        headTextView.setText(note.getHead());

        return convertView;
    }
}