package madbarsoft.com.myappproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getCategoryJsonData();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(this, "Json Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        gridView = (GridView) findViewById(R.id.homePageWithGridViewId);

       // CustomAdapterForGridView customAdapter = new CustomAdapterForGridView(this, personArr, profilePicArr);
        CustomAdapterForGridView customAdapter = new CustomAdapterForGridView(this, categoryList);
        gridView.setAdapter(customAdapter);

        // create onClickListener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
             //   Toast.makeText(MainActivity.this, "You Select: "+personArr[itemPosition]+"\nArray Index No: "+itemPosition, Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(MainActivity.this, ListViewWithCustomAdapterActivity.class );
                inten.putExtra("itemPosition",itemPosition);
                inten.putExtra("categoryId",categoryList.get(itemPosition).getId());
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
            }
        });

    }

    public void getCategoryJsonData() throws IOException, JSONException {
        String json;
        InputStream inputStream = getResources().openRawResource(R.raw.category_json_data);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        json = new String(buffer, "UTF-8");
        JSONArray jsonArray=new JSONArray(json);

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            categoryList.add( new Category(Integer.parseInt(obj.getString("id")), Integer.parseInt(obj.getString("sNo")),obj.getString("title").toString(), obj.getString("description").toString(), Integer.parseInt(obj.getString("numberOfQuestion")) ));
        }

       //   Toast.makeText(this, "Data: "+categoryList.toString(), Toast.LENGTH_SHORT).show();
    }
}
