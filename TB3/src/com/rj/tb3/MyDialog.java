package com.rj.tb3;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class MyDialog extends DialogFragment 
{

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Builder builder = new Builder(getActivity()); //Creating AlertDialog
		Builder builder1 = builder.setMessage("Opening Dialog")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() 
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			});
		return builder1.create();
	}
	
}
