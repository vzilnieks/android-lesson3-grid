package com.example.vadim.lesson3_grid;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    Resources res = getResources();
//    String[] items = res.getStringArray(R.array.car_types);
////    Integer[] images = res.getIntArray(R.array.car_images);
//    final Car[] carsAdvanced = {};
//    for (int i = 0; i < 1; i++) {
//      carsAdvanced[i] = new Car("van", R.drawable.van);
//    }

    final Car[] carsAdvanced = {
            new Car("truck", R.drawable.truck),
            new Car("sedan", R.drawable.sedan),
            new Car("van", R.drawable.van),
            new Car("coupe", R.drawable.coupe),
            new Car("wagon", R.drawable.wagon),
            new Car("convertible", R.drawable.convertible),
            new Car("crossover", R.drawable.crossover),
    };

    setContentView(R.layout.activity_main);

    ArrayAdapter<Car> carAdapter = new ArrayAdapter<Car>(this,0,carsAdvanced) {
      @NonNull
      @Override
      public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car currentCar = carsAdvanced[position];

        if (convertView == null) {
          convertView = getLayoutInflater().inflate(R.layout.item,null,false);
        }

        if (convertView.getTag() == null) {
          ViewHolder viewHolder = new ViewHolder();
          viewHolder.carImage = (ImageView) convertView.findViewById(R.id.car_image);
          viewHolder.carType = (TextView) convertView.findViewById(R.id.car_name);
          convertView.setTag(viewHolder);
        }

        ImageView carImage =
                ((ViewHolder) convertView.getTag()).carImage;
        TextView carType =
                ((ViewHolder) convertView.getTag()).carType;

        carType.setText(currentCar.type);
        carImage.setImageResource(currentCar.imageId);

        return convertView;
      }
    };

    GridView carGrid = new GridView(this);
    setContentView(carGrid);
    carGrid.setNumColumns(2);
    carGrid.setVerticalSpacing(20);
    carGrid.setHorizontalSpacing(20);
    carGrid.setAdapter(carAdapter);
    carGrid.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
    Resources res = getResources();
    String[] items = res.getStringArray(R.array.car_types);
    Toast toast = Toast.makeText(getApplicationContext(),items[position],Toast.LENGTH_SHORT);
    toast.show();
  }

}


