package org.ustudio.lib.builder;
 
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import com.jaiselrahman.filepicker.model.MediaFile;
import org.ustudio.lib.builder.file.Util;
import android.net.Uri;
import android.widget.TextView;
import org.ustudio.lib.builder.dex.DexBuilder;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.BuildConfig;
import android.widget.EditText;

public class MainActivity extends Activity {
    
    String locallib = "/storage/emulated/0/.sketchware/libs/local_libs";
    Button jarfile;
    TextView logtext;
    EditText foldername;
    Intent intent = new Intent(this, FilePickerActivity.class);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        jarfile.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View btn) {
                    int FILE_REQUEST_CODE = 0;
                    startActivityForResult(intent, FILE_REQUEST_CODE);
                }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    
        ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
        //Do something with files
        String filePath = locallib.concat("/".concat(foldername.getText().toString().concat("/")));
        
        jarfile.setText(Uri.parse(files.get(0).toString()).getLastPathSegment());
        if(!Util.isExistFile(filePath)){
            Util.makeDir(filePath);
        }else{
            DexBuilder.dex(filePath, files.get(0).toString());
            Util.moveFile(files.get(0).toString(), filePath);
        }
    }
    
    public void config(){
        Configurations.Builder cb = new Configurations.Builder();
        cb.setCheckPermission(true);
        cb.setSuffixes("jar");
        cb.setSingleChoiceMode(true);
        cb.build();
    }
    
    private void initialize(){
        jarfile = findViewById(R.id.jarfile);
        logtext = findViewById(R.id.logtext);
        foldername = findViewById(R.id.foldername);
    }
    
} 
