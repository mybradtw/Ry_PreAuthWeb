package tw.brad.sakuratestall;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;


public class MyIntentService extends IntentService {
    private String name;

    public static void acceptAJob(Context context, String jobname){
        Intent intent = new Intent(context, MyIntentService.class);
        intent.putExtra("jobname", jobname);
        context.startService(intent);
    }

    public MyIntentService() {
        super("default");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //final String action = intent.getAction();
            String jobname = intent.getStringExtra("jobname");
            Log.v("brad", jobname);
            doAJob(jobname);
        }
    }

    //  background service
    private void doAJob(String jobname){
        Log.v("brad", jobname);
        try {
            URL url = new URL("https://www.bradchao.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            conn.getInputStream();
            Log.v("brad", "OK");
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }


}
