package com.example.hp.opencv_2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.path;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.R.id.list;
import static android.app.Activity.RESULT_OK;
import static android.media.CamcorderProfile.get;
import static com.example.hp.opencv_2.R.id.imgView;
//import org.opencv.videoio;   // VideoCapture


public class MainActivity extends AppCompatActivity {
    private final int SELECT_PHOTO = 1;
    static int ACTION_MODE = 0;
    int counter = 0;
    Uri c;
    Uri s;
    Mat Cover;
    Mat Secret;
    EditText mEdit;
    TextView mID;
    Bitmap stego;
    private static final String TAG = "mainactivty";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*public Uri loadIFingerPrint() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, SELECT_PHOTO);


        Uri imageUri = galleryIntent.getData();
        return imageUri;
    }*/

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, SELECT_PHOTO);
        counter++;
        /*Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "select multiple images"), SELECT_PHOTO);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    /*if (data != null) {
                        ClipData clipData = data.getClipData();
                        if (clipData != null) {
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                ClipData.Item item = clipData.getItemAt(i);
                                 paths.add(item.getUri());

                                //In case you need image's absolute path

                            }
                            Log.d("uri","first"+paths.get(1)+"second"+paths.get(2));
                          //  hide(paths);
                        }
                    }*/
                    //String[] projection = {MediaStore.Images.Media.DATA};
                    //final Uri imageUri = data.getData();
                    // list.add(imageUri);
                    //*  final InputStream imageStream;

                    if (counter == 1) {
                        c = data.getData();
                    } else {
                        s = data.getData();

                    }


                }
               /* ImageView imgView = (ImageView) findViewById(R.id.imgView);
                 //Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));*/

        }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                }else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        Log.v("URIS", "URI Selected Images" +mArrayUri.get(2));
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/
    }

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "failed to start");
        } else {
            Log.d(TAG, "sucess to start");
        }
    }
    ArrayList<Integer> pos=new ArrayList<Integer>();// colum position to hide
   // ArrayList<Integer> cover_vector=new ArrayList<Integer>();//cover image pixel which use to hide pixel
    ArrayList<Integer> secret_vector=new ArrayList<Integer>();// secret image vector contain pixel to hide
   // ArrayList<Integer> raw_pos=new ArrayList<Integer>();//raw postion to hide
    ArrayList<Integer> key_binary=new ArrayList<Integer>();//key in binary
    ArrayList<Integer> secret_binary=new ArrayList<Integer>();//secret pixel in binary

    to_binary binary=new to_binary();
    no_repeated_numbers num_generator =new no_repeated_numbers();

    public void hide(View v) {
        // String uri = "/storage/emulated/0/Download/kk.jpg";

        try {
            //-------read SN from edit text---------
            mEdit=(EditText)findViewById(R.id.SN);
            String SN=mEdit.getText().toString();
           // Log.d("SN","NAtional id is: "+SN);

            //----------read cover from gallery------------
            InputStream imageStream = getContentResolver().openInputStream(c);
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            Cover = new Mat(selectedImage.getHeight(), selectedImage.getWidth(), CvType.CV_8UC4);
            Utils.bitmapToMat(selectedImage, Cover);
            double[] t =Cover.get(0,1);
            int qq=(int)t[0];
           // Log.d("cover first pixel"," "+qq);
            Size size = Cover.size();
            int Cover_Row = Cover.rows();
            Long tot=Cover.total();
            int chanell=Cover.channels();
            int Cover_cols = Cover.cols();
            int Cover_size=Cover_cols*Cover_Row;
            Log.d("Cover info", "Size" + size + " " + "no_rows: " + Cover_Row + " " + "no_cols: " + Cover_cols+" "+chanell);
            //------------- read finger from gallery---------------
            InputStream image = getContentResolver().openInputStream(s);
            Bitmap secret = BitmapFactory.decodeStream(image);
            Secret = new Mat(secret.getHeight(), secret.getWidth(), CvType.CV_8UC1);
            Utils.bitmapToMat(secret, Secret);
            //-------secret Mat info--------------
            int Secret_Row = Secret.rows();
            Long total=Secret.total();
            int Secret_cols = Secret.cols();
            int Secret_size =Secret_cols*Secret_Row;
            int bit_nom=Secret_size*8+(14*6);
                 //   Log.d("secret info", "Size" + Secret_size + " " + "no_rows: " + Secret_Row + " " + "no_cols: " + Secret_cols+" "+total);
            //---------generate position----------
            Random random_nom = new Random();
            int key=random_nom.nextInt(999);
           // Log.d("key info"," key to rows :"+key);
            pos=num_generator.generte(key,Cover_size,bit_nom);
            //---------convert cover mat to ArrayList-----------
            /*for(int i=0;i<raw_pos.size();i++) {
                for (int j = 0; j < col_pos.size(); j++) {
                    double[] temp=Cover.get(raw_pos.get(i),col_pos.get(j));
                    int x=(int)temp[0];
                    // int y =(int)temp[1];
                    // int z= (int)temp[2];
                    cover_vector.add(x);
                    //Log.d("pixel value"," "+x);
                }
            }*/
            //------------convert secret to ArrayList---------
            for(int i=0;i<Secret_Row;i++) {
                for (int j = 0; j < Secret_cols; j++) {
                    double[] temp=Secret.get(i,j);
                    int x1=(int)temp[0];
                    // int y =(int)temp[1];
                    // int z= (int)temp[2];
                    secret_vector.add(x1);
                  //  Log.d("pixel value"," "+x1);
                }
            }
            //-------------convert SN to Arraylist-------------
            ArrayList<Integer> SN_num=new ArrayList<Integer>();
            ArrayList<Integer>SN_bin=new ArrayList<Integer>();
            ArrayList<Integer>width_bin=new ArrayList<Integer>();
            ArrayList<Integer>hight_bin=new ArrayList<Integer>();
            for(int i=0;i<14;i++){
                int temp=(int)(SN.charAt(i));
                SN_num.add(temp);
            }
            //--------------SN to binary-----------
            SN_bin=binary.dec2bin_6(SN_num);
            //------------convert key and data to binary---------------
            key_binary=binary.dec2bin_10(key);
            width_bin=binary.dec2bin_10(Secret_cols);
            hight_bin=binary.dec2bin_10(Secret_Row);
            for(int i=0;i<10;i++){
                key_binary.add(width_bin.get(i));
            }
            for(int i=0;i<10;i++){
                key_binary.add(hight_bin.get(i));
            }
            //----------secret to binary-----------------
            secret_binary=binary.dec2bin_8(secret_vector);
            //----------hide key and data-----------
            Mat c=Cover.clone();

            for(int i=0;i<30;i++){
                double [] temp=c.get(0,i);
                int x=(int)temp[0];
                if(x%2==key_binary.get(i)){
                    temp[0]=(double)x;

                    c.put(0,i,temp);
                }else{
                    if(x%2==1){
                        x=x-1;
                        temp[0]=(double)x;

                        c.put(0,i,temp);
                    }else{
                        x=x+1;
                        temp[0]=(double)x;

                        c.put(0,i,temp);
                    }
                }
            }
            //----------hide fingerprint and national ID-----------
            int row_num,col_num;
            //-------hide national ID---------
            for(int i=0;i<84;i++) {
                int p = pos.get(i);
                if (p % Cover_cols == 0) {
                    row_num = p / Cover_cols - 1;
                    col_num = Cover_cols - 1;
                } else {
                    row_num = p / Cover_cols;
                    col_num = p % Cover_cols - 1;
                }
                double[] temp = c.get(row_num, col_num);
                int x = (int) temp[0];
                if (x % 2 == SN_bin.get(i)) {
                    temp[0] = (double) x;
                    c.put(row_num, col_num, temp);
                } else {
                    if (x % 2 == 1) {
                        x -= 1;
                        temp[0] = (double) x;
                        c.put(row_num, col_num, temp);
                    } else {
                        x += 1;
                        temp[0] = (double) x;
                        c.put(row_num, col_num, temp);
                    }
                }
            }
            //---------hide fingerprint----------
            int count =0;
            for(int i=84;i<pos.size();i++){

              int p=pos.get(i);
                if(p%Cover_cols==0){
                    row_num=p/Cover_cols-1;
                    col_num=Cover_cols-1;
                }else {
                    row_num = p / Cover_cols;
                    col_num = p % Cover_cols-1;
                }
                double [] temp=c.get(row_num,col_num);
                int x=(int)temp[0];
                if(x%2==secret_binary.get(count)){
                    temp[0]=(double)x;
                    c.put(row_num,col_num,temp);
                    count++;
                }else{
                    if(x%2==1){
                        x-=1;
                        temp[0]=(double)x;
                        c.put(row_num,col_num,temp);
                        count++;
                    }
                    else{
                        x+=1;
                        temp[0]=(double)x;
                        c.put(row_num,col_num,temp);
                        count++;
                    }
                }
            }

            //----------display stego image------------
            stego = Bitmap.createBitmap(c.cols(), c.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(c, stego);
            double [] tempee=c.get(0,1);
            int xqq=(int)tempee[0];
            int test=key_binary.get(1);
           // Log.d("stego first pixel","stego "+xqq+" key first bit "+test);
            /*ImageView Img = (ImageView)findViewById(R.id.imgView);
            Img.setImageBitmap(stego);*/
            //Mat A = Highgui.imread(image_addr);
          // Log.d("test value","before "+qq+"after "+xqq+" bit to hide "+test);
            //--------test case----
            for(int i=0;i<30;i++){
                double[] oo=Cover.get(0,i);
                double []pp=c.get(0,i);
                int y=(int)pp[0];
                int x=(int)oo[0];
                int z=key_binary.get(i);
                Log.d("secret key bit","value "+i+": "+z);
                Log.d("Cover first 10 pixel","value "+i+": "+x);
                Log.d("Stego first 10 pixel","value "+i+": "+y);
            }
            counter=0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

public void retrieve(View view){
    //--------read key and data from stego------
    ArrayList<Integer> new_pos=new ArrayList<Integer>();
    String National_ID="";
    String key="";
    String width="";
    String hight="";
    int coun=0;
    Mat stegoo=new Mat();
    Utils.bitmapToMat(stego, stegoo);
    for(int i=0;i<30;i++){

        double []temp=stegoo.get(0,i);
        int x=(int)temp[0];
        if(x%2==0){
            x=0;
        }else{
            x=1;
        }
        if(coun<10){
            key+=x;
            coun++;
        }else if(coun>10&&coun<20){
            width+=x;
            coun++;
        }else{
            hight+=x;
            coun++;
        }
    }

    int new_row,new_col;
    int Stego_col=stegoo.cols();
    int Stego_row=stegoo.rows();
    int Stego_size=Stego_col*Stego_row;
    int sKey=Integer.parseInt(key,2);
    int swidth=Integer.parseInt(width,2);
    int shight=Integer.parseInt(hight,2);
    int bitNom=swidth*shight*8+(6*14);
    new_pos=num_generator.generte(sKey,Stego_size,bitNom);
    Mat FigerPrint=new Mat(shight, swidth, CvType.CV_8UC1);
    //Imgproc.cvtColor(FigerPrint, FigerPrint, Imgproc.COLOR_BGR2GRAY);
    Log.d("data"," width"+swidth+" hight "+shight);
    //--------get SN--------
    ArrayList<Integer>SSN=new ArrayList<Integer>();
    String t="";
    for (int i=0;i<84;i++){
        int p=pos.get(i);
        if(p%Stego_col==0){
            new_row=p/Stego_col-1;
            new_col=Stego_col-1;
        }else {
            new_row = p / Stego_col;
            new_col = p % Stego_col-1;
        }
        double []temp1=stegoo.get(new_row,new_col);
        int temp=(int)temp1[0];
        if(temp%2==0){
            SSN.add(0);
        }else{
            SSN.add(1);
        }
    }
    for (int i=0;i<84;i++) {
        t+=SSN.get(i);
        if(t.length()%6==0){
            int q=Integer.parseInt(t,2);
            National_ID+=(char)q;
            t="";
        }
    }
Log.d("National id","is "+National_ID);
    //-------read fingerprint ------------
    int c =0;
    ArrayList<Integer> Finger_bin=new ArrayList<Integer>();
    ArrayList<Integer>Finger_pixel=new ArrayList<Integer>();
    for(int i=84;i<new_pos.size();i++){

        int p=pos.get(i);
        if(p%Stego_col==0){
            new_row=p/Stego_col-1;
            new_col=Stego_col-1;
        }else {
            new_row = p / Stego_col;
            new_col = p % Stego_col-1;
        }
        double []temp=stegoo.get(new_row,new_col);
        int x=(int)temp[0];
        if(x%2==0){
            Finger_bin.add(0);
        }else{
            Finger_bin.add(1);
        }
    }
    String k="";
    for(int i=0;i<new_pos.size()-84;i++){
        k+=Finger_bin.get(i);
        if(k.length()%8==0){
            int q=Integer.parseInt(k,2);
            Finger_pixel.add(q);
            k="";
        }
    }
    Log.d("finger pixel : ","1 "+Finger_pixel.get(0));
    for(int i=0;i<shight;i++) {
        for (int j = 0; j < swidth; j++) {
            FigerPrint.put(i,j,(double)Finger_pixel.get(c));
            double []z=FigerPrint.get(i,j);
            Log.d("finger print","value: "+z.length);
            c++;
        }
    }
    Bitmap F = Bitmap.createBitmap(FigerPrint.cols(), FigerPrint.rows(), Bitmap.Config.ARGB_8888);
    Utils.matToBitmap(FigerPrint, F);
    ImageView Img = (ImageView)findViewById(R.id.imgView);
    Img.setImageBitmap(F);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
        // Log.d("fff","new:"+" "+size+" "+R+" "+W);

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/

