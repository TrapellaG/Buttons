package com.example.buttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonFragment.OnListFragmentInteractionListener
{
    public ImageView resetImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final List<Button>buttons;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetImageView = findViewById(R.id.resetImageView);

        resetImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public void onListFragmentInteraction(Button item)
    {

    }

}
