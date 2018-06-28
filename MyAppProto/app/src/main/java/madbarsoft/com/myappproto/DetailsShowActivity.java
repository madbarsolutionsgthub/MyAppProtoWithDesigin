package madbarsoft.com.myappproto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailsShowActivity extends AppCompatActivity implements NextAndBackDetailsListenerInteF{
    Button btnNext, btnBack;
    int currentDataPosition = 0;
    int startPoint = 0;
    int categoryId = 0;
    Person person = null;
    List<QuestionAndAns> questionAndAnsList = new ArrayList<>();
    QuestionAndAns questionAndAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_show);


        Intent intent = getIntent();
        startPoint = intent.getIntExtra("itemPosition",-1);
        categoryId = intent.getIntExtra("categoryId",-1);

        try {
            generateQuestionAndAnswerData(categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        questionAndAns = questionAndAnsList.get(startPoint);
/*
        person = new Person();
        person.setName(questionAndAns.getQst());
        person.setAge(questionAndAns.getsNo());
        person.setCity(questionAndAns.getAns());

        Toast.makeText(this, "You Select: "+questionAndAns.getQst().toString(), Toast.LENGTH_SHORT).show();
*/


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("currentDataPosition", startPoint);
        bundle.putSerializable("questionAndAns", questionAndAns);
        detailsFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragmentContainerId, detailsFragment);
   //     fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void nextData(QuestionAndAns questionAndAns, int currentDataPosition) {
     //   Toast.makeText(this, "Msg: You Click Next Button, Pos:"+currentDataPosition+"\n Person Name: "+person.getName(), Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("currentDataPosition", currentDataPosition);
        bundle.putSerializable("questionAndAns", questionAndAns);
        detailsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainerId, detailsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void backData(QuestionAndAns questionAndAns, int currentDataPosition) {
     //   Toast.makeText(this, "Msg: You Click Back Button, Pos:"+currentDataPosition+"\n Person Name: "+person.getName(), Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("currentDataPosition", currentDataPosition);
        bundle.putSerializable("questionAndAns", questionAndAns);
        detailsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainerId, detailsFragment);
        fragmentTransaction.commit();
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

}
