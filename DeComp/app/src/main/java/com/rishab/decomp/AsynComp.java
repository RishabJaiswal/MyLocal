package com.rishab.decomp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Base64;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

/**
 * Created by Rishab on 22-04-2015.
 */
public class AsynComp extends AsyncTask<Context, Void, String>
{
    private Context context;

    @Override
    protected String doInBackground(Context... c)
    {
        context = c[0];
        for(String s: Utils.filePaths)
        {

            Bitmap bm = BitmapFactory.decodeFile(s);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            /*Node node = new Node();
            Huffman.buildTable(node);
            Huffman.postorder(node, encodedImage);
            File f = new File(s);
            f.delete();
            File f2= new File(Utils.directory.getPath() + "\\comp1.compd");
            try {
                BufferedOutputStream bff = new BufferedOutputStream(new FileOutputStream(f2));
                bff.write((Huffman.compress(s).getBytes();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
            try {
                Huffman.doEverything(encodedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String result)
    {
        Toast.makeText(context, "Images Compressed Successfully", Toast.LENGTH_LONG).show();
    }
}

