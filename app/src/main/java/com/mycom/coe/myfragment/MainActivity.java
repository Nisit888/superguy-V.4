package com.mycom.coe.myfragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JavaFragment";
    public static final int NOTIFICATION_ID = 0;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnBack;
    private Button btnNotification;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startBlankFragment();
//        addRemoveFragment();
//        startJavaFragment();
        startMultipleFragment();

        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com/"));

                //  Define notification action
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,   // context
                                0,      // requestCode (defined PendingIntent id)
                                resultIntent,
                                // if a previous PendingIntent already exists,
                                // then the current one will update it with the latest intent
                                PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat
                                .Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.ic_stat_name)
                                .setContentTitle("My notification")
                                .setContentText("Open Google website!")

                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setVibrate(new long[]{1000})

                                .addAction(0, "Action", resultPendingIntent)
                                .addAction(0, "Close", null)
                                .setAutoCancel(true);

                mBuilder.setContentIntent(resultPendingIntent);


                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                mNotifyMgr.notify(NOTIFICATION_ID, mBuilder.build());
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
                nMgr.cancel(NOTIFICATION_ID);
            }
        });


    }

    private void startMultipleFragment() {
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
                if (fragment != null) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.javaFragment, new OneFragment(), TAG)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.javaFragment, new TwoFragment(), TAG)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.javaFragment, ThreeFragment.newInstance("Three F"), TAG)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void addRemoveFragment() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRemove = (Button) findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JavaFragment javaFragment = new JavaFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.javaFragment,
                        javaFragment,
                        TAG);
                transaction.commit();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentByTag(TAG);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(fragment)
                            .commit();
                }
            }
        });
    }

    private void startJavaFragment() {
        JavaFragment javaFragment = new JavaFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.javaFragment, javaFragment);
        transaction.commit();
    }

    private void startBlankFragment() {
        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        TextView tvf1 = (TextView) blankFragment.getView().findViewById(R.id.tvf1);
        tvf1.setText("Blank fragment");
    }
}
