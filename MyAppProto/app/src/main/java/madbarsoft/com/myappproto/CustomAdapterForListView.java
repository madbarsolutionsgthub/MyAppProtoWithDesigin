package madbarsoft.com.myappproto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterForListView extends BaseAdapter{

    private List<Person> personArr;
    List<QuestionAndAns> questionAndAnsList;
    Context context;
    private LayoutInflater layoutInflater;

    CustomAdapterForListView(Context context, List<QuestionAndAns> questionAndAnsList){
       this.context = context;
       this.questionAndAnsList = questionAndAnsList;
    }


    @Override
    public int getCount() {
        return questionAndAnsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertViewForPersonAdp, ViewGroup viewGroup) {
        if(convertViewForPersonAdp==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertViewForPersonAdp =  layoutInflater.inflate(R.layout._custom_list_view_holder, viewGroup, false);
        }
        TextView personNameView = (TextView)convertViewForPersonAdp.findViewById(R.id.person_name_holder);
        personNameView.setText(questionAndAnsList.get(position).getQst());
        return convertViewForPersonAdp;
    }
}
