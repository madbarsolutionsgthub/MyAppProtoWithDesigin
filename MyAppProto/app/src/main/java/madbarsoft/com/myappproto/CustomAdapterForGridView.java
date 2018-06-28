package madbarsoft.com.myappproto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterForGridView extends BaseAdapter{

    List<Category> categoryList = new ArrayList<>();
    private String[] personArr;
    int[] profilePicArr;
    Context context;
    private LayoutInflater layoutInflater;

    CustomAdapterForGridView(Context context, List<Category> categoryList){
       this.context = context;
       this.categoryList = categoryList;
    }


    @Override
    public int getCount() {
        return categoryList.size();
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
            convertViewForPersonAdp =  layoutInflater.inflate(R.layout._custom_grid_view_holder, viewGroup, false);
        }
       // ImageView imageView = (ImageView) convertViewForPersonAdp.findViewById(R.id.imageViewPlaceId);
        TextView title = (TextView)convertViewForPersonAdp.findViewById(R.id.titlePlaceId);
      //  TextView description = (TextView)convertViewForPersonAdp.findViewById(R.id.descriptionPlaceId);

        title.setText(categoryList.get(position).getTitle().toString());
       // description.setText("Number O "+categoryList.get(position).getNumberOfQuestion());
        return convertViewForPersonAdp;
    }
}
