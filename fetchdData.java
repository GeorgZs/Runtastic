package WorkExperience.myapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchdData extends AsyncTask<Void,Void,Void> {
    String Data = "";
    String dataParsed = "";
    String singleParsed = "";
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://codingcontest.runtastic.com/mobile_and_web_2016/groups.json");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                Data = Data + line;
            }


            JSONArray JA = new JSONArray(Data);
            JSONArray jsonArray = JA.getJSONArray(1);
            for(int j = 0; j < jsonArray.length(); j++) {
                JSONObject JO = (JSONObject) JA.get(j);
                singleParsed = "member_id:" + JO.get("member_id") + "\n" +
                                "member_first_name:" + JO.get("member_first_name") + "\n" +
                                "member_last_name:" + JO.get("member_last_name") + "\n" +
                                "member_distance_covered:" + JO.get("member_distance_covered") + "\n" +
                                "member_active_minutes:" + JO.get("member_active_minutes") + "\n";

                dataParsed = dataParsed + singleParsed + "\n";
                }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
    }
}
