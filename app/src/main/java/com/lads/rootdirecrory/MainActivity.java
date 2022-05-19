package com.lads.rootdirecrory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lads.rootdirecrory.Utils.PrefUtil;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
    ArrayList<String> imageList = new ArrayList<String>();
    PrefUtil prefUtil;
    Cursor cursor;

//        create file
//        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator+"MyAppFolder");
//        filePath.mkdirs();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{(Manifest.permission.READ_EXTERNAL_STORAGE)},
                    121
            );
        }

//        textView = findViewById(R.id.text);
        FloatingActionButton fabImages = findViewById(R.id.fabImages);
        FloatingActionButton fabVideos = findViewById(R.id.fabVideos);
        FloatingActionButton fabAudios = findViewById(R.id.fabAudios);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        getData();
        fabImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                spacecrafts = getData();
                imageList.clear();
                imageList = fetchImages();
//                Search_Dir(Environment.getExternalStorageDirectory());

//                String path = Environment.getExternalStorageDirectory().toString();
//                File directory = new File(String.valueOf(path));
//                File[] files = directory.listFiles();
//                for (int i = 0; i < files.length; i++) {
//                    Log.d("Files", "FileName:" + files[i].getName());
//                    Log.d("Files", "FileName:" + files[i].getAbsolutePath());
//
//                }
                Log.e("TESTTAG", "List" + imageList);
                MyAdapter adapter = new MyAdapter(MainActivity.this, imageList, new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String details) {
                        showToast(details);

//                        dialog = new Dialog(getApplicationContext());
//                        dialog.setContentView(R.layout.dialog_box);
//                        dialog.show();

                        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class).putExtra("path", details);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
            }
        });
        fabVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageList.clear();
                imageList = fetchVideos();
                Log.e("TESTTAG", "List" + imageList);
                MyAdapter adapter = new MyAdapter(MainActivity.this, imageList, new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String details) {
                        showToast(details);
                    }
                });

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

            }
        });
        fabAudios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageList.clear();
                imageList = fetchAudios();

                Log.e("TESTTAG", "List" + imageList);
                MyAdapter adapter = new MyAdapter(MainActivity.this, imageList, new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String details) {
                        showToast(details);

                    }
                });

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            }
        });
    }

    ArrayList<String> fetchImages() {


        String[] strArr = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        cursor = this.managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "",
                null, ""
        );
        Log.e("TESTTAG", "" + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            int dataColumnIndex =
                    cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            imageList.add(cursor.getString(dataColumnIndex));
        }
        return imageList;

    }


    ArrayList<String> fetchVideos() {
//       File directory =
//                File(Environment.getExternalStorageDirectory().toString() + "/Video Recovery/$type")

        String[] strArr = {MediaStore.Video.Media.DATA, MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};
        cursor = this.managedQuery(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, "",
                null, ""
        );

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            int dataColumnIndex =
                    cursor.getColumnIndex(MediaStore.Video.Media.DATA);
            imageList.add(cursor.getString(dataColumnIndex));
        }
        return imageList;
    }

    ArrayList<String> fetchAudios() {

        String[] strArr = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media._ID};
        cursor = this.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, strArr, "",
                null, ""
        );
        Log.e("TESTTAG", "" + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            int dataColumnIndex =
                    cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            imageList.add(cursor.getString(dataColumnIndex));
        }
        return imageList;
    }

//    private ArrayList<Spacecraft> getData() {
//        File download = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile() + "/Camera"));
////        File download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
////        var cols = listOf<String>(MediaStore.Images.Thumbnails.DATA).toTypedArray();
//
//        Spacecraft s;
//
//        if (download.exists()) {
//            File[] files = download.listFiles();
////            if (files != null) {
//            for (int i = 0; i < files.length; i++) {
//                File file = files[i];
//                s = new Spacecraft();
//                s.setName(download.getAbsolutePath());
//                s.setUri(Uri.fromFile(file));
//                spacecrafts.add(s);
//                Log.e("data:", spacecrafts.get(i).toString());
//            }
//
////            } else {
////                Log.e("TAG", "File is Null ");
////            }
//        }
//        return spacecrafts;
//    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void Search_Dir(File dir) {
        String pdfPattern = ".pdf";

        File FileList[] = dir.listFiles();

        if (FileList != null) {
            for (int i = 0; i < FileList.length; i++) {

                if (FileList[i].isDirectory()) {
                    Search_Dir(FileList[i]);
                } else {
//                    if (FileList[i].getName().endsWith(".jpg")){
//                            //here you have that file.
//                }
                }
            }
        }
    }
}
