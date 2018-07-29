package app.unithon2018.team1.team1unithon2018android.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import app.unithon2018.team1.team1unithon2018android.R;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    private final int GALLERY_CODE=1112;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        final EditText up_contents = (EditText) findViewById(R.id.upload_contents);
        final EditText up_hashtag = (EditText) findViewById(R.id.upload_hashtag);
        ImageButton bt_photo = (ImageButton) findViewById(R.id.photoButton);


        bt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = up_contents.getText().toString();
                String b = up_hashtag.getText().toString().replace(" ", "");
                String c = b.replace("#", " #");
                String splitString[] = b.split("#");
                selectGallery();
            }
        });


    }

    private void selectGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("*/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CODE:
                    sendPicture(data.getData());
                    break;

                default:
                    break;
            }

        }
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree) {

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    private void sendPicture(Uri imgUri) {

        String imagePath = getRealPathFromURI(imgUri);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);


        LinearLayout layout = (LinearLayout) findViewById(R.id.selecet_image);
        View view =  View.inflate(this, R.layout.image_item, null);
        ((ImageView) view.findViewById(R.id.image)).setImageBitmap(rotate(bitmap, exifDegree));

        layout.addView(view);
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }

}
