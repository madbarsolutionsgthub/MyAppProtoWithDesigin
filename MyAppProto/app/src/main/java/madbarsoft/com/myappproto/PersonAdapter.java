package madbarsoft.com.myappproto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context context;
    private List<Person> personList;

    public PersonAdapter(@NonNull Context context, List<Person> personList){
        super(context, R.layout.person_row, personList);
        this.context=context;
        this.personList=personList;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        convertView= LayoutInflater.from(context).inflate(R.layout.person_row, parent, false);
        TextView nameTv = convertView.findViewById(R.id.rowNameId);
        TextView ageTv = convertView.findViewById(R.id.rowAgeId);

        nameTv.setText(personList.get(position).getName());
        ageTv.setText(String.valueOf(personList.get(position).getAge()));
        return convertView;
    }



}
