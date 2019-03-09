package com.example.weather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;


public class MainActivity extends AppCompatActivity
//        implements MainNavigator
{

    private SearchFragment searchFragment;
    private ResultFragment resultFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchFragment = new SearchFragment();
        resultFragment = new ResultFragment();
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        // добавить фрагмент
//        fragmentTransaction.add(R.id.search, searchFragment);
//        // закрыть транзакцию
//        fragmentTransaction.commit();
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.layout.activity_main) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch(screenKey) {
                case "1":
                    return searchFragment;
                case "2":
                    return resultFragment;
                default:
                    throw new RuntimeException("Unknown screen key!");
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        SampleApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SampleApplication.INSTANCE.getNavigatorHolder().removeNavigator();
    }


//    @Override
//    public void startSecondFragment(EditText city, StringBuilder result) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.remove(searchFragment);
//
//        // добавить фрагмент
//        fragmentTransaction.add(R.id.search, resultFragment);
//        fragmentTransaction.addToBackStack("");
//
//        // закрыть транзакцию
//        fragmentTransaction.commit();
//
//        resultFragment.setCity(city);
//        resultFragment.setResult(result);
//    }

}

