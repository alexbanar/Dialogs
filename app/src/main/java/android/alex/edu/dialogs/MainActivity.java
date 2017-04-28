package android.alex.edu.dialogs;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
                    TimePickerDialog.OnTimeSetListener, DialogInterface.OnClickListener
{
    AlertDialog dialog;
    ArrayList<Integer> selectedItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickTheTime(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, this, 10, 22, true);
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    public void showStandardDialog(View view)
    {
        //title, Message, PositiveButton, negativeButton
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        // DialogInterface.OnClickListener
        builder.setTitle("The app reqiers Internet to proceed")
                .setMessage("Do you want to quit?")
                .setPositiveButton("Quit", this)
                .setNegativeButton("Stay", this);
//        AlertDialogdialog alertDialog = builder.create();
//        alertDialog.show();

        dialog = builder.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch(which)
        {
            case AlertDialog.BUTTON_POSITIVE:
                //Toast.makeText(this, "Quiting...", Toast.LENGTH_SHORT).show();
                System.exit(0);//from java
                //finish(); from android closes only one activity
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                Toast.makeText(this, "Staying...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void ShowListDialog(View view) //title, and setItems
    {
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);

        //final String[] colors = {"Red", "Green", "Blue"};

        final String[] toppings = getResources().getStringArray(R.array.toppings);

        builder.setTitle("Pick a Color: ");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(toppings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String selectedColor = toppings[which];
                Toast.makeText(MainActivity.this, selectedColor, Toast.LENGTH_SHORT).show();
            }
        });

        dialog = builder.show();
    }


    public void ShowMultiChoiceDialog(View view)
    {
        final ArrayList<Integer> mSelectedItems = new ArrayList<>();

        AlertDialog.Builder builder = new  AlertDialog.Builder(this);

        final String[] toppings = getResources().getStringArray(R.array.toppings);

        builder.setTitle("Pick your toppings")
            .setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked)
                {
                    if(isChecked)
                    {
                        mSelectedItems.add( which);
                    }
                    else
                    {
                        if(mSelectedItems.contains(which))
                        {
                            mSelectedItems.remove(Integer.valueOf(which));
                        }
                    }

                    String selectedColor = toppings[which];
                    Toast.makeText(MainActivity.this, selectedColor, Toast.LENGTH_SHORT).show();
                }
             })
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    selectedItemsList = mSelectedItems;
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
    }


}
