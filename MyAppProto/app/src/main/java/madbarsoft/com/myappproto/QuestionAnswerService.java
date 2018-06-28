//package madbarsoft.com.myappproto;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class QuestionAnswerService {
//
//    public void getJsonData() throws IOException, JSONException {
//        String json;
//
//        InputStream inputStream = getResources().openRawResource(R.raw.file_networking);
//        int size = inputStream.available();
//        byte[] buffer = new byte[size];
//        inputStream.read(buffer);
//        inputStream.close();
//
//        json = new String(buffer, "UTF-8");
//        JSONArray jsonArray=new JSONArray(json);
//
//        for(int i=0; i<jsonArray.length(); i++){
//            JSONObject obj = jsonArray.getJSONObject(i);
//            // personList.add(new Person(obj.getString("qst"),Integer.parseInt(obj.getString("sNo")), obj.getString("ans")));
//            questionAndAnsList.add(new QuestionAndAns(Integer.parseInt(obj.getString("sNo")), Integer.parseInt(obj.getString("categoryId")), obj.getString("qst").toString(), obj.getString("ans").toString()));
//        }
//
//        //  Toast.makeText(this, "Data: "+dataList.toString(), Toast.LENGTH_SHORT).show();
//    }
//
//
//}
