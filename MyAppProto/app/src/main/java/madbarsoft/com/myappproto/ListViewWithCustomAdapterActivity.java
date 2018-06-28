package madbarsoft.com.myappproto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListViewWithCustomAdapterActivity extends AppCompatActivity {

    private ListView customListView;
    private String[] personArr;
//    ArrayList<Person> personList = new ArrayList<>();
    List<Person> personList = new ArrayList<>();
    List<QuestionAndAns> questionAndAnsList = new ArrayList<>();
    private int position;
    private int categoryId;
    private Category currentCategory;
    List<Category> categoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_custom_adapter);
        TextView categoryNameHolderId = findViewById(R.id.categoryNameHolderId);
        TextView currentStatusId = findViewById(R.id.currentStatusId);
        Intent intent = getIntent();
        position = intent.getIntExtra("itemPosition",-1);
        categoryId = intent.getIntExtra("categoryId",-1);

      //  Toast.makeText(this, "You CategoryId : "+categoryId, Toast.LENGTH_SHORT).show();

        try {
            generateQuestionAndAnswerData(categoryId);
            getCategoryJsonData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(Category category:categoryList){
             if(category.getId()==categoryId){
                 this.currentCategory=category;
             }
        }
        if(currentCategory !=null){
            categoryNameHolderId.setText(currentCategory.getTitle().toString());
        }
        currentStatusId.setText("Number Of Question: "+questionAndAnsList.size());
        // personArr = getResources().getStringArray(R.array.person_arr);
        //personArr = (String[]) dataList.toArray();
       // personList = (ArrayList<Person>) generateList();



     //   Toast.makeText(this, "You select : "+categoryList.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();

        customListView = (ListView)findViewById(R.id.listViewCustomAdapter);
        CustomAdapterForListView customAdapter = new CustomAdapterForListView(this, questionAndAnsList);
        customListView.setAdapter(customAdapter);

        // create onClickListener
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
                Intent inten = new Intent(ListViewWithCustomAdapterActivity.this, DetailsShowActivity.class );
                inten.putExtra("itemPosition",itemPosition);
                inten.putExtra("categoryId",categoryId);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);

            }
        });

    }

    public void generateQuestionAndAnswerData(int categoryId) throws IOException, JSONException {
        String json;
        InputStream inputStream=null;
        if(categoryId==11){
             inputStream = getResources().openRawResource(R.raw.file_networking);
        }else if(categoryId==22){
             inputStream = getResources().openRawResource(R.raw.file_operatingsystem);
        }else if(categoryId==10){
            inputStream = getResources().openRawResource(R.raw.computer_history);
        }else{
            inputStream = getResources().openRawResource(R.raw.question_answer_empty_data);
        }
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        json = new String(buffer, "UTF-8");
        JSONArray jsonArray=new JSONArray(json);

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            // personList.add(new Person(obj.getString("qst"),Integer.parseInt(obj.getString("sNo")), obj.getString("ans")));
            questionAndAnsList.add(new QuestionAndAns(Integer.parseInt(obj.getString("sNo")), Integer.parseInt(obj.getString("categoryId")), obj.getString("qst").toString(), obj.getString("ans").toString()));
        }

        //  Toast.makeText(this, "Data: "+dataList.toString(), Toast.LENGTH_SHORT).show();
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
