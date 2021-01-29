package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.databinding.ActivityAddStoryBinding;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.AddStoryViewModel;

public class AddStoryActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private static final String TAG = AddStoryActivity.class.getSimpleName();
    private AddStoryViewModel addStoryViewModel;
    private ActivityAddStoryBinding addStoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addStoryViewModel = new ViewModelProvider(this).get(AddStoryViewModel.class);
        addStoryBinding = DataBindingUtil.setContentView(AddStoryActivity.this, R.layout.activity_add_story);
        addStoryBinding.setLifecycleOwner(this);
        addStoryBinding.setAddStoryViewModel(addStoryViewModel);

        addStoryBinding.dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddStoryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addStoryViewModel.getStory().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                if(!isInputValid(story)) {
                    if(TextUtils.isEmpty(story.getTitle())) {
                        addStoryBinding.titleInput.setError(getString(R.string.title_input_error));
                    }
                    if(story.getDate() == null) {
                        checkStringEmpty(addStoryBinding.dateInput,
                                addStoryBinding.dateInput.getText().toString(),
                                getString(R.string.date_input_error));
                    }
                    if(TextUtils.isEmpty(story.getDescription())) {
                        addStoryBinding.descInput.setError(getString(R.string.desc_input_error));
                    }
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddStoryActivity.this);

                    builder
                            .setMessage(getString(R.string.dialogue_confirmation))
                            .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogue, int id) {
                                    new insertStoryTask().execute(story);
                                }
                            })
                            .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogue, int id) {
                                    dialogue.cancel();
                                }
                            })
                            .show();
                }
            }
        });

        addStoryViewModel.title.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                checkStringEmpty(addStoryBinding.titleInput, s, getString(R.string.title_input_error));
            }
        });

        addStoryViewModel.day.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                checkStringEmpty(addStoryBinding.dateInput, s, getString(R.string.date_input_error));
            }
        });

        addStoryViewModel.description.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                checkStringEmpty(addStoryBinding.descInput, s, getString(R.string.desc_input_error));
            }
        });
    }

    private void checkStringEmpty(TextView textView, String s, String error) {
        if (TextUtils.isEmpty(s)) {
            textView.setError(error);
            textView.requestFocus();
        }
        else {
            textView.setError(null);
        }
    }

    private boolean isInputValid(Story story) {
        if (TextUtils.isEmpty(story.getTitle()) || story.getDate() == null ||
            TextUtils.isEmpty(story.getDescription())) {
            return false;
        }
        return true;
    }

    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        }
    };

    private void updateLabelDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        addStoryBinding.dateInput.setText(sdf.format(myCalendar.getTime()));
    }

    class insertStoryTask extends AsyncTask<Story, AddStoryViewModel, Void> {

        @Override
        protected Void doInBackground(Story... stories) {
            addStoryViewModel.insert(stories[0]);
            Intent intent = new Intent(AddStoryActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return null;
        }
    }
}