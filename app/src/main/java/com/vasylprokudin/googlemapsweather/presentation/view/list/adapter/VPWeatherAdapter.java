package com.vasylprokudin.googlemapsweather.presentation.view.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.vasylprokudin.googlemapsweather.C;
import com.vasylprokudin.googlemapsweather.R;
import com.vasylprokudin.googlemapsweather.data.model.VPWeatherDayDetail;
import com.vasylprokudin.googlemapsweather.data.model.VPWeatherInfoModel;
import com.vasylprokudin.googlemapsweather.util.VPDateFormatter;
import com.vasylprokudin.googlemapsweather.util.VPTemperatureConverter;
import com.vasylprokudin.googlemapsweather.util.VPWeatherIconProvider;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class VPWeatherAdapter extends RecyclerView.Adapter<VPWeatherAdapter.VPWeatherViewHolder> {

    private ArrayList<VPWeatherDayDetail> list;

    private VPWeatherInfoModel weatherInfoModel;

    private VPTemperatureConverter temperatureConverter;

    private VPWeatherIconProvider weatherIconProvider;

    private VPDateFormatter dateFormatter;

    private Context context;

    public VPWeatherAdapter(
            VPWeatherInfoModel weatherInfoModel,
            VPTemperatureConverter temperatureConverter,
            VPDateFormatter dateFormatter,
            VPWeatherIconProvider weatherIconProvider,
            Context context
    ) {
        this.weatherInfoModel = weatherInfoModel;
        this.temperatureConverter = temperatureConverter;
        this.dateFormatter = dateFormatter;
        this.weatherIconProvider = weatherIconProvider;
        this.context = context;

        list = weatherInfoModel.getList();
    }

    @NonNull
    @Override
    public VPWeatherAdapter.VPWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vp_component_weather_cell, parent, false);
        return new VPWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VPWeatherAdapter.VPWeatherViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return provideItemCount();
    }

    class VPWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cityName)
        TextView city;

        @BindView(R.id.weatherDate)
        TextView date;

        @BindView(R.id.weatherTemperature)
        TextView temperature;

        @BindView(R.id.weatherDescription)
        TextView description;

        @BindView(R.id.civWeather)
        CircleImageView icon;

        VPWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            VPWeatherDayDetail listItem = weatherInfoModel.getList().get(position);
            String iconCode = listItem.getWeather().get(0).getIcon();
            double tempInKelvin = listItem.getMain().getTemp();
            long longDate = listItem.getDt();
            int tempInCelsius = temperatureConverter.convertKelvinToCelsius(tempInKelvin);

            city.setText(weatherInfoModel.getCity().getName());
            description.setText(weatherInfoModel.getList().get(position).getWeather().get(0).getMain());
            date.setText(String.valueOf(dateFormatter.getConvertedDate(longDate)));
            temperature.setText(String.format("%s %s", String.valueOf(tempInCelsius), C.Strings.CELSIUS));
            Glide.with(context).load(weatherIconProvider.provideCurrentIcon(iconCode)).into(icon);
        }
    }

    private int provideItemCount() {
        if (weatherInfoModel.getList() != null) {
            return list.size();
        } else {
            return C.Integer.ZERO;
        }
    }
}
