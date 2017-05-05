package br.com.a3maismais.a321go.activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import br.com.a3maismais.a321go.R;
import br.com.a3maismais.a321go.model.RoutineConfig;

public class ActivityPrincipal extends AppCompatActivity {

    RoutineConfig config;

    TextView numberExecutedCicles;
    TextView numberExecutedExercises;
    TextView executedExerciseTime;
    TextView executedRestTime;

    GridLayout background;

    private Chronometer chronometer;
    boolean isPaused = false;
    long savedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        Button startButton = (Button) findViewById(R.id.start_button);
        Button settingButton = (Button) findViewById(R.id.settings_button);
        Button pauseButton = (Button) findViewById(R.id.pause_button);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        numberExecutedCicles = (TextView) findViewById(R.id.number_executed_cicles);
        numberExecutedExercises = (TextView) findViewById(R.id.number_executed_exercises);
        executedExerciseTime = (TextView) findViewById(R.id.executed_exercise_time);
        executedRestTime = (TextView) findViewById(R.id.executed_rest_time);

        background = (GridLayout) findViewById(R.id.background_chron);

        config = (RoutineConfig) getIntent().getSerializableExtra(RoutineConfig.CONFIG_NAME);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FIXME - Não esta recebendo valores da tela
                //FunctionalRoutine fr = new FunctionalRoutine(ActivityPrincipal.this);
                //fr.start();

                if(isPaused){
                    chronometer.setBase(SystemClock.elapsedRealtime() + savedTime);
                    chronometer.start();
                    savedTime = 0;
                    isPaused = false;
                }
                else{
                    chronometer.setBase(SystemClock.elapsedRealtime()); //executedExerciseTime
                    chronometer.start();
                    savedTime = 0;
                }


            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaused == false){ //entra para false;
                    savedTime = chronometer.getBase() - SystemClock.elapsedRealtime();
                }
                chronometer.stop();
                isPaused = true;
            }
        });
    }

    public int getNumberOfLoops() {
        if (config == null) {
            return 0;
        } else {
            return config.getNumberOfLoops();
        }
    }

    public void setNumberOfLoops(int loopNumber) {
        numberExecutedCicles. setText(String.valueOf(loopNumber));
    }

    public int getNumberOfExercises() {
        if (config == null) {
            return 0;
        } else {
            return config.getNumberOfExercises();
        }
    }

    public Long getExerciseTime() {
        return config.getExerciseTime();
    }

    public Long getPrepareTime() {
        return config.getPrepareTime();
    }

    public Long getRestTime() {
        return config.getRestTime();
    }

    public void setNumberOfExercies(int numberOfExercies) {
        numberExecutedExercises.setText(String.valueOf(numberOfExercies));
    }

    public void setBackgroundColor(int backgroundColor) {
        this.background.setBackgroundColor(backgroundColor);
    }
}
