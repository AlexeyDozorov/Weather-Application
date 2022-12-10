package com.example.firstfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Pogodnic {
    private static double PRESSURE_KOEF = 0.75;

    @FXML
    private Button ButtonWeather;

    @FXML
    private TextField TextFieldName;

    @FXML
    private Text TextPressure;

    @FXML
    private Text TextTemperature;

    @FXML
    private Text TextWind;
    @FXML
    void initialize() {
        ButtonWeather.setOnAction(actionEvent -> {
            String field = TextFieldName.getText().trim();
            if (!field.isEmpty()){
                String output = getURL("https://api.openweathermap.org/data/2.5/weather?q="+ field +"&appid=2ed8b0a231012409885374e249053b55");
                System.out.println(output);
                if (!output.isEmpty()){
                    JSONObject obj = new JSONObject(output);
                    TextTemperature.setText("Температура:" + String.format("%.1f",Double.parseDouble(String.valueOf(obj.getJSONObject("main").getDouble("temp"))) - 273) + " C");
                    TextPressure.setText("Давление: " + obj.getJSONObject("main").getInt("pressure") * PRESSURE_KOEF);
                    TextWind.setText("Скорость ветра: " + obj.getJSONObject("wind").getDouble("speed") + " м/c");
                }
            }

        });
    }
    private static String getURL(String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Такой город не был найден");
        }
        return content.toString();
    }
}