package com.vodafone.demo.service;

import com.vodafone.demo.exceptions.NotFoundException;
import com.vodafone.demo.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("ctn")
public class CtnService implements BaseService {
    @Override
    public CtnDetails getDetails(String identifier) {
        JSONParser jsonParser = new JSONParser();
        List<CtnDetails> ctnDetailsList = new ArrayList<>();
        try (FileReader reader = new FileReader("src/main/resources/ctns.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject ctnNode = (JSONObject) obj;
            JSONArray ctns = (JSONArray) ctnNode.get("CTNS");
            ctnDetailsList = parseCtns(ctns);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ctnDetailsList.stream().filter(x-> x.getIdentifier().equals(identifier)).findFirst().orElseThrow(NotFoundException::new);
    }

    private List<Product> parseProducts(List<JSONObject> jsonList) {
        List<Product> productList = new ArrayList<>();
        for (int i =0 ;i < jsonList.size();i++){
            JSONObject obj = jsonList.get(i);
            productList.add(Product.builder().productName((String) obj.get("name")).status((String) obj.get("status")).build());
        }
        return productList;
    }

    private List<CtnDetails> parseCtns(JSONArray ctnsJson){
        List<CtnDetails> ctnDetails = new ArrayList<>();
        for (int i = 0 ; i< ctnsJson.size() ; i++){
            JSONObject ctn =(JSONObject) ctnsJson.get(i);
            CtnDetails details = new CtnDetails();
            details.setIdentifier((String) ctn.get("identifier"));
            details.setAddress((String) ctn.get("address"));
            details.setCustomerName((String) ctn.get("name"));
            List<JSONObject> tempJsonList = new ArrayList<>();
            List<Product> productList = new ArrayList<>();
            JSONArray productJsonList = (JSONArray) ctn.get("products");
            productJsonList.forEach(x-> tempJsonList.add((JSONObject) x));
            details.setInstalledProducts(parseProducts(tempJsonList));
            details.setSubscriptionType((String) ctn.get("subscription"));
            ctnDetails.add(details);
        }
        return ctnDetails;
    }
}
