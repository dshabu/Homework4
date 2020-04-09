package com.dshabu.CurrencyConverter.controller;

import com.dshabu.CurrencyConverter.model.History;
import com.dshabu.CurrencyConverter.repository.HistoryRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class MainController {
    private final HistoryRepo historyRepo;

    public MainController(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @GetMapping("/")
    public ModelAndView doHome() {
        ArrayList<String> currencyList = new ArrayList<>();
        ArrayList<String> currencyDescList = new ArrayList<>();

        JSONObject jsonObject = null;

        try {
            String AVAILABLECURRENCYURL = "https://currency-converter5.p.rapidapi.com/currency/list";
            jsonObject = fetchJSONObject(AVAILABLECURRENCYURL);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (jsonObject != null) {
            JSONObject currencyObj = (JSONObject) jsonObject.get("currencies");

            Iterator<String> keys = currencyObj.keys();
            keys.forEachRemaining(currencyList::add);

            for (String currency : currencyList) {
                currencyDescList.add((String) currencyObj.get(currency));
            }
        }

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("cList", currencyList);
        mv.addObject("cDescList", currencyDescList);
        return mv;
    }

    @GetMapping("/convert")
    public ModelAndView redirect() {
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/convert")
    public ModelAndView convert(@RequestParam("fromAmt") double fromAmt,
                                @RequestParam("fromCurrency") String fromCurrency,
                                @RequestParam("toCurrency") String toCurrency) {
        JSONObject jsonObject = null;

        try {
            String CONVERTCURRENCYURL = "https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from=%s&to=%s&amount=%.2f";
            jsonObject = fetchJSONObject(String.format(CONVERTCURRENCYURL, fromCurrency, toCurrency, fromAmt));
        } catch (IOException e) {
            e.printStackTrace();
        }

        double toAmt = 0.0;
        if (jsonObject != null) {
            toAmt = Double.parseDouble(jsonObject.getJSONObject("rates").getJSONObject(toCurrency).getString("rate_for_amount"));
        }

        History history = new History();
        history.setFromCurrency(fromCurrency);
        history.setFromAmt(fromAmt);
        history.setToCurrency(toCurrency);
        history.setToAmt(toAmt);
        historyRepo.save(history);

        ModelAndView mv = new ModelAndView("result");
        mv.addObject("result", history);
        return mv;
    }

    @GetMapping("/history")
    public ModelAndView getHistory() {
        ModelAndView mv = new ModelAndView("history");
        mv.addObject("historyList", historyRepo.findAllByOrderByInsertedDesc());
        return mv;
    }

    @GetMapping("/history/delete/{id}")
    public ModelAndView deleteHistory(@PathVariable("id") int id) {
        historyRepo.deleteById(id);
        return getHistory();
    }

    private JSONObject fetchJSONObject(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String APIKEY = "";
        conn.setRequestProperty("x-rapidapi-key", APIKEY);
        conn.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }

        bufferedReader.close();

        return new JSONObject(builder.toString());
    }
}
